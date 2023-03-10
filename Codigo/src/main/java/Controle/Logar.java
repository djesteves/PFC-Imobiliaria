package Controle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dao.UsuarioDAO;
import Modelo.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Logar implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            String email = request.getParameter("loginmail");
            String senha = Usuario.criptografia(request.getParameter("loginsenha"));

            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);

            UsuarioDAO dao = new UsuarioDAO();

            Boolean logado = dao.logar(usuario);

            HttpSession session = request.getSession(true);

            if (logado) {
                Map<String, Object> usuarioLogado = new HashMap<>();
                usuarioLogado.put("id", usuario.getId_usuario());
                usuarioLogado.put("nome", usuario.getNome());
                usuarioLogado.put("email", usuario.getEmail());
                usuarioLogado.put("nivel", usuario.getNivel());
                usuarioLogado.put("situacao", usuario.getSituacao());

                session.setAttribute("usuarioLogado", usuarioLogado);

                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "Usúario ou senha inválidos");
                return "login.jsp";
            }
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
