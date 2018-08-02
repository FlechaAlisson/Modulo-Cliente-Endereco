package Dao.EnderecoDao;

import Dao.ConexaoDao;
import Model.Endereco.*;

import java.sql.*;

public class EnderecoDao {
    public int cadastraEndereco(Endereco endereco)  {
        CidadeDao cidadeDao = new CidadeDao();
        RuaDao ruaDao = new RuaDao();
        BairroDao bairroDao = new BairroDao();
        int idRua = ruaDao.cadastraRua(endereco.getRua());
        int idBairro = bairroDao.cadastraBairro(endereco.getBairro());
        int idCidade = cidadeDao.cadastraCidade(endereco.getCidade());

        endereco.getRua().setId(idRua);
        endereco.getBairro().setId(idBairro);
        endereco.getCidade().setId(idCidade);

        Connection  c = new ConexaoDao().getConexaoMySQL();
        String sql ="INSERT INTO `endereco` (`idRua`, `idBairro`, `idCidade`) VALUES ('"+idRua+"', '"+idBairro+"'," +
                " '"+idCidade+"')";
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

    public Endereco buscaEnderecoById(int id)
    {
        Connection c = new ConexaoDao().getConexaoMySQL();
        Statement st = null;
        Endereco endereco = null;
        try {
            st = c.createStatement();
            ResultSet r = st.executeQuery("SELECT * FROM endereco where  idEndereco=  '" + id + "'");
            while (r.next()) {
                endereco = new Endereco();
                RuaDao ruaDao = new RuaDao();
                endereco.setRua(ruaDao.buscaRuaById(r.getInt("idRua")));

                BairroDao bairroDao = new BairroDao();
                endereco.setBairro(bairroDao.buscaBairroById(r.getInt("idBairro")));

                CidadeDao cidadeDao = new CidadeDao();
                endereco.setCidade(cidadeDao.buscaCidadeById(r.getInt("idCidade")));
                }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return endereco;

        }
    }

}
