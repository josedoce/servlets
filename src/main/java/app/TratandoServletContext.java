package app;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class Main
 */
public class TratandoServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TratandoServletContext() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.pegandoUmValorDoContexto(request, response);
		this.pegandoVariosValoresDoContexto(request, response);
	}
	
	private void pegandoUmValorDoContexto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet context
		PrintWriter pw = response.getWriter();
		ServletContext ctx = getServletContext();
		String key = "api_version";
		String s =  ctx.getInitParameter(key);
		pw.println(key+": "+s);
	}
	
	private void pegandoVariosValoresDoContexto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//servlet context
		PrintWriter pw = response.getWriter();
		ServletContext ctx = getServletContext();
		Enumeration<String> e = ctx.getInitParameterNames();
		String s =  "";
		while(e.hasMoreElements()) {
			s = e.nextElement();
			if(!s.equals("api_version")) {
				pw.println(s+": "+ctx.getInitParameter(s));				
			}
			
		}
		pw.close();
	}
}
