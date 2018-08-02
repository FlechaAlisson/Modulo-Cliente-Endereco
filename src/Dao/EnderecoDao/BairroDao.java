package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.Bairro;

import java.sql.*;

public class BairroDao {
    public int cadastraBairro(Bairro bairro) {
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO bairro (bairro) VALUES ('"+ bairro.getNome() + "');";
        PreparedStatement pst = null;
        int id = -1;
        try {
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return id;
        }

    }

    public Bairro buscaBairro(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Bairro bairro = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM bairro where bairro like '" + nome + "'");

            while (r.next()) {
                bairro = new Bairro();
                bairro.setNome(r.getString("bairro"));
                bairro.setId(r.getInt("idBairro"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return bairro;

        }
    }

    public Bairro buscaBairroById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Bairro bairro = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM bairro where idBairro = '" + id + "'");

            while (r.next()) {
                bairro = new Bairro();
                bairro.setNome(r.getString("bairro"));
                bairro.setId(r.getInt("idBairro"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return bairro;

        }
    }

}
