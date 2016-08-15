package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entity.Video;

@WebServlet("/Publico")
public class PublicoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("edtConsole", "");
		request.getRequestDispatcher("/Publico.jsp").forward(request, response);
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

		if (acao.equals("btnConsultar")) {
			msg = obterFilme(Integer.parseInt(quantidade),
					Integer.parseInt(concorrencia), fonteDados, resolucao);
		} else if (acao.equals("btnMedir")) {
			msg = efetuarMedicoes(Integer.parseInt(quantidade),
					Integer.parseInt(concorrencia), fonteDados, resolucao);
		} else {
			msg = "Nenhuma operação foi realizada.";
		}

		request.setAttribute("selectFonteDados", fonteDados);
		request.setAttribute("selectResolucao", resolucao);
		request.setAttribute("edtConcorrencia", concorrencia);
		request.setAttribute("edtQuantidade", quantidade);
		request.setAttribute("edtConsole", msg);

		request.getRequestDispatcher("/Publico.jsp").forward(request, response);
	}

	public String obterFilme(int quantidade, int concorrencia,
			String fonteDados, String resolucao) {
		long tempoIni = System.currentTimeMillis();

		Video video;

		for (int i = 0; i < quantidade; i++) {
			video = new Video(resolucao, fonteDados);
			video.buscar();
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
}
