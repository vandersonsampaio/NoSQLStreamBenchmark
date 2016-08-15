package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Publico")
public class PublicoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fonteDados = request.getParameter("selectFonteDados");
		String resolucao = request.getParameter("selectResolucao");
		String concorrencia = request.getParameter("edtConcorrencia");
		String quantidade = request.getParameter("edtQuantidade");
		
		response.setContentType("text/html");
	      // Write to network
	      PrintWriter out = response.getWriter();
	 
	      // Your servlet's logic here
	      out.println("<html>");
	      out.println("<h3>" + fonteDados + "</h3></br>");
	      out.println("<h3>" + resolucao + "</h3></br>");
	      out.println("<h3>" + concorrencia + "</h3></br>");
	      out.println("<h3>" + quantidade + "</h3></br>");
	      out.println("</html>");
	}
	
	public Byte[] obterFilme(){
		return null;
	}
	
	public boolean inserirFilme(){
		return true;
	}
	
	public void efetuarMedicoes(){
		
	}
}
