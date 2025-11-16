package Model;

public class EnderecoModel {
    private int Id_Endereco;
    private int Id_cliente;
    private int Id_Restaurante;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoModel(int id_Endereco, int id_cliente, int id_Restaurante, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        Id_Endereco = id_Endereco;
        Id_cliente = id_cliente;
        Id_Restaurante = id_Restaurante;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public int getId_Endereco() {
        return Id_Endereco;
    }

    public void setId_Endereco(int id_Endereco) {
        Id_Endereco = id_Endereco;
    }

    public int getId_cliente() {
        return Id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        Id_cliente = id_cliente;
    }

    public int getId_Restaurante() {
        return Id_Restaurante;
    }

    public void setId_Restaurante(int id_Restaurante) {
        Id_Restaurante = id_Restaurante;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}