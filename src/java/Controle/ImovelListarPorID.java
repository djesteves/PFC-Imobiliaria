/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.ImovelDAO;
import Modelo.Imovel;
import Modelo.Perfil;
import Modelo.Sessao;

/**
 *
 * @author Diego
 */
public class ImovelListarPorID implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            int id = Integer.parseInt(request.getParameter("id"));
            int idu = Integer.parseInt(request.getParameter("idu"));
            boolean autorizado = false;

            if (sessao.getNivel().equals(Perfil.ADMINISTRADOR)) {
                autorizado = true;
            }
            if (!sessao.getNivel().equals(Perfil.ADMINISTRADOR) && sessao.getId_usuario() == idu) {
                autorizado = true;
            }

            if (!autorizado) {
                request.setAttribute("msgerro", "Você não tem permissão para visualizar este Imóvel");
                return "index.jsp";
            } else {
                ImovelDAO dao = new ImovelDAO();

                Imovel imovel = dao.listarPorId(id);

                request.setAttribute("imovel", imovel);
                return "Usuario/FormImovel.jsp";
            }

        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(ImovelListarPorID.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
