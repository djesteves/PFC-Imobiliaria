
package controle.command;

import controle.Command;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.DAO.UsuarioDAO;
import util.Criptografia;

public class UsuarioAlterar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        boolean atualizado = false;
        String msg = "";
        HttpSession usuarioLogado = request.getSession();
        Usuario session = (Usuario) usuarioLogado.getAttribute("usuarioLogado");

        try {
            //Area de edição
            String acao = request.getParameter("acao");
            request.setAttribute("acao", acao);
            Usuario usuario = new Usuario();
            usuario.setId_usuario(session.getId_usuario());

            if (acao.equalsIgnoreCase("usersenha")) {
                //Senha
                String senha = request.getParameter("senha");
                String resenha = request.getParameter("resenha");

                if (!senha.equalsIgnoreCase(resenha)) {
                    msg = "As senhas não coincidem!";
                    request.setAttribute("msgerro", msg);
                    return "erro.jsp";
                } else {
                    //Login
                    senha = Criptografia.criptografia(senha);
                    usuario.getLogin().setSenha(senha);
                    request.getSession().removeAttribute("usuarioLogado");
                }
            } else if (acao.equalsIgnoreCase("userdados")) {
                //Parameters
                String name = request.getParameter("name");
                String tel_celular = request.getParameter("tel_celular");
                String tel_residencial = request.getParameter("tel_residencial");
                String tppessoa = request.getParameter("inputtp");

                //Sets
                usuario.setNome(name);
                usuario.setTipoPessoa(tppessoa);
                usuario.setTel_celular(tel_celular);
                usuario.setTel_residencial(tel_residencial);

            } else if (acao.equalsIgnoreCase("userendereco")) {
                //Endereço

                String logradouro = request.getParameter("logradouro");
                int numero = Integer.parseInt(request.getParameter("numero"));
                String complemento = request.getParameter("complemento");
                String cidade = request.getParameter("cidade");
                String estado = request.getParameter("estado");
                String cep = request.getParameter("cep");
                String bairro = request.getParameter("bairro");

                //Endereço
                //if(true) throw new Exception("aq"+session.getEndereco().getId_endereco());
                usuario.getEndereco().setId_endereco(session.getEndereco().getId_endereco());
                usuario.getEndereco().setLogradouro(logradouro);
                usuario.getEndereco().setNumero(numero);
                usuario.getEndereco().setComplemento(complemento);

                usuario.getEndereco().setCidade(cidade);
                usuario.getEndereco().setEstado(estado);
                usuario.getEndereco().setCep(cep);
                usuario.getEndereco().setBairro(bairro);

            }

            UsuarioDAO dao = new UsuarioDAO();
            atualizado = dao.alterar(usuario, request, response);
            msg = (String) request.getAttribute("msg");

            if (atualizado) {
                //request.getSession().removeAttribute("usuarioLogado");
                request.setAttribute("msg", msg);
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", request.getAttribute("msgerro"));
                return "erro.jsp";
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioAlterar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }

}
