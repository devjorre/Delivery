package Controller;

import Model.TipoRestaurante;
import Service.TipoRestauranteService;

import java.sql.SQLException;
import java.util.List;

public class TipoRestauranteController {
    private final TipoRestauranteService tipoService;

    public TipoRestauranteController() {
        this.tipoService = new TipoRestauranteService();
    }

    public void adicionarTipo(String nome) {
        TipoRestaurante tipo = new TipoRestaurante(nome);
        tipoService.criarTipo(tipo);
        System.out.println("Tipo de restaurante cadastrado com sucesso!");
    }

    public void listarTipos() {
        try {
            List<TipoRestaurante> tipos = tipoService.listarTodosTiposRestaurante();
            for (TipoRestaurante t : tipos) {
                System.out.println("ID: " + t.getId_tipo() + " | Nome: " + t.getNome());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tipos: " + e.getMessage());
        }
    }

    public void atualizarTipo(int id, String novoNome) {
        try {
            TipoRestaurante tipo = new TipoRestaurante(id, novoNome);
            tipoService.atualizarTipoRestaurante(tipo);
            System.out.println("Tipo de restaurante atualizado!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tipo: " + e.getMessage());
        }
    }

    public void deletarTipo(int id) {
        try {
            tipoService.deletarTipoRestaurante(id);
            System.out.println("Tipo de restaurante deletado!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tipo: " + e.getMessage());
        }
    }
}
