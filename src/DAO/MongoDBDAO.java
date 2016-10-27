package DAO;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDBDAO implements IDAO {
	
	private final int TAMANHO_ARQUIVO = 16000000;
	private final String HOST_CONNECTION = "localhost";
	private static List<byte[]> partFile = null;
	private MongoClient mongo;
	private DB db;
	private DBCollection table;
	
	public MongoDBDAO(){
		this.mongo = new MongoClient(HOST_CONNECTION);
		this.db = this.mongo.getDB("test");
		this.table = this.db.getCollection("movie");
	}

	@Override
	public byte[] obter(String resolucao){
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("resolucao", resolucao);

		DBCursor cursor = table.find(searchQuery);

		while (cursor.hasNext()) {
			DBObject ret = cursor.next();
			return (byte[])ret.get("valor");
		}
		
		return null;
	}
	
	@Override
	public long inserir(String resolucao, byte[] dados){
		long retorno = 0;
		
		int repeticoes = dados.length % TAMANHO_ARQUIVO != 0 ? (dados.length / TAMANHO_ARQUIVO) + 1 : dados.length / TAMANHO_ARQUIVO;

		if(MongoDBDAO.partFile == null){
			MongoDBDAO.partFile = new ArrayList<byte[]>();
			for(int i = 1; i <= repeticoes; i++){
				MongoDBDAO.partFile.add(splitFile(dados, i));
			}
		}
			
		for(int i = 1; i <= repeticoes; i++){
			BasicDBObject document = new BasicDBObject();
			document.put("resolucao", resolucao + i);
			document.put("valor", MongoDBDAO.partFile.get(i - 1));
			
			long timeIni = System.currentTimeMillis();			
			this.table.insert(document);
			long timeFim = System.currentTimeMillis();
			
			retorno += (timeFim - timeIni);
		}
		
		return retorno;
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
	public void close(){
		this.mongo.close();
	}
	
	@Override
	public void limpar(){
		
	}
	
	@Override
	public long adicionar(String resolucao, byte[] dados){
		return inserir(resolucao, dados);
	}
	
	@Override
	public boolean remover(String resolucao){
		this.table.drop();
		return true;
	}
}
