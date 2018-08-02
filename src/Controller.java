import Dao.ClienteDao.ClienteDao;
import Dao.ClienteDao.EmailDao;
import Dao.EnderecoCliente.EnderecoClienteDao;
import Dao.EnderecoDao.*;
import Model.Cliente.Cliente;
import Model.Cliente.Email;
import Model.Endereco.*;
import Model.EnderecoCliente.EnderecoCliente;

import java.sql.SQLException;

public class Controller {
    public static void main(String[] args) throws SQLException {
        Rua rua = new Rua();
        rua.setNome("blaalb");

        Bairro bairro= new Bairro();
        bairro.setNome("blaalb");

        Pais pais = new Pais();
        pais.setNome("blaalb");

        Estado estado = new Estado();
        estado.setNome("blaalb");
        estado.setPais(pais);

        Cidade cidade = new Cidade();
        cidade.setNome("blaalb");
        cidade.setEstado(estado);

        Endereco endereco = new Endereco();
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setRua(rua);
        Email email = new Email();
        email.setEmail("blabla@gmail.com");
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setNome("aaaaa");
        cliente.setCpf("12332131");
        EnderecoClienteDao enderecoClienteDao = new EnderecoClienteDao();
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        enderecoCliente.setCliente(cliente);
        enderecoCliente.setEndereco(endereco);
        enderecoCliente.setNum(12);
        enderecoCliente.setComplemento("quero");

        enderecoCliente = enderecoClienteDao.buscaEndCliByCli(2);
        System.out.println(enderecoCliente);

    }
}
