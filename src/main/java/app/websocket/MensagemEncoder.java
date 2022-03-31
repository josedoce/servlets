package app.websocket;

import com.google.gson.Gson;

import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;

public class MensagemEncoder implements Encoder.Text<Mensagem> {
	private static Gson gson = new Gson();
	@Override
	public String encode(Mensagem object) throws EncodeException {
		return gson.toJson(object);
	}

}
