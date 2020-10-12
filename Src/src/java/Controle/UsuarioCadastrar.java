package Controle;

import Modelo.Usuario;
import Dao.UsuarioDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import Modelo.Perfil;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioCadastrar implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        //Endereço
        String logradouro = request.getParameter("logradouro");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");

        //Usuario
        String name = request.getParameter("name");
        String tppessoa = request.getParameter("tipo_pessoa");
        String cpfcnpj = request.getParameter("cpfcnpj");
        String rg = request.getParameter("rg");
        String tel_celular = request.getParameter("tel_celular");
        String tel_residencial = request.getParameter("tel_residencial");

        //Login
        String mail = request.getParameter("mail");
        String senha = Usuario.criptografia(request.getParameter("senha"));
        String nivel = request.getParameter("nivel");

        if (nivel == "" || nivel == null) {
            nivel = "USUARIO";
        }

        //Endereço
        Usuario usuario = new Usuario();
        usuario.getEndereco().setLogradouro(logradouro);
        usuario.getEndereco().setNumero(numero);
        usuario.getEndereco().setComplemento(complemento);
        usuario.getEndereco().setCidade(cidade);
        usuario.getEndereco().setEstado(estado);
        usuario.getEndereco().setCep(cep);
        usuario.getEndereco().setBairro(bairro);

        //Usuario
        usuario.setNome(name);
        usuario.setCpfcnpj(cpfcnpj);
        usuario.setRg(rg);
        usuario.setTipoPessoa(tppessoa);
        usuario.setTel_celular(tel_celular);
        usuario.setTel_residencial(tel_residencial);
        usuario.setSituacao("Ativo");

        //Login
        usuario.setEmail(mail);
        usuario.setSenha(senha);
        usuario.setNivel(Perfil.valueOf(nivel));

        UsuarioDAO dao = new UsuarioDAO();

        try {
            if (dao.cadastrar(usuario)) {
                request.setAttribute("msg", "Usuário cadastrado com sucesso!");
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "EMAIL ou CPF/CNPJ já cadastrado em nosso sistema, utilize o botão 'Recuperar senha'");
                return "index.jsp";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCadastrar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
