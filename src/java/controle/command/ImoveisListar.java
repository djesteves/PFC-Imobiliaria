/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;

/**
 *
 * @author Diego
 */
public class ImoveisListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImovelDAO dao = new ImovelDAO();
            List<Imovel> imovel = dao.listarImoveis("Todos", 0, 0);
            request.setAttribute("listaImoveis", imovel);
            return "catalogoimoveis.jsp";
        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }
}
