package ViewCliente;


import Controller.PedidoController;
import Controller.RestauranteController;
import Model.CardapioModel;
import Model.PedidoModel;
import Model.RestauranteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteHistoricoDePedidosView extends JFrame {
    private JTable tabelaPedidos;
    private DefaultTableModel modeloTabela;
    private JButton btnVerDetalhes, btnAtualizar;
    private int idClienteLogado;
    private PedidoController pedidoController;
    private RestauranteController restauranteController;

    public ClienteHistoricoDePedidosView(int idCliente, CardapioModel restauranteSelecionado) {
        this.idClienteLogado = idCliente;
        pedidoController = new PedidoController();
        restauranteController = new RestauranteController();
        initComponents(restauranteSelecionado);
        carregarPedidos(restauranteSelecionado);
    }

    private void initComponents(CardapioModel restauranteSelecionado) {
        setTitle("Meus Pedidos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelSuperior.setBackground(new Color(52, 152, 219));

        JLabel lblTitulo = new JLabel("Histórico de Pedidos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        painelSuperior.add(lblTitulo);

        add(painelSuperior, BorderLayout.NORTH);


        String[] colunas = {"Nº Pedido", "Restaurante", "Data/Hora", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaPedidos = new JTable(modeloTabela);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPedidos.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        add(scrollPane, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBackground(new Color(52, 152, 219));
        btnAtualizar.setForeground(Color.WHITE);
        painelBotoes.add(btnAtualizar);

        btnVerDetalhes = new JButton("Ver Detalhes");
        btnVerDetalhes.setBackground(new Color(46, 204, 113));
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.BOLD, 14));
        painelBotoes.add(btnVerDetalhes);

        add(painelBotoes, BorderLayout.SOUTH);

        btnAtualizar.addActionListener(e -> carregarPedidos(restauranteSelecionado));
        btnVerDetalhes.addActionListener(e -> verDetalhes());
    }

    private void carregarPedidos(CardapioModel restauranteSelecionado) {
        modeloTabela.setRowCount(0);
        List<PedidoModel> pedidos = pedidoController.listarTodosPedidos();

        if (pedidos != null) {
            for (PedidoModel pedido : pedidos) {
                if (pedido.getId_cliente() == idClienteLogado) {
                    RestauranteModel restaurante = restauranteController.buscarRestaurantePorId(pedido.getId_restaurante());
                    String nomeRestaurante = restaurante != null ? restaurante.getNome() : "Desconhecido";

                    Object[] linha = {
                            "#" + pedido.getId_pedido(),
                            nomeRestaurante,
                            pedido.getData_hora().toString(),
                            (pedido.getStatus() != null ? pedido.getStatus().name().toUpperCase() : "N/A")



                    };
                    modeloTabela.addRow(linha);
                }
            }
        }
    }

    private void verDetalhes() {
        int linhaSelecionada = tabelaPedidos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para ver os detalhes!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String numeroPedido = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        int idPedido = Integer.parseInt(numeroPedido.substring(1)); // Remove o '#'

        ClienteAcompanhamentoView detalhesView = new ClienteAcompanhamentoView(idClienteLogado, idPedido);
        detalhesView.setVisible(true);
    }
}

