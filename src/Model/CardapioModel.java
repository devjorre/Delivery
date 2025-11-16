package Model;

public class CardapioModel {
    private int id_cardapio;

    private int id_restaurante;

    private String nome_item;

    private String descricao;

    private double preco;

    private boolean disponivel;

    public CardapioModel(int id_cardapio, int id_restaurante, String nome_item, String descricao, double preco, boolean disponivel) {
        this.id_cardapio = id_cardapio;
        this.id_restaurante = id_restaurante;
        this.nome_item = nome_item;
        this.descricao = descricao;
        this.preco = preco;
        this.disponivel = disponivel;
    }

    public CardapioModel() {

    }

    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public int getId_restaurante() {
        return id_restaurante;
    }

    public void setId_restaurante(int id_restaurante) {
        this.id_restaurante = id_restaurante;
    }

    public String getNome_item() {
        return nome_item;
    }

    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}