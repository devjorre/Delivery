package Service;

import DAO.TipoRestauranteDAO;
import Model.TipoRestaurante;

import java.sql.SQLException;
import java.util.List;

public class TipoRestauranteService {
    private TipoRestauranteDAO tipoRestauranteDAO;

    public TipoRestauranteService() {
        this.tipoRestauranteDAO = new TipoRestauranteDAO();
    }

    public void adicionarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
        if (tipoRestaurante.getNome() == null || tipoRestaurante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do tipo de restaurante não pode ser vazio.");
        }
        tipoRestauranteDAO.criar(tipoRestaurante);
    }

    public TipoRestaurante buscarTipoRestaurantePorId(int id) throws SQLException {
        return tipoRestauranteDAO.findById(id);
    }

    public List<TipoRestaurante> listarTodosTiposRestaurante() throws SQLException {
        return tipoRestauranteDAO.findAll();
    }

    public void atualizarTipoRestaurante(TipoRestaurante tipoRestaurante) throws SQLException {
        if (tipoRestaurante.getNome() == null || tipoRestaurante.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do tipo de restaurante não pode ser vazio.");
        }
        tipoRestauranteDAO.atualizar(tipoRestaurante);
    }

    public void deletarTipoRestaurante(int id) throws SQLException {
        tipoRestauranteDAO.deletar(id);
    }

    public void criarTipo(TipoRestaurante tipo) {

    }
}
