package Model;

public class RestauranteModel {
    private int id_restaurante;

    private String nome;

    private String tipo_cozinha;

    private String telefone;

    public RestauranteModel(int id_restaurante, String nome, String tipo_cozinha, String telefone) {
        this.id_restaurante = id_restaurante;
        this.nome = nome;
        this.tipo_cozinha = tipo_cozinha;
        this.telefone = telefone;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_cozinha() {
        return tipo_cozinha;
    }

    public void setTipo_cozinha(String tipo_cozinha) {
        this.tipo_cozinha = tipo_cozinha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    @Override
    public String toString() {
        return getNome();
    }


}
