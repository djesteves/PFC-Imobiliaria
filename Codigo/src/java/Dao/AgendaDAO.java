/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Modelo.Agenda;
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

    public List<Agenda> listarAgendaCorretor(int id) throws SQLException {

        String queryAgendaCorretor = "SELECT u.nome, ag.id_imovel, ag.dataagendamento, ag.datasolicitacao, ag.status\n"
                + " FROM agenda a\n"
                + " JOIN agendamento ag on ag.id_agendamento = a.id_agendamento\n"
                + " JOIN usuario u on u.id_usuario = ag.id_usuario\n"
                + "WHERE a.id_corretor = ?"
                + "ORDER BY ag.dataagendamento DESC";

        List<Agenda> listaAgenda = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryAgendaCorretor);
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

    public List<Agenda> listarAgendaUsuario(int id) throws SQLException {

        String queryAgendaUsuario = "SELECT uc.nome as corretor, ag.id_imovel, ag.dataagendamento, ag.datasolicitacao, ag.status\n"
                + " FROM agenda a\n"
                + " JOIN agendamento ag on ag.id_agendamento = a.id_agendamento\n"
                + " JOIN usuario u on u.id_usuario = ag.id_usuario\n"
                + " JOIN Usuario uc on uc.id_usuario = a.id_corretor\n"
                + "WHERE ag.id_usuario = ?";

        List<Agenda> listaAgenda = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryAgendaUsuario);
        smt.setInt(1, id);
        rs = smt.executeQuery();

        while (rs.next()) {

            Agenda ag = new Agenda();

            ag.getAgendamento().getUsuario().setNome(rs.getString("corretor"));
            ag.getAgendamento().getImovel().setId_imovel(rs.getInt("id_imovel"));
            ag.getAgendamento().setDataAgendamento(rs.getTimestamp("dataagendamento"));
            ag.getAgendamento().setDataSolicitacao(rs.getTimestamp("datasolicitacao"));
            ag.getAgendamento().setStatus(rs.getString("status"));

            listaAgenda.add(ag);

        }

        return listaAgenda;

    }
    
    public List<Agenda> listarAgendaImovel(int id) throws SQLException {

        String queryAgendaImovel = "SELECT u.nome as solicitante, ag.id_imovel, ag.dataagendamento, ag.datasolicitacao, ag.status\n"
                + " FROM agenda a\n"
                + " JOIN agendamento ag on ag.id_agendamento = a.id_agendamento\n"
                + " JOIN usuario u on u.id_usuario = ag.id_usuario\n"
                + "WHERE ag.id_imovel = ?";

        List<Agenda> listaAgenda = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryAgendaImovel);
        smt.setInt(1, id);
        rs = smt.executeQuery();

        while (rs.next()) {

            Agenda ag = new Agenda();

            ag.getAgendamento().getUsuario().setNome(rs.getString("solicitante"));
            ag.getAgendamento().getImovel().setId_imovel(rs.getInt("id_imovel"));
            ag.getAgendamento().setDataAgendamento(rs.getTimestamp("dataagendamento"));
            ag.getAgendamento().setDataSolicitacao(rs.getTimestamp("datasolicitacao"));
            ag.getAgendamento().setStatus(rs.getString("status"));

            listaAgenda.add(ag);

        }

        return listaAgenda;

    }
    
    
}
