package app.inputstream;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Servlet implementation class EnvioDeArquivoServlet
 */
public class EnvioDeArquivoServlet extends HttpServlet {
	//configuração do tamanho de arquivos e onde será salvo estão no web.xml
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		String imagem = "dorohedoro.jpg";
		String local = "/home/josedoce/Pictures/estudos/";
		ServletOutputStream output;
		output = response.getOutputStream();
		
		FileInputStream fin = new FileInputStream(local+imagem);
		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(output);
		int ch = 0;
		
		while((ch=bin.read())!=-1) {
			//escreva byte a byte o arquivo
			bout.write(ch);
		}
		bin.close();
		fin.close();
		bout.close();
		output.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("fileUpload"); //pega o arquivo em partes...
		String fileName = filePart.getSubmittedFileName(); //pega o nome do arquivo.
		
		for(Part part : request.getParts()) {
			part.write(fileName);
		}
		response.getWriter().append("Upload concluido.");
	}

}
