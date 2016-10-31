package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

import com.datastax.driver.core.utils.Bytes;

public class VoldemortDAO implements IDAO {

	private final int TAMANHO_ARQUIVO = 20000000;
	private static List<String> partFile = null;
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
		return null;// client.getValue(resolucao);
	}

	@Override
	public long inserir(String resolucao, byte[] dados) {
		long retorno = 0;

		int repeticoes = dados.length % TAMANHO_ARQUIVO != 0 ? (dados.length / TAMANHO_ARQUIVO) + 1
				: dados.length / TAMANHO_ARQUIVO;

		if (VoldemortDAO.partFile == null) {
			VoldemortDAO.partFile = new ArrayList<String>();
			for (int i = 1; i <= repeticoes; i++) {
				VoldemortDAO.partFile.add(Bytes
						.toHexString(splitFile(dados, i)));
			}
		}

		for (int i = 1; i <= repeticoes; i++) {
			long timeIni = System.currentTimeMillis();
			client.put(resolucao + i, VoldemortDAO.partFile.get(i - 1));
			long timeFim = System.currentTimeMillis();

			retorno += (timeFim - timeIni);
		}

		return retorno;
	}

	private byte[] splitFile(byte[] dados, int parte) {
		int inicio = TAMANHO_ARQUIVO * (parte - 1);
		int fim = TAMANHO_ARQUIVO * parte;

		fim = fim > dados.length ? dados.length : fim;

		byte[] retorno = new byte[fim - inicio];

		int k = 0;
		for (int i = inicio; i < fim; i++)
			retorno[k++] = dados[i];

		return retorno;
	}

	@Override
	public void close() {
		this.factory.close();
	}

	@Override
	public void limpar() {

	}

	@Override
	public long adicionar(String resolucao, byte[] dados) {
		return inserir(resolucao, dados);
	}

	@Override
	public boolean remover(String resolucao) {
		if (VoldemortDAO.partFile != null)
			for (int i = 1; i < VoldemortDAO.partFile.size(); i++)
				this.client.put(resolucao + i, "tmp");

		return true;
	}
}
