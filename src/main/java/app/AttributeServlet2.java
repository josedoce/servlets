package app;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import app.events.servletcontextevent.Cidadao;

/**
 * Servlet implementation class AttributeServlet2
 */
public class AttributeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttributeServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		//pegando o atributo setado no contexto.
		ServletContext ctx = this.getServletContext();
		String user = ctx.getAttribute("logado").toString();
		String cidade = ctx.getAttribute("cidade").toString();
		Cidadao cidadao = (Cidadao) ctx.getAttribute("cidadao");
		Integer totalusers = (Integer) ctx.getAttribute("totalusers");
		Integer currentusers = (Integer) ctx.getAttribute("currentusers");
		
		pw.println("Bem vindo ao dashboard <strong>"+user+"</strong>");
		pw.println("<br>de: "+cidade);
		pw.println("<br>nome: "+cidadao.getNome());
		pw.println("<br>idade: "+cidadao.getIdade());
		pw.println("<br>sessões de usuarios feitas: "+ totalusers);
		pw.println("<br>usuários atuais: "+currentusers);
		
		pw.close();
	}

}
