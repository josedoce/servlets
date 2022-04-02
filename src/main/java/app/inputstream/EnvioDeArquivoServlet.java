package app.inputstream;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


/**
 * Servlet implementation class EnvioDeArquivoServlet
 */
public class EnvioDeArquivoServlet extends HttpServlet {
	//configuração do tamanho de arquivos e onde será salvo estão no web.xml
	private static final long serialVersionUID = 1L;
	/**
	 * https://javacodepoint.com/ajax-file-upload-with-advance-progress-bar-in-java/
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part filePart = request.getPart("multipartFile"); //pega o arquivo em partes...
		String fileName = filePart.getSubmittedFileName(); //pega o nome do arquivo.
		
		for(Part part : request.getParts()) {
			part.write(fileName);
		}
		response.getWriter().append("Upload concluido.");
	}
}
