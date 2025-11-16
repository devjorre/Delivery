package Controller;

import Model.ItemPedidoModel;
import Service.ItemPedidoService;

import java.sql.SQLException;
import java.util.List;

public class ItemPedidoController {
    private ItemPedidoService itemPedidoService;

    public ItemPedidoController() {
        this.itemPedidoService = new ItemPedidoService();
    }

    public void adicionarItemPedido(ItemPedidoModel itemPedido) {
        try {
            itemPedidoService.adicionarItemPedido(itemPedido);
            System.out.println("Item de pedido adicionado com sucesso! ID: " + itemPedido.getId_item());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao adicionar item de pedido: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao adicionar item de pedido: " + e.getMessage());
        }
    }

    public ItemPedidoModel buscarItemPedidoPorId(int id) {
        try {
            return itemPedidoService.buscarItemPedidoPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar item de pedido: " + e.getMessage());
            return null;
        }
    }

    public List<ItemPedidoModel> listarTodosItensPedido() {
        try {
            return itemPedidoService.listarTodosItensPedido();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar itens de pedido: " + e.getMessage());
            return null;
        }
    }

    public void atualizarItemPedido(ItemPedidoModel itemPedido) {
        try {
            itemPedidoService.atualizarItemPedido(itemPedido);
            System.out.println("Item de pedido atualizado com sucesso! ID: " + itemPedido.getId_item());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar item de pedido: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar item de pedido: " + e.getMessage());
        }
    }

    public void deletarItemPedido(int id) {
        try {
            itemPedidoService.deletarItemPedido(id);
            System.out.println("Item de pedido deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar item de pedido: " + e.getMessage());
        }
    }

    public void cadastrarItemPedido(ItemPedidoModel item) {

    }
}

