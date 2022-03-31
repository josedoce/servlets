package app.pdf;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Usaremos a o pacote darwinSys para fazer os pdfs
 * http://darwinsys.com/darwinsys-api/
 * 
 * um tutorial bacaninha
 * https://howtodoinjava.com/java/library/read-generate-pdf-java-itext
 */
public class CriandoPdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;filename='resuminho.pdf'");
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD, new BaseColor(30,144,255));
			var paragrafo1 = new Paragraph("Anime legal, anime de milhões! Recomendo.",font);
			
			document.add(paragrafo1);
			String filename = "dorohedoro.jpg";
			Image image = Image.getInstance("/home/josedoce/Pictures/estudos/"+filename);
			image.setAlt("uma imagem");
			//						   X	  Y
			image.setAbsolutePosition(100f,  320f);
			image.scaleAbsolute(400, 387);
			
			
			document.add(image);
			document.close();
		} catch(Exception e) {
			document.close();
			throw new RuntimeException(e);
		}
	}

}
