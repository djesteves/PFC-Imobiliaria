/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ImovelDAO;
import Modelo.Imovel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


/**
 *
 * @author Diego
 */
public class ImovelExcluir implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Imovel imovel = new Imovel();
        imovel.setId_imovel(Integer.parseInt(request.getParameter("id")));
        imovel.setSituacao("Inativo");
        
        ImovelDAO dao = new ImovelDAO();

        try {
            dao.excluir(imovel);
            request.setAttribute("msg", "Imóvel deletado com sucesso!");
            return "index.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
