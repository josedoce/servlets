package app;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class TratandoServletConfig
 */
public class TratandoServletConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TratandoServletConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.umParametroInicializado(request, response);
		this.todosOsParametrosInicializados(request, response);
	}
	
	private void umParametroInicializado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		//pegará as configurações deste servlet, obs: tipo um parametro padrão de url 
		ServletConfig config = this.getServletConfig();
		
		String mode = config.getInitParameter("modo");
		pw.println(mode);
	}
	
	private void todosOsParametrosInicializados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		//pegará as configurações deste servlet, obs: tipo um parametro padrão de url 
		ServletConfig config = this.getServletConfig();
		
		//retorna todos os nomes de parametros
		Enumeration<String> e = config.getInitParameterNames();
		String name = "";
		while(e.hasMoreElements()) {
			name = e.nextElement();
			pw.println(name+": "+config.getInitParameter(name));
		}
		
		pw.close();
	}
	
	
}
