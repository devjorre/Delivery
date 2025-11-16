package DAO;

import Conexao.ConnectionFactory;
import Model.TipoRestaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoRestauranteDAO implements GenericDAO<TipoRestaurante> {

    @Override
    public void criar(TipoRestaurante tipo) throws SQLException {
        String sql = "INSERT INTO TipoRestaurante (nome) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tipo.getNome());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tipo.setId_tipo(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public TipoRestaurante findById(int id) throws SQLException {
        String sql = "SELECT * FROM TipoRestaurante WHERE id_tipo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TipoRestaurante(
                            rs.getInt("id_tipo"),
                            rs.getString("nome")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<TipoRestaurante> findAll() throws SQLException {
        List<TipoRestaurante> tipos = new ArrayList<>();
        String sql = "SELECT * FROM TipoRestaurante";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tipos.add(new TipoRestaurante(
                        rs.getInt("id_tipo"),
                        rs.getString("nome")
                ));
            }
        }
        return tipos;
    }

    @Override
    public void atualizar(TipoRestaurante tipo) throws SQLException {
        String sql = "UPDATE TipoRestaurante SET nome = ? WHERE id_tipo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo.getNome());
            stmt.setInt(2, tipo.getId_tipo());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM TipoRestaurante WHERE id_tipo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
