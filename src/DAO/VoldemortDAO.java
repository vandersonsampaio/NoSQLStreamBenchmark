package DAO;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

import com.datastax.driver.core.utils.Bytes;

public class VoldemortDAO implements IDAO {

	private String HOST_CONNECTION = "tcp://localhost:6666";
	private StoreClientFactory factory;
	StoreClient<String, String> client;

	public VoldemortDAO() {
		this.factory = new SocketStoreClientFactory(
				new ClientConfig().setBootstrapUrls(HOST_CONNECTION));
		this.client = this.factory.getStoreClient("test");
	}

	@Override
	public byte[] obter(String resolucao) {
		return null;//client.getValue(resolucao);
	}

	@Override
	public long inserir(String resolucao, byte[] dados) {
		long timeIni = System.currentTimeMillis();
		
		client.put(resolucao, Bytes.toHexString(dados));
		
		long timeFim = System.currentTimeMillis();
		
		return timeFim - timeIni;
	}

	@Override
	public void close() {
		this.factory.close();
	}

	@Override
	public void limpar() {

	}
	
	@Override
	public long adicionar(String resolucao, byte[] dados){
		return inserir(resolucao, dados);
	}
	
	@Override
	public boolean remover(String resolucao){
		this.client.put(resolucao, "tmp");
		
		return true;
	}
}
