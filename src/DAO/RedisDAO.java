package DAO;

import java.util.ArrayList;
import java.util.List;

import Util.Util;
import redis.clients.jedis.Jedis;

public class RedisDAO implements IDAO {
	
	private final int TAMANHO_ARQUIVO = 500000000;
	private static List<byte[]> partFile = null;
	private Jedis jedis;
	
	public RedisDAO(){
		System.out.println("Construtor");
		Util util = new Util();
		String HOST_CONNECTION = util.getValueByName("databases", "redis", "host");
		int port = Integer.parseInt(util.getValueByName("databases", "redis", "port"));
		
		this.jedis = new Jedis(HOST_CONNECTION, port, 10000, 10000);
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
		System.out.println("Tamanho: " + dados.length);
		
		if(dados.length < TAMANHO_ARQUIVO){
			System.out.println("Insert");
			return inserir(resolucao, dados);
		}
		else{
			System.out.println("Append");
			long retorno = 0;
			
			int repeticoes = dados.length % TAMANHO_ARQUIVO != 0 ? (dados.length / TAMANHO_ARQUIVO) + 1 : dados.length / TAMANHO_ARQUIVO;

			if(RedisDAO.partFile == null){
				RedisDAO.partFile = new ArrayList<byte[]>();
				for(int i = 1; i <= repeticoes; i++){
					RedisDAO.partFile.add(splitFile(dados, i));
				}
			}
				
			for(int i = 1; i <= repeticoes; i++){
				long timeIni = System.currentTimeMillis();
				this.jedis.rpush(resolucao.getBytes(), RedisDAO.partFile.get(i - 1));
				long timeFim = System.currentTimeMillis();
				retorno += (timeFim - timeIni);
			}
			
			return retorno;
		}
			
	}
	
	private byte[] splitFile(byte[] dados, int parte){
		int inicio = TAMANHO_ARQUIVO * (parte - 1);
		int fim = TAMANHO_ARQUIVO * parte;
		
		fim = fim > dados.length ? dados.length : fim;
		
		byte[] retorno = new byte[fim - inicio];

		int k = 0;
		for(int i = inicio; i < fim; i++)
			retorno[k++] = dados[i];
		
		return retorno;
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
