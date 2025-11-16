package DAO;

import Conexao.ConnectionFactory;
import Model.ClienteModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements GenericDAO<ClienteModel> {

    @Override
    public void criar(ClienteModel cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nome, telefone) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId_cliente(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public ClienteModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ClienteModel(
                            rs.getInt("id_cliente"),
                            rs.getString("nome"),
                            rs.getString("telefone")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<ClienteModel> findAll() throws SQLException {
        List<ClienteModel> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(new ClienteModel(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                ));
            }
        }
        return clientes;
    }

    @Override
    public void atualizar(ClienteModel cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nome = ?, telefone = ? WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getId_cliente());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

