package app.events.servletcontextevent;



import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class MeuOuvinte
 *
 */
public class MeuOuvinte implements ServletContextListener {
	Logger logger = Logger.getLogger(MeuOuvinte.class.getName());
    /**
     * Default constructor. 
     */
    public MeuOuvinte() {
        // TODO Auto-generated constructor stub
    }
	
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	//aqui podemos adicionar parametros ao servletContext durante o deploy
    	//isso é: adicionar um dado ao contexto da aplicação...
    	ServletContext ctx = sce.getServletContext();
    	ctx.setAttribute("cidade", "RECIFE");
    	//você pode colocar qualquer coisa, até um objeto se desejar.
    	
    	ctx.setAttribute("cidadao", new Cidadao("Maria", 21));
    	logger.info("[DEPLOYED]EVENTO OUVINTE FOI INICIADO...");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    	//tudo aqui será desfeito durante o undeploy
    	logger.warning("[UNDEPLOYED]EVENTO OUVINTE FOI FINALIZADO...");
    }
}
