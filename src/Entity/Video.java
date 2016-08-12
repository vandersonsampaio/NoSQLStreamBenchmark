package Entity;

import Adapter.Adaptador;

public class Video {

	//Atributos
	private String resolucao;
	private Byte[] dados;	
	private String fonteDados;
	private Adaptador adaptador;
	
	//Construtor
	public Video(){
		this.resolucao = "";
		this.dados = null;
		this.fonteDados = "";
		this.adaptador = new Adaptador();
	}
	
	//Métodos de Acesso
	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

	public Byte[] getDados() {
		return dados;
	}

	public void setDados(Byte[] dados) {
		this.dados = dados;
	}

	public String getFonteDados() {
		return fonteDados;
	}

	public void setFonteDados(String fonteDados) {
		this.fonteDados = fonteDados;
	}

	public Adaptador getAdaptador() {
		return adaptador;
	}

	public void setAdaptador(Adaptador adaptador) {
		this.adaptador = adaptador;
	}
}
