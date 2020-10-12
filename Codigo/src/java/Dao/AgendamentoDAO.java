/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Agenda;
import Modelo.Usuario;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diego
 */
public class AgendamentoDAO {

    Date data = new Date();
    Timestamp timestamp = new Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    public List<Usuario> listarCorretores() throws SQLException {

        String queryCorretores = "SELECT * FROM Usuario U "
                + " INNER JOIN Endereco E ON E.id_endereco = U.id_endereco"
                + " WHERE u.nivel_acesso = 'CORRETOR'";

        List<Usuario> corretores = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryCorretores);
        rs = smt.executeQuery();

        while (rs.next()) {

            Usuario usuario = new Usuario();

            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setNome(rs.getString("nome"));

            corretores.add(usuario);

        }

        return corretores;

    }

    public void Agendar(Agenda agenda) throws SQLException {

        String insertAgendamento = "INSERT INTO Agendamento VALUES (DEFAULT,?,?,?,?,?,?)";
        String insertAgenda = "INSERT INTO Agenda VALUES (DEFAULT,?,?)";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(insertAgendamento, Statement.RETURN_GENERATED_KEYS);
        
        Date dataAgendamento = agenda.getAgendamento().getDataAgendamento();
        Timestamp dataParaGravar = new Timestamp(dataAgendamento.getTime());

        smt.setTimestamp(1, dataParaGravar);
        smt.setTimestamp(2, timestamp);
        smt.setString(3, agenda.getAgendamento().getStatus());
        smt.setString(4,agenda.getAgendamento().getSituacao());
        smt.setInt(5, agenda.getAgendamento().getUsuario().getId_usuario());
        smt.setInt(6, agenda.getAgendamento().getImovel().getId_imovel());
        smt.execute();

        rs = smt.getGeneratedKeys();
        rs.next();
        int idAgendamento = rs.getInt(1);

        smt = connection.prepareStatement(insertAgenda);
        smt.setInt(1, agenda.getUsuarioCorretor().getId_usuario());
        smt.setInt(2, idAgendamento);
        smt.execute();

        smt.close();
        connection.close();

    }
}
