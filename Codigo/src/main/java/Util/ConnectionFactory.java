package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {
    }

    public static java.sql.Connection getConexao() {
        Connection connection;
        try {
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
            String serverName = "localhost:5432";
            String database = "dbimobiliaria";
            String url = "jdbc:postgresql://" + serverName + "/" + database;
            String username = "postgres";
            String password = "postgres";

            connection = DriverManager.getConnection(url, username, password);

            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("O driver expecificado nao foi encontrado." + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar ao Banco de Dados." + e.getMessage());
            return null;
        }
    }

}
