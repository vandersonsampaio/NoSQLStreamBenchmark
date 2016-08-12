package Adapter;

import DAO.*;

public class Adaptador {
	private RedisDAO redis;
	private VoldemortDAO voldemort;
	private HBaseDAO hbase;
	private CassandraDAO cassandra;
	private MongoDBDAO mongo;
	
	public Byte[] obterFilme(String resolucao){
		return null;
	}
	
	public boolean inserirFilme(String resolucao, Byte[] dados){
		return true;
	}
}
