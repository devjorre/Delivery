package DAO;

import Conexao.ConnectionFactory;
import Model.CardapioModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardapioDAO implements GenericDAO<CardapioModel> {

    @Override
    public void criar(CardapioModel cardapio) throws SQLException {
        String sql = "INSERT INTO Cardapio (id_restaurante, nome_item, descricao, preco, disponivel) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cardapio.getId_restaurante());
            stmt.setString(2, cardapio.getNome_item());
            stmt.setString(3, cardapio.getDescricao());
            stmt.setDouble(4, cardapio.getPreco());
            stmt.setBoolean(5, cardapio.isDisponivel());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cardapio.setId_cardapio(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public CardapioModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM Cardapio WHERE id_cardapio = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new CardapioModel(
                            rs.getInt("id_cardapio"),
                            rs.getInt("id_restaurante"),
                            rs.getString("nome_item"),
                            rs.getString("descricao"),
                            rs.getDouble("preco"),
                            rs.getBoolean("disponivel")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<CardapioModel> findAll() throws SQLException {
        List<CardapioModel> cardapios = new ArrayList<>();
        String sql = "SELECT * FROM Cardapio";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cardapios.add(new CardapioModel(
                        rs.getInt("id_cardapio"),
                        rs.getInt("id_restaurante"),
                        rs.getString("nome_item"),
                        rs.getString("descricao"),
                        rs.getDouble("preco"),
                        rs.getBoolean("disponivel")
                ));
            }
        }
        return cardapios;
    }


    public List<CardapioModel> findByRestaurante(int idRestaurante) throws SQLException {
        List<CardapioModel> cardapios = new ArrayList<>();
        String sql = "SELECT * FROM Cardapio WHERE id_restaurante = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idRestaurante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cardapios.add(new CardapioModel(
                            rs.getInt("id_cardapio"),
                            rs.getInt("id_restaurante"),
                            rs.getString("nome_item"),
                            rs.getString("descricao"),
                            rs.getDouble("preco"),
                            rs.getBoolean("disponivel")
                    ));
                }
            }
        }
        return cardapios;
    }

    @Override
    public void atualizar(CardapioModel cardapio) throws SQLException {
        String sql = "UPDATE Cardapio SET id_restaurante = ?, nome_item = ?, descricao = ?, preco = ?, disponivel = ? WHERE id_cardapio = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cardapio.getId_restaurante());
            stmt.setString(2, cardapio.getNome_item());
            stmt.setString(3, cardapio.getDescricao());
            stmt.setDouble(4, cardapio.getPreco());
            stmt.setBoolean(5, cardapio.isDisponivel());
            stmt.setInt(6, cardapio.getId_cardapio());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Cardapio WHERE id_cardapio = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
