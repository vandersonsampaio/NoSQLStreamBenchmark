package DAO;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.versioning.Versioned;

public class VoldemortDAO implements IDAO {
	
	private String HOST_CONNECTION = "tcp://178.62.254.52:6666"; 
	private StoreClientFactory factory;
	StoreClient<String, String> client;
	
	public VoldemortDAO(){
		//ClientConfig config = new ClientConfig().setBootstrapUrls(HOST_CONNECTION);
		factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(HOST_CONNECTION));
		client = factory.getStoreClient("test");
	}
	
	@Override
	public byte[] obter(String resolucao){
		String retorno1 = client.get("hello").toString();
		Versioned<String> retorno2 = client.get("some_key");
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		String retorno1 = client.get("hello").toString();
		Versioned<String> retorno2 = client.get("some_key");
		retorno2.setObject("some_value");
		client.put("some_key", retorno2);
		return true;
	}
	
	@Override
	public void close(){
		this.factory.close();
	}
	
	@Override
	public void limpar(){
		
	}
}
