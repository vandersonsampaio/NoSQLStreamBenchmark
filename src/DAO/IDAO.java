package DAO;

public interface IDAO {

	public Byte[] obter(String resolucao);
	
	public boolean inserir(String resolucao, Byte[] dados);
	
	public void limpar();
	
}
