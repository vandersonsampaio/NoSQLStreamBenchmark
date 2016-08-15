package DAO;

import redis.clients.jedis.Jedis;

public class RedisDAO implements IDAO {
	
	private Jedis jedis;
	
	public RedisDAO(){
		this.jedis = new Jedis();
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
