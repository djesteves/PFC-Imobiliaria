
package controle.command;

import controle.Command;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.DAO.UsuarioDAO;

public class UsuarioListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        ResultSet rs = null;
        try {
            HttpSession usuarioLogado = request.getSession();
            Usuario usuario = (Usuario) usuarioLogado.getAttribute("usuarioLogado");
            
            int id = usuario.getId_usuario();
            
            UsuarioDAO dao = new UsuarioDAO();

            rs = dao.listar(id);

            request.setAttribute("rsUser", rs);
            return "Usuario/GerenciarUsuario.jsp";
        } catch (Exception ex) {
            Logger.getLogger(UsuarioListar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }

}
