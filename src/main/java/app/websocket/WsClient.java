package app.websocket;

import java.io.IOException;
import java.net.URI;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;

//https://stackoverflow.com/questions/26452903/javax-websocket-client-simple-example
@ClientEndpoint(
decoders = MensagemDecoder.class,
encoders = MensagemEncoder.class
)
public class WsClient {
	
	Session userSession = null;
  private MessageHandler messageHandler;

  public WsClient(URI endpointURI) {
  	//aqui pegandos o container do websocket usando
      try {
          WebSocketContainer container = ContainerProvider.getWebSocketContainer();
          container.connectToServer(this, endpointURI);
      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }

  /**
   * Padrão do websocket
   * Isto acontece quando for feita uma conexão.
   */
  @OnOpen
  public void onOpen(Session userSession) {
      System.out.println("opening websocket");
      this.userSession = userSession;
  }

  /**
   * Padrão do websocket
   * Isto acontece quando for finalizado a conexão.
   */
  @OnClose
  public void onClose(Session userSession, CloseReason reason) {
      System.out.println("closing websocket");
      this.userSession = null;
  }

  /**
   * Padrão do websocket
   * Isto acontece quando enviarem uma mensagem.
   * @param message The text message
   */
  @OnMessage
  public void onMessage(Mensagem message) {
      if (this.messageHandler != null) {
          this.messageHandler.handleMessage(message);
      }
  }

  /**
   * responsavel por atribuir ao messageHandler o valor contendo o metodo
   * declarado na interface MessageHandler
   */
  public void addMessageHandler(MessageHandler msgHandler) {
      this.messageHandler = msgHandler;
  }

  /**
   * responsavel por enviar a menssagem
   */
  public void sendMessage(Mensagem message) {
      this.userSession.getAsyncRemote().sendObject(message);
  }
  
  /**
   * responsavel por finalizar a conexão
   * https://stackoverflow.com/questions/46450721/how-do-you-close-websocketcontainer-websocketclient-jetty-client-in-java
   * 
   */
  public void close() {
  	WebSocketContainer c = this.userSession.getContainer();
  		try {
  			if(c != null && c instanceof Lifecycle) {
  				((Lifecycle) c).stop();
  			}
				this.userSession.close();
				
			} catch (LifecycleException | IOException e) {
				throw new RuntimeException(e);
			}
  	
  }
  
  
  /**
   * MessageHandler
   * 
   * quem tiver essa interface, receberá um metodo como valor.
   */
  public static interface MessageHandler {
      public void handleMessage(Mensagem message);
  }
}
