package DAO;

import java.util.Date;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBDAO implements IDAO {
	
	private String HOST_CONNECTION = "178.62.254.52";
	private MongoClient mongo;
	private DB db;
	
	public MongoDBDAO(){
		this.mongo = new MongoClient(HOST_CONNECTION);
		this.db = this.mongo.getDB("test");
	}
	
	public void teste(){
		DBCollection table = db.getCollection("user");
		
		BasicDBObject document = new BasicDBObject();
		document.put("name", "mkyong");
		document.put("age", 30);
		document.put("createdDate", new Date());
		table.insert(document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "mkyong");

		DBCursor cursor = table.find(searchQuery);

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
	}
	
	@Override
	public byte[] obter(String resolucao){
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		teste();
		return true;
	}
	
	@Override
	public void limpar(){
		
	}
}
