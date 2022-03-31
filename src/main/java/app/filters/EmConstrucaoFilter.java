package app.filters;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class EmConstrucaoFilter
 */
public class EmConstrucaoFilter extends HttpFilter {
    private FilterConfig config;
    

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		//pegou uma das informações contidas no tag <filter>
		String status = this.config.getInitParameter("status");
		if(status.equals("construction")) {
			pw.println("A pagina está em construção.");
			pw.close();
		}else {
			chain.doFilter(request, response);			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		//carrega as configurações do filtro declarado em web.xml
		this.config = config;
	}

}
