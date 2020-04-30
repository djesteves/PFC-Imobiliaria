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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Perfil;
import modelo.Usuario;
import util.ConnectionFactory;

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

    public List<Usuario> listarUsuarios() throws SQLException, Exception {

        try (Connection connection = ConnectionFactory.getConexao()) {
            List<Usuario> listUsuario = new ArrayList<>();
            PreparedStatement smt = connection.prepareStatement(SELECT_USUARIOS);
            ResultSet resultSet = smt.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(resultSet.getInt("id_usuario"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setCpfcnpj(resultSet.getString("cpf_cnpj"));
                usuario.getLogin().setNivel(Perfil.valueOf(resultSet.getString("nivel_acesso")));
                usuario.getLogin().setSituacao(resultSet.getString("situacao"));
                listUsuario.add(usuario);
            }

            resultSet.close();
            smt.close();
            connection.close();

            return listUsuario;
        } catch (Exception ex) {
            System.err.println("Erro: "+ex);
            return null;
        }

    }

    public boolean excluirUsuario(int id, HttpServletRequest request, HttpServletResponse response) {
        String msg = "";
        try (Connection connection = ConnectionFactory.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(UPDATE_SITUACAO);

            smt.setString(1, "Inativo");
            smt.setInt(2, id);

            smt.executeUpdate();

            msg = "Usuário removido com sucesso!";

        } catch (Exception ex) {
            System.err.println("Erro: "+ex);
            return false;
        }

        request.setAttribute("msg", msg);
        return true;

    }

}
