package View;

import Controller.ClienteController;
import Controller.PedidoController;
import Controller.RestauranteController;
import Model.CardapioModel;
import Model.ClienteModel;
import Model.PedidoModel;
import Model.RestauranteModel;
import Enum.PedidoStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PedidoView extends JFrame {
    private JTextField txtId, txtDataHora;
    private JComboBox<String> cbCliente, cbRestaurante, cbStatus;
    private JButton btnCadastrar, btnAtualizar, btnDeletar, btnBuscar, btnLimpar;
    private JTable tabelaPedidos;
    private DefaultTableModel modeloTabela;

    private PedidoController controller;
    private ClienteController clienteController;
    private RestauranteController restauranteController;
    private List<ClienteModel> clientes;
    private List<RestauranteModel> restaurantes;

    public PedidoView() {
        controller = new PedidoController();
        clienteController = new ClienteController();
        restauranteController = new RestauranteController();
        carregarClientesERestaurantes();
        initComponents();
        carregarPedidos();
    }

    private void carregarClientesERestaurantes() {
        clientes = clienteController.listarTodosClientes();
        restaurantes = restauranteController.listarTodosRestaurantes();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Pedidos");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Pedido"));

        // ID
        painelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        painelFormulario.add(txtId);

        // Cliente
        painelFormulario.add(new JLabel("Cliente:"));
        cbCliente = new JComboBox<>();
        if (clientes != null) {
            for (ClienteModel cliente : clientes) {
                cbCliente.addItem(cliente.getId_cliente() + " - " + cliente.getNome());
            }
        }
        painelFormulario.add(cbCliente);

        // Restaurante
        painelFormulario.add(new JLabel("Restaurante:"));
        cbRestaurante = new JComboBox<>();
        if (restaurantes != null) {
            for (RestauranteModel restaurante : restaurantes) {
                cbRestaurante.addItem(restaurante.getId_restaurante() + " - " + restaurante.getNome());
            }
        }
        painelFormulario.add(cbRestaurante);

        // Data/Hora
        painelFormulario.add(new JLabel("Data/Hora:"));
        txtDataHora = new JTextField();
        txtDataHora.setEditable(false);
        txtDataHora.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        painelFormulario.add(txtDataHora);

        // Status
        painelFormulario.add(new JLabel("Status:"));
        cbStatus = new JComboBox<>(new String[]{"em preparo", "a caminho", "entregue"});
        painelFormulario.add(cbStatus);

        // Botões
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

        painelFormulario.add(new JLabel());
        painelFormulario.add(painelBotoes);
        add(painelFormulario, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Cliente", "Restaurante", "Data/Hora", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaPedidos = new JTable(modeloTabela);
        tabelaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // Eventos
        btnCadastrar.addActionListener(e -> cadastrarPedido());
        btnAtualizar.addActionListener(e -> atualizarPedido());
        btnDeletar.addActionListener(e -> deletarPedido());
        btnBuscar.addActionListener(e -> buscarPedido());
        btnLimpar.addActionListener(e -> limparCampos());
    }

    private void cadastrarPedido() {
        if (cbCliente.getSelectedIndex() == -1 || cbRestaurante.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente e um restaurante!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCliente = Integer.parseInt(cbCliente.getSelectedItem().toString().split(" - ")[0]);
        int idRestaurante = Integer.parseInt(cbRestaurante.getSelectedItem().toString().split(" - ")[0]);
        LocalDateTime dataHora = LocalDateTime.now();
        String statusSelecionado = cbStatus.getSelectedItem().toString();

        PedidoStatus status;
        try {
            status = PedidoStatus.valueOf(statusSelecionado.toUpperCase().replace(" ", "_"));
        } catch (Exception ex) {
            status = PedidoStatus.EM_PREPARO; // fallback
        }

        PedidoModel pedido = new PedidoModel(0, idCliente, idRestaurante, dataHora, status);

        try {
            controller.cadastrarPedido(pedido);
            carregarPedidos();
            JOptionPane.showMessageDialog(this, "Pedido cadastrado com sucesso!");
            limparCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void atualizarPedido() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para atualizar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        int idCliente = Integer.parseInt(cbCliente.getSelectedItem().toString().split(" - ")[0]);
        int idRestaurante = Integer.parseInt(cbRestaurante.getSelectedItem().toString().split(" - ")[0]);
        LocalDateTime dataHora = LocalDateTime.parse(txtDataHora.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String statusSelecionado = cbStatus.getSelectedItem().toString();
        PedidoStatus status = PedidoStatus.valueOf(statusSelecionado.toUpperCase().replace(" ", "_"));

        PedidoModel pedido = new PedidoModel(id, idCliente, idRestaurante, dataHora, status);
        controller.atualizarPedido(pedido);
        JOptionPane.showMessageDialog(this, "Pedido atualizado com sucesso!");
        carregarPedidos();
        limparCampos();
    }

    private void deletarPedido() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido para deletar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar este pedido?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);
            controller.deletarPedido(id);
            JOptionPane.showMessageDialog(this, "Pedido deletado com sucesso!");
            carregarPedidos();
            limparCampos();
        }
    }

    private void buscarPedido() {
        String idTexto = txtId.getText().trim();
        try {
            if (!idTexto.isEmpty()) {
                int id = Integer.parseInt(idTexto);
                PedidoModel pedido = controller.buscarPedidoPorId(id);
                if (pedido != null) preencherCampos(pedido);
                else JOptionPane.showMessageDialog(this, "Pedido não encontrado!");
            } else {
                JOptionPane.showMessageDialog(this, "Informe o ID do pedido!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar pedido: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preencherCampos(PedidoModel pedido) {
        txtId.setText(String.valueOf(pedido.getId_pedido()));
        txtDataHora.setText(pedido.getData_hora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        cbStatus.setSelectedItem(pedido.getStatus().toString().toLowerCase().replace("_", " "));
    }

    private void atualizarTabela(List<PedidoModel> pedidos) {
        modeloTabela.setRowCount(0);
        for (PedidoModel pedido : pedidos) {
            String clienteNome = clientes.stream()
                    .filter(c -> c.getId_cliente() == pedido.getId_cliente())
                    .map(ClienteModel::getNome)
                    .findFirst().orElse("Desconhecido");

            String restauranteNome = restaurantes.stream()
                    .filter(r -> r.getId_restaurante() == pedido.getId_restaurante())
                    .map(RestauranteModel::getNome)
                    .findFirst().orElse("Desconhecido");

            modeloTabela.addRow(new Object[]{
                    pedido.getId_pedido(),
                    clienteNome,
                    restauranteNome,
                    pedido.getData_hora(),
                    pedido.getStatus()
            });
        }
    }

    private void carregarPedidos() {
        try {
            List<PedidoModel> pedidos = controller.listarTodosPedidos(); // agora lista todos
            atualizarTabela(pedidos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar pedidos: " + e.getMessage());
        }
    }

    private void limparCampos() {
        txtId.setText("");
        if (cbCliente.getItemCount() > 0) cbCliente.setSelectedIndex(0);
        if (cbRestaurante.getItemCount() > 0) cbRestaurante.setSelectedIndex(0);
        txtDataHora.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        cbStatus.setSelectedIndex(0);
        tabelaPedidos.clearSelection();
    }
}
