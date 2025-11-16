package DAO;

import Conexao.ConnectionFactory;
import Model.PedidoModel;
import Enum.PedidoStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements GenericDAO<PedidoModel> {

    @Override
    public void criar(PedidoModel pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (id_cliente, id_restaurante, data_hora, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getId_cliente());
            stmt.setInt(2, pedido.getId_restaurante());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getData_hora()));
            stmt.setString(4, pedido.getStatus().name());




            int rows = stmt.executeUpdate();

            System.out.println("Linhas inseridas: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public PedidoModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PedidoModel(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_restaurante"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            PedidoStatus.fromString(rs.getString("status").trim())


                    );

                }
            }
        }
        return null;
    }

    @Override
    public List<PedidoModel> findAll() throws SQLException {
        List<PedidoModel> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pedidos.add(new PedidoModel(
                        rs.getInt("id_pedido"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_restaurante"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        PedidoStatus.fromString(rs.getString("status").trim())

                ));

            }
        }
        return pedidos;
    }


    public List<PedidoModel> findByRestaurante(int idRestaurante) throws SQLException {
        List<PedidoModel> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRestaurante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new PedidoModel(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_restaurante"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            PedidoStatus.fromString(rs.getString("status").trim())


                    ));


                }
            }
        }
        return pedidos;
    }

    public List<PedidoModel> findByCliente(String nomeCliente) throws SQLException {
        List<PedidoModel> pedidos = new ArrayList<>();
        String sql = "SELECT p.* FROM Pedido p " +
                "JOIN Cliente c ON p.id_cliente = c.id_cliente " +
                "WHERE c.nome LIKE ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeCliente + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new PedidoModel(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_restaurante"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            PedidoStatus.fromString(rs.getString("status").trim())

                    ));
                }
            }
        }
        return pedidos;
    }


    public List<PedidoModel> findByRestauranteNome(String nomeRestaurante) throws SQLException {
        List<PedidoModel> pedidos = new ArrayList<>();
        String sql = "SELECT p.* FROM Pedido p " +
                "JOIN Restaurante r ON p.id_restaurante = r.id_restaurante " +
                "WHERE r.nome LIKE ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeRestaurante + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(new PedidoModel(
                            rs.getInt("id_pedido"),
                            rs.getInt("id_cliente"),
                            rs.getInt("id_restaurante"),
                            rs.getTimestamp("data_hora").toLocalDateTime(),
                            PedidoStatus.fromString(rs.getString("status").trim())

                    ));
                }
            }
        }
        return pedidos;
    }



    public void atualizarStatus(int idPedido, String novoStatus) throws SQLException {
        String sql = "UPDATE Pedido SET status = ? WHERE id_pedido = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizar(PedidoModel pedido) throws SQLException {
        String sql = "UPDATE Pedido SET id_cliente = ?, id_restaurante = ?, data_hora = ?, status = ? WHERE id_pedido = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getId_cliente());
            stmt.setInt(2, pedido.getId_restaurante());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getData_hora()));
            stmt.setString(4, pedido.getStatus().name());
            stmt.setInt(5, pedido.getId_pedido());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}