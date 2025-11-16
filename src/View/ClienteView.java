package View;

import Controller.ClienteController;
import Model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteView extends JFrame {
    private ClienteController clienteController;


    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtTelefone;
    private JButton btnCadastrar;
    private JButton btnAtualizar;
    private JButton btnDeletar;
    private JButton btnLimpar;
    private JButton btnBuscar;
    private JTable tabelaClientes;
    private DefaultTableModel modeloTabela;

    public ClienteView() {
        this.clienteController = new ClienteController();
        initComponents();
        carregarClientes();
    }

    private void initComponents() {
        setTitle("Sistema de Delivery - Gerenciamento de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelFormulario = criarPainelFormulario();
        add(painelFormulario, BorderLayout.NORTH);


        JPanel painelTabela = criarPainelTabela();
        add(painelTabela, BorderLayout.CENTER);


        JPanel painelBotoes = criarPainelBotoes();
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(new JLabel("ID:"), gbc);

        gbc.gridx = 1;
        txtId = new JTextField(10);
        txtId.setEditable(true);

        painel.add(txtId, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        painel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        painel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        txtTelefone = new JTextField(15);
        painel.add(txtTelefone, gbc);

        return painel;
    }

    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Clientes"));


        String[] colunas = {"ID", "Nome", "Telefone"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaClientes = new JTable(modeloTabela);
        tabelaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaClientes.getSelectedRow();
                if (selectedRow != -1) {
                    preencherCamposComLinhaSelecionada(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarCliente());
        painel.add(btnCadastrar);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarCliente());
        painel.add(btnAtualizar);

        btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(e -> deletarCliente());
        painel.add(btnDeletar);

        btnBuscar = new JButton("Buscar por ID");
        btnBuscar.addActionListener(e -> buscarClientePorId());
        painel.add(btnBuscar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        painel.add(btnLimpar);

        return painel;
    }

    private void cadastrarCliente() {
        try {
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do cliente não pode estar vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ClienteModel cliente = new ClienteModel(0, nome, telefone);
            clienteController.cadastrarCliente(cliente);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso! ID: " + cliente.getId_cliente(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarClientes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCliente() {
        try {
            String idTexto = txtId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente da tabela para atualizar.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idTexto);
            String nome = txtNome.getText().trim();
            String telefone = txtTelefone.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O nome do cliente não pode estar vazio.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ClienteModel cliente = new ClienteModel(id, nome, telefone);
            clienteController.atualizarCliente(cliente);

            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarClientes();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarCliente() {
        try {
            String idTexto = txtId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente da tabela para deletar.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int id = Integer.parseInt(idTexto);
            int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar o cliente ID " + id + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                clienteController.deletarCliente(id);
                JOptionPane.showMessageDialog(this, "Cliente deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                carregarClientes();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao deletar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarClientePorId() {
        try {
            String idTexto = JOptionPane.showInputDialog(this, "Digite o ID do cliente:");
            if (idTexto == null || idTexto.trim().isEmpty()) {
                return;
            }

            int id = Integer.parseInt(idTexto.trim());
            ClienteModel cliente = clienteController.buscarClientePorId(id);

            if (cliente != null) {
                txtId.setText(String.valueOf(cliente.getId_cliente()));
                txtNome.setText(cliente.getNome());
                txtTelefone.setText(cliente.getTelefone());
                JOptionPane.showMessageDialog(this, "Cliente encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente com ID " + id + " não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarClientes() {
        modeloTabela.setRowCount(0);
        List<ClienteModel> clientes = clienteController.listarTodosClientes();

        if (clientes != null) {
            for (ClienteModel cliente : clientes) {
                Object[] linha = {
                        cliente.getId_cliente(),
                        cliente.getNome(),
                        cliente.getTelefone()
                };
                modeloTabela.addRow(linha);
            }
        }
    }

    private void preencherCamposComLinhaSelecionada(int row) {
        txtId.setText(modeloTabela.getValueAt(row, 0).toString());
        txtNome.setText(modeloTabela.getValueAt(row, 1).toString());
        txtTelefone.setText(modeloTabela.getValueAt(row, 2).toString());
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtTelefone.setText("");
        tabelaClientes.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClienteView view = new ClienteView();
            view.setVisible(true);
        });
    }
}

