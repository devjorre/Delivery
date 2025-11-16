package Service;

import DAO.RestauranteDAO;
import Model.RestauranteModel;

import java.sql.SQLException;
import java.util.List;

public class RestauranteService {
    private RestauranteDAO restauranteDAO;

    public RestauranteService() {
        this.restauranteDAO = new RestauranteDAO();
    }

    public void cadastrarRestaurante(RestauranteModel restaurante) throws SQLException {
        if (restaurante.getNome() == null || restaurante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser vazio.");
        }
        restauranteDAO.criar(restaurante);
    }

    public RestauranteModel buscarRestaurantePorId(int id) throws SQLException {
        return restauranteDAO.findById(id);
    }

    public List<RestauranteModel> listarTodosRestaurantes() throws SQLException {
        return restauranteDAO.findAll();
    }

    public void atualizarRestaurante(RestauranteModel restaurante) throws SQLException {
        if (restaurante.getNome() == null || restaurante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante não pode ser vazio.");
        }
        restauranteDAO.atualizar(restaurante);
    }

    public void deletarRestaurante(int id) throws SQLException {
        restauranteDAO.deletar(id);
    }
}

