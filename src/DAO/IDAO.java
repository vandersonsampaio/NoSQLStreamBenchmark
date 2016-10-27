package DAO;

public interface IDAO {

	public byte[] obter(String resolucao);
	
	public long inserir(String resolucao, byte[] dados);
	
	public void limpar();
	
	public void close();
	
	public long adicionar(String resolucao, byte[] dados);
	
	public boolean remover(String resolucao);
}
