package app.events.httpsessionevent;

import java.util.logging.Logger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Este evento funciona em tempo real.
 * 
 *
 */
public class MeuOuvinte implements HttpSessionListener {
	private Logger logger = Logger.getLogger(MeuOuvinte.class.getName());
	private ServletContext ctx = null;
	private static int total= 0, atual= 0;
	
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	//quando uma sessão for criada:
    	total++;
    	atual++;
    	
    	//pegue o serveletContext desta sessão
    	ctx = se.getSession().getServletContext();
    	
    	ctx.setAttribute("totalusers", total);
    	ctx.setAttribute("currentusers", atual);
    	
    	logger.info("[SESSION CREATED]SESSÃO CRIADA");
    }
	
    public void sessionDestroyed(HttpSessionEvent se) {
    	//quando fecharem uma sessão
    	atual--;
    	ctx.setAttribute("currentusers", atual);
    	logger.info("[SESSION DESTROYED]SESSÃO FINALIZADA");
    }
}
