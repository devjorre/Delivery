//package ViewCliente;
//
//import Controller.EnderecoController;
//import Controller.ItemPedidoController;
//import Controller.PagamentoController;
//import Controller.PedidoController;
//import Model.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//
//public class ClienteCheckoutView extends JFrame {
//    private JComboBox<String> cbEndereco, cbMetodoPagamento;
//    private JTextArea txtResumo;
//    private JLabel lblTotal;
//    private JButton btnConfirmar, btnCancelar;
//    private int idClienteLogado;
//    private int idRestaurante;
//    private Map<Integer, ClienteCardapioView.ItemCarrinho> carrinho;
//    private EnderecoController enderecoController;
//    private PedidoController pedidoController;
//    private ItemPedidoController itemPedidoController;
//    private PagamentoController pagamentoController;
//    private List<EnderecoModel> enderecos;
//
//    public ClienteCheckoutView(int idCliente, int idRestaurante, Map<Integer, ClienteCardapioView.ItemCarrinho> carrinho, CardapioModel restauranteSelecionado) {
//        this.idClienteLogado = idCliente;
//        this.idRestaurante = idRestaurante;
//        this.carrinho = carrinho;
//        enderecoController = new EnderecoController();
//        pedidoController = new PedidoController();
//        itemPedidoController = new ItemPedidoController();
//        pagamentoController = new PagamentoController();
//        initComponents(restauranteSelecionado);
//        carregarEnderecos();
//        exibirResumo();
//    }
//
//    private void initComponents(CardapioModel restauranteSelecionado) {
//        setTitle("Finalizar Pedido");
//        setSize(700, 600);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setLayout(new BorderLayout(10, 10));
//
//
//        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
//        painelSuperior.setBackground(new Color(52, 152, 219));
//
//        JLabel lblTitulo = new JLabel("Finalizar Pedido");
//        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
//        lblTitulo.setForeground(Color.WHITE);
//        painelSuperior.add(lblTitulo);
//
//        add(painelSuperior, BorderLayout.NORTH);
//
//
//        JPanel painelCentral = new JPanel(new BorderLayout(10, 10));
//        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//
//        JPanel painelDados = new JPanel(new GridLayout(3, 2, 10, 10));
//        painelDados.setBorder(BorderFactory.createTitledBorder("Dados do Pedido"));
//
//        painelDados.add(new JLabel("Endereço de Entrega:"));
//        cbEndereco = new JComboBox<>();
//        painelDados.add(cbEndereco);
//
//        painelDados.add(new JLabel("Método de Pagamento:"));
//        cbMetodoPagamento = new JComboBox<>(new String[]{"cartão", "pix", "dinheiro"});
//        painelDados.add(cbMetodoPagamento);
//
//        painelCentral.add(painelDados, BorderLayout.NORTH);
//
//
//        JPanel painelResumo = new JPanel(new BorderLayout());
//        painelResumo.setBorder(BorderFactory.createTitledBorder("Resumo do Pedido"));
//
//        txtResumo = new JTextArea();
//        txtResumo.setEditable(false);
//        txtResumo.setFont(new Font("Monospaced", Font.PLAIN, 12));
//        JScrollPane scrollResumo = new JScrollPane(txtResumo);
//        painelResumo.add(scrollResumo, BorderLayout.CENTER);
//
//        lblTotal = new JLabel("Total: R$ 0,00");
//        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
//        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
//        painelResumo.add(lblTotal, BorderLayout.SOUTH);
//
//        painelCentral.add(painelResumo, BorderLayout.CENTER);
//        add(painelCentral, BorderLayout.CENTER);
//
//
//        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
//
//        btnCancelar = new JButton("Cancelar");
//        btnCancelar.setBackground(new Color(231, 76, 60));
//        btnCancelar.setForeground(Color.WHITE);
//        painelBotoes.add(btnCancelar);
//
//        btnConfirmar = new JButton("Confirmar Pedido");
//        btnConfirmar.setBackground(new Color(46, 204, 113));
//        btnConfirmar.setForeground(Color.WHITE);
//        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
//        painelBotoes.add(btnConfirmar);
//
//        add(painelBotoes, BorderLayout.SOUTH);
//
//
//        btnCancelar.addActionListener(e -> dispose());
//        btnConfirmar.addActionListener(e -> confirmarPedido(restauranteSelecionado));
//    }
//
//    private void carregarEnderecos() {
//        enderecos = enderecoController.listarTodosEnderecos();
//        cbEndereco.removeAllItems();
//
//        if (enderecos != null) {
//            for (EnderecoModel endereco : enderecos) {
//                if (endereco.getId_cliente() == idClienteLogado) {
//                    String enderecoStr = String.format("%s, %s - %s, %s/%s",
//                            endereco.getRua(),
//                            endereco.getNumero(),
//                            endereco.getBairro(),
//                            endereco.getCidade(),
//                            endereco.getEstado());
//                    cbEndereco.addItem(enderecoStr);
//                }
//            }
//        }
//
//        if (cbEndereco.getItemCount() == 0) {
//            cbEndereco.addItem("Nenhum endereço cadastrado");
//            btnConfirmar.setEnabled(false);
//        }
//    }
//
//    private void exibirResumo() {
//        StringBuilder resumo = new StringBuilder();
//        resumo.append("ITENS DO PEDIDO:\n");
//        resumo.append("----------------------------------------\n");
//
//        BigDecimal total = BigDecimal.ZERO;
//
//        for (ClienteCardapioView.ItemCarrinho item : carrinho.values()) {
//            resumo.append(String.format("%dx %s\n", item.quantidade, item.nomeItem));
//            resumo.append(String.format("   R$ %.2f x %d = R$ %.2f\n\n",
//                    item.preco, item.quantidade, item.getSubtotal()));
//            total = total.add(item.getSubtotal());
//        }
//
//        resumo.append("----------------------------------------\n");
//        resumo.append(String.format("TOTAL: R$ %.2f", total));
//
//        txtResumo.setText(resumo.toString());
//        lblTotal.setText(String.format("Total: R$ %.2f", total));
//    }
//
//    private void confirmarPedido(CardapioModel restauranteSelecionado) {
//        if (cbEndereco.getSelectedIndex() == -1 || cbEndereco.getItemAt(0).equals("Nenhum endereço cadastrado")) {
//            JOptionPane.showMessageDialog(this, "Selecione um endereço de entrega!", "Erro", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        int confirmacao = JOptionPane.showConfirmDialog(this,
//                "Confirmar pedido?",
//                "Confirmação",
//                JOptionPane.YES_NO_OPTION);
//
//        if (confirmacao == JOptionPane.YES_OPTION) {
//            try {
//
//                PedidoModel pedido = new PedidoModel(
//                        0,
//                        idClienteLogado,
//                        idRestaurante,
//                        LocalDateTime.now(),
//                        "em preparo"
//                );
//                pedidoController.cadastrarPedido(pedido);
//
//
//                List<PedidoModel> pedidos = pedidoController.listarTodosPedidos();
//                int idPedido = pedidos.get(pedidos.size() - 1).getId_pedido();
//
//
//                for (ClienteCardapioView.ItemCarrinho item : carrinho.values()) {
//                    ItemPedidoModel itemPedido = new ItemPedidoModel(
//                            0,
//                            idPedido,
//                            item.nomeItem,
//                            item.quantidade,
//                            Double.valueOf(String.valueOf(item.preco)),
//                            "em preparo"
//                    );
//                    itemPedidoController.cadastrarItemPedido(itemPedido);
//                }
//
//
//                BigDecimal total = calcularTotal();
//                PagamentoModel pagamento = new PagamentoModel(
//                        0,
//                        idPedido,
//                        cbMetodoPagamento.getSelectedItem().toString(),
//                        total,
//                        "pendente"
//                );
//                pagamentoController.cadastrarPagamento(pagamento);
//
//
//                ClienteCardapioView.limparCarrinho();
//
//                JOptionPane.showMessageDialog(this,
//                        "Pedido realizado com sucesso!\nNúmero do pedido: " + idPedido,
//                        "Sucesso",
//                        JOptionPane.INFORMATION_MESSAGE);
//
//
//                ClienteAcompanhamentoView acompanhamentoView = new ClienteAcompanhamentoView(idClienteLogado, idPedido);
//                acompanhamentoView.setVisible(true);
//                dispose();
//
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this,
//                        "Erro ao finalizar pedido: " + ex.getMessage(),
//                        "Erro",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    private BigDecimal calcularTotal() {
//        BigDecimal total = BigDecimal.ZERO;
//        for (ClienteCardapioView.ItemCarrinho item : carrinho.values()) {
//            total = total.add(item.getSubtotal());
//        }
//        return total;
//    }
//}
//
