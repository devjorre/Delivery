package Service;

import DAO.PedidoDAO;
import Model.PedidoModel;

import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    private PedidoDAO pedidoDAO;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
    }

    public void criarPedido(PedidoModel pedido) throws SQLException {
        if (pedido.getId_cliente() <= 0 || pedido.getId_restaurante() <= 0) {
            throw new IllegalArgumentException("IDs de cliente e restaurante devem ser válidos.");
        }
        pedidoDAO.criar(pedido);
    }

    public PedidoModel buscarPedidoPorId(int id) throws SQLException {
        return pedidoDAO.findById(id);
    }

    public List<PedidoModel> listarTodosPedidos() throws SQLException {
        return pedidoDAO.findAll();
    }

    public List<PedidoModel> listarPedidosPorRestaurante(int idRestaurante) throws SQLException {
        return pedidoDAO.findByRestaurante(idRestaurante);
    }

    public void atualizarPedido(PedidoModel pedido) throws SQLException {
        if (pedido.getId_cliente() <= 0 || pedido.getId_restaurante() <= 0) {
            throw new IllegalArgumentException("IDs de cliente e restaurante devem ser válidos.");
        }
        pedidoDAO.atualizar(pedido);
    }

    public void deletarPedido(int id) throws SQLException {
        pedidoDAO.deletar(id);
    }

}