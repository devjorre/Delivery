//package Controller;
//
//
//import Model.PagamentoModel;
//import Service.PagamentoService;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class PagamentoController {
//    private PagamentoService pagamentoService;
//
//    public PagamentoController() {
//        this.pagamentoService = new PagamentoService();
//    }
//
//    public void registrarPagamento(PagamentoModel pagamento) {
//        try {
//            pagamentoService.registrarPagamento(pagamento);
//            System.out.println("Pagamento registrado com sucesso! ID: " + pagamento.getId_pagamento());
//        } catch (IllegalArgumentException e) {
//            System.err.println("Erro de validação ao registrar pagamento: " + e.getMessage());
//        } catch (SQLException e) {
//            System.err.println("Erro de banco de dados ao registrar pagamento: " + e.getMessage());
//        }
//    }
//
//    public PagamentoModel buscarPagamentoPorId(int id) {
//        try {
//            return pagamentoService.buscarPagamentoPorId(id);
//        } catch (SQLException e) {
//            System.err.println("Erro de banco de dados ao buscar pagamento: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public List<PagamentoModel> listarTodosPagamentos() {
//        try {
//            return pagamentoService.listarTodosPagamentos();
//        } catch (SQLException e) {
//            System.err.println("Erro de banco de dados ao listar pagamentos: " + e.getMessage());
//            return null;
//        }
//    }
//
//    public void atualizarPagamento(PagamentoModel pagamento) {
//        try {
//            pagamentoService.atualizarPagamento(pagamento);
//            System.out.println("Pagamento atualizado com sucesso! ID: " + pagamento.getId_pagamento());
//        } catch (IllegalArgumentException e) {
//            System.err.println("Erro de validação ao atualizar pagamento: " + e.getMessage());
//        } catch (SQLException e) {
//            System.err.println("Erro de banco de dados ao atualizar pagamento: " + e.getMessage());
//        }
//    }
//
//    public void deletarPagamento(int id) {
//        try {
//            pagamentoService.deletarPagamento(id);
//            System.out.println("Pagamento deletado com sucesso! ID: " + id);
//        } catch (SQLException e) {
//            System.err.println("Erro de banco de dados ao deletar pagamento: " + e.getMessage());
//        }
//    }
//
//    public void cadastrarPagamento(PagamentoModel pagamento) {
//
//    }
//}
//
