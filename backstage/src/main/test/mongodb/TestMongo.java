package mongodb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.zee.app.start.Starter;
import com.zee.bll.extend.unity.da.DaIotMonitorDataUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaIotMonitorData;
import com.zee.utl.DateUtils;
import com.zee.utl.FileUtil;
import com.zee.utl.SymbolicConstant;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**
 * @author lxy
 * @createDate 2018年4月26日 下午7:45:29
 * @updateDate 2018年4月26日 下午7:45:29
 * @description redis测试demo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Starter.class)
@WebAppConfiguration
public class TestMongo {
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	@Qualifier("daIotMonitorDataUntBll")
	protected DaIotMonitorDataUntBll daIotMonitorDataUntBll;
	
	@Autowired
//    private MongoCollection<Document> mongoCollection;
	

	@Test
    public void test(){
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("viewName", "date");//视图名，年year，月month，日date，小时hour（默认为年）
//		map.put("hasCurrent", "true");
//		map.put("pastNum", "1");
//		List<String> timesList = TimesView.getTimesView(map);
//		for (String timeStr : timesList) {
//			insert(timeStr);
//		}
		
//		query();
		huifu();
	}
	
	@SuppressWarnings("unchecked")
	private void huifu(){
		MongoClient mongoClient = new MongoClient( "192.168.200.238" , 60017 );
		
		MongoDatabase database = mongoClient.getDatabase("mango");                              
		MongoCollection<Document> collection = database.getCollection("DaIotMonitorData");   
		
		Document doc = new Document();
		doc.append("timeStr", new Document("$gte", "2018-11-24 00:00:00"));
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
//		ArrayList arrayList = new ArrayList();
		for (Map<String, Object> map : list) {
			DaIotMonitorData daIotMonitorData = new DaIotMonitorData();
			daIotMonitorData.setId(map.get("id")==null?"":map.get("id").toString());
			daIotMonitorData.setDisplayName(map.get("displayName")==null?"":map.get("displayName").toString());
			daIotMonitorData.setHid(map.get("hid")==null?"":map.get("hid").toString());
			daIotMonitorData.setRemark(map.get("remark")==null?"":map.get("remark").toString());
			daIotMonitorData.setSensorName(map.get("sensorName")==null?"":map.get("sensorName").toString());
			daIotMonitorData.setSensorType(map.get("sensorType")==null?0:Integer.valueOf(map.get("sensorType").toString()));
			daIotMonitorData.setTime(map.get("timeStr")==null?null:DateUtils.string2Date(map.get("timeStr").toString(), "yyyy-MM-dd HH:mm:ss"));
			daIotMonitorData.setUnit(map.get("unit")==null?"":map.get("unit").toString());
			daIotMonitorData.setVal(map.get("val")==null?0:Double.valueOf(map.get("val").toString()));
			try {
				daIotMonitorDataUntBll.add(daIotMonitorData);
			} catch (Exception e) {
				try {
					daIotMonitorDataUntBll.update(daIotMonitorData);
					fileUtil.writeText("F:\\Mango\\", "id.txt", true, map.get("id").toString()+"\n");
				} catch (Exception e1) {
					//id重复继续执行
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * 工具类使用了反射效率太低，大批量导入原生速度更快
	 * ps:getListBySQL默认只查询1000条，修改下
	 * @param monthStr
	 */
	@SuppressWarnings("unchecked")
	private void insert(String timeStr) {
		
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.200.238:27017");  
		MongoClient mongoClient = new MongoClient(connectionString);                             
		MongoDatabase database = mongoClient.getDatabase("mango");                              
		MongoCollection<Document> collection = database.getCollection("DaIotMonitorData");    
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                            ");
		selectBuffer.append("		A.id id,                                                      ");
		selectBuffer.append("		A.hid hid,                                                    ");
		selectBuffer.append("		A.sensor_type sensorType,                                     ");
		selectBuffer.append("		A.sensor_name sensorName,                                     ");
		selectBuffer.append("		A.display_name displayName,                                   ");
		selectBuffer.append("		A.val val,                                                    ");
		selectBuffer.append("		A.unit unit,                                                  ");
		selectBuffer.append("		DATE_FORMAT(A.time,'%Y-%m-%d %H:%i:%s') time,                 ");
		selectBuffer.append("		A.remark remark                                               ");
		selectBuffer.append("	FROM                                                              ");
		selectBuffer.append("		da_iot_monitor_data A                                         ");
		selectBuffer.append("	WHERE                                                             ");
		selectBuffer.append("	    DATE_FORMAT(A.time, '%Y-%m-%d') = '"+timeStr+"'               ");
		map.put("Sql", selectBuffer.toString());

		ResultModel resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		long i = 1;
//		List<Document> documents = new ArrayList<Document>();
		for (Map<String, Object> map2 : modelList) {
			Document doc = new Document();
			if(map2.get("id") != null){
				doc.append("id", map2.get("id").toString());
			}
			if(map2.get("hid") != null){
				doc.append("hid", map2.get("hid").toString());
			}
			if(map2.get("sensorType") != null){
				doc.append("sensorType", Integer.valueOf(map2.get("sensorType").toString()));
				doc.append("sensorTypeStr", map2.get("sensorType").toString());
			}
			if(map2.get("sensorName") != null){
				doc.append("sensorName", map2.get("sensorName").toString());
			}
			if(map2.get("displayName") != null){
				doc.append("displayName", map2.get("displayName").toString());
			}
			if(map2.get("val") != null){
				doc.append("val", Double.valueOf(map2.get("val").toString()));
				doc.append("valStr", map2.get("val").toString());
			}
			if(map2.get("unit") != null){
				doc.append("unit", map2.get("unit").toString());
			}
			if(map2.get("time") != null){
				doc.append("time", DateUtils.string2Date(map2.get("time").toString(), SymbolicConstant.TIME_FORMAT));
				doc.append("timeStr", map2.get("time").toString());
			}
			if(map2.get("remark") != null){
				doc.append("remark", map2.get("remark").toString());
			}
			collection.insertOne(doc);
			System.out.println(i);
			i++;
		}
		mongoClient.close();
	}
	
	/**
	 * 工具类使用了反射效率太低，大批量导入原生速度更快
	 * ps:getListBySQL默认只查询1000条，修改下
	 * @param monthStr
	 */
	@SuppressWarnings("unchecked")
	private void insert2(String timeStr) {
		
		MongoClientURI connectionString = new MongoClientURI("mongodb://192.168.200.39:27017");  
		MongoClient mongoClient = new MongoClient(connectionString);                             
		MongoDatabase database = mongoClient.getDatabase("mango");                              
		MongoCollection<Document> collection = database.getCollection("DaIotMonitorData");    
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                            ");
		selectBuffer.append("		A.id id,                                                      ");
		selectBuffer.append("		A.hid hid,                                                    ");
		selectBuffer.append("		A.sensor_type sensorType,                                     ");
		selectBuffer.append("		A.sensor_name sensorName,                                     ");
		selectBuffer.append("		A.display_name displayName,                                   ");
		selectBuffer.append("		A.val val,                                                    ");
		selectBuffer.append("		A.unit unit,                                                  ");
		selectBuffer.append("		DATE_FORMAT(A.time,'%Y-%m-%d %H:%i:%s') time,                 ");
		selectBuffer.append("		A.remark remark                                               ");
		selectBuffer.append("	FROM                                                              ");
		selectBuffer.append("		da_iot_monitor_data A                                         ");
		selectBuffer.append("	WHERE                                                             ");
		selectBuffer.append("	    DATE_FORMAT(A.time, '%Y-%m-%d') = '"+timeStr+"'               ");
		map.put("Sql", selectBuffer.toString());

		ResultModel resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		long i = 1;
//		List<Document> documents = new ArrayList<Document>();
		for (Map<String, Object> map2 : modelList) {
			Document doc = new Document();
			if(map2.get("id") != null){
				doc.append("id", map2.get("id").toString());
			}
			if(map2.get("hid") != null){
				doc.append("hid", map2.get("hid").toString());
			}
			if(map2.get("sensorType") != null){
				doc.append("sensorType", Integer.valueOf(map2.get("sensorType").toString()));
				doc.append("sensorTypeStr", map2.get("sensorType").toString());
			}
			if(map2.get("sensorName") != null){
				doc.append("sensorName", map2.get("sensorName").toString());
			}
			if(map2.get("displayName") != null){
				doc.append("displayName", map2.get("displayName").toString());
			}
			if(map2.get("val") != null){
				doc.append("val", Double.valueOf(map2.get("val").toString()));
				doc.append("valStr", map2.get("val").toString());
			}
			if(map2.get("unit") != null){
				doc.append("unit", map2.get("unit").toString());
			}
			if(map2.get("time") != null){
				doc.append("time", DateUtils.string2Date(map2.get("time").toString(), SymbolicConstant.TIME_FORMAT));
				doc.append("timeStr", map2.get("time").toString());
			}
			if(map2.get("remark") != null){
				doc.append("remark", map2.get("remark").toString());
			}
			collection.insertOne(doc);
			System.out.println(i);
			i++;
		}
		mongoClient.close();
	}
	
	private void query() {
//		Document sub_match = new Document();
//		sub_match.put("time", new Document("$gte", "2018-07-08"));
//		
//		Document sub_group = new Document();
//		sub_group.put("_id", "$hid");
//		sub_group.put("maxtime", new Document("$max", "$time"));
//		
//		Document match = new Document("$match", sub_match);
//		Document group = new Document("$group", sub_group);
//		
//		List<Document> aggregateList = new ArrayList<Document>();
//		aggregateList.add(match);
//		aggregateList.add(group);
//		
//		AggregateIterable<Document> resultset = mongoCollection.aggregate(aggregateList);
//		MongoCursor<Document> cursor = resultset.iterator();
//		
//		try {
//			while(cursor.hasNext()) {
//				Document item_doc = cursor.next();
//				System.out.println(item_doc.getString("_id"));
//				System.out.println(item_doc.getString("maxtime"));
//			}
//		} finally {
//			cursor.close();
//		}
		
//		Document sub_match = new Document();
//		sub_match.put("id", new Document("$eq", "000934d5f11d49999254a07b474655af"));
//		FindIterable<Document> find = mongoCollection.find(sub_match);
//		MongoCursor<Document> cursor = find.iterator();
//		
//		try {
//			while(cursor.hasNext()) {
//				Document item_doc = cursor.next();
//				System.out.println(item_doc.getString("id"));
//				System.out.println(item_doc.getString("time"));
//			}
//		} finally {
//			cursor.close();
//		}
		
	}
	
//	public static void main(String[] args) {
//		String time1 = "2018-05-31 19:05:22";
//		System.out.println(DateUtils.string2Date(time1, "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.date2String(DateUtils.string2Date(time1, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd-HH-mm-ss"));
//	}
	
	public static void main(String[] args) throws IOException {}

}