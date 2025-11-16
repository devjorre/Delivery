package Model;

public class ClienteModel {
    private int id_cliente;

    private String nome;

    private String telefone;

    public ClienteModel(int id_cliente, String nome, String telefone) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.telefone = telefone;
    }
    public String toString(){
        return this.nome;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
