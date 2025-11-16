package Controller;

import Model.ClienteModel;
import Service.ClienteService;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    public void cadastrarCliente(ClienteModel cliente) {
        try {
            clienteService.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId_cliente());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao cadastrar cliente: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao cadastrar cliente: " + e.getMessage());
        }
    }

    public ClienteModel buscarClientePorId(int id) {
        try {
            return clienteService.buscarClientePorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar cliente: " + e.getMessage());
            return null;
        }
    }

    public List<ClienteModel> listarTodosClientes() {
        try {
            return clienteService.listarTodosClientes();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar clientes: " + e.getMessage());
            return null;
        }
    }

    public void atualizarCliente(ClienteModel cliente) {
        try {
            clienteService.atualizarCliente(cliente);
            System.out.println("Cliente atualizado com sucesso! ID: " + cliente.getId_cliente());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar cliente: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar cliente: " + e.getMessage());
        }
    }

    public void deletarCliente(int id) {
        try {
            clienteService.deletarCliente(id);
            System.out.println("Cliente deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar cliente: " + e.getMessage());
        }
    }
}

