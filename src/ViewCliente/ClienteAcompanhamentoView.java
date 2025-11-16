package ViewCliente;



import Controller.ItemPedidoController;
import Controller.PedidoController;
import Controller.RestauranteController;
import Model.ItemPedidoModel;
import Model.PedidoModel;
import Model.RestauranteModel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class ClienteAcompanhamentoView extends JFrame {
    private JLabel lblNumeroPedido, lblRestaurante, lblStatus, lblDataHora;
    private JTextArea txtItens;
    private JLabel lblTotal;
    private JButton btnAtualizar, btnFechar;
    private JProgressBar progressBar;
    private int idClienteLogado;
    private int idPedido;
    private PedidoController pedidoController;
    private ItemPedidoController itemPedidoController;
    private RestauranteController restauranteController;

    public ClienteAcompanhamentoView(int idCliente, int idPedido) {
        this.idClienteLogado = idCliente;
        this.idPedido = idPedido;
        pedidoController = new PedidoController();
        itemPedidoController = new ItemPedidoController();
        restauranteController = new RestauranteController();
        initComponents();
        carregarDadosPedido();
    }

    private void initComponents() {
        setTitle("Acompanhamento do Pedido");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelSuperior.setBackground(new Color(52, 152, 219));

        JLabel lblTitulo = new JLabel("Acompanhamento do Pedido");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        painelSuperior.add(lblTitulo);

        add(painelSuperior, BorderLayout.NORTH);


        JPanel painelCentral = new JPanel(new BorderLayout(10, 10));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        JPanel painelInfo = new JPanel(new GridLayout(4, 2, 10, 10));
        painelInfo.setBorder(BorderFactory.createTitledBorder("Informações do Pedido"));

        painelInfo.add(new JLabel("Número do Pedido:"));
        lblNumeroPedido = new JLabel("#" + idPedido);
        lblNumeroPedido.setFont(new Font("Arial", Font.BOLD, 14));
        painelInfo.add(lblNumeroPedido);

        painelInfo.add(new JLabel("Restaurante:"));
        lblRestaurante = new JLabel("Carregando...");
        painelInfo.add(lblRestaurante);

        painelInfo.add(new JLabel("Data/Hora:"));
        lblDataHora = new JLabel("Carregando...");
        painelInfo.add(lblDataHora);

        painelInfo.add(new JLabel("Status:"));
        lblStatus = new JLabel("Carregando...");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
        painelInfo.add(lblStatus);

        painelCentral.add(painelInfo, BorderLayout.NORTH);


        JPanel painelProgresso = new JPanel(new BorderLayout());
        painelProgresso.setBorder(BorderFactory.createTitledBorder("Progresso"));
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        painelProgresso.add(progressBar, BorderLayout.CENTER);
        painelCentral.add(painelProgresso, BorderLayout.CENTER);


        JPanel painelItens = new JPanel(new BorderLayout());
        painelItens.setBorder(BorderFactory.createTitledBorder("Itens do Pedido"));

        txtItens = new JTextArea();
        txtItens.setEditable(false);
        txtItens.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollItens = new JScrollPane(txtItens);
        painelItens.add(scrollItens, BorderLayout.CENTER);

        lblTotal = new JLabel("Total: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        painelItens.add(lblTotal, BorderLayout.SOUTH);

        painelCentral.add(painelItens, BorderLayout.SOUTH);
        add(painelCentral, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAtualizar = new JButton("Atualizar Status");
        btnAtualizar.setBackground(new Color(52, 152, 219));
        btnAtualizar.setForeground(Color.WHITE);
        painelBotoes.add(btnAtualizar);

        btnFechar = new JButton("Fechar");
        btnFechar.setBackground(new Color(149, 165, 166));
        btnFechar.setForeground(Color.WHITE);
        painelBotoes.add(btnFechar);

        add(painelBotoes, BorderLayout.SOUTH);


        btnAtualizar.addActionListener(e -> carregarDadosPedido());
        btnFechar.addActionListener(e -> dispose());
    }

    private void carregarDadosPedido() {
        PedidoModel pedido = pedidoController.buscarPedidoPorId(idPedido);

        if (pedido != null) {

            RestauranteModel restaurante = restauranteController.buscarRestaurantePorId(pedido.getId_restaurante());
            if (restaurante != null) {
                lblRestaurante.setText(restaurante.getNome());
            }


            lblDataHora.setText(pedido.getData_hora().toString());


            String status = String.valueOf(pedido.getStatus());

            if (status != null) {
                lblStatus.setText(status.toUpperCase());
            } else {
                lblStatus.setText("INDEFINIDO");
                status = "indefinido"; 
            }


            switch (status.toLowerCase()) {
                case "em preparo":
                    lblStatus.setForeground(new Color(241, 196, 15));
                    progressBar.setValue(33);
                    progressBar.setString("Em Preparo (33%)");
                    break;
                case "a caminho":
                    lblStatus.setForeground(new Color(52, 152, 219));
                    progressBar.setValue(66);
                    progressBar.setString("A Caminho (66%)");
                    break;
                case "entregue":
                    lblStatus.setForeground(new Color(46, 204, 113));
                    progressBar.setValue(100);
                    progressBar.setString("Entregue (100%)");
                    break;
                default:
                    lblStatus.setForeground(Color.BLACK);
                    progressBar.setValue(0);
                    progressBar.setString("Aguardando");
            }


            List<ItemPedidoModel> itens = itemPedidoController.listarTodosItensPedido();
            StringBuilder resumoItens = new StringBuilder();
            BigDecimal total = BigDecimal.ZERO;

            if (itens != null) {
                for (ItemPedidoModel item : itens) {
                    if (item.getId_pedido() == idPedido) {
                        BigDecimal subtotal = BigDecimal.valueOf(item.getPreco());
                        resumoItens.append(String.format("%dx %s\n", item.getQuantidade(), item.getDescricao()));
                        resumoItens.append(String.format("   R$ %.2f x %d = R$ %.2f\n\n",
                                item.getPreco(), item.getQuantidade(), subtotal));
                        total = total.add(subtotal);
                    }
                }
            }

            txtItens.setText(resumoItens.toString());
            lblTotal.setText(String.format("Total: R$ %.2f", total));
        } else {
            JOptionPane.showMessageDialog(this, "Pedido não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

