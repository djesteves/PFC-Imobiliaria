package controle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/controle/*"})
@MultipartConfig
public class ControleFactory extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //recupera a URL da requisição
            String url = request.getRequestURI();

            url = url.replace(request.getContextPath() + "/controle/", "").replaceAll("/", ".");
         
            //usa Java Reflection para invocar a classe dinamicamente com o nome da URL
            String nomeDaClasse = "controle.command." + url;
            

            Class classeAction = Class.forName(nomeDaClasse);
            Command commandAction = (Command) classeAction.newInstance();

            //Executa o comando e redireciona a resposta
            String urlRetorno = commandAction.executar(request, response);

            request.getRequestDispatcher("/" + urlRetorno).forward(request, response);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | ServletException ex) {
            Logger.getLogger(ControleFactory.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            request.getRequestDispatcher("/erro.jsp").forward(request, response);

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
