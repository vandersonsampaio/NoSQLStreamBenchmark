package DAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseDAO implements IDAO {

	private final int TAMANHO_ARQUIVO = 10000000;
	private String MASTER_IP;
	private String ZOOKEEPER_PORT;
	private Configuration config;
	private static List<byte[]> partFile = null;

	public HBaseDAO() {
		this.MASTER_IP = "localhost";
		this.ZOOKEEPER_PORT = "2181";
		this.config = HBaseConfiguration.create();

		this.config.set("hbase.zookeeper.quorum", MASTER_IP);
		this.config.set("hbase.zookeeper.property.clientPort", ZOOKEEPER_PORT);
	}

	@Override
	public byte[] obter(String resolucao) {

		return null;
	}

	@Override
	public long inserir(String resolucao, byte[] dados) {
		long retorno = 0;
		
		int repeticoes = dados.length % TAMANHO_ARQUIVO != 0 ? (dados.length / TAMANHO_ARQUIVO) + 1
				: dados.length / TAMANHO_ARQUIVO;

		if (HBaseDAO.partFile == null) {
			HBaseDAO.partFile = new ArrayList<byte[]>();
			for (int i = 1; i <= repeticoes; i++) {
				HBaseDAO.partFile.add(splitFile(dados, i));
			}
		}

		try {
			HTable table = new HTable(config, "testk");
			for (int i = 1; i <= repeticoes; i++) {
				Put put = new Put(Bytes.toBytes(resolucao + i));
				put.add(Bytes.toBytes("movie"), Bytes.toBytes(resolucao + i),
						HBaseDAO.partFile.get(i - 1));
				
				long timeIni = System.currentTimeMillis();
				table.put(put);
				long timeFim = System.currentTimeMillis();
				
				retorno += (timeFim - timeIni);
			}

			System.out.println("insert recored " + resolucao
					+ " to table test ok.");
		} catch (IOException e) {
			e.printStackTrace();
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
	public void limpar() {

	}

	@Override
	public void close() {

	}

	@Override
	public long adicionar(String resolucao, byte[] dados) {
		return inserir(resolucao, dados);
	}

	@Override
	public boolean remover(String resolucao) {
		deleteTable("test4k");
		creatTable("test4k", "movie");
		return true;
	}

	private void creatTable(String tableName, String familys) {
		try {
			HBaseAdmin admin = new HBaseAdmin(this.config);
			if (!admin.tableExists(tableName)) {
				HTableDescriptor tableDesc = new HTableDescriptor(tableName);
				tableDesc.addFamily(new HColumnDescriptor(familys));
				admin.createTable(tableDesc);
				System.out.println("create table " + tableName + " ok.");
			}
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteTable(String tableName){
		try {
			HBaseAdmin admin = new HBaseAdmin(this.config);
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
			System.out.println("delete table " + tableName + " ok.");
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
