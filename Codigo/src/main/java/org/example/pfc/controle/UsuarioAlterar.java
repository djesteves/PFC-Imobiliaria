package org.example.pfc.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.pfc.dao.UsuarioDAO;
import org.example.pfc.modelo.Usuario;

import java.sql.SQLException;

public class UsuarioAlterar implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            //Login
            String mail = request.getParameter("mail");

            String nome = request.getParameter("name");
            String tel_celular = request.getParameter("tel_celular");
            String tel_residencial = request.getParameter("tel_residencial");
            String tppessoa = request.getParameter("inputtp");

            Usuario usuario = new Usuario();
            usuario.setId_usuario(id);
            usuario.setNome(nome);
            usuario.setTipoPessoa(tppessoa);
            usuario.setTel_celular(tel_celular);
            usuario.setTel_residencial(tel_residencial);
            usuario.setEmail(mail);

            String logradouro = request.getParameter("logradouro");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");
            String bairro = request.getParameter("bairro");

            usuario.getEndereco().setId_endereco(Integer.parseInt(request.getParameter("ide")));
            usuario.getEndereco().setLogradouro(logradouro);
            usuario.getEndereco().setNumero(numero);
            usuario.getEndereco().setComplemento(complemento);
            usuario.getEndereco().setCidade(cidade);
            usuario.getEndereco().setEstado(estado);
            usuario.getEndereco().setCep(cep);
            usuario.getEndereco().setBairro(bairro);

            UsuarioDAO dao = new UsuarioDAO();

            dao.alterar(usuario);
            request.setAttribute("msg", "Dados alterados com sucesso!");
            return "index.jsp";

        } catch (SQLException | NumberFormatException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
