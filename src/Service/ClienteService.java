package Service;

import DAO.ClienteDAO;
import Model.ClienteModel;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public void cadastrarCliente(ClienteModel cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        clienteDAO.criar(cliente);
    }

    public ClienteModel buscarClientePorId(int id) throws SQLException {
        return clienteDAO.findById(id);
    }

    public List<ClienteModel> listarTodosClientes() throws SQLException {
        return clienteDAO.findAll();
    }

    public void atualizarCliente(ClienteModel cliente) throws SQLException {

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        clienteDAO.atualizar(cliente);
    }

    public void deletarCliente(int id) throws SQLException {
        clienteDAO.deletar(id);
    }
}

