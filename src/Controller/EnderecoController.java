package Controller;

import Model.EnderecoModel;
import Service.EnderecoService;

import java.sql.SQLException;
import java.util.List;

public class EnderecoController {
    private EnderecoService enderecoService;

    public EnderecoController() {
        this.enderecoService = new EnderecoService();
    }

    public void cadastrarEndereco(EnderecoModel endereco) {
        try {
            enderecoService.cadastrarEndereco(endereco);
            System.out.println("Endereço cadastrado com sucesso! ID: " + endereco.getId_Endereco());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao cadastrar endereço: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao cadastrar endereço: " + e.getMessage());
        }
    }

    public EnderecoModel buscarEnderecoPorId(int id) {
        try {
            return enderecoService.buscarEnderecoPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar endereço: " + e.getMessage());
            return null;
        }
    }

    public List<EnderecoModel> listarTodosEnderecos() {
        try {
            return enderecoService.listarTodosEnderecos();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar endereços: " + e.getMessage());
            return null;
        }
    }

    public void atualizarEndereco(EnderecoModel endereco) {
        try {
            enderecoService.atualizarEndereco(endereco);
            System.out.println("Endereço atualizado com sucesso! ID: " + endereco.getId_Endereco());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar endereço: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar endereço: " + e.getMessage());
        }
    }

    public void deletarEndereco(int id) {
        try {
            enderecoService.deletarEndereco(id);
            System.out.println("Endereço deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar endereço: " + e.getMessage());
        }
    }
}

