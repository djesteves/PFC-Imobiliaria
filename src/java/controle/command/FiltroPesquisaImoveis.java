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
public class FiltroPesquisaImoveis implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        String nroquartos = request.getParameter("selectquartos");
        String vlrimovel = request.getParameter("selectvalor");
        
        int quartos = 0;
        double vlr = 0;

        if (nroquartos != null) {
            quartos = Integer.parseInt(nroquartos);
        }

        if (nroquartos != null) {
            vlr = Double.parseDouble(vlrimovel);
        }

        ImovelDAO dao = new ImovelDAO();
        List<Imovel> imovel = dao.listarImoveis("Filtro", quartos, vlr);
        request.setAttribute("listaImoveis", imovel);
        return "catalogoimoveis.jsp";

    }

}
