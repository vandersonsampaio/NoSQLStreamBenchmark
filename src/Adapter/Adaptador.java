package Adapter;

import DAO.*;

public class Adaptador {	
	private IDAO dao;
	
	public Adaptador(String fonteDados){
		switch (fonteDados) {
		case "C":
			this.dao = new CassandraDAO();
			break;
			
		case "V":
			this.dao = new VoldemortDAO();
			break;
			
		case "H":
			this.dao = new HBaseDAO();
			break;
			
		case "R":
			this.dao = new RedisDAO();
			break;
			
		case "M":
			this.dao = new MongoDBDAO();
			break;

		}
	}

	public Byte[] obterFilme(String resolucao) {
		return dao.obter(resolucao);
	}

	public boolean inserirFilme(String resolucao, Byte[] dados) {
		return dao.inserir(resolucao, dados);
	}

	public boolean limparBase(String resolucao) {
		dao.limpar();
		return true;
	}
}
