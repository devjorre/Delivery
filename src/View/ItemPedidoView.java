package View;

import Controller.ItemPedidoController;
import Controller.PedidoController;
import Model.ItemPedidoModel;
import Model.PedidoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ItemPedidoView extends JFrame {

    private final ItemPedidoController controller;
    private final PedidoController pedidoController;
    private List<PedidoModel> pedidos;

    private JTextField txtId;
    private JTextField txtDescricao;
    private JTextField txtQuantidade;
    private JTextField txtPreco;
    private JTextField txtStatus;
    private JComboBox<String> cbPedido;
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JButton btnDeletar;
    private JButton btnBuscar;
    private JButton btnLimpar;
    private JTable tabelaItens;
    private DefaultTableModel modeloTabela;

    public ItemPedidoView() {
        controller = new ItemPedidoController();
        pedidoController = new PedidoController();
        carregarPedidos(); // ← garante que pedidos sejam carregados antes da UI
        initComponents();
        carregarItens();
    }

    /**
     * Carrega a lista de pedidos do banco de dados.
     */
    private void carregarPedidos() {
        try {
            // Chama o método sem parâmetro (ver no PedidoController)
            pedidos = pedidoController.listarTodosPedidos();

            if (pedidos == null || pedidos.isEmpty()) {
                System.out.println("⚠️ Nenhum pedido encontrado no banco.");
                pedidos = null;
            } else {
                System.out.println("✅ " + pedidos.size() + " pedidos encontrados.");
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao carregar pedidos: " + e.getMessage());
            pedidos = null;
        }
    }

    private void initComponents() {
        setTitle("Gerenciamento de Itens de Pedido");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Item de Pedido"));


        painelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        painelFormulario.add(txtId);


        painelFormulario.add(new JLabel("Pedido:"));
        cbPedido = new JComboBox<>();
        atualizarComboPedidos(); // atualiza combo ao iniciar
        painelFormulario.add(cbPedido);


        painelFormulario.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painelFormulario.add(txtDescricao);


        painelFormulario.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        painelFormulario.add(txtQuantidade);


        painelFormulario.add(new JLabel("Preço (R$):"));
        txtPreco = new JTextField();
        painelFormulario.add(txtPreco);


        painelFormulario.add(new JLabel("Status:"));
        txtStatus = new JTextField();
        painelFormulario.add(txtStatus);


        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnCadastrar = new JButton("Cadastrar");
        btnAtualizar = new JButton("Atualizar");
        btnDeletar = new JButton("Deletar");
        btnBuscar = new JButton("Buscar");
        btnLimpar = new JButton("Limpar");

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnDeletar);
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnLimpar);

        add(painelFormulario, BorderLayout.NORTH);

        JPanel painelBotoesGeral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoesGeral.add(painelBotoes);
        add(painelBotoesGeral, BorderLayout.SOUTH);


        String[] colunas = {"ID", "Pedido", "Descrição", "Quantidade", "Preço (R$)", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaItens = new JTable(modeloTabela);
        tabelaItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaItens);
        add(scrollPane, BorderLayout.CENTER);


        btnCadastrar.addActionListener(e -> cadastrarItem());
        btnAtualizar.addActionListener(e -> atualizarItem());
        btnDeletar.addActionListener(e -> deletarItem());
        btnBuscar.addActionListener(e -> buscarItem());
        btnLimpar.addActionListener(e -> limparCampos());

        tabelaItens.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaItens.getSelectedRow();
                if (linhaSelecionada != -1) {
                    preencherCamposComLinhaSelecionada(linhaSelecionada);
                }
            }
        });
    }


    private void atualizarComboPedidos() {
        cbPedido.removeAllItems();
        if (pedidos != null && !pedidos.isEmpty()) {
            for (PedidoModel pedido : pedidos) {
                cbPedido.addItem("Pedido #" + pedido.getId_pedido() + " - " + pedido.getStatus());
            }
        } else {
            cbPedido.addItem("Nenhum pedido disponível");
        }
    }

    private void preencherCamposComLinhaSelecionada(int linhaSelecionada) {
        txtId.setText(modeloTabela.getValueAt(linhaSelecionada, 0).toString());
        String pedidoInfo = modeloTabela.getValueAt(linhaSelecionada, 1).toString();

        String idPedidoStr = pedidoInfo.split(" - ")[0].replace("Pedido #", "").trim();
        for (int i = 0; i < cbPedido.getItemCount(); i++) {
            String item = cbPedido.getItemAt(i);
            if (item.startsWith("Pedido #" + idPedidoStr + " ")) {
                cbPedido.setSelectedIndex(i);
                break;
            }
        }

        txtDescricao.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
        txtQuantidade.setText(modeloTabela.getValueAt(linhaSelecionada, 3).toString());
        txtPreco.setText(modeloTabela.getValueAt(linhaSelecionada, 4).toString());
        txtStatus.setText(modeloTabela.getValueAt(linhaSelecionada, 5).toString());
    }

    private void cadastrarItem() {
        if (cbPedido.getSelectedItem() == null || cbPedido.getSelectedItem().toString().contains("Nenhum")) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido válido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String descricao = txtDescricao.getText().trim();
        String quantidadeTexto = txtQuantidade.getText().trim();
        String precoTexto = txtPreco.getText().trim();
        String status = txtStatus.getText().trim();

        if (descricao.isEmpty() || quantidadeTexto.isEmpty() || precoTexto.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int idPedido = Integer.parseInt(cbPedido.getSelectedItem().toString().split("#")[1].split(" - ")[0]);
            int quantidade = Integer.parseInt(quantidadeTexto);
            double preco = Double.parseDouble(precoTexto);

            ItemPedidoModel item = new ItemPedidoModel(0, idPedido, descricao, quantidade, preco, status);
            controller.cadastrarItemPedido(item);
            JOptionPane.showMessageDialog(this, "Item cadastrado com sucesso!");
            carregarItens();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade e Preço devem ser numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarItem() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um item para atualizar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            int idPedido = Integer.parseInt(cbPedido.getSelectedItem().toString().split("#")[1].split(" - ")[0]);
            String descricao = txtDescricao.getText().trim();
            int quantidade = Integer.parseInt(txtQuantidade.getText().trim());
            double preco = Double.parseDouble(txtPreco.getText().trim());
            String status = txtStatus.getText().trim();

            ItemPedidoModel item = new ItemPedidoModel(id, idPedido, descricao, quantidade, preco, status);
            controller.atualizarItemPedido(item);
            JOptionPane.showMessageDialog(this, "Item atualizado com sucesso!");
            carregarItens();
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantidade e Preço devem ser numéricos válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarItem() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um item para deletar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar este item?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);
            controller.deletarItemPedido(id);
            JOptionPane.showMessageDialog(this, "Item deletado com sucesso!");
            carregarItens();
            limparCampos();
        }
    }

    private void buscarItem() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do item para buscar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        ItemPedidoModel item = controller.buscarItemPedidoPorId(id);
        if (item != null) {
            for (int i = 0; i < cbPedido.getItemCount(); i++) {
                if (cbPedido.getItemAt(i).contains("#" + item.getId_pedido() + " ")) {
                    cbPedido.setSelectedIndex(i);
                    break;
                }
            }
            txtDescricao.setText(item.getDescricao());
            txtQuantidade.setText(String.valueOf(item.getQuantidade()));
            txtPreco.setText(String.valueOf(item.getPreco()));
            txtStatus.setText(item.getStatus());
        } else {
            JOptionPane.showMessageDialog(this, "Item não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void carregarItens() {
        modeloTabela.setRowCount(0);
        List<ItemPedidoModel> itens = controller.listarTodosItensPedido();
        if (itens != null) {
            for (ItemPedidoModel item : itens) {
                String pedidoInfo = "Pedido #" + item.getId_pedido();

                if (pedidos != null) {
                    for (PedidoModel pedido : pedidos) {
                        if (pedido.getId_pedido() == item.getId_pedido()) {
                            pedidoInfo = "Pedido #" + item.getId_pedido() + " - " + pedido.getStatus();
                            break;
                        }
                    }
                }

                Object[] linha = {
                        item.getId_item(),
                        pedidoInfo,
                        item.getDescricao(),
                        item.getQuantidade(),
                        item.getPreco(),
                        item.getStatus()
                };
                modeloTabela.addRow(linha);
            }
        }
    }

    private void limparCampos() {
        txtId.setText("");
        if (cbPedido.getItemCount() > 0) cbPedido.setSelectedIndex(0);
        txtDescricao.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
        txtStatus.setText("");
        tabelaItens.clearSelection();
    }
}
