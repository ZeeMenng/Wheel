//package com.zee.utl;
//
//import java.io.Serializable;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.DataType;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
///**
// * @author lxy
// * @createDate 2018年4月26日 下午1:36:35
// * @updateDate 2018年4月26日 下午1:36:35
// * @description redis 工具类
// */
//@SuppressWarnings("unchecked")
//@Component
//public class RedisUtil {
//
//	@SuppressWarnings("rawtypes")
//	@Autowired
//	private RedisTemplate redisTemplate;
//
//	/**
//	 * 设置登陆用户sessionData,并返回token
//	 * @param map
//	 * @return
//	 */
//	public String setSessionData(Map<String, Object> map) {
//		String userName = map.get("userName").toString();
//		String token = map.get("token").toString();
//		this.hmset(SymbolicConstant.REQUEST_CURRENT_USER + ":userName:" + userName + ":token:" + token, map);
//		return token;
//	}
//
//	/**
//	 * 获取登陆用户sessionData
//	 * @param map
//	 * @return
//	 */
//	public Map<String, Object> getSessionData(String userName, String token) {
//		Map<String, Object> sessionMap = this.hmget(SymbolicConstant.REQUEST_CURRENT_USER + ":userName:" + userName + ":token:" + token);
//		return sessionMap;
//	}
//
//	/**
//	 * 删除单个key
//	 * 
//	 * @param key
//	 */
//	public void remove(final String key) {
//		if (exists(key)) {
//			redisTemplate.delete(key);
//		}
//	}
//
//	/**
//	 * 批量删除keys
//	 * 
//	 * @param keys
//	 */
//	public void remove(final String... keys) {
//		for (String key : keys) {
//			remove(key);
//		}
//	}
//
//	/**
//	 * 按模板匹配删除keys
//	 * 
//	 * @param pattern
//	 */
//	public void removePattern(final String pattern) {
//		Set<Serializable> keys = redisTemplate.keys(pattern);
//		if (keys.size() > 0)
//			redisTemplate.delete(keys);
//	}
//
//	/**
//	 * 判断缓存中是否有对应的key
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public boolean exists(final String key) {
//		return redisTemplate.hasKey(key);
//	}
//
//	/**
//	 * 读取缓存
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public String get(final String key) {
//		Object result = null;
//		redisTemplate.setValueSerializer(new StringRedisSerializer());
//		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//		result = operations.get(key);
//		if (result == null) {
//			return null;
//		}
//		return result.toString();
//	}
//
//	/**
//	 * 写入缓存
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean set(final String key, Object value) {
//		boolean result = false;
//		try {
//			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//			operations.set(key, value);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 写入缓存，带失效时间，单位：秒
//	 * 
//	 * @param key
//	 * @param value
//	 * @param expireTime
//	 *            秒
//	 * @return
//	 */
//	public boolean set(final String key, Object value, Long expireTime) {
//		boolean result = false;
//		try {
//			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//			operations.set(key, value);
//			this.expire(key, expireTime);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 写入对象
//	 * 
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public boolean hmset(String key, Map<String, Object> value) {
//		boolean result = false;
//		try {
//			redisTemplate.opsForHash().putAll(key, value);
//			result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 获取对象
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public Map<String, Object> hmget(String key) {
//		Map<String, Object> result = null;
//		try {
//			result = redisTemplate.opsForHash().entries(key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
//
//	/**
//	 * 指定缓存失效时间
//	 * 
//	 * @param key
//	 *            键
//	 * @param time
//	 *            时间(秒)
//	 * @return
//	 */
//	public boolean expire(String key, long time) {
//		try {
//			if (time > 0) {
//				redisTemplate.expire(key, time, TimeUnit.SECONDS);
//			}
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 根据key 获取过期时间
//	 * 
//	 * @param key
//	 *            键 不能为null
//	 * @return 时间(秒) 返回0代表为永久有效
//	 */
//	public long getExpire(String key) {
//		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
//	}
//
//	/**
//	 * 获取数据结构类型
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public DataType getType(String key) {
//		return redisTemplate.type(key);
//	}
//
//	/**
//	 * 获取所有的key
//	 * 
//	 * @return
//	 */
//	public Set<String> getAllKeys() {
//		return redisTemplate.keys("*");
//	}
//	
//	/**
//	 * 按模板匹配获取key
//	 * 
//	 * @param pattern
//	 */
//	public Set<String> getPatternKeys(final String pattern) {
//		return redisTemplate.keys(pattern);
//	}
//
//	/**
//	 * 获取所有字符串类型的key及value
//	 * 
//	 * @return
//	 */
//	public Map<String, Object> getAllString() {
//		Set<String> stringSet = getAllKeys();
//		Map<String, Object> map = new HashMap<String, Object>();
//		Iterator<String> iterator = stringSet.iterator();
//		while (iterator.hasNext()) {
//			String k = iterator.next();
//			if (getType(k) == DataType.STRING) {
//				map.put(k, get(k));
//			}
//		}
//		return map;
//	}
//
//	/**
//	 * 获取所有hash类型的key
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public Map<String, Object> getMap(String key) {
//		return redisTemplate.boundHashOps(key).entries();
//	}
//
//	/**
//	 * 获取所有hash类型的key及value
//	 * 
//	 * @return
//	 */
//	public Map<String, Map<String, Object>> getAllMap() {
//		Set<String> stringSet = getAllKeys();
//		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
//		Iterator<String> iterator = stringSet.iterator();
//		while (iterator.hasNext()) {
//			String k = iterator.next();
//			if (getType(k) == DataType.HASH) {
//				map.put(k, getMap(k));
//			}
//		}
//		return map;
//	}
//
//	/**
//	 * 获取list缓存的内容
//	 * 
//	 * @param key
//	 *            键
//	 * @param start
//	 *            开始
//	 * @param end
//	 *            结束 0 到 -1代表所有值
//	 * @return
//	 */
//	public List<Object> lGet(String key, long start, long end) {
//		try {
//			return redisTemplate.opsForList().range(key, start, end);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * 获取list缓存的长度
//	 * 
//	 * @param key
//	 *            键
//	 * @return
//	 */
//	public long lGetListSize(String key) {
//		try {
//			return redisTemplate.opsForList().size(key);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//	/**
//	 * 通过索引 获取list中的值
//	 * 
//	 * @param key
//	 *            键
//	 * @param index
//	 *            索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
//	 * @return
//	 */
//	public Object lGetIndex(String key, long index) {
//		try {
//			return redisTemplate.opsForList().index(key, index);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	/**
//	 * 将list放入缓存
//	 * 
//	 * @param key
//	 *            键
//	 * @param value
//	 *            值
//	 * @param time
//	 *            时间(秒)
//	 * @return
//	 */
//	public boolean lSet(String key, Object value) {
//		try {
//			redisTemplate.opsForList().rightPush(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 将list放入缓存
//	 * 
//	 * @param key
//	 *            键
//	 * @param value
//	 *            值
//	 * @param time
//	 *            时间(秒)
//	 * @return
//	 */
//	public boolean lSet(String key, Object value, long time) {
//		try {
//			redisTemplate.opsForList().rightPush(key, value);
//			if (time > 0)
//				expire(key, time);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 将list放入缓存
//	 * 
//	 * @param key
//	 *            键
//	 * @param value
//	 *            值
//	 * @param time
//	 *            时间(秒)
//	 * @return
//	 */
//	public boolean lSet(String key, List<Object> value) {
//		try {
//			redisTemplate.opsForList().rightPushAll(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 将list放入缓存
//	 * 
//	 * @param key
//	 *            键
//	 * @param value
//	 *            值
//	 * @param time
//	 *            时间(秒)
//	 * @return
//	 */
//	public boolean lSet(String key, List<Object> value, long time) {
//		try {
//			redisTemplate.opsForList().rightPushAll(key, value);
//			if (time > 0)
//				expire(key, time);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 根据索引修改list中的某条数据
//	 * 
//	 * @param key
//	 *            键
//	 * @param index
//	 *            索引
//	 * @param value
//	 *            值
//	 * @return
//	 */
//	public boolean lUpdateIndex(String key, long index, Object value) {
//		try {
//			redisTemplate.opsForList().set(key, index, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	/**
//	 * 移除N个值为value
//	 * 
//	 * @param key
//	 *            键
//	 * @param count
//	 *            移除多少个
//	 * @param value
//	 *            值
//	 * @return 移除的个数
//	 */
//	public long lRemove(String key, long count, Object value) {
//		try {
//			Long remove = redisTemplate.opsForList().remove(key, count, value);
//			return remove;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return 0;
//		}
//	}
//
//}
