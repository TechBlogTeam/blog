package com.blog.controller.api;

import com.blog.dto.article.ArticleDto;
import com.blog.dto.article.ArticleTypeDto;
import com.blog.entity.article.Article;
import com.blog.entity.article.ArticleType;
import com.blog.service.ArticleService;
import com.blog.common.utils.FileUploadUtil;
import com.blog.common.utils.PagerUtil;
import com.blog.vo.article.ArticleVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import com.blog.common.utils.Result;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    /**
     * 新增文章
     * @param request 查询参数
     * @return 新增文章id
     */
    @PostMapping("/article/addArticle")
    public Result addArticle(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);

        FileUploadUtil fileUtil = new FileUploadUtil(params);

        fileUtil.setSavePath("/uploads/article/"+fmtDate("yyyyMM/dd/"));
        fileUtil.setFileName("file");
        fileUtil.setPrefix("thumbnail");
        List<String> urlList = fileUtil.save();
        String url = urlList.get(0);
        //获取最后一条数据并生成uuid(uuid生成规则：6位随机数字符串拼接当前最后一条数据id，再转化位十六进制)
        Article newestArticle = articleService.getNewestArticle();
        String newestId = "";
        if(newestArticle != null){
            newestId = newestArticle.getArticleId().toString();
        }
        String uid =Integer.toHexString(Integer.parseInt((int)((Math.random()*9+1)*100000)+ "" + (newestId)));

        //设置插入数据库中的数据
        ArticleDto articleDto = new ArticleDto();
        articleDto.setArticleTitle(params.getParameter("articleTitle"));
        articleDto.setArticleKeywords(params.getParameter("articleKeywords"));
        articleDto.setArticleExcerpt(params.getParameter("articleExcerpt"));
        articleDto.setArticleContent(params.getParameter("articleContent"));
        articleDto.setArticleThumbnail(url);
        articleDto.setArticleTypeId(Integer.parseInt(params.getParameter("articleTypeId")));
        articleDto.setArticleUid(uid);

        int articleId = articleService.addArticle(articleDto);

        return Result.success("新增成功");

    }

    /**
     * 获取文章列表
     * @param params 分页+其他参数
     * @return 文章列表
     */
    @GetMapping("/article/getArticleByPage")
    public Result<Map<String, Object>> getArticleByPage(@RequestParam Map<String, Object> params){
        PagerUtil pageUtil = new PagerUtil(params);
        List<Article> articleList = articleService.getArticleByPage(pageUtil);
        int articleTotal = articleService.getArticleTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("data",articleList);
        map.put("total",articleTotal);
        return Result.success(map);
    }

    /**
     * 文章内容图片上传
     * @param request 文件流
     * @return 文件的url映射
     */
    @RequestMapping(value = "/article/uploadArticlePic", method = RequestMethod.POST)
    public Result<String> uploadArticlePic(HttpServletRequest request) {
        FileUploadUtil fileUtil = new FileUploadUtil((MultipartHttpServletRequest) request);

        fileUtil.setSavePath("/uploads/article/"+fmtDate("yyyyMM/dd/"));
        fileUtil.setPrefix("content");
        fileUtil.setFileName("file");
        List<String> urlList = fileUtil.save();
        return Result.success(urlList.get(0));
    }

    /**
     * 根据id获取文章内容
     * @param articleUid 文章uid
     * @return 文件的url映射
     */
    @GetMapping("/article/getArticleById")
    public Result getArticleById(@RequestParam String articleUid){
        Article article = articleService.getArticleById(articleUid);
        if(article !=null){
            ArticleVo articleVo = new ArticleVo();
            articleVo.setArticleUid(article.getArticleUid());
            articleVo.setArticleAuthor(article.getArticleAuthor());
            articleVo.setArticleContent(article.getArticleContent());
            articleVo.setArticleExcerpt(article.getArticleExcerpt());
            articleVo.setArticleKeywords(article.getArticleKeywords());
            articleVo.setArticleThumbnail(article.getArticleThumbnail());
            articleVo.setArticleTitle(article.getArticleTitle());
            articleVo.setArticleTypeId(article.getArticleTypeId());

            return Result.success(articleVo);
        }else {
            return Result.fail("查询失败","10");
        }
    }

    /**
     * 文章更新
     * @param request 文章
     * @return map
     */
    @RequestMapping(value = "/article/uploadArticle", method = RequestMethod.POST)
    public Result uploadArticle(HttpServletRequest request){
        ArticleDto articleDto = new ArticleDto();

        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        // 上传文件处理
        List<MultipartFile> files = params.getFiles("file");
        if(files.size() != 0){
            FileUploadUtil fileUtil = new FileUploadUtil(params);
            fileUtil.setSavePath("/uploads/article/"+fmtDate("yyyyMM/dd/"));
            fileUtil.setFileName("file");
            fileUtil.setPrefix("thumbnail");
            List<String> urlList = fileUtil.save();
            String url = urlList.get(0);
            articleDto.setArticleThumbnail(url);
        }
        //设置插入数据库中的数据
        articleDto.setArticleTitle(params.getParameter("articleTitle"));
        articleDto.setArticleKeywords(params.getParameter("articleKeywords"));
        articleDto.setArticleExcerpt(params.getParameter("articleExcerpt"));
        articleDto.setArticleContent(params.getParameter("articleContent"));
        articleDto.setArticleTypeId(Integer.parseInt(params.getParameter("articleTypeId")));
        articleDto.setArticleUid(params.getParameter("articleUid"));
        try {
            articleService.updateArticle(articleDto);
            return Result.success("更新成功");
        }catch (Exception e){
            return Result.fail("更新失败","10");
        }
    }


    /**
     * 获取分类列表
     * @return 文章列表
     */
    @GetMapping("/articleType/getArticleTypeList")
    public Result getArticleTypeList(){
        List<ArticleType> articleTypeList = articleService.getArticleTypeList();
        return Result.success(articleTypeList);
    }

    /**
     * 新增分类
     * params 新增分类参数
     * @return 分类id
     */
    @GetMapping("/articleType/addArticleType")
    public Result addArticleType(ArticleTypeDto params){
        int articleTypeId = articleService.addArticleType(params);
        return Result.success(articleTypeId);
    }

    /**
     * 删除分类
     * params 分类id
     * @return 分类id
     */
    @GetMapping("/articleType/deleteArticleType")
    public Result deleteArticleType(Integer query){
        int articleTypeId = articleService.deleteArticleType(query) ;
        return Result.success(articleTypeId);
    }


    /**
     * 格式化时间
     * @param rex 日期格式
     * @return 格式化后的日期
     */
    private String fmtDate(String rex){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(rex);
        return sdf.format(date);
    }

}
