package Service;

import DAO.CardapioDAO;
import Model.CardapioModel;

import java.sql.SQLException;
import java.util.List;

public class CardapioService {
    private CardapioDAO cardapioDAO;

    public CardapioService() {
        this.cardapioDAO = new CardapioDAO();
    }

    public void adicionarItemCardapio(CardapioModel cardapio) throws SQLException {
        if (cardapio.getId_restaurante() <= 0 ||
                cardapio.getNome_item() == null || cardapio.getNome_item().trim().isEmpty() ||
                cardapio.getPreco() <= 0) {
            throw new IllegalArgumentException("Dados do item do cardápio inválidos.");
        }

        cardapioDAO.criar(cardapio);
    }

    public CardapioModel buscarItemCardapioPorId(int id) throws SQLException {
        return cardapioDAO.findById(id);
    }

    public List<CardapioModel> listarTodosItensCardapio() throws SQLException {
        return cardapioDAO.findAll();
    }


    public List<CardapioModel> listarCardapiosPorRestaurante(int idRestaurante) throws SQLException {
        return cardapioDAO.findByRestaurante(idRestaurante);
    }

    public void atualizarItemCardapio(CardapioModel cardapio) throws SQLException {
        if (cardapio.getId_restaurante() <= 0 ||
                cardapio.getNome_item() == null || cardapio.getNome_item().trim().isEmpty() ||
                cardapio.getPreco() <= 0) {
            throw new IllegalArgumentException("Dados do item do cardápio inválidos.");
        }

        cardapioDAO.atualizar(cardapio);
    }

    public void deletarItemCardapio(int id) throws SQLException {
        cardapioDAO.deletar(id);
    }
}
