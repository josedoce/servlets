package app.session.usandoformulario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class FormSessionServlet
 */
public class FormSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String userName = request.getParameter("userName");
		pw.print("<form action='FormSessionServlet2'>");  
	    pw.print("<input type='hidden' name='uname' value='"+userName+"'>");  
	    pw.print("<input type='submit' value='go'>");  
	    pw.print("</form>");  
		pw.close();
		
	}

}
