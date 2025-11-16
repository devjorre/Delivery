//package DAO;
//
//import Conexao.ConnectionFactory;
//import Model.PagamentoModel;
//import Enum.MetodoPagamento;
//import Enum.PagamentoStatus;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PagamentoDAO implements GenericDAO<PagamentoModel> {
//
//    @Override
//    public void criar(PagamentoModel pagamento) throws SQLException {
//        String sql = "INSERT INTO Pagamento (id_pedido, metodo, valor_total, status) VALUES (?, ?, ?, ?)";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            stmt.setInt(1, pagamento.getId_pedido());
//            stmt.setString(2, pagamento.getMetodo().name());
//            stmt.setDouble(3, pagamento.getValor_total());
//            stmt.setString(4, pagamento.getStatus().name());
//            stmt.executeUpdate();
//
//            try (ResultSet rs = stmt.getGeneratedKeys()) {
//                if (rs.next()) {
//                    pagamento.setId_pagamento(rs.getInt(1));
//                }
//            }
//        }
//    }
//
//    @Override
//    public PagamentoModel findById(int id) throws SQLException {
//        String sql = "SELECT * FROM Pagamento WHERE id_pagamento = ?";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new PagamentoModel(
//                            rs.getInt("id_pagamento"),
//                            rs.getInt("id_pedido"),
//                            MetodoPagamento.valueOf(rs.getString("metodo")),
//                            rs.getDouble("valor_total"),
//                            PagamentoStatus.valueOf(rs.getString("status"))
//                    );
//
//                }
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<PagamentoModel> findAll() throws SQLException {
//        List<PagamentoModel> pagamentos = new ArrayList<>();
//        String sql = "SELECT * FROM Pagamento";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                while (rs.next()) {
//                    pagamentos.add(new PagamentoModel(
//                            rs.getInt("id_pagamento"),
//                            rs.getInt("id_pedido"),
//                            MetodoPagamento.valueOf(rs.getString("metodo")),
//                            rs.getDouble("valor_total"),
//                            PagamentoStatus.valueOf(rs.getString("status"))
//                    ));
//
//                }
//
//            }
//        }
//        return pagamentos;
//    }
//
//    @Override
//    public void atualizar(PagamentoModel pagamento) throws SQLException {
//        String sql = "UPDATE Pagamento SET id_pedido = ?, metodo = ?, valor_total = ?, status = ? WHERE id_pagamento = ?";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, pagamento.getId_pedido());
//            stmt.setString(2, pagamento.getMetodo().name());
//            stmt.setDouble(3, pagamento.getValor_total());
//            stmt.setString(4, pagamento.getStatus().name());
//            stmt.setInt(5, pagamento.getId_pagamento());
//            stmt.executeUpdate();
//        }
//    }
//
//    @Override
//    public void deletar(int id) throws SQLException {
//        String sql = "DELETE FROM Pagamento WHERE id_pagamento = ?";
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        }
//    }
//}
//
