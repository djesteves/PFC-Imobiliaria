package Controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Modelo.Usuario;
import Dao.UsuarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Login;
import Modelo.Sessao;

public class Logar implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("loginmail");
            String senha = Login.criptografia(request.getParameter("loginsenha"));

            Usuario usuario = new Usuario();
            usuario.getLogin().setEmail(email);
            usuario.getLogin().setSenha(senha);

            UsuarioDAO dao = new UsuarioDAO();

            Sessao sessao = dao.logar(usuario);

            if (sessao != null) {
                request.getSession(true).setAttribute("usuarioLogado", sessao);
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "Usúario ou senha inválidos");
                return "index.jsp";
            }
        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
