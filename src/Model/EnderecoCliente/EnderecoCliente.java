package Model.EnderecoCliente;

import Model.Cliente.Cliente;
import Model.Endereco.Endereco;

public class EnderecoCliente {
    private Endereco endereco = new Endereco();
    private Cliente cliente = new Cliente();
    private String complemento;
    private int num;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "EnderecoCliente{" +
                "endereco=" + endereco +
                ", cliente=" + cliente +
                ", complemento='" + complemento + '\'' +
                ", num=" + num +
                '}';
    }
}
