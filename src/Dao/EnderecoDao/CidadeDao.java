package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.Cidade;

import java.sql.*;

public class CidadeDao {
    public int cadastraCidade(Cidade cidade) {
        EstadoDao estadoDao= new EstadoDao();
        int idEstado = estadoDao.cadastraEstado(cidade.getEstado());
        cidade.getEstado().setId(idEstado);
        Connection c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO `cidade` (`cidade`, `idEstado`) VALUES ('"+cidade.getNome()+"', '"+idEstado+"')";
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

    public Cidade buscaCidade(String nome)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Cidade cidade = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cidade where  cidade like '" + nome + "'");
            while (r.next()) {
                cidade = new Cidade();
                cidade.setNome(r.getString("cidade"));
                cidade.setId(r.getInt("idCidade"));

                EstadoDao estadoDao = new EstadoDao();
                cidade.setEstado(estadoDao.buscaEstadoById(r.getInt("idEstado")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return cidade;

        }
    }
    public Cidade buscaCidadeById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Cidade cidade = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM cidade where  idCidade =  '" + id + "'");
            while (r.next()) {
                cidade = new Cidade();
                cidade.setNome(r.getString("cidade"));
                cidade.setId(r.getInt("idCidade"));

                EstadoDao estadoDao = new EstadoDao();
                cidade.setEstado(estadoDao.buscaEstadoById(r.getInt("idEstado")));
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return cidade;

        }
    }
}
