/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.UsuarioDAO;
import modelo.Sessao;
import modelo.Usuario;
import util.Criptografia;

/**
 *
 * @author tr0j4nh4x
 */
public class AlterarSenha implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        HttpSession usuarioLogado = request.getSession();
        Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
        Usuario usuario = new Usuario();

        String senha = request.getParameter("senha");
        String resenha = request.getParameter("resenha");

        if (!senha.equalsIgnoreCase(resenha)) {
            request.setAttribute("msgerro", "As senhas não coincidem!");
            return "Usuario/AlterarSenhaUsuario.jsp";
        } else {
            try {
                usuario.getLogin().setSenha(Criptografia.criptografia(senha));
                               
                UsuarioDAO dao = new UsuarioDAO();
                boolean sucesso = dao.AlterarSenha(sessao.getId_usuario(), usuario);
                if (sucesso) {
                    request.setAttribute("msg", "A senha foi alterada, realize o login novamente!");
                    request.getSession().removeAttribute("usuarioLogado");
                } 
                return "index.jsp";

            } catch (SQLException ex) {
                Logger.getLogger(AlterarSenha.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("msgerro", ex.getMessage());
                return "erro.jsp";
            }

        }
    }

}
