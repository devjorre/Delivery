package View;

import Controller.RestauranteController;
import Controller.CardapioController;
import Controller.PedidoController;
import Model.RestauranteModel;
import Model.CardapioModel;
import Model.PedidoModel;
import View.CardapioItemDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RestauranteAreaView extends JFrame {

    private JComboBox<RestauranteModel> comboRestaurantes;
    private JTable tabelaPedidos, tabelaCardapio;
    private DefaultTableModel modeloPedidos, modeloCardapio;
    private JButton btnAdicionarItem, btnEditarItem, btnRemoverItem, btnAtualizarPedidos, btnVoltar, btnCarregarDados;
    private RestauranteController restauranteController;
    private CardapioController cardapioController;
    private PedidoController pedidoController;
    private RestauranteModel restauranteSelecionado;

    public RestauranteAreaView() {
        setTitle("Área do Restaurante - Sistema de Delivery");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        restauranteController = new RestauranteController();
        cardapioController = new CardapioController();
        pedidoController = new PedidoController();


        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setBackground(new Color(155, 89, 182));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("🍽️ Área do Restaurante");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelSuperior.add(lblTitulo, BorderLayout.CENTER);

        add(painelSuperior, BorderLayout.NORTH);


        JPanel painelSelecao = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        painelSelecao.setBorder(BorderFactory.createTitledBorder("Selecione o Restaurante"));
        painelSelecao.setBackground(new Color(240, 240, 240));

        JLabel lblRestaurante = new JLabel("Restaurante:");
        lblRestaurante.setFont(new Font("Arial", Font.BOLD, 14));
        painelSelecao.add(lblRestaurante);

        comboRestaurantes = new JComboBox<>();
        comboRestaurantes.setPreferredSize(new Dimension(300, 30));
        comboRestaurantes.addActionListener(this::onRestauranteSelecionado);
        painelSelecao.add(comboRestaurantes);

        btnCarregarDados = new JButton("🔄 Carregar Dados");
        btnCarregarDados.addActionListener(this::carregarDadosRestaurante);
        painelSelecao.add(btnCarregarDados);

        add(painelSelecao, BorderLayout.NORTH);


        JTabbedPane abas = new JTabbedPane();


        JPanel painelPedidos = new JPanel(new BorderLayout(10, 10));
        painelPedidos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloPedidos = new DefaultTableModel(
                new Object[]{"ID Pedido", "Cliente", "Itens", "Status"},
                0
        );
        tabelaPedidos = new JTable(modeloPedidos);
        JScrollPane scrollPedidos = new JScrollPane(tabelaPedidos);
        painelPedidos.add(scrollPedidos, BorderLayout.CENTER);

        JPanel painelBotoesPedidos = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAtualizarPedidos = new JButton("🔄 Atualizar Status do Pedido");
        btnAtualizarPedidos.addActionListener(this::atualizarStatusPedido);
        painelBotoesPedidos.add(btnAtualizarPedidos);
        painelPedidos.add(painelBotoesPedidos, BorderLayout.SOUTH);

        abas.addTab("Pedidos", painelPedidos);

        JPanel painelCardapio = new JPanel(new BorderLayout(10, 10));
        painelCardapio.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloCardapio = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Descrição", "Preço (R$)", "Disponível"},
                0
        );
        tabelaCardapio = new JTable(modeloCardapio);
        JScrollPane scrollCardapio = new JScrollPane(tabelaCardapio);
        painelCardapio.add(scrollCardapio, BorderLayout.CENTER);

        JPanel painelBotoesCardapio = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAdicionarItem = new JButton("➕ Adicionar Item");
        btnEditarItem = new JButton("✏️ Editar Item");
        btnRemoverItem = new JButton("🗑️ Remover Item");

        btnAdicionarItem.addActionListener(this::adicionarItem);
        btnEditarItem.addActionListener(this::editarItem);
        btnRemoverItem.addActionListener(this::removerItem);

        painelBotoesCardapio.add(btnAdicionarItem);
        painelBotoesCardapio.add(btnEditarItem);
        painelBotoesCardapio.add(btnRemoverItem);

        painelCardapio.add(painelBotoesCardapio, BorderLayout.SOUTH);

        abas.addTab("Cardápio", painelCardapio);

        add(abas, BorderLayout.CENTER);


        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnVoltar = new JButton("⬅️ Voltar ao Menu Principal");
        btnVoltar.addActionListener(e -> dispose());
        painelInferior.add(btnVoltar);

        add(painelInferior, BorderLayout.SOUTH);


        carregarRestaurantes();
    }

    private void carregarRestaurantes() {
        try {
            List<RestauranteModel> restaurantes = restauranteController.listarTodosRestaurantes();
            comboRestaurantes.removeAllItems();
            for (RestauranteModel restaurante : restaurantes) {
                comboRestaurantes.addItem(restaurante);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar restaurantes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onRestauranteSelecionado(ActionEvent e) {
        restauranteSelecionado = (RestauranteModel) comboRestaurantes.getSelectedItem();
        if (restauranteSelecionado != null) {
            JOptionPane.showMessageDialog(this, "Restaurante selecionado: " + restauranteSelecionado.getNome());
        }
    }

    private void carregarDadosRestaurante(ActionEvent e) {
        if (restauranteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um restaurante primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            List<PedidoModel> pedidos = pedidoController.listarPedidosPorRestaurante(restauranteSelecionado.getId_restaurante());
            modeloPedidos.setRowCount(0);
            for (PedidoModel pedido : pedidos) {
                modeloPedidos.addRow(new Object[]{
                        pedido.getId_pedido(),
                        pedido.getId_cliente(),
                        "Itens do pedido",
                        pedido.getStatus().name()
                });
            }


            carregarDadosCardapio();

            JOptionPane.showMessageDialog(this, "Dados do restaurante carregados com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarDadosCardapio() throws Exception {
        List<CardapioModel> cardapios = cardapioController.listarCardapiosPorRestaurante(restauranteSelecionado.getId_restaurante());
        modeloCardapio.setRowCount(0);
        for (CardapioModel cardapio : cardapios) {
            modeloCardapio.addRow(new Object[]{
                    cardapio.getId_cardapio(),
                    cardapio.getNome_item(),
                    cardapio.getDescricao(),
                    cardapio.getPreco(),
                    cardapio.isDisponivel() ? "Sim" : "Não"
            });
        }
    }

    private void adicionarItem(ActionEvent e) {
        if (restauranteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um restaurante primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        CardapioItemDialog dialog = new CardapioItemDialog(this, null);
        dialog.setVisible(true);

        if (dialog.isSalvo()) {
            CardapioModel novoItem = dialog.getItemCardapio();
            novoItem.setId_restaurante(restauranteSelecionado.getId_restaurante());

            try {
                cardapioController.adicionarItemCardapio(novoItem);
                carregarDadosCardapio();
                JOptionPane.showMessageDialog(this, "Item adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarItem(ActionEvent e) {
        if (restauranteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um restaurante primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tabelaCardapio.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int idCardapio = (int) modeloCardapio.getValueAt(selectedRow, 0);
            CardapioModel itemOriginal = cardapioController.buscarItemCardapioPorId(idCardapio);

            if (itemOriginal == null) {
                JOptionPane.showMessageDialog(this, "Item não encontrado no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CardapioItemDialog dialog = new CardapioItemDialog(this, itemOriginal);
            dialog.setVisible(true);

            if (dialog.isSalvo()) {
                CardapioModel itemEditado = dialog.getItemCardapio();
                cardapioController.atualizarItemCardapio(itemEditado);
                carregarDadosCardapio();
                JOptionPane.showMessageDialog(this, "Item atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerItem(ActionEvent e) {
        if (restauranteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um restaurante primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tabelaCardapio.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCardapio = (int) modeloCardapio.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este item?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                cardapioController.deletarItemCardapio(idCardapio);
                carregarDadosCardapio();
                JOptionPane.showMessageDialog(this, "Item removido com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao remover item: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void atualizarStatusPedido(ActionEvent e) {
        if (restauranteSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um restaurante primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedRow = tabelaPedidos.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um pedido para atualizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPedido = (int) modeloPedidos.getValueAt(selectedRow, 0);
        String[] opcoes = {"em preparo", "a caminho", "entregue"};
        String novoStatus = (String) JOptionPane.showInputDialog(this, "Selecione o novo status:", "Atualizar Status", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (novoStatus != null) {
            try {
                pedidoController.atualizarPedido(idPedido, novoStatus);
                modeloPedidos.setValueAt(novoStatus, selectedRow, 3);
                JOptionPane.showMessageDialog(this, "Status do pedido atualizado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar status: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestauranteAreaView view = new RestauranteAreaView();
            view.setVisible(true);
        });
    }
}