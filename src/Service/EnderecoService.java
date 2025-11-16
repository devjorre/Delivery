package Service;

import DAO.EnderecoDAO;
import Model.EnderecoModel;

import java.sql.SQLException;
import java.util.List;

public class EnderecoService {
    private EnderecoDAO enderecoDAO;

    public EnderecoService() {
        this.enderecoDAO = new EnderecoDAO();
    }

    public void cadastrarEndereco(EnderecoModel endereco) throws SQLException {
        if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
            throw new IllegalArgumentException("Rua do endereço não pode ser vazia.");
        }
        enderecoDAO.criar(endereco);
    }

    public EnderecoModel buscarEnderecoPorId(int id) throws SQLException {
        return enderecoDAO.findById(id);
    }

    public List<EnderecoModel> listarTodosEnderecos() throws SQLException {
        return enderecoDAO.findAll();
    }

    public void atualizarEndereco(EnderecoModel endereco) throws SQLException {
        if (endereco.getRua() == null || endereco.getRua().trim().isEmpty()) {
            throw new IllegalArgumentException("Rua do endereço não pode ser vazia.");
        }
        enderecoDAO.atualizar(endereco);
    }

    public void deletarEndereco(int id) throws SQLException {
        enderecoDAO.deletar(id);
    }
}

