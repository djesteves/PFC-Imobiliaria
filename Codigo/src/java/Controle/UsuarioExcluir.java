/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UsuarioDAO;

/**
 *
 * @author Diieg
 */
public class UsuarioExcluir implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        int id = Integer.parseInt(request.getParameter("id"));

        UsuarioDAO dao = new UsuarioDAO();

        try {
            dao.excluir(id);
            request.setAttribute("msg", "Usuário removido com sucesso!");
            return "index.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
