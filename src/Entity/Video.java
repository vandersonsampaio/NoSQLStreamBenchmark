package Entity;

import Adapter.Adaptador;

public class Video {

	// Atributos
	private String resolucao;
	private byte[] dados;
	private String fonteDados;
	private Adaptador adaptador;

	// Construtor
	public Video() {
		this.resolucao = "";
		this.dados = null;
		this.fonteDados = "";
		this.adaptador = null;
	}

	public Video(String resolucao, String fonteDados) {
		this.resolucao = resolucao;
		this.dados = null;
		this.fonteDados = fonteDados;
		this.adaptador = new Adaptador(fonteDados);
	}

	public Video(String resolucao, String fonteDados, byte[] dados) {
		this.resolucao = resolucao;
		this.dados = dados;
		this.fonteDados = fonteDados;
		this.adaptador = new Adaptador(fonteDados);
	}

	// Métodos de Acesso
	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}

	public String getFonteDados() {
		return fonteDados;
	}

	public void setFonteDados(String fonteDados) {
		this.adaptador = new Adaptador(fonteDados);
		this.fonteDados = fonteDados;
	}

	public Adaptador getAdaptador() {
		return adaptador;
	}

	public long inserir() {
		return adaptador.inserirFilme(this.resolucao, this.dados);
	}

	public boolean buscar() {
		this.dados = adaptador.obterFilme(resolucao);

		return true;
	}

	public boolean limpar() {
		return adaptador.limparBase(resolucao);
	}
}
