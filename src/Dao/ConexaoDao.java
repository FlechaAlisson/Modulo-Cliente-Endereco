package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
    public static String status = "N�o conectou...";

    private Connection connection;

    public Connection getConexaoMySQL() {


        try {


            String driverName = "com.mysql.cj.jdbc.Driver";

            Class.forName(driverName);

            String url = "jdbc:mysql://localhost/trab2?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "";


            connection = DriverManager.getConnection(url, user, password);




            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->N�o foi possivel realizar conex�o");

            }


            return connection;


        } catch (ClassNotFoundException e) {  //Driver n�o encontrado

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;
        } catch (SQLException e) {

            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }
    }

    public String statusConection() {

        return status;

    }

    public boolean FecharConexao() {

        try {

            this.connection.close();

            return true;

        } catch (SQLException e) {

            return false;

        }

    }


}
