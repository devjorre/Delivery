package Controller;

import DAO.PedidoDAO;
import Model.PedidoModel;
import Service.PedidoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController() {
        this.pedidoService = new PedidoService();
    }

    public void criarPedido(PedidoModel pedido) {
        try {
            pedidoService.criarPedido(pedido);
            System.out.println("Pedido criado com sucesso! ID: " + pedido.getId_pedido());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar pedido: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao criar pedido: " + e.getMessage());
        }
    }

    public PedidoModel buscarPedidoPorId(int id) {
        try {
            return pedidoService.buscarPedidoPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar pedido: " + e.getMessage());
            return null;
        }
    }

    public List<PedidoModel> listarPedidosPorRestaurante(int idRestaurante) {
        try {
            return pedidoService.listarPedidosPorRestaurante(idRestaurante);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar pedidos por restaurante: " + e.getMessage());
            return null;
        }
    }

    public List<PedidoModel> listarTodosPedidos() {
        try {
            return pedidoService.listarTodosPedidos();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar todos os pedidos: " + e.getMessage());
            return null;
        }
    }

    public void atualizarPedido(PedidoModel pedido) {
        try {
            pedidoService.atualizarPedido(pedido);
            System.out.println("Pedido atualizado com sucesso! ID: " + pedido.getId_pedido());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar pedido: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar pedido: " + e.getMessage());
        }
    }

    public void deletarPedido(int id) {
        try {
            pedidoService.deletarPedido(id);
            System.out.println("Pedido deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar pedido: " + e.getMessage());
        }
    }

    public void cadastrarPedido(PedidoModel pedido) {
        try {
            pedidoService.criarPedido(pedido);
            System.out.println("Pedido cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar pedido: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado ao cadastrar pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void atualizarPedido(int idPedido, String novoStatus) {
    }


}
