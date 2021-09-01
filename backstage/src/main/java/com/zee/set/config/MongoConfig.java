package com.zee.set.config;

import java.util.Arrays;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author lxy
 * @createDate 2018年7月12日 下午3:02:04
 * @updateDate 2018年7月12日 下午3:02:04
 * @description MongoConfig
 */
// @Configuration
public class MongoConfig {

	// @Value("${mongo.username}")
	// private String username;
	// @Value("${mongo.password}")
	// private String password;

	@Value("${mongo.maxConnect}")
	private String maxConnect;
	@Value("${mongo.maxWaitThread}")
	private String maxWaitThread;
	@Value("${mongo.maxTimeOut}")
	private String maxTimeOut;
	@Value("${mongo.maxWaitTime}")
	private String maxWaitTime;

	@Value("${mongo.address}")
	private String address;
	@Value("${mongo.port}")
	private String port;
	@Value("${mongo.database}")
	private String database;
	@Value("${mongo.collection}")
	private String collection;

	@Bean
	public MongoClient mongoClient() {
		MongoClientOptions.Builder build = new MongoClientOptions.Builder();
		build.connectionsPerHost(Integer.valueOf(maxConnect));
		build.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(maxWaitThread));
		build.connectTimeout(Integer.valueOf(maxTimeOut) * 1000);
		build.maxWaitTime(Integer.valueOf(maxWaitTime) * 1000);
		final MongoClientOptions options = build.build();

		MongoClient mongoClient = new MongoClient(address + ":" + port, options);
		return mongoClient;

		// try {//设置集群及密码
		// List<ServerAddress> addrs = new ArrayList<ServerAddress>();
		// for (String hostport : hostports.split(", *")) {
		// if (StringUtils.isBlank(hostport)) {
		// continue;
		// }
		// hostport = hostport.trim();
		// ServerAddress serverAddress = new
		// ServerAddress(hostport.split(":")[0],Integer.valueOf(hostport.split(":")[1]));
		// addrs.add(serverAddress);
		// }
		// MongoCredential credential =
		// MongoCredential.createScramSha1Credential(username, database,
		// password.toCharArray());
		// List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		// credentials.add(credential);
		// mongoClient = new MongoClient(addrs,credentials, options);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	@Bean
	public MongoDatabase mongoDatabase(MongoClient mongoClient) {
		MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
		return mongoDatabase;
	}

	@Bean
	public MongoCollection<Document> mongoCollection(MongoDatabase mongoDatabase) {
		MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(collection);
		return mongoCollection;
	}

}