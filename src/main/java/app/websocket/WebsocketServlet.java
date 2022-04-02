package app.websocket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Servlet implementation class WebsocketServlet
 */
public class WebsocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebsocketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		this.wsc();
		response.getWriter().append("Requisição feita.");
		
	}
	//o cliente websocket é capaz de emitir e receber eventos
	private void wsc() {
		try {
			//mesmo processo do navegador, só que no java
			final WsClient websocketClient = new WsClient(new URI("ws://localhost:8080/Estudos/chat/servidor"));
			
			//adicionando o evento
			websocketClient.addMessageHandler(new WsClient.MessageHandler() {
				
				@Override
				public void handleMessage(Mensagem message) {
					System.out.println(message.getFrom()+" enviou mensagem.");
					
				}
			});
			Mensagem msg = new Mensagem();
			msg.setFrom("servidor");
		
			msg.setContent("olá, tudo bem ?");
			websocketClient.sendMessage(msg);
			websocketClient.close();	
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		
	}

}
