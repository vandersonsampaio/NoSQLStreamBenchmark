package DAO;

public interface IDAO {

	public byte[] obter(String resolucao);
	
	public boolean inserir(String resolucao, byte[] dados);
	
	public void limpar();
	
	public void close();
	
	public boolean adicionar(String resolucao, byte[] dados);
	
	public boolean remover(String resolucao);
}
