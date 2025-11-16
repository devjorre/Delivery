package Service;

import DAO.ItemPedidoDAO;
import Model.ItemPedidoModel;

import java.sql.SQLException;
import java.util.List;

public class ItemPedidoService {
    private ItemPedidoDAO itemPedidoDAO;

    public ItemPedidoService() {
        this.itemPedidoDAO = new ItemPedidoDAO();
    }

    public void adicionarItemPedido(ItemPedidoModel itemPedido) throws SQLException {
        if (itemPedido.getId_pedido() <= 0 || itemPedido.getDescricao() == null || itemPedido.getDescricao().trim().isEmpty() || itemPedido.getQuantidade() <= 0 || itemPedido.getPreco() <= 0) {
            throw new IllegalArgumentException("Dados do item do pedido inválidos.");
        }
        itemPedidoDAO.criar(itemPedido);
    }

    public ItemPedidoModel buscarItemPedidoPorId(int id) throws SQLException {
        return itemPedidoDAO.findById(id);
    }

    public List<ItemPedidoModel> listarTodosItensPedido() throws SQLException {
        return itemPedidoDAO.findAll();
    }

    public void atualizarItemPedido(ItemPedidoModel itemPedido) throws SQLException {
        if (itemPedido.getId_pedido() <= 0 || itemPedido.getDescricao() == null || itemPedido.getDescricao().trim().isEmpty() || itemPedido.getQuantidade() <= 0 || itemPedido.getPreco() <= 0) {
            throw new IllegalArgumentException("Dados do item do pedido inválidos.");
        }
        itemPedidoDAO.atualizar(itemPedido);
    }

    public void deletarItemPedido(int id) throws SQLException {
        itemPedidoDAO.deletar(id);
    }


}

