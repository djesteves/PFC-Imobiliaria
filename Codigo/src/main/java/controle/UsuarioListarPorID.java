package controle;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UsuarioListarPorID implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {

            int id = Integer.parseInt(request.getParameter("id"));

            UsuarioDAO dao = new UsuarioDAO();

            Usuario usuario = dao.listarPorID(id);

            request.setAttribute("usuario", usuario);
            return "usuario/FormUsuario.jsp";

        } catch (SQLException | NumberFormatException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
