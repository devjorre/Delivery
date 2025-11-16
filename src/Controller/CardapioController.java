package Controller;

import Model.CardapioModel;
import Service.CardapioService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardapioController {
    private CardapioService cardapioService;

    public CardapioController() {
        this.cardapioService = new CardapioService();
    }

    public void adicionarItemCardapio(CardapioModel cardapio) {
        try {
            cardapioService.adicionarItemCardapio(cardapio);
            System.out.println("Item de cardápio adicionado com sucesso! ID: " + cardapio.getId_cardapio());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao adicionar item de cardápio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao adicionar item de cardápio: " + e.getMessage());
        }
    }

    public CardapioModel buscarItemCardapioPorId(int id) {
        try {
            return cardapioService.buscarItemCardapioPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao buscar item de cardápio: " + e.getMessage());
            return null;
        }
    }

    public List<CardapioModel> listarTodosItensCardapio() {
        try {
            return cardapioService.listarTodosItensCardapio();
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar itens de cardápio: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Lista todos os itens do cardápio de um restaurante específico.
     * @param idRestaurante ID do restaurante
     * @return Lista de CardapioModel do restaurante
     */
    public List<CardapioModel> listarCardapiosPorRestaurante(int idRestaurante) {
        try {
            return cardapioService.listarCardapiosPorRestaurante(idRestaurante);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao listar cardápios do restaurante: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void atualizarItemCardapio(CardapioModel cardapio) {
        try {
            cardapioService.atualizarItemCardapio(cardapio);
            System.out.println("Item de cardápio atualizado com sucesso! ID: " + cardapio.getId_cardapio());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao atualizar item de cardápio: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao atualizar item de cardápio: " + e.getMessage());
        }
    }

    public void deletarItemCardapio(int id) {
        try {
            cardapioService.deletarItemCardapio(id);
            System.out.println("Item de cardápio deletado com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados ao deletar item de cardápio: " + e.getMessage());
        }
    }

    public List<CardapioModel> listarTodosCardapios() {
        return listarTodosItensCardapio();
    }
}
