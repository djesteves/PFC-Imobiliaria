package org.example.pfc.controle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/controle/*" })
@MultipartConfig
public class ControleFactory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // recupera a URL da requisição
            String url = request.getRequestURI();

            url = url.replace(request.getContextPath() + "/controle/", "").replaceAll("/", ".");

            // usa Java Reflection para invocar a classe dinamicamente com o nome da URL
            String nomeDaClasse = "org.example.pfc.controle." + url;

            Class<?> classeAction = Class.forName(nomeDaClasse);
            ICommand commandAction = (ICommand) classeAction.getDeclaredConstructor().newInstance();

            // Executa o comando e redireciona a resposta
            String urlRetorno = commandAction.executar(request, response);

            request.getRequestDispatcher("/" + urlRetorno).forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
