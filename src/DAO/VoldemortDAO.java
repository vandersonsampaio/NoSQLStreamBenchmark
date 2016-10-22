package DAO;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.versioning.Versioned;

import org.apache.log4j.Logger;

public class VoldemortDAO implements IDAO {

	private String HOST_CONNECTION = "tcp://localhost:6666";
	private StoreClientFactory factory;
	StoreClient<String, Object> client;

	public VoldemortDAO() {
		this.factory = new SocketStoreClientFactory(
				new ClientConfig().setBootstrapUrls(HOST_CONNECTION));
		this.client = this.factory.getStoreClient("test");
	}

	@Override
	public byte[] obter(String resolucao) {
		return (byte[])client.getValue(resolucao);
	}

	@Override
	public boolean inserir(String resolucao, byte[] dados) {
		System.out.println("Resolucao: " + resolucao);
		System.out.println("dados: " + dados);
		
		client.put(resolucao, dados);
		
		return true;
	}

	@Override
	public void close() {
		this.factory.close();
	}

	@Override
	public void limpar() {

	}
	
	@Override
	public boolean adicionar(String resolucao, byte[] dados){
		inserir(resolucao, dados);
		return true;
	}
	
	@Override
	public boolean remover(String resolucao){
		return this.client.delete(resolucao);
	}
}
