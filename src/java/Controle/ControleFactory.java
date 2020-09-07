package Controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Controle/*"})
@MultipartConfig
public class ControleFactory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //recupera a URL da requisição
            String url = request.getRequestURI();

            url = url.replace(request.getContextPath() + "/Controle/", "").replaceAll("/", ".");

            //usa Java Reflection para invocar a classe dinamicamente com o nome da URL
            String nomeDaClasse = "Controle." + url;
            
            

            Class classeAction = Class.forName(nomeDaClasse);
            ICommand commandAction = (ICommand) classeAction.newInstance();

            //Executa o comando e redireciona a resposta
            String urlRetorno = commandAction.executar(request, response);
            System.err.println(urlRetorno);

            request.getRequestDispatcher("/" + urlRetorno).forward(request, response);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | ServletException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
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
