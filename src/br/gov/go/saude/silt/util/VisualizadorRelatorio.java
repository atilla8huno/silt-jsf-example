package br.gov.go.saude.silt.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Átilla Barros
 * @version	$Rev: 176 $ $Author: atillabarros $ $Date: 2013-09-12 10:12:38 -0300 (Qui, 12 Set 2013) $
 * @category Servlet
 */
@WebServlet(
		name 		= "VisualizadorRelatorio",
		description = "Servlet responsável por exibir os relatórios jasper.", 
		urlPatterns = { 
				"/VisualizadorRelatorio", 
				"/VisualizaRelatorio",
				"/paginas/VisualizadorRelatorio",
				"/paginas/VisualizaRelatorio"
		})
public class VisualizadorRelatorio extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] bytes = (byte[]) request.getSession().getAttribute(Relatorio.SESSAO);

        if (bytes != null) {
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            response.setHeader("Content-Disposition", "inline;filename=\"relatorio.pdf\"");
            response.getOutputStream().write(bytes, 0, bytes.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();

            request.getSession().removeAttribute(Relatorio.SESSAO);
        }
	}
}
