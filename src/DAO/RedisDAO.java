package DAO;

import redis.clients.jedis.Jedis;

public class RedisDAO implements IDAO {
	
	private String HOST_CONNECTION = "178.62.254.52";
	
	private Jedis jedis;
	
	public RedisDAO(){
		this.jedis = new Jedis(HOST_CONNECTION, 6379, 6000);
	}
		
	@Override
	public byte[] obter(String resolucao){
		return jedis.get(resolucao.getBytes());
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		jedis.set(resolucao.getBytes(), dados);
		return true;
	}
	
	@Override
	public void close(){
		jedis.close();
	}
	
	@Override
	public void limpar(){
		
	}
}
