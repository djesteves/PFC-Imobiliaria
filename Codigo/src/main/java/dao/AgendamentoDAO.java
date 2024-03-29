/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Agendamento;
import modelo.Usuario;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        String queryCorretores = "SELECT * FROM usuario U "
                + " INNER JOIN Endereco E ON E.id_endereco = U.id_endereco"
                + " WHERE u.nivel_acesso = 'CORRETOR'"
                + " AND u.situacao = 'Ativo'";

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

        connection.close();

        return corretores;

    }

    public List<Agendamento> listarAgendamentos(Map parametros) throws SQLException {

        String queryAgendaImovel = "SELECT ag.id_agendamento, u.nome, uc.nome as corretor, ag.id_imovel, ag.dataagendamento, ag.datasolicitacao, ag.status,\n"
                + "	   u.email as emailSolicitante, uc.email as emailCorretor, ua.email as emailAnunciante\n"
                + "                 FROM agendamento ag\n"
                + "                 JOIN usuario u on u.id_usuario = ag.id_usuario\n"
                + "                 JOIN usuario uc on uc.id_usuario = ag.id_corretor\n"
                + "                 JOIN imovel i on i.id_imovel = ag.id_imovel\n"
                + "                 JOIN usuario ua on ua.id_usuario = i.id_usuario ";

        String modo = (String) parametros.get("modo");

        if (modo.equalsIgnoreCase("corretor")) {
            queryAgendaImovel += "WHERE ag.id_corretor = ?";
        } else if (modo.equalsIgnoreCase("usuario")) {
            queryAgendaImovel += "WHERE ag.id_usuario = ?";
        } else if (modo.equalsIgnoreCase("Imovel")) {
            queryAgendaImovel += "WHERE ag.id_imovel = ?";
        }

        queryAgendaImovel += " AND ag.situacao = 'Ativo'";
        queryAgendaImovel += "ORDER BY ag.dataagendamento ASC";

        List<Agendamento> listaAgenda = new ArrayList<>();

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(queryAgendaImovel);
        if (!modo.equalsIgnoreCase("Administrador")) {
            smt.setInt(1, (int) parametros.get("id"));
        }
        rs = smt.executeQuery();

        if (rs != null) {
            while (rs.next()) {
                Agendamento ag = new Agendamento();

                ag.setId_agendamento(rs.getInt("id_agendamento"));
                ag.getUsuario().setNome(rs.getString("nome"));
                ag.getUsuarioCorretor().setNome(rs.getString("corretor"));
                ag.getImovel().setId_imovel(rs.getInt("id_imovel"));
                ag.setDataAgendamento(rs.getTimestamp("dataagendamento"));
                ag.setDataSolicitacao(rs.getTimestamp("datasolicitacao"));
                ag.getUsuario().setEmail(rs.getString("emailSolicitante"));
                ag.getUsuarioCorretor().setEmail(rs.getString("emailCorretor"));
                ag.getImovel().getUsuario().setEmail(rs.getString("emailAnunciante"));
                ag.setStatus(rs.getString("status"));

                listaAgenda.add(ag);
            }
        }

        connection.close();

        return listaAgenda;

    }

    public void Agendar(Agendamento agenda) throws SQLException {

        String insertAgendamento = "INSERT INTO Agendamento VALUES (DEFAULT,?,?,?,?,?,?,?)";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(insertAgendamento);
        smt.setTimestamp(1, new Timestamp(agenda.getDataAgendamento().getTime()));
        smt.setTimestamp(2, timestamp);
        smt.setString(3, agenda.getStatus());
        smt.setString(4, agenda.getSituacao());
        smt.setInt(5, agenda.getUsuario().getId_usuario());
        smt.setInt(6, agenda.getImovel().getId_imovel());
        smt.setInt(7, agenda.getUsuarioCorretor().getId_usuario());
        smt.execute();

        connection.close();

    }

    public int alocaCorretor(Agendamento agendamento) throws SQLException {

        int id = 0;

        String corretores = "SELECT id_usuario "
                + " FROM usuario "
                + "WHERE nivel_acesso = 'CORRETOR'"
                + "  AND Situacao = 'Ativo'";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(corretores);
        ResultSet rsCorretor = smt.executeQuery();

        while (rsCorretor.next()) {

            String corretorDisponivel = "SELECT id_corretor FROM Agendamento WHERE id_corretor = ? AND dataagendamento = ?";

            smt = connection.prepareStatement(corretorDisponivel);
            smt.setInt(1, rsCorretor.getInt("id_usuario"));
            smt.setTimestamp(2, new Timestamp(agendamento.getDataAgendamento().getTime()));
            rs = smt.executeQuery();

            if (!rs.next()) {
                id = rsCorretor.getInt("id_usuario");
                break;
            }

        }

        connection.close();

        return id;

    }

    public boolean disponibilidadeCorretor(Agendamento agendamento) throws SQLException {

        String disponibilidadeCorretor = "SELECT dataagendamento "
                + " FROM Agendamento "
                + "WHERE id_corretor  = ? "
                + "  AND dataagendamento = ?"
                + "  AND Situacao = 'Ativo'";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(disponibilidadeCorretor);
        smt.setInt(1, agendamento.getUsuarioCorretor().getId_usuario());
        smt.setTimestamp(2, new Timestamp(agendamento.getDataAgendamento().getTime()));
        rs = smt.executeQuery();

        if (rs.next()) {
            connection.close();
            return false;
        } else {
            connection.close();
            return true;
        }

    }

    public boolean disponibilidadeImovel(Agendamento agendamento) throws SQLException {

        Connection connection = ConnectionFactory.getConexao();

        String disponibilidadeImovel = "SELECT dataagendamento "
                + " FROM Agendamento "
                + "WHERE id_imovel  = ? "
                + "  AND dataagendamento = ?"
                + "  AND Situacao = 'Ativo'";

        smt = connection.prepareStatement(disponibilidadeImovel);
        smt.setInt(1, agendamento.getImovel().getId_imovel());
        smt.setTimestamp(2, new Timestamp(agendamento.getDataAgendamento().getTime()));
        rs = smt.executeQuery();

        System.out.println(rs);

        if (rs.next()) {
            connection.close();
            return false;
        } else {
            connection.close();
            return true;
        }

    }

    public void cancelar(Agendamento agenda) throws SQLException {

        String insertAgendamento = "UPDATE Agendamento SET Status = ?, Situacao = ? WHERE id_agendamento = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(insertAgendamento);
        smt.setString(1, agenda.getStatus());
        smt.setString(2, agenda.getSituacao());
        smt.setInt(3, agenda.getId_agendamento());
        smt.executeUpdate();

        connection.close();

    }

    public void concluir(Agendamento agenda) throws SQLException {

        String updateAgendamento = "UPDATE Agendamento SET Status = ? WHERE id_agendamento = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(updateAgendamento);
        smt.setString(1, agenda.getStatus());
        smt.setInt(2, agenda.getId_agendamento());
        smt.executeUpdate();

        connection.close();

    }
}
