/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ImovelDAO;
import modelo.Imovel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Diego
 */
public class ImovelEmAnalise implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            ImovelDAO dao = new ImovelDAO();

            List<Imovel> imoveis = dao.emAnalise();

            request.setAttribute("ImoveisEmAnalise", imoveis);
            return "corretor/ImoveisAguardandoAprovacao.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
