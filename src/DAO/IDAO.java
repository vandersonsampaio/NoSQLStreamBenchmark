package DAO;

public interface IDAO {

	public byte[] obter(String resolucao);
	
	public boolean inserir(String resolucao, byte[] dados);
	
	public void limpar();
	
}
