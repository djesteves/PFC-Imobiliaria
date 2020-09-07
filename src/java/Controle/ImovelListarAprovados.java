/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.ImovelDAO;
import Modelo.Imovel;

/**
 *
 * @author Diego
 */
public class ImovelListarAprovados implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            ImovelDAO dao = new ImovelDAO();

            String array = request.getParameter("selectquartos") + ";" + request.getParameter("selectvalor");
            String pesquisa[] = new String[2];
            pesquisa = array.split(";");

            List<Imovel> imovel = dao.listarAprovados(pesquisa);

            request.setAttribute("listaImoveis", imovel);
            return "catalogoimoveis.jsp";
        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
