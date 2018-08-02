package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.Pais;

import java.sql.*;

public class PaisDao {
    public int cadastraPais(Pais pais) {
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO pais (pais) VALUES ('"+ pais.getNome() + "');";
        PreparedStatement pst = null;
        int id = -1;
        try {
            pst = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next())
                id = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        }
    }

    public Pais buscaRuaById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Pais pais = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM pais where idPais = '" + id + "'");

            while (r.next()) {
                pais = new Pais();
                pais.setNome(r.getString("pais"));
                pais.setId(r.getInt("idPais"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return pais;

        }
    }
    public Pais buscaRua(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Pais pais = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM pais where pais like '" + nome + "'");

            while (r.next()) {
                pais = new Pais();
                pais.setNome(r.getString("pais"));
                pais.setId(r.getInt("idPais"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return pais;

        }
    }
}
