package Model;

public class TipoRestaurante {
    private int id_tipo;

    private String nome;

    public TipoRestaurante(int id_tipo, String nome) {
        this.id_tipo = id_tipo;
        this.nome = nome;
    }

    public TipoRestaurante(String nome) {
    }


    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
