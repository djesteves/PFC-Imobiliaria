package controle.command;

import modelo.Usuario;
import modelo.DAO.UsuarioDAO;
import controle.Command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import modelo.Perfil;
import util.Criptografia;

public class UsuarioCadastrar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Endereço
            String logradouro = request.getParameter("logradouro");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");
            String bairro = request.getParameter("bairro");

            //Cliente
            String name = request.getParameter("name");
            String tppessoa = request.getParameter("tipo_pessoa");
            String cpfcnpj = request.getParameter("cpfcnpj");
            String rg = request.getParameter("rg");
            String tel_celular = request.getParameter("tel_celular");
            String tel_residencial = request.getParameter("tel_residencial");

            //Login
            String mail = request.getParameter("mail");
            String senha = Criptografia.criptografia(request.getParameter("senha"));

            //Endereço
            Usuario usuario = new Usuario();
            usuario.getEndereco().setLogradouro(logradouro);
            usuario.getEndereco().setNumero(numero);
            usuario.getEndereco().setComplemento(complemento);
            usuario.getEndereco().setCidade(cidade);
            usuario.getEndereco().setEstado(estado);
            usuario.getEndereco().setCep(cep);
            usuario.getEndereco().setBairro(bairro);

            //Cliente
            usuario.setNome(name);
            usuario.setCpfcnpj(cpfcnpj);
            usuario.setRg(rg);
            usuario.setTipoPessoa(tppessoa);
            usuario.setTel_celular(tel_celular);
            usuario.setTel_residencial(tel_residencial);

            //Login
            usuario.getLogin().setEmail(mail);
            usuario.getLogin().setSenha(senha);
            usuario.getLogin().setNivel(Perfil.USUARIO);

            UsuarioDAO dao = new UsuarioDAO();
            boolean cadastro = dao.cadastrar(usuario);

            if (cadastro) {
                request.setAttribute("msgcadastro", "Usuário cadastrado com sucesso!");
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "EMAIL ou CPF/CNPJ já cadastrado em nosso sistema, utilize o botão 'Recuperar senha'");
                return "erro.jsp";
            }

        } catch (NumberFormatException ex) {
            Logger.getLogger(UsuarioCadastrar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }

    }

}
