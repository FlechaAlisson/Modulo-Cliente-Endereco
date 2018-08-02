package Dao.ClienteDao;

import Dao.ConexaoDao;
import Model.Cliente.Email;

import java.sql.*;

public class EmailDao {
    public int cadastraEmail(Email email)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO `email` (`idEmail`, `email`) VALUES (NULL, '"+email.getEmail()+"');";
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

    public Email buscaEmail(String email)
    {
        {
            Connection c = new ConexaoDao().getConexaoMySQL();
            Statement st = null;
            Email emailaux = null;
            try {
                st = c.createStatement();
                ResultSet r = st.executeQuery("SELECT * FROM `email` WHERE `email` LIKE '" + email + "'");
                while (r.next()) {
                    emailaux = new Email();
                    emailaux.setEmail(r.getString("email"));
                    emailaux.setId(r.getInt("idEmail"));

                }
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                return emailaux;

            }
        }
    }
    public Email buscaEmailById(int id)
    {
        {
            Connection c = new ConexaoDao().getConexaoMySQL();
            Statement st = null;
            Email emailaux = null;
            try {
                st = c.createStatement();
                ResultSet r = st.executeQuery("SELECT * FROM `email` WHERE `idEmail` ='" + id + "'");
                while (r.next()) {
                    emailaux = new Email();
                    emailaux.setEmail(r.getString("email"));
                    emailaux.setId(r.getInt("idEmail"));

                }
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                return emailaux;

            }
        }
    }
}
