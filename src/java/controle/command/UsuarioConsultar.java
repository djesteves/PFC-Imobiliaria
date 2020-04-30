package controle.command;

import controle.Command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.DAO.UsuarioDAO;
import modelo.Perfil;
import modelo.Sessao;

public class UsuarioConsultar implements Command {

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

                Usuario usuario = dao.getUsuario(id);

                request.setAttribute("usuario", usuario);
                return "Usuario/FormUsuario.jsp";
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(UsuarioConsultar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }

}
