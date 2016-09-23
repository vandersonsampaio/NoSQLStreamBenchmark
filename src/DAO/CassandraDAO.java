package DAO;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraDAO implements IDAO {
	
	private Cluster cluster;
	private Session session;
	
	public CassandraDAO(){
		this.cluster = Cluster.builder().addContactPoint("178.62.254.52").build();
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
	public boolean inserir(String resolucao, byte[] dados){
		
		//session.execute("INSERT INTO movies (resolucao, valor) VALUES (" + resolucao + ", " + dados.toString());
		
		session.execute("INSERT INTO users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");

		ResultSet results = session.execute("SELECT * FROM users WHERE lastname='Jones'");
		for (Row row : results) {
			System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
		}
		
		return true;
	}
	
	@Override
	public void limpar(){
		
	}
	
	@Override
	public void close(){
		cluster.close();
	}
}
