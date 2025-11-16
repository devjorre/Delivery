package DAO;

import Conexao.ConnectionFactory;
import Model.RestauranteModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO implements GenericDAO<RestauranteModel> {

    @Override
    public void criar(RestauranteModel restaurante) throws SQLException {
        String sql = "INSERT INTO Restaurante (nome, tipo_cozinha, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTipo_cozinha());
            stmt.setString(3, restaurante.getTelefone());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    restaurante.setId_restaurante(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public RestauranteModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM Restaurante WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new RestauranteModel(
                            rs.getInt("id_restaurante"),
                            rs.getString("nome"),
                            rs.getString("tipo_cozinha"),
                            rs.getString("telefone")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<RestauranteModel> findAll() throws SQLException {
        List<RestauranteModel> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                restaurantes.add(new RestauranteModel(
                        rs.getInt("id_restaurante"),
                        rs.getString("nome"),
                        rs.getString("tipo_cozinha"),
                        rs.getString("telefone")
                ));
            }
        }
        return restaurantes;
    }

    @Override
    public void atualizar(RestauranteModel restaurante) throws SQLException {
        String sql = "UPDATE Restaurante SET nome = ?, tipo_cozinha = ?, telefone = ? WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTipo_cozinha());
            stmt.setString(3, restaurante.getTelefone());
            stmt.setInt(4, restaurante.getId_restaurante());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Restaurante WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

