package Dao.EnderecoCliente;

import Dao.ClienteDao.ClienteDao;
import Dao.ConexaoDao;
import Dao.EnderecoDao.EnderecoDao;
import Model.Endereco.Endereco;
import Model.EnderecoCliente.EnderecoCliente;

import java.sql.*;

public class EnderecoClienteDao {
    public int cadastraEndClie(EnderecoCliente enderecoCliente) {
        ClienteDao clienteDao = new ClienteDao();
        EnderecoDao enderecoDao = new EnderecoDao();

        int idCliente = clienteDao.cadastraCliente(enderecoCliente.getCliente());
        int idEnd =  enderecoDao.cadastraEndereco(enderecoCliente.getEndereco());

        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql = "INSERT INTO `enderecocliente` (`idEndereco`, `idCliente`, `complemento`, `num`) " +
                "VALUES ('"+idEnd+"', '"+idCliente+"', '"+enderecoCliente.getComplemento()+"', '"+enderecoCliente.getNum()+"');";
        PreparedStatement pst = null;
        int id = -1;
        try {
            java.sql.Statement st = c.createStatement();
            st.executeQuery("SET FOREIGN_KEY_CHECKS=0;");
            pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            st.executeQuery("SET FOREIGN_KEY_CHECKS=1;");
            if (rs.next()) {
                id = rs.getInt(1);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            return id;
        }
    }

    public EnderecoCliente buscaEndCliByEnd(int idEnd)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        EnderecoCliente enderecoCliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM `enderecocliente` WHERE `idEndereco` =" + idEnd);

            while (r.next()) {
                enderecoCliente = new EnderecoCliente();
                ClienteDao clienteDao = new ClienteDao();
                EnderecoDao enderecoDao = new EnderecoDao();

                enderecoCliente.setComplemento(r.getString("complemento"));
                enderecoCliente.setNum(r.getInt("num"));
                enderecoCliente.setEndereco(enderecoDao.buscaEnderecoById(r.getInt("idEndereco")));
                enderecoCliente.setCliente(clienteDao.buscaClienteById(r.getInt("idCliente")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return enderecoCliente;

        }
    }
    public EnderecoCliente buscaEndCliByCli(int idCli)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        EnderecoCliente enderecoCliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM `enderecocliente` WHERE `idCliente` =" + idCli);

            while (r.next()) {
                enderecoCliente = new EnderecoCliente();
                ClienteDao clienteDao = new ClienteDao();
                EnderecoDao enderecoDao = new EnderecoDao();

                enderecoCliente.setComplemento(r.getString("complemento"));
                enderecoCliente.setNum(r.getInt("num"));
                enderecoCliente.setEndereco(enderecoDao.buscaEnderecoById(r.getInt("idEndereco")));
                enderecoCliente.setCliente(clienteDao.buscaClienteById(r.getInt("idCliente")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return enderecoCliente;

        }
    }

}
