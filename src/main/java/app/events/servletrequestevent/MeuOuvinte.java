package app.events.servletrequestevent;

import java.util.logging.Logger;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Quando se faz uma requisição, esse evento é disparado...
 * È quase um filtro :P na vdd ainda vou descobrir...
 * OBS: SAO EVENTOS DISPARADOS, NÃO AGEM COMO FILTRO...
 */
public class MeuOuvinte implements ServletRequestListener {
	private Logger logger = Logger.getLogger(MeuOuvinte.class.getName());
	
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		//DA PRA FAZER MUITAS PERIPECIAS AQUI, VOU ANALIZAR.
		//sre.getServletRequest() instanceof HttpServletRequest == true //VALIDE ISSO SE PUDER.
		String endpoint = request.getMethod();
		String path = request.getServletPath();
		
		logger.info("[REQUESTED]["+endpoint+" - "+path+"] REQUISIÇÃO FEITA.");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.warning("[UNREQUESTED] REQUISIÇÃO FINALIZADA.");
	}
	
}
