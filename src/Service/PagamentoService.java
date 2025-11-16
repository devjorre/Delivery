//package Service;
//
//import DAO.PagamentoDAO;
//import Model.PagamentoModel;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class PagamentoService {
//    private PagamentoDAO pagamentoDAO;
//
//    public PagamentoService() {
//        this.pagamentoDAO = new PagamentoDAO();
//    }
//
//    public void registrarPagamento(PagamentoModel pagamento) throws SQLException {if (pagamento.getMetodo() == null || pagamento.getValor_total() <= 0) {
//        throw new IllegalArgumentException("Dados do pagamento inválidos.");
//    }
//
//        pagamentoDAO.criar(pagamento);
//    }
//
//    public PagamentoModel buscarPagamentoPorId(int id) throws SQLException {
//        return pagamentoDAO.findById(id);
//    }
//
//    public List<PagamentoModel> listarTodosPagamentos() throws SQLException {
//        return pagamentoDAO.findAll();
//    }
//
//    public void atualizarPagamento(PagamentoModel pagamento) throws SQLException {
//        if (pagamento.getId_pedido() <= 0 || pagamento.getMetodo() == null || pagamento.getMetodo().toString().isEmpty() || pagamento.getValor_total() <= 0) {
//            throw new IllegalArgumentException("Dados do pagamento inválidos.");
//        }
//        pagamentoDAO.atualizar(pagamento);
//    }
//
//    public void deletarPagamento(int id) throws SQLException {
//        pagamentoDAO.deletar(id);
//    }
//}
//
