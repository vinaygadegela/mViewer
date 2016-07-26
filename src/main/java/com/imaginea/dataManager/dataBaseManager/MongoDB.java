package com.imaginea.dataManager.dataBaseManager;

import java.util.Collection;
import java.util.Set;

import com.imaginea.utils.ConfigUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

public class MongoDB {
	ConfigUtil configUtil;
	MongoCredential credential;
	MongoClient mongoClient;
	DBCollection dbCollection = null;

	/**
	 * Establish Connection with MongoDb
	 */
	public MongoDB() {
		configUtil = ConfigUtil.getInstance();
		credential = MongoCredential.createCredential(
				configUtil.getProperty("DBUSERNAME"),
				configUtil.getProperty("DBDATABASE"),
				configUtil.getProperty("DBPASSWORD").toCharArray());
		mongoClient = new MongoClient(configUtil.getProperty("DBHOST")
				.toString());
	}

	/**
	 * Get All Database Name
	 * 
	 * @return
	 */
	public Collection<DB> getAllDatabaseNames() {
		return mongoClient.getUsedDatabases();
	}

	/**
	 * Get All Collection for each DB
	 * 
	 * @param dbName
	 * @return
	 */
	public Set<String> getDbCollections(String dbName) {
		return mongoClient.getDB(dbName).getCollectionNames();
	}

	/**
	 * Save Document in Collection
	 * 
	 * @param dbName
	 * @param collectionName
	 * @param documents
	 * 
	 *            Example: DBCollection table = db.getCollection("user");
	 *            BasicDBObject document = new BasicDBObject();
	 *            document.put("year","2016" ); document.put("createdDate", new
	 *            Date()); table.insert(document);
	 */
	public void saveDocumentInCollection(String dbName, String collectionName,
			BasicDBObject documents) {
		dbCollection = (DBCollection) mongoClient.getDatabase(dbName)
				.getCollection(collectionName);
		dbCollection.insert(documents);
	}

	/**
	 * Update Document by Key Value Pair
	 * 
	 * @param dbName
	 * @param collectionName
	 * @param query
	 * @param newDocument
	 * 
	 *            Example: I/P { "hosting" : "hostA", "type" : "vps", "clients"
	 *            : 1000 }, { "hosting" : "hostB", "type" : "dedicated server",
	 *            "clients" : 100 }, { "hosting" : "hostC", "type" : "vps",
	 *            "clients" : 900 }
	 * 
	 *            Expected O/P Find document where hosting = ‘hostB’, and update
	 *            it’s clients values from 100 to 110.
	 * 
	 *            { "_id" : { "$oid" : "id"} , "hosting" : "hostA" , "type" :
	 *            "vps" , "clients" : 1000} { "_id" : { "$oid" : "id"} ,
	 *            "hosting" : "hostB" , "type" : "dedicated server" , "clients"
	 *            : 110} { "_id" : { "$oid" : "id"} , "hosting" : "hostC" ,
	 *            "type" : "vps" , "clients" : 900}
	 * 
	 *            BasicDBObject newDocument = new BasicDBObject();
	 *            newDocument.append("$set", new
	 *            BasicDBObject().append("clients", 110));
	 * 
	 *            BasicDBObject searchQuery = new
	 *            BasicDBObject().append("hosting", "hostB");
	 * 
	 *            collection.update(searchQuery, newDocument);
	 * 
	 */
	public void updateDocument(String dbName, String collectionName,
		BasicDBObject query, BasicDBObject newDocument) {
		BasicDBObject updateObj = null;
		dbCollection = (DBCollection) mongoClient.getDatabase(dbName)
				.getCollection(collectionName);
		updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
		dbCollection.update(query, updateObj);
	}

}
