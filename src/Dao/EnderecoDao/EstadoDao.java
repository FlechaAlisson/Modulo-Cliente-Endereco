package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.Estado;
import Model.Endereco.Pais;

import java.sql.*;

public class EstadoDao {
    public int cadastraEstado(Estado estado) {
        PaisDao paisDao = new PaisDao();
        int idPais = paisDao.cadastraPais(estado.getPais());
        estado.getPais().setId(idPais);
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO `estado` ( `estado`, `idPais`) VALUES ( '"+estado.getNome()+"'," +
                " '"+idPais+"');";
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
    public Estado buscaEstadoById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Estado estado = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM estado where idEstado = '" + id + "'");
            while (r.next()) {
                estado = new Estado();
                estado.setNome(r.getString("estado"));
                estado.setId(r.getInt("idEstado"));

                PaisDao paisDao =new PaisDao();
                estado.setPais(paisDao.buscaRuaById(r.getInt("idPais")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return estado;

        }
    }

    public Estado buscaEstado(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Estado estado = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM estado where  estado like '" + nome + "'");
            while (r.next()) {
                estado = new Estado();
                estado.setNome(r.getString("estado"));
                estado.setId(r.getInt("idEstado"));

                PaisDao paisDao =new PaisDao();
                estado.setPais(paisDao.buscaRuaById(r.getInt("idPais")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return estado;

        }
    }
}
