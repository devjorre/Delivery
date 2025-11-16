package DAO;

import Conexao.ConnectionFactory;
import Model.EnderecoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO implements GenericDAO<EnderecoModel> {

    @Override
    public void criar(EnderecoModel endereco) throws SQLException {
        String sql = "INSERT INTO Endereco (id_cliente, id_restaurante, rua, numero, bairro, cidade, estado, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if (endereco.getId_cliente() != 0) {
                stmt.setInt(1, endereco.getId_cliente());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            if (endereco.getId_Restaurante() != 0) {
                stmt.setInt(2, endereco.getId_Restaurante());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, endereco.getRua());
            stmt.setString(4, endereco.getNumero());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getCep());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    endereco.setId_Endereco(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public EnderecoModel findById(int id) throws SQLException {
        String sql = "SELECT * FROM Endereco WHERE id_endereco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EnderecoModel(
                            rs.getInt("id_endereco"),
                            rs.getObject("id_cliente", Integer.class),
                            rs.getObject("id_restaurante", Integer.class),
                            rs.getString("rua"),
                            rs.getString("numero"),
                            rs.getString("bairro"),
                            rs.getString("cidade"),
                            rs.getString("estado"),
                            rs.getString("cep")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<EnderecoModel> findAll() throws SQLException {
        List<EnderecoModel> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM Endereco";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                enderecos.add(new EnderecoModel(
                        rs.getInt("id_endereco"),
                        rs.getObject("id_cliente", Integer.class),
                        rs.getObject("id_restaurante", Integer.class),
                        rs.getString("rua"),
                        rs.getString("numero"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep")
                ));
            }
        }
        return enderecos;
    }

    @Override
    public void atualizar(EnderecoModel endereco) throws SQLException {
        String sql = "UPDATE Endereco SET id_cliente = ?, id_restaurante = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ? WHERE id_endereco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (endereco.getId_cliente() != 0) {
                stmt.setInt(1, endereco.getId_cliente());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }
            if (endereco.getId_Restaurante() != 0) {
                stmt.setInt(2, endereco.getId_Restaurante());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, endereco.getRua());
            stmt.setString(4, endereco.getNumero());
            stmt.setString(5, endereco.getBairro());
            stmt.setString(6, endereco.getCidade());
            stmt.setString(7, endereco.getEstado());
            stmt.setString(8, endereco.getCep());
            stmt.setInt(9, endereco.getId_Endereco());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Endereco WHERE id_endereco = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

