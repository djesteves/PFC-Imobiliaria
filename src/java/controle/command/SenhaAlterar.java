/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
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
public class SenhaAlterar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        HttpSession usuarioLogado = request.getSession();
        Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
        Usuario usuario = new Usuario();
        
        String senha = request.getParameter("senha");
        String resenha = request.getParameter("resenha");

        if (!senha.equalsIgnoreCase(resenha)) {
            request.setAttribute("msgerro", "As senhas não coincidem!");
            return "erro.jsp";
        } else {
            usuario.getLogin().setSenha(Criptografia.criptografia(senha));
            request.getSession().removeAttribute("usuarioLogado");
            UsuarioDAO dao = new UsuarioDAO();
            dao.AlterarSenha(sessao.getId_usuario());
            request.setAttribute("msgerro", "A senhas foi alterada, logue novamente!");
            return "index.jsp";
        }
    }

}
