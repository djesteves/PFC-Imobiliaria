/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.UsuarioDAO;
import Modelo.Login;
import Modelo.Sessao;
import Modelo.Usuario;

/**
 *
 * @author Diego
 */
public class AlterarSenha implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            Usuario usuario = new Usuario();

            String senha = request.getParameter("senha");
            String resenha = request.getParameter("resenha");

            if (!senha.equalsIgnoreCase(resenha)) {
                request.setAttribute("msgerro", "As senhas não coincidem!");
                return "Usuario/AlterarSenhaUsuario.jsp";
            } else {
                usuario.getLogin().setSenha(Login.criptografia(senha));
                UsuarioDAO dao = new UsuarioDAO();
                if (dao.alterarSenha(sessao.getId_usuario(), usuario)) {
                    request.setAttribute("msg", "A senha foi alterada, realize o login novamente!");
                    request.getSession().removeAttribute("usuarioLogado");
                    return "index.jsp";
                } else {
                    request.setAttribute("msgerro", "Não foi possivel alterar a senha, tente novamente!");
                    return "Usuario/AlterarSenhaUsuario.jsp";
                }

            }
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
