package com.zee.utl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * @author lxy
 * @createDate 2018年7月13日 上午10:40:24
 * @updateDate 2018年7月13日 上午10:40:24
 * @description MongoUtil
 * 1、mongo 的 collection 以bean命名
 */
@Component
public class MongoUtil {

	private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

	@Autowired
	private MongoDatabase db;
	
	public boolean insert(Object obj) throws Exception {
		
		Class<? extends Object> clazz = obj.getClass();
		String table = clazz.getSimpleName();
		Document doc = new Document();
		Field[] declaredFields = clazz.getSuperclass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			if (!"serialVersionUID".equals(field.getName())) {
				doc.append(field.getName(), field.get(obj));
			}
		}
		MongoCollection<Document> collection = db.getCollection(table);
		System.out.println(doc.toJson());
		collection.insertOne(doc);
		long count = collection.count(doc);
		if (count >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public int insertMany(ArrayList<?> list) throws Exception{
		if(list == null || list.size() == 0)
			throw new Exception();

		Class<?> clazz = list.get(0).getClass();
		String table = clazz.getSimpleName();
		ArrayList<Document> docList = new ArrayList<Document>();
		for (Object obj : list) {
			Document doc = new Document();
			Field[] declaredFields = clazz.getSuperclass().getDeclaredFields();
			for (Field field : declaredFields) {
				field.setAccessible(true);
				if (!"serialVersionUID".equals(field.getName())) {
					doc.append(field.getName(), field.get(obj));
				}
			}
			docList.add(doc);
		}
		MongoCollection<Document> collection = db.getCollection(table);
		long preCount = collection.count();
		collection.insertMany(docList);
		long nowCount = collection.count();
		if ((nowCount - preCount) == docList.size()) {
			return docList.size();
		} else {
			throw new Exception();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForAggregate(String table, List<Document> docList) {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = null;
		try {
			MongoCollection<Document> collection = db.getCollection(table);
			System.out.println(docList.toString());
			AggregateIterable<Document> resultset = collection.aggregate(docList);
			cursor = resultset.iterator();
			while (cursor.hasNext()) {
				Document user = cursor.next();
				String jsonString = user.toJson();
				Map<String, Object> jsonStrToMap = (Map<String, Object>) JSON.parse(jsonString);
				list.add(jsonStrToMap);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			cursor.close();
		}
		
		return list;
	}

	public Map<String, Object> queryByID(String table, Object id)
			throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		BasicDBObject query = new BasicDBObject("_id", id);
		// DBObject接口和BasicDBObject对象：表示一个具体的记录，BasicDBObject实现了DBObject，是key-value的数据结构，用起来和HashMap是基本一致的。
		FindIterable<Document> iterable = collection.find(query);

		Map<String, Object> jsonStrToMap = null;
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document user = cursor.next();
			String jsonString = user.toJson();
			jsonStrToMap = (Map<String, Object>) JSON.parse(jsonString);
		}

		return jsonStrToMap;
	}

	public List<Map<String, Object>> queryByDoc(String table, BasicDBObject doc)
			throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		FindIterable<Document> iterable = collection.find(doc);
		/**
		 * 1. 获取迭代器FindIterable<Document> 2. 获取游标MongoCursor<Document>
		 * 3.通过游标遍历检索出的文档集合
		 * */

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document user = cursor.next();
			String jsonString = user.toJson();
			Map<String, Object> jsonStrToMap = (Map<String, Object>) JSON.parse(jsonString);
			list.add(jsonStrToMap);
		}
		return list;
	}
	
	public List<Map<String, Object>> queryAll(String table) throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		FindIterable<Document> iterable = collection.find();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document user = cursor.next();
			String jsonString = user.toJson();
			Map<String, Object> jsonStrToMap = (Map<String, Object>) JSON.parse(jsonString);
			list.add(jsonStrToMap);
		}
		return list;
	}

	public List<Document> findIterable(FindIterable<Document> iterable)
			throws Exception {
		List<Document> list = new ArrayList<Document>();
		MongoCursor<Document> cursor = iterable.iterator();
		while (cursor.hasNext()) {
			Document doc = cursor.next();
			list.add(doc);
		}
		cursor.close();
		return list;
	}

	public boolean delete(String table, BasicDBObject doc) throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		DeleteResult deleteManyResult = collection.deleteMany(doc);
		long deletedCount = deleteManyResult.getDeletedCount();

		if (deletedCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteOne(String table, BasicDBObject doc) throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		DeleteResult deleteOneResult = collection.deleteOne(doc);
		long deletedCount = deleteOneResult.getDeletedCount();
		if (deletedCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean update(String table, BasicDBObject whereDoc,
			BasicDBObject updateDoc) throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		UpdateResult updateManyResult = collection.updateMany(whereDoc,
				new Document("$set", updateDoc));
		long modifiedCount = updateManyResult.getModifiedCount();

		if (modifiedCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateOne(String table, BasicDBObject whereDoc,
			BasicDBObject updateDoc) throws Exception {
		MongoCollection<Document> collection = db.getCollection(table);
		UpdateResult updateOneResult = collection.updateOne(whereDoc,
				new Document("$set", updateDoc));
		long modifiedCount = updateOneResult.getModifiedCount();
		System.out.println("修改的数量: " + modifiedCount);
		if (modifiedCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void createCol(String table) throws Exception {
		db.createCollection(table);
	}

	public void dropCol(String table) throws Exception {
		db.getCollection(table).drop();
	}

}
