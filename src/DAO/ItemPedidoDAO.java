package DAO;

import Conexao.ConnectionFactory;
import Model.ItemPedidoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO implements GenericDAO<ItemPedidoModel> {

    @Override
    public void criar(ItemPedidoModel itemPedido) throws SQLException {
        String sql = "INSERT INTO ItemPedido (id_pedido, descricao, quantidade, preco, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, itemPedido.getId_pedido());
            stmt.setString(2, itemPedido.getDescricao());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setDouble(4, itemPedido.getPreco());
            stmt.setString(5, itemPedido.getStatus());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    itemPedido.setId_item(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public ItemPedidoModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM ItemPedido WHERE id_item = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ItemPedidoModel(
                            rs.getInt("id_item"),
                            rs.getInt("id_pedido"),
                            rs.getString("descricao"),
                            rs.getInt("quantidade"),
                            rs.getDouble("preco"),
                            rs.getString("status")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<ItemPedidoModel> findAll() throws SQLException {
        List<ItemPedidoModel> itensPedido = new ArrayList<>();
        String sql = "SELECT * FROM ItemPedido";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                itensPedido.add(new ItemPedidoModel(
                        rs.getInt("id_item"),
                        rs.getInt("id_pedido"),
                        rs.getString("descricao"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"),
                        rs.getString("status")
                ));
            }
        }
        return itensPedido;
    }

    @Override
    public void atualizar(ItemPedidoModel itemPedido) throws SQLException {
        String sql = "UPDATE ItemPedido SET id_pedido = ?, descricao = ?, quantidade = ?, preco = ? , status = ? WHERE id_item = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, itemPedido.getId_pedido());
            stmt.setString(2, itemPedido.getDescricao());
            stmt.setInt(3, itemPedido.getQuantidade());
            stmt.setDouble(4, itemPedido.getPreco());
            stmt.setInt(5, itemPedido.getId_item());
            stmt.setString(6, itemPedido.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM ItemPedido WHERE id_item = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

