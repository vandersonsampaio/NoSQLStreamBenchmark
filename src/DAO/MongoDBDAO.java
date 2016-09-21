package DAO;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDAO implements IDAO {
	
	private String HOST_CONNECTION = "178.62.254.52";
	private MongoClient mongo;
	private DB db;
	private DBCollection table;
	
	public MongoDBDAO(){
		this.mongo = new MongoClient(HOST_CONNECTION);
		this.db = this.mongo.getDB("test");
		this.table = this.db.getCollection("user");
	}

	@Override
	public byte[] obter(String resolucao){
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("resolucao", resolucao);

		DBCursor cursor = table.find(searchQuery);

		while (cursor.hasNext()) {
			DBObject ret = cursor.next();
			Object retorno = ret.get("valor");
		}
		
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		
		BasicDBObject document = new BasicDBObject();
		document.put("resolucao", resolucao);
		document.put("valor", dados);
		table.insert(document);
		
		return true;
	}
	
	@Override
	public void limpar(){
		
	}
}
