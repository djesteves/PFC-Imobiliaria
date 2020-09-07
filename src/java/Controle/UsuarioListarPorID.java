package Controle;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Modelo.Usuario;
import Dao.UsuarioDAO;
import Modelo.Perfil;
import Modelo.Sessao;

public class UsuarioListarPorID implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            int id = Integer.parseInt(request.getParameter("id"));
            boolean autorizado = false;
            
            if(sessao.getNivel().equals(Perfil.ADMINISTRADOR)){
                autorizado = true;   
            }
            if (!sessao.getNivel().equals(Perfil.ADMINISTRADOR) && sessao.getId_usuario() == id) {
                autorizado = true;   
            }
            
            if (!autorizado) {
                request.setAttribute("msgerro", "Você não tem permissão para visualizar este Usuário");
                return "index.jsp";
            } else {
                UsuarioDAO dao = new UsuarioDAO();

                Usuario usuario = dao.listarPorID(id);

                request.setAttribute("usuario", usuario);
                return "Usuario/FormUsuario.jsp";
            }
        } catch (SQLException | NumberFormatException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
