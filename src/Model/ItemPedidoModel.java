package Model;

import java.math.BigDecimal;

public class ItemPedidoModel {
    private int id_item;

    private int id_pedido;

    private String descricao;

    private int quantidade;

    private double preco;

    private String status;



    public ItemPedidoModel(int id_item, int id_pedido, String descricao, int quantidade, double preco, String status) {
        this.id_item = id_item;
        this.id_pedido = id_pedido;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
    }


    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
