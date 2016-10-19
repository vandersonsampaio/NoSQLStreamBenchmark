package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.Util;
import Entity.Video;

@WebServlet("/Administrador")
public class AdministradorController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("edtConsole", "");
		request.getRequestDispatcher("/Administrador.jsp").forward(request,
				response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String fonteDados = request.getParameter("selectFonteDados");
		String resolucao = request.getParameter("selectResolucao");
		String concorrencia = request.getParameter("edtConcorrencia");
		String quantidade = request.getParameter("edtQuantidade");

		String acao = request.getParameter("btnSubmit");
		String msg = "";

		try {
			if (acao.equals("btnConsultar")) {
				msg = obterFilme(Integer.parseInt(quantidade),
						Integer.parseInt(concorrencia), fonteDados, resolucao);
			} else if (acao.equals("btnInserir")) {
				
					msg = inserirFilme(Integer.parseInt(quantidade),
							Integer.parseInt(concorrencia), fonteDados, resolucao);
				
			} else if (acao.equals("btnLimpar")) {
				msg = limparBase(fonteDados, resolucao);
			} else {
				msg = "Nenhuma operação foi realizada.";
			}
		} catch (NumberFormatException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("selectFonteDados", fonteDados);
		request.setAttribute("selectResolucao", resolucao);
		request.setAttribute("edtConcorrencia", concorrencia);
		request.setAttribute("edtQuantidade", quantidade);
		request.setAttribute("edtConsole", msg);

		request.getRequestDispatcher("/Administrador.jsp").forward(request,
				response);
	}

	public String obterFilme(int quantidade, int concorrencia,
			String fonteDados, String resolucao) throws InterruptedException {
		long tempoIni = System.currentTimeMillis();

		Concorrencia con;
		Thread thread;
		List<Thread> listaThread = new ArrayList<Thread>();
		
		for(int i = 0; i < concorrencia; i++){
			con = new Concorrencia((i + 1), quantidade, resolucao, fonteDados, "Busca");
			thread = new Thread(con);
			thread.start();
			listaThread.add(thread);
		}
		
		Iterator<Thread> itr = listaThread.iterator();
		
		while(itr.hasNext()){
			itr.next().join();
		}

		long tempoFim = System.currentTimeMillis();

		long tempoProcessamento = tempoFim - tempoIni;

		String retorno = "Solicitação de Busca: Fonte de Dados = " + fonteDados
				+ ", Resolução = " + resolucao + "\n Concorrência = "
				+ concorrencia + ", Quantidade de busca = " + quantidade
				+ "\n Tempo gasto para processamento total = "
				+ tempoProcessamento + " milisegundos";

		return retorno;
	}

	public String inserirFilme(int quantidade, int concorrencia,
			String fonteDados, String resolucao) throws InterruptedException {
		long tempoIni = System.currentTimeMillis();

		Concorrencia con;
		Thread thread;
		List<Thread> listaThread = new ArrayList<Thread>();
		
		for(int i = 0; i < concorrencia; i++){
			con = new Concorrencia((i + 1), quantidade, resolucao, fonteDados, "Inserção");
			thread = new Thread(con);
			thread.start();
			listaThread.add(thread);
		}
		
		Iterator<Thread> itr = listaThread.iterator();
		
		while(itr.hasNext()){
			itr.next().join();
		}
		
		long tempoFim = System.currentTimeMillis();
		long tempoProcessamento = tempoFim - tempoIni;
		
		String retorno = "Solicitação de Inserção: Fonte de Dados = "
				+ fonteDados + ", Resolução = " + resolucao
				+ "\n Concorrência = " + concorrencia
				+ ", Quantidade de busca = " + quantidade
				+ "\n Tempo gasto para processamento total = "
				+ tempoProcessamento + " milisegundos";
		
		return retorno;
	}

	public String efetuarMedicoes(int quantidade, int concorrencia,
			String fonteDados, String resolucao) {
		long tempoIni = System.currentTimeMillis();
		long tempoFim = System.currentTimeMillis();

		long tempoProcessamento = tempoFim - tempoIni;

		String retorno = "Solicitação de Medições: Fonte de Dados = "
				+ fonteDados + ", Resolução = " + resolucao
				+ "\n Concorrência = " + concorrencia
				+ ", Quantidade de busca = " + quantidade
				+ "\n Tempo gasto para processamento total = "
				+ tempoProcessamento + " milisegundos";

		return retorno;
	}

	public String limparBase(String fonteDados, String resolucao) {
		long tempoIni = System.currentTimeMillis();

		Video video = new Video(resolucao, fonteDados);
		video.limpar();

		long tempoFim = System.currentTimeMillis();

		long tempoProcessamento = tempoFim - tempoIni;

		String retorno = "Solicitação de limpeza de base: Fonte de Dados = "
				+ fonteDados + ", Resolução = " + resolucao
				+ "\n Tempo gasto para processamento total = "
				+ tempoProcessamento + " milisegundos";

		return retorno;
	}
	
	private class Concorrencia implements Runnable {

		private int id;
		private int quantidade;
		private String resolucao;
		private String fonteDados;
		private String operacao;
		
		public Concorrencia(int id, int quantidade, String resolucao, String fonteDados, String operacao){
			this.id = id;
			this.quantidade = quantidade;
			this.resolucao = resolucao;
			this.fonteDados = fonteDados;
			this.operacao = operacao;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Video video;

			for (int i = 0; i < quantidade; i++) {
				
				long tempoIni = System.currentTimeMillis();
				
				video = new Video(resolucao, fonteDados);
				if(operacao.equals("Inserção"))
					video.inserir();
				else
					video.buscar();
				
				long tempoFim = System.currentTimeMillis();
				long tempoProcessamento = tempoFim - tempoIni;
				
				StringBuilder str = new StringBuilder();
				str.append("Thread;" + this.id + ";");
				str.append(operacao + ";" + i + ";");
				str.append("Tempo;" + tempoProcessamento + ";");
				
				try {
					System.out.println(str.toString());
					saveLog(str.toString(), fonteDados);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		private void saveLog(String content, String fonteDados) throws IOException{
			String fileName = (new Util().getValueByName("files", "output", "path")) + File.separator + fonteDados + ".txt";
			File file = new File(fileName);

			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
			bw.write(content+"\n");
			bw.close();
		}

	}
}
