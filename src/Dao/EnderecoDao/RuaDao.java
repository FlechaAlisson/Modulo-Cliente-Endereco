package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.Rua;

import java.sql.*;

public class RuaDao {
    public int cadastraRua(Rua rua)  {
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO rua (rua) VALUES ('"+ rua.getNome() + "');";
        int id = -1;
        PreparedStatement pst = null;
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

    public Rua buscaRuaById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Rua rua = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM rua where idRua = '" + id + "'");

            while (r.next()) {
                rua = new Rua();
                rua.setNome(r.getString("rua"));
                rua.setId(r.getInt("idRua"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rua;

        }
    }

    public Rua buscaRua(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Rua rua = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM rua where rua like '" + nome + "'");

            while (r.next()) {
                rua = new Rua();
                rua.setNome(r.getString("rua"));
                rua.setId(r.getInt("idRua"));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            return rua;

        }


    }
}
