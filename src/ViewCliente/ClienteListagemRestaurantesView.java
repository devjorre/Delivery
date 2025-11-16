package ViewCliente;


import Controller.RestauranteController;
import Model.RestauranteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteListagemRestaurantesView extends JFrame {
    private JTextField txtBusca;
    private JComboBox<String> cbTipoCozinha;
    private JButton btnBuscar, btnLimpar, btnVerCardapio;
    private JTable tabelaRestaurantes;
    private DefaultTableModel modeloTabela;
    private RestauranteController controller;
    private int idClienteLogado;

    public ClienteListagemRestaurantesView(int idCliente) {
        this.idClienteLogado = idCliente;
        controller = new RestauranteController();
        initComponents();
        carregarRestaurantes();
    }

    private void initComponents() {
        setTitle("Delivery - Restaurantes Disponíveis");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelSuperior.setBackground(new Color(52, 152, 219));

        JLabel lblTitulo = new JLabel("Restaurantes Disponíveis");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        painelSuperior.add(lblTitulo);

        add(painelSuperior, BorderLayout.NORTH);


        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelBusca.setBorder(BorderFactory.createTitledBorder("Buscar Restaurantes"));

        painelBusca.add(new JLabel("Nome:"));
        txtBusca = new JTextField(20);
        painelBusca.add(txtBusca);

        painelBusca.add(new JLabel("Tipo de Cozinha:"));
        cbTipoCozinha = new JComboBox<>(new String[]{"Todos", "Italiana", "Japonesa", "Brasileira", "Mexicana", "Fast Food", "Vegetariana"});
        painelBusca.add(cbTipoCozinha);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(46, 204, 113));
        btnBuscar.setForeground(Color.WHITE);
        painelBusca.add(btnBuscar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBackground(new Color(149, 165, 166));
        btnLimpar.setForeground(Color.WHITE);
        painelBusca.add(btnLimpar);

        add(painelBusca, BorderLayout.CENTER);


        JPanel painelTabela = new JPanel(new BorderLayout());
        String[] colunas = {"ID", "Nome", "Tipo de Cozinha", "Telefone"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaRestaurantes = new JTable(modeloTabela);
        tabelaRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaRestaurantes.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabelaRestaurantes);
        painelTabela.add(scrollPane, BorderLayout.CENTER);


        JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnVerCardapio = new JButton("Ver Cardápio");
        btnVerCardapio.setBackground(new Color(241, 196, 15));
        btnVerCardapio.setForeground(Color.WHITE);
        btnVerCardapio.setFont(new Font("Arial", Font.BOLD, 14));
        painelAcoes.add(btnVerCardapio);
        painelTabela.add(painelAcoes, BorderLayout.SOUTH);


        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(painelBusca, BorderLayout.NORTH);
        painelCentral.add(painelTabela, BorderLayout.CENTER);
        add(painelCentral, BorderLayout.CENTER);


        btnBuscar.addActionListener(e -> buscarRestaurantes());
        btnLimpar.addActionListener(e -> limparBusca());
        btnVerCardapio.addActionListener(e -> verCardapio());
    }

    private void carregarRestaurantes() {
        modeloTabela.setRowCount(0);
        List<RestauranteModel> restaurantes = controller.listarTodosRestaurantes();
        if (restaurantes != null) {
            for (RestauranteModel restaurante : restaurantes) {
                Object[] linha = {
                        restaurante.getId_restaurante(),
                        restaurante.getNome(),
                        restaurante.getTipo_cozinha(),
                        restaurante.getTelefone()
                };
                modeloTabela.addRow(linha);
            }
        }
    }

    private void buscarRestaurantes() {
        String nomeBusca = txtBusca.getText().trim().toLowerCase();
        String tipoCozinha = cbTipoCozinha.getSelectedItem().toString();

        modeloTabela.setRowCount(0);
        List<RestauranteModel> restaurantes = controller.listarTodosRestaurantes();

        if (restaurantes != null) {
            for (RestauranteModel restaurante : restaurantes) {
                boolean matchNome = nomeBusca.isEmpty() || restaurante.getNome().toLowerCase().contains(nomeBusca);
                boolean matchTipo = tipoCozinha.equals("Todos") || restaurante.getTipo_cozinha().equalsIgnoreCase(tipoCozinha);

                if (matchNome && matchTipo) {
                    Object[] linha = {
                            restaurante.getId_restaurante(),
                            restaurante.getNome(),
                            restaurante.getTipo_cozinha(),
                            restaurante.getTelefone()
                    };
                    modeloTabela.addRow(linha);
                }
            }
        }
    }

    private void limparBusca() {
        txtBusca.setText("");
        cbTipoCozinha.setSelectedIndex(0);
        carregarRestaurantes();
    }

    private void verCardapio() {
        int linhaSelecionada = tabelaRestaurantes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um restaurante para ver o cardápio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idRestaurante = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeRestaurante = (String) modeloTabela.getValueAt(linhaSelecionada, 1);

        ClienteCardapioView cardapioView = new ClienteCardapioView(idClienteLogado, idRestaurante, nomeRestaurante);
        cardapioView.setVisible(true);
    }
}

