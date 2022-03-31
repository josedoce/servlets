package app.websocket;


import com.google.gson.Gson;

import jakarta.websocket.DecodeException;
import jakarta.websocket.Decoder;

public class MensagemDecoder implements Decoder.Text<Mensagem>{
	private static Gson gson = new Gson();
	@Override
	public Mensagem decode(String s) throws DecodeException {
		
		return gson.fromJson(s, Mensagem.class);
	}

	@Override
	public boolean willDecode(String s) {
		
		return (s != null);
	}

}
