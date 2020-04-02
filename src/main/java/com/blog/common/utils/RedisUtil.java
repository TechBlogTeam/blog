package com.blog.common.utils;

import com.alibaba.fastjson.JSON;
import com.blog.common.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisUtil {
    private static JedisPool jedisPool;


    @Autowired
    public void setRedis(JedisPool jedisPool) {
        RedisUtil.jedisPool = jedisPool;
    }

    /**
     * 获取Jedis实例
     * @return redis.clients.jedis.Jedis
     */
    public static synchronized Jedis getRedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new CustomException("获取Jedis资源异常:" + e.getMessage());
        }
    }

    /**
     * 设置redis键值-object-expiretime
     * @param key key
     * @param value val
     * @param expireTime 过期时间
     * @return java.lang.String
     */
    public static <T> Boolean set(String key, Object value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
            jedis.expire(key, expireTime);
            return true;
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setObject方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        } finally {
            returnToPool(jedis);
        }
    }
    /**
     * 设置redis键值-object
     * @param key
     * @param value
     * @return java.lang.String
     */
    public static <T> Boolean set(String key, Object value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
            return true;
        } catch (Exception e) {
            throw new CustomException("设置Redis键值setObject方法异常:key=" + key + " value=" + value + " cause=" + e.getMessage());
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 获取
     * @param key redis储存的key
     * @return java.lang.Long
     */
    public static <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            return stringToBean(str,clazz);
        } catch (Exception e) {
            throw new CustomException("获取Redis键值getObject方法异常:key=" + key + " cause=" + e.getMessage());
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除
     * @param key redis储存的key
     * @return java.lang.Long
     */
    public static Long delete(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            throw new CustomException("删除Redis的键delKey方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * key是否存在
     * @param key redis储存的key
     * @return java.lang.Boolean
     */
    public static Boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            throw new CustomException("查询Redis的键是否存在exists方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }

    /**
     * 获取过期剩余时间
     * @param key
     * @return java.lang.String
     */
    public static Long ttl(String key) {
        Long result = -2L;
        try (Jedis jedis = jedisPool.getResource()) {
            result = jedis.ttl(key);
            return result;
        } catch (Exception e) {
            throw new CustomException("获取Redis键过期剩余时间ttl方法异常:key=" + key + " cause=" + e.getMessage());
        }
    }


    private static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return String.valueOf(value);
        } else if (clazz == long.class || clazz == Long.class) {
            return String.valueOf(value);
        } else if (clazz == String.class) {
            return (String) value;
        } else {
            return JSON.toJSONString(value);
        }

    }

    private static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private static void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();//不是关闭，只是返回连接池
        }
    }
}
