package DAO;

import Util.Util;
import redis.clients.jedis.Jedis;

public class RedisDAO implements IDAO {
	
	private Jedis jedis;
	
	public RedisDAO(){
		System.out.println("Construtor");
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
	public long inserir(String resolucao, byte[] dados){
		long timeIni = System.currentTimeMillis();
		
		jedis.set(resolucao.getBytes(), dados);
		
		long timeFim = System.currentTimeMillis();
		System.out.println("Tempo: " + (timeFim - timeIni));
		return (timeFim - timeIni);
	}
	
	@Override
	public long adicionar(String resolucao, byte[] dados){
		System.out.println("Adicionar");
		return inserir(resolucao, dados);
	}
	
	@Override
	public boolean remover(String resolucao){
		System.out.println("Remove");
		if(jedis.exists(resolucao.getBytes()))
			jedis.del(resolucao.getBytes());
		
		return true;
	}
	
	@Override
	public void close(){
		System.out.println("Close");
		jedis.close();
	}
	
	@Override
	public void limpar(){
		
	}
}
