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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego
 */
public class AgendaDAO {

    PreparedStatement smt;
    ResultSet rs;

    public List<Agenda> listarAgenda(int id) throws SQLException {

        String queryAgenda = "SELECT u.nome, ag.id_imovel, ag.dataagendamento, ag.datasolicitacao, ag.status\n"
                + " FROM agenda a\n"
                + " JOIN agendamento ag on ag.id_agendamento = a.id_agendamento\n"
                + " JOIN usuario u on u.id_usuario = ag.id_usuario\n"
                + "WHERE a.id_corretor = ?";

        List<Agenda> listaAgenda = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryAgenda);
        smt.setInt(1, id);
        rs = smt.executeQuery();

        while (rs.next()) {

            Agenda ag = new Agenda();

            ag.getAgendamento().getUsuario().setNome(rs.getString("nome"));
            ag.getAgendamento().getImovel().setId_imovel(rs.getInt("id_imovel"));
            ag.getAgendamento().setDataAgendamento(rs.getTimestamp("dataagendamento"));
            ag.getAgendamento().setDataSolicitacao(rs.getTimestamp("datasolicitacao"));
            ag.getAgendamento().setStatus(rs.getString("status"));

            listaAgenda.add(ag);

        }

        return listaAgenda;

    }
}
