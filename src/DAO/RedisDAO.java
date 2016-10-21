package DAO;

import Util.Util;
import redis.clients.jedis.Jedis;

public class RedisDAO implements IDAO {
	
	private Jedis jedis;
	
	public RedisDAO(){
		Util util = new Util();
		String HOST_CONNECTION = util.getValueByName("databases", "redis", "host");
		int port = Integer.parseInt(util.getValueByName("databases", "redis", "port"));
		
		this.jedis = new Jedis(HOST_CONNECTION, port, 6000);
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
	public boolean adicionar(String resolucao, byte[] dados){
		jedis.append(resolucao.getBytes(), dados);
		return true;
	}
	
	@Override
	public boolean remover(String resolucao){
		if(jedis.exists(resolucao.getBytes()))
			jedis.del(resolucao.getBytes());
		
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
