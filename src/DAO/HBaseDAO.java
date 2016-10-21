package DAO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HBaseDAO implements IDAO {

	private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	private String HOST_CONNECTION = "178.62.254.52";
	private Client cliente;

	private static Configuration config = HBaseConfiguration.create();

	public HBaseDAO() {
		config.set("hbase.zookeeper.quorum", "178.62.254.52");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("hbase.master", "178.62.254.52:60000");
	}

	@Override
	public byte[] obter(String resolucao) {
		// HTable table;
		try {
			System.out.println("conexão");
			Connection connection = ConnectionFactory.createConnection(config);
			Table table = connection.getTable(TableName.valueOf("movie"));
			System.out.println("tabela");
			try {
				// Use the table as needed, for a single operation and a single
				// thread
				System.out.println("consulta");
				Get s = new Get("4k".getBytes());
				System.out.println(table.getName());
				Result ss = table.get(s);
				System.out.println("Resultado");

				for (KeyValue kv : ss.raw()) {
					System.out.print(new String(kv.getRow()) + " ");
					System.out.print(new String(kv.getFamily()) + ":");
					System.out.print(new String(kv.getQualifier()) + " ");
					System.out.print(kv.getTimestamp() + " ");
					System.out.println(new String(kv.getValue()));
				}

				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				table.close();
				connection.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void get(final String paybackBarcode) {
		WebResource service;
		try {
			service = cliente
					.resource("178.62.254.52:8030/"
							+ URLEncoder.encode("movie",
									UTF8_CHARSET.displayName())
							+ "/"
							+ URLEncoder.encode("4k",
									UTF8_CHARSET.displayName()));

			ClientResponse response = service.get(ClientResponse.class);
			if (response.hasEntity() && response.getStatus() == 200) {
				System.out.println(response.getEntity(String.class));
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean inserir(String resolucao, byte[] dados) {
		try {
			WebResource service = cliente
					.resource("http://"
							+ HOST_CONNECTION
							+ ":8030/"
							+ URLEncoder.encode("test",
									UTF8_CHARSET.displayName())
							+ "/"
							+ URLEncoder.encode("movie",
									UTF8_CHARSET.displayName())
							+ "/"
							+ URLEncoder.encode("resolucao",
									UTF8_CHARSET.displayName())
							+ "/"
							+ URLEncoder.encode("240p",
									UTF8_CHARSET.displayName()));

			ClientResponse response = service.put(ClientResponse.class);

			if (response.getStatus() == 200)
				System.out.println("Deu certo!");
			else
				System.out.println("Deu errado: " + response.getStatus());

			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void limpar() {

	}

	@Override
	public void close() {

	}
	
	@Override
	public boolean adicionar(String resolucao, byte[] dados){
		return true;
	}
	
	@Override
	public boolean remover(String resolucao){
		return true;
	}

}
