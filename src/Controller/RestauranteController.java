package Controller;

import Model.RestauranteModel;
import Service.RestauranteService;
import java.sql.SQLException;
import java.util.List;

public class RestauranteController {
    private RestauranteService restauranteService;

    public RestauranteController() {
        this.restauranteService = new RestauranteService();
    }

    public void cadastrarRestaurante(RestauranteModel restaurante) {
        try {
            restauranteService.cadastrarRestaurante(restaurante);
            System.out.println("Restaurante cadastrado com sucesso! ID: " + restaurante.getId_restaurante());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao cadastrar restaurante: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao cadastrar restaurante: " + e.getMessage());
        }
    }

    public RestauranteModel buscarRestaurantePorId(int id) {
        try {
            return restauranteService.buscarRestaurantePorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar restaurante: " + e.getMessage());
            return null;
        }
    }

    public List<RestauranteModel> listarTodosRestaurantes() {
        try {
            return restauranteService.listarTodosRestaurantes();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar restaurantes: " + e.getMessage());
            return null;
        }
    }

    public void atualizarRestaurante(RestauranteModel restaurante) {
        try {
            restauranteService.atualizarRestaurante(restaurante);
            System.out.println("Restaurante atualizado com sucesso! ID: " + restaurante.getId_restaurante());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar restaurante: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar restaurante: " + e.getMessage());
        }
    }

    public void deletarRestaurante(int id) {
        try {
            restauranteService.deletarRestaurante(id);
            System.out.println("Restaurante deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar restaurante: " + e.getMessage());
        }
    }
}

