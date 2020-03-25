/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DataAccess;

/**
 *
 * @author Diieg
 */
public class AdminDAO {

    private final String SELECT_USUARIOS = "SELECT U.id_usuario, nome, cpf_cnpj, nivel_acesso, l.situacao FROM "
            + "Login L LEFT JOIN Usuario U ON U.id_usuario = l.id_usuario "
            + "  WHERE l.situacao = 'Ativo'"
            + " ORDER BY nivel_acesso";

    private final String UPDATE_SITUACAO = "UPDATE Login SET situacao = ?"
            + " WHERE id_usuario = ?";


    public ResultSet listarUsuarios() throws SQLException, Exception {
        ResultSet rs;

        try (Connection connection = DataAccess.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(SELECT_USUARIOS);
          
            rs = smt.executeQuery();

            connection.close();

        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rs;
    }
    
    public boolean AlterarSituacaoUsuario(int id, HttpServletRequest request, HttpServletResponse response)  {
        String msg = "";
        try (Connection connection = DataAccess.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(UPDATE_SITUACAO);

            smt.setString(1, "Inativo");
            smt.setInt(2, id);
  
            smt.executeUpdate();

            msg = "Usuário removido com sucesso!";

        } catch (Exception ex) {
            Logger.getLogger(AdminDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        request.setAttribute("msg", msg);
        return true;

    }
    

}
