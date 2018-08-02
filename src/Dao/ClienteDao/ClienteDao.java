package Dao.ClienteDao;

import Dao.ConexaoDao;
import Model.Cliente.Cliente;
import Model.Cliente.Email;

import java.sql.*;

public class ClienteDao {
    public int cadastraCliente(Cliente cliente)
    {
        EmailDao emailDao = new EmailDao();
        int idEmail = emailDao.cadastraEmail(cliente.getEmail());

        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO `cliente` (`idCliente`, `nome`, `cpf`, `idEmail`) VALUES (NULL, '"+cliente.getNome()+"', " +
                "'"+cliente.getCpf()+"', '"+idEmail+"');";
        PreparedStatement pst = null;
        int id = -1;
        try {
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return id;
        }
    }

    public Cliente buscaCliente(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Cliente cliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM `cliente` WHERE `nome` LIKE '" + nome + "'");
            while (r.next()) {
                cliente = new Cliente();
                EmailDao emailDao = new EmailDao();
                Email email = emailDao.buscaEmailById(r.getInt("idEmail"));
                cliente.setEmail(email);

                cliente.setCpf(r.getString("cpf"));
                cliente.setNome(r.getString("nome"));
                cliente.setId(r.getInt("idCliente"));

            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return cliente;

        }
    }
    public Cliente buscaClienteById(int  id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Cliente cliente = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM `cliente` WHERE `idCliente` =" + id);
            while (r.next()) {
                cliente = new Cliente();
                EmailDao emailDao = new EmailDao();
                Email email = emailDao.buscaEmailById(r.getInt("idEmail"));
                cliente.setEmail(email);

                cliente.setCpf(r.getString("cpf"));
                cliente.setNome(r.getString("nome"));
                cliente.setId(r.getInt("idCliente"));

            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return cliente;

        }
    }
}
