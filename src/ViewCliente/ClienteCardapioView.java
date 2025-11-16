package ViewCliente;

import Controller.CardapioController;
import Model.CardapioModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteCardapioView extends JFrame {
    private JTable tabelaCardapio;
    private DefaultTableModel modeloTabela;
    private JButton btnAdicionarCarrinho, btnVerCarrinho;
    private JSpinner spinnerQuantidade;
    private CardapioController controller;
    private int idClienteLogado;
    private int idRestaurante;
    private String nomeRestaurante;


    private static Map<Integer, ItemCarrinho> carrinho = new HashMap<>();

    public ClienteCardapioView(int idCliente, int idRestaurante, String nomeRestaurante) {
        this.idClienteLogado = idCliente;
        this.idRestaurante = idRestaurante;
        this.nomeRestaurante = nomeRestaurante;
        controller = new CardapioController();
        initComponents();
        carregarCardapio();
    }

    private void initComponents() {
        setTitle("Cardápio - " + nomeRestaurante);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelSuperior.setBackground(new Color(52, 152, 219));

        JLabel lblTitulo = new JLabel("Cardápio - " + nomeRestaurante);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        painelSuperior.add(lblTitulo);

        add(painelSuperior, BorderLayout.NORTH);


        String[] colunas = {"ID", "Nome do Item", "Descrição", "Preço (R$)", "Disponível"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaCardapio = new JTable(modeloTabela);
        tabelaCardapio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCardapio.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(tabelaCardapio);
        add(scrollPane, BorderLayout.CENTER);


        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        painelInferior.add(new JLabel("Quantidade:"));
        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        painelInferior.add(spinnerQuantidade);

        btnAdicionarCarrinho = new JButton("Adicionar ao Carrinho");
        btnAdicionarCarrinho.setBackground(new Color(46, 204, 113));
        btnAdicionarCarrinho.setForeground(Color.WHITE);
        btnAdicionarCarrinho.setFont(new Font("Arial", Font.BOLD, 14));
        painelInferior.add(btnAdicionarCarrinho);

        btnVerCarrinho = new JButton("Ver Carrinho");
        btnVerCarrinho.setBackground(new Color(241, 196, 15));
        btnVerCarrinho.setForeground(Color.WHITE);
        btnVerCarrinho.setFont(new Font("Arial", Font.BOLD, 14));
        painelInferior.add(btnVerCarrinho);

        add(painelInferior, BorderLayout.SOUTH);


        btnAdicionarCarrinho.addActionListener(e -> adicionarAoCarrinho());
        btnVerCarrinho.addActionListener(e -> verCarrinho());
    }

    private void carregarCardapio() {
        modeloTabela.setRowCount(0);
        List<CardapioModel> itens = controller.listarTodosCardapios();

        if (itens != null) {
            for (CardapioModel item : itens) {
                if (item.getId_restaurante() == idRestaurante) {
                    Object[] linha = {
                            item.getId_cardapio(),
                            item.getNome_item(),
                            item.getDescricao(),
                            item.getPreco(),
                            item.isDisponivel() ? "Sim" : "Não"
                    };
                    modeloTabela.addRow(linha);
                }
            }
        }
    }

    private void adicionarAoCarrinho() {
        int linhaSelecionada = tabelaCardapio.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item do cardápio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCardapio = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
        String nomeItem = (String) modeloTabela.getValueAt(linhaSelecionada, 1);
        Object valor = tabelaCardapio.getValueAt(linhaSelecionada, 3);
        BigDecimal preco = new BigDecimal(valor.toString());

        String disponivel = (String) modeloTabela.getValueAt(linhaSelecionada, 4);

        if (disponivel.equals("Não")) {
            JOptionPane.showMessageDialog(this, "Este item não está disponível no momento!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int quantidade = (int) spinnerQuantidade.getValue();

        if (carrinho.containsKey(idCardapio)) {
            ItemCarrinho itemExistente = carrinho.get(idCardapio);
            itemExistente.quantidade += quantidade;
        } else {
            carrinho.put(idCardapio, new ItemCarrinho(idCardapio, nomeItem, preco, quantidade, idRestaurante));
        }

        JOptionPane.showMessageDialog(this, quantidade + "x " + nomeItem + " adicionado(s) ao carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        spinnerQuantidade.setValue(1);
    }

    private void verCarrinho() {
        if (carrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seu carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ClienteCarrinhoView carrinhoView = new ClienteCarrinhoView(idClienteLogado, carrinho);
        carrinhoView.setVisible(true);
    }

    public static Map<Integer, ItemCarrinho> getCarrinho() {
        return carrinho;
    }

    public static void limparCarrinho() {
        carrinho.clear();
    }


    public static class ItemCarrinho {
        public int idCardapio;
        public String nomeItem;
        public BigDecimal preco;
        public int quantidade;
        public int idRestaurante;

        public ItemCarrinho(int idCardapio, String nomeItem, BigDecimal preco, int quantidade, int idRestaurante) {
            this.idCardapio = idCardapio;
            this.nomeItem = nomeItem;
            this.preco = preco;
            this.quantidade = quantidade;
            this.idRestaurante = idRestaurante;
        }

        public BigDecimal getSubtotal() {
            return preco.multiply(new BigDecimal(quantidade));
        }
    }
}

