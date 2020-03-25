package controle.command;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import modelo.Usuario;
import modelo.DAO.UsuarioDAO;
import controle.Command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Criptografia;

public class Logar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            String email = request.getParameter("loginmail");
            String senha = Criptografia.criptografia(request.getParameter("loginsenha"));

            Usuario cliente = new Usuario();
            cliente.getLogin().setEmail(email);
            cliente.getLogin().setSenha(senha);

            UsuarioDAO dao = new UsuarioDAO();

            boolean autenticado = dao.logar(cliente);

            if (autenticado) {
                request.getSession(true).setAttribute("usuarioLogado", cliente);

                return "index.jsp";

            } else {
                request.setAttribute("msgerro", "Usúario ou senha inválidos");
                return "erro.jsp";
            }

        } catch (Exception ex) {
            Logger.getLogger(Logar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }
}
