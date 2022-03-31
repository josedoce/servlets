package app.websocket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;

import org.apache.tomcat.websocket.WsWebSocketContainer;

/**
 * rota do ws http://localhost:8080/java-websocket/
 */
@ServerEndpoint(value="/chat/{username}",
decoders = MensagemDecoder.class, //essa parte é interessante.
encoders = MensagemEncoder.class
)
public class Ws {
	private static final long serialVersionUID = 1L;
	private Session session;
	private static Set<Ws> wss = new CopyOnWriteArraySet<>();
	private static HashMap<String, String> users = new HashMap<>();
	
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		System.out.println("conexão ws estabelecida");
		//pega a sessão e conexão websocket 
		this.session = session;
		wss.add(this); //adicionamos a instancia contendo os dados de conexão do usuário.
		users.put(session.getId(), username);//salvamos o usuário na lista de usuários.
		
		//criamos a mensagem.
		Mensagem message = new Mensagem();
		message.setFrom(username);
		message.setContent("Conectado com sucesso!");
		broadcast(message);
	}
	
	@OnMessage
	public void onMessage(Session session, Mensagem mensagem) throws IOException {
		System.out.println("enviou mensagem");
		//lidando com mensagem
		//na lista de usuários encontre um com este determinado id
		mensagem.setFrom(users.get(session.getId()));
		broadcast(mensagem);
	}
	
	@OnClose
	public void onClose(Session session) throws IOException {
		//fechando conexão websocket 
		wss.remove(this);
		System.out.println("desconectou");
		Mensagem message = new Mensagem();
		message.setFrom(users.get(session.getId()));
		message.setContent("Desconectado com sucesso!");
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable th) throws IOException {
		//trate o erro aqui como desejar.
	}
	
	private static void broadcast(Mensagem msg) {
		//basicamente está transmitindo a informção para todos os 
		//usuários conectados na lista.
		wss.forEach(endpoint -> {
			//para cada usuário, envie uma menssage.
			synchronized (endpoint) {
				try {
					
					endpoint.session.getBasicRemote()
					.sendObject(msg);							
					
				} catch (IOException | EncodeException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
