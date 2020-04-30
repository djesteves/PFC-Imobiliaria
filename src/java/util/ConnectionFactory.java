package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static String status = "Não conectou...";

    public ConnectionFactory() {
    }

    public static java.sql.Connection getConexao() {
        Connection connection = null;
        try {
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
            String serverName = "localhost:5432";
            String mydatabase = "dbimobiliaria";
            String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
            String username = "postgres";
            String password = "postgres";

            connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                status = ("Conectado com sucesso!");
            } else {
                status = ("Não foi possivel realizar a conexão");
            }
            
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado."+e);
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados."+e);
            return null;
        }
    }

    public static String statusConection() {
        return status;
    }

    public static boolean FecharConexao() {
        try {
            ConnectionFactory.getConexao().close();
            return true;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel encerrar a conexao ao Banco de Dados."+e);
            return false;
        }
    }

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConnectionFactory.getConexao();
    }

}
