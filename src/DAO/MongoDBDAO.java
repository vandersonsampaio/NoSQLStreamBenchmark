package DAO;

import com.mongodb.MongoClient;

public class MongoDBDAO implements IDAO {
	
	private MongoClient mongo;	
	
	public MongoDBDAO(){
		this.mongo = new MongoClient();
	}
	
	@Override
	public Byte[] obter(String resolucao){
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, Byte[] dados){
		return true;
	}
	
	@Override
	public void limpar(){
		
	}
}
