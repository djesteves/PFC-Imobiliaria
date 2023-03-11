/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class AlterarSenha implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

        Usuario usuario = new Usuario();

        String senha = request.getParameter("senha");
        String resenha = request.getParameter("resenha");

        if (!senha.equalsIgnoreCase(resenha)) {
            request.setAttribute("msgerro", "As senhas não coincidem!");
            return "usuario/AlterarSenha.jsp";
        } else {
            usuario.setId_usuario(Integer.parseInt(session.get("id").toString()));
            usuario.setSenha(Usuario.criptografia(senha));

            UsuarioDAO dao = new UsuarioDAO();

            try {
                dao.alterarSenha(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(AlterarSenha.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.setAttribute("msg", "A senha foi alterada, realize o login novamente!");
            request.getSession().removeAttribute("usuarioLogado");
            return "index.jsp";
        }
    }
}
