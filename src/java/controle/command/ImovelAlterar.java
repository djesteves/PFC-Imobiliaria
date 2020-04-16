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
import modelo.DAO.ImovelDAO;
import modelo.Imovel;
import modelo.Perfil;
import modelo.Sessao;

/**
 *
 * @author tr0j4nh4x
 */
public class ImovelAlterar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        HttpSession usuarioLogado = request.getSession();
        Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
        boolean autorizado = false;

        if (sessao.getNivel().equals(Perfil.ADMINISTRADOR)) {
            autorizado = true;
        }
        if (!sessao.getNivel().equals(Perfil.ADMINISTRADOR) && sessao.getId_usuario() == Integer.parseInt(request.getParameter("idu"))) {
            autorizado = true;
        }

        if (!autorizado) {
            request.setAttribute("msgerro", "Você não tem permissão para editar este Imóvel");
            return "erro.jsp";
        } else {
            try {
                boolean atualizado = false;
                Imovel imovel = new Imovel();
                
                imovel.setId_imovel(Integer.parseInt(request.getParameter("id")));
                imovel.setArea_edificada(Double.parseDouble(request.getParameter("areaedificada")));
                imovel.setArea_total(Double.parseDouble(request.getParameter("areatotal")));
                imovel.setBanheiros(Integer.parseInt(request.getParameter("banheiro")));
                imovel.setComodos(Integer.parseInt(request.getParameter("comodos")));
                imovel.setDescricao(request.getParameter("descricao"));
                imovel.setTipo_imovel(request.getParameter("tpimovel"));
                imovel.setTitulo(request.getParameter("titulo"));
                imovel.setVagas_garagem(Integer.parseInt(request.getParameter("garagem")));
                imovel.setValor(Double.parseDouble(request.getParameter("valorimovel")));

                imovel.getEndereco().setId_endereco(Integer.parseInt(request.getParameter("ide")));
                imovel.getEndereco().setLogradouro(request.getParameter("logradouro"));
                imovel.getEndereco().setNumero(Integer.parseInt(request.getParameter("numero")));
                imovel.getEndereco().setComplemento(request.getParameter("complemento"));
                imovel.getEndereco().setCidade(request.getParameter("cidade"));
                imovel.getEndereco().setEstado(request.getParameter("estado"));
                imovel.getEndereco().setCep(request.getParameter("cep"));
                imovel.getEndereco().setBairro(request.getParameter("bairro"));

                ImovelDAO dao = new ImovelDAO();
                atualizado = dao.alterar(imovel);

                if (atualizado) {
                    request.setAttribute("msg", "Dados atualizados com sucesso!");
                    return "index.jsp";
                } else {
                    request.setAttribute("msgerro", "Não foi possivel atualizar os dados, tente novamente após alguns minutos!");
                    return "erro.jsp";
                }
            } catch (NumberFormatException | SQLException ex) {
                Logger.getLogger(ImovelAlterar.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("msgerro", ex.getMessage());
                return "erro.jsp";
            }
        }
    }

}
