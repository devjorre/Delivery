//package Model;
//
//import java.math.BigDecimal;
//import Enum.MetodoPagamento;
//import jdk.jshell.Snippet;
//import Enum.PagamentoStatus;
//public class PagamentoModel {
//    private int id_pagamento;
//
//    private int id_pedido;
//
//    private MetodoPagamento metodo;
//
//    private double valor_total;
//
//    private PagamentoStatus status;
//
//    public PagamentoModel(int id_pagamento, int id_pedido, MetodoPagamento metodo, double valor_total, PagamentoStatus status) {
//        this.id_pagamento = id_pagamento;
//        this.id_pedido = id_pedido;
//        this.metodo = metodo;
//        this.valor_total = valor_total;
//        this.status = status;
//    }
//
//    public PagamentoModel(int idPagamento, int idPedido, String string, BigDecimal total, String pendente) {
//    }
//
//    public int getId_pagamento() {
//        return id_pagamento;
//    }
//
//    public void setId_pagamento(int id_pagamento) {
//        this.id_pagamento = id_pagamento;
//    }
//
//    public int getId_pedido() {
//        return id_pedido;
//    }
//
//    public void setId_pedido(int id_pedido) {
//        this.id_pedido = id_pedido;
//    }
//
//    public MetodoPagamento getMetodo() {
//        return metodo;
//    }
//
//    public void setMetodo(MetodoPagamento metodo) {
//        this.metodo = metodo;
//    }
//
//    public double getValor_total() {
//        return valor_total;
//    }
//
//    public void setValor_total(int valor_total) {
//        this.valor_total = valor_total;
//    }
//
//    public PagamentoStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(PagamentoStatus status) {
//        this.status = status;
//    }
//}
