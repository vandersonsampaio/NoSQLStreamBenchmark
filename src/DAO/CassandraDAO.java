package DAO;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;

public class CassandraDAO implements IDAO {
	
	private Cluster cluster;
	private Session session;
	private static String execucao = null;
	
	public CassandraDAO(){
		this.cluster = Cluster.builder().addContactPoint("localhost").build();
		this.session = this.cluster.connect("demo");
	}
	
	@Override
	public byte[] obter(String resolucao){
		
		ResultSet results = session.execute("SELECT valor FROM movies WHERE resolucao = '" + resolucao + "'");
		
		for (Row row : results) {
			return row.getBytes(resolucao).array();
		}
		
		return null;
	}
	
	@Override
	public long inserir(String resolucao, byte[] dados){
		if(CassandraDAO.execucao == null){
			StringBuilder str = new StringBuilder();
			str.append("INSERT INTO movies (id, resolucao, valor) VALUES (");
			str.append(System.currentTimeMillis());
			str.append(", '");
			str.append(resolucao);
			str.append("', ");
			str.append(Bytes.toHexString(dados));
			str.append(")");
			
			CassandraDAO.execucao = str.toString();
		}
		
		long timeIni = System.currentTimeMillis();
		session.execute(CassandraDAO.execucao);
		long timeFim = System.currentTimeMillis();
		
		return (timeFim - timeIni);
	}
	
	@Override
	public void limpar(){
		
	}
	
	@Override
	public void close(){
		cluster.close();
	}
	
	@Override
	public long adicionar(String resolucao, byte[] dados){
		return inserir(resolucao, dados);
	}
	
	@Override
	public boolean remover(String resolucao){
		session.execute("TRUNCATE movies");
		
		return true;
	}
}
