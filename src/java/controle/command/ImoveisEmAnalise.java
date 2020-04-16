/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;

/**
 *
 * @author Diego
 */
public class ImoveisEmAnalise implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            ImovelDAO dao = new ImovelDAO();

            List<Imovel> imoveis = dao.imoveisEmAnalise();

            request.setAttribute("ImoveisEmAnalise", imoveis);
            return "Funcionario/ImoveisAguardandoAprovacao.jsp";
            
        } catch (SQLException ex) {
            Logger.getLogger(ImoveisEmAnalise.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }

}
