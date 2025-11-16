package View;

import Controller.RestauranteController;
import Model.RestauranteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RestauranteView extends JFrame {
    private JTextField txtId, txtNome, txtTipoCozinha, txtTelefone;
    private JButton btnCadastrar, btnAtualizar, btnDeletar, btnBuscar, btnLimpar, btnBuscarPorId;
    private JTextField txtBuscarId;
    private JTable tabelaRestaurantes;
    private DefaultTableModel modeloTabela;
    private RestauranteController controller;

    public RestauranteView() {
        controller = new RestauranteController();
        initComponents();
        carregarRestaurantes();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Restaurantes");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Restaurante"));

        painelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        painelFormulario.add(txtId);

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Tipo de Cozinha:"));
        txtTipoCozinha = new JTextField();
        painelFormulario.add(txtTipoCozinha);

        painelFormulario.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painelFormulario.add(txtTelefone);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarRestaurante());
        painelBotoes.add(btnCadastrar);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarRestaurante());
        painelBotoes.add(btnAtualizar);

        JButton btnDeletar = new JButton("Deletar");
        btnDeletar.addActionListener(e -> deletarRestaurante());
        painelBotoes.add(btnDeletar);

        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.addActionListener(e -> buscarRestaurante());
        painelBotoes.add(btnBuscarPorId);
        painelBotoes.add(new JLabel("ID para busca:"));

        txtBuscarId = new JTextField(8);
        painelBotoes.add(txtBuscarId);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        painelBotoes.add(btnLimpar);


        painelFormulario.add(new JLabel());
        painelFormulario.add(painelBotoes);

        add(painelFormulario, BorderLayout.NORTH);


        String[] colunas = {"ID", "Nome", "Tipo de Cozinha", "Telefone"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaRestaurantes = new JTable(modeloTabela);
        tabelaRestaurantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tabelaRestaurantes);
        add(scrollPane, BorderLayout.CENTER);


        btnCadastrar.addActionListener(e -> cadastrarRestaurante());
        btnAtualizar.addActionListener(e -> atualizarRestaurante());
        btnDeletar.addActionListener(e -> deletarRestaurante());
        btnBuscarPorId.addActionListener(e -> buscarRestaurantePorId());

        btnLimpar.addActionListener(e -> limparCampos());

        btnBuscarPorId.addActionListener(e -> {
            String idTexto = txtBuscarId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Digite o ID para buscar!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idTexto);
                RestauranteModel restaurante = controller.buscarRestaurantePorId(id);

                if (restaurante != null) {
                    txtId.setText(String.valueOf(restaurante.getId_restaurante()));
                    txtNome.setText(restaurante.getNome());
                    txtTipoCozinha.setText(restaurante.getTipo_cozinha());
                    txtTelefone.setText(restaurante.getTelefone());
                } else {
                    JOptionPane.showMessageDialog(this, "Restaurante não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        tabelaRestaurantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int linhaSelecionada = tabelaRestaurantes.getSelectedRow();
                if (linhaSelecionada != -1) {
                    txtId.setText(modeloTabela.getValueAt(linhaSelecionada, 0).toString());
                    txtNome.setText(modeloTabela.getValueAt(linhaSelecionada, 1).toString());
                    txtTipoCozinha.setText(modeloTabela.getValueAt(linhaSelecionada, 2).toString());
                    txtTelefone.setText(modeloTabela.getValueAt(linhaSelecionada, 3).toString());
                }
            }
        });
    }

    private void cadastrarRestaurante() {
        String nome = txtNome.getText().trim();
        String tipoCozinha = txtTipoCozinha.getText().trim();
        String telefone = txtTelefone.getText().trim();

        if (nome.isEmpty() || tipoCozinha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e Tipo de Cozinha são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RestauranteModel restaurante = new RestauranteModel(0, nome, tipoCozinha, telefone);
        controller.cadastrarRestaurante(restaurante);
        JOptionPane.showMessageDialog(this, "Restaurante cadastrado com sucesso!");
        carregarRestaurantes();
        limparCampos();
    }

    private void atualizarRestaurante() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um restaurante para atualizar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        String nome = txtNome.getText().trim();
        String tipoCozinha = txtTipoCozinha.getText().trim();
        String telefone = txtTelefone.getText().trim();

        RestauranteModel restaurante = new RestauranteModel(id, nome, tipoCozinha, telefone);
        controller.atualizarRestaurante(restaurante);
        JOptionPane.showMessageDialog(this, "Restaurante atualizado com sucesso!");
        carregarRestaurantes();
        limparCampos();
    }

    private void deletarRestaurante() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um restaurante para deletar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja deletar este restaurante?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(idTexto);
            controller.deletarRestaurante(id);
            JOptionPane.showMessageDialog(this, "Restaurante deletado com sucesso!");
            carregarRestaurantes();
            limparCampos();
        }
    }

    private void buscarRestaurante() {
        String idTexto = txtId.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do restaurante para buscar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            RestauranteModel restaurante = controller.buscarRestaurantePorId(id);

            if (restaurante != null) {
                txtNome.setText(restaurante.getNome());
                txtTipoCozinha.setText(restaurante.getTipo_cozinha());
                txtTelefone.setText(restaurante.getTelefone());
            } else {
                JOptionPane.showMessageDialog(this, "Restaurante não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
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

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtTipoCozinha.setText("");
        txtTelefone.setText("");
        txtBuscarId.setText("");
        tabelaRestaurantes.clearSelection();
    }
    private void buscarRestaurantePorId() {
        try {
            String idTexto = JOptionPane.showInputDialog(this, "Digite o ID do restaurante:");
            if (idTexto == null || idTexto.trim().isEmpty()) {
                return;
            }

            int id = Integer.parseInt(idTexto.trim());
            RestauranteModel restaurante = controller.buscarRestaurantePorId(id);

            if (restaurante != null) {
                txtId.setText(String.valueOf(restaurante.getId_restaurante()));
                txtNome.setText(restaurante.getNome());
                txtTipoCozinha.setText(restaurante.getTipo_cozinha());
                txtTelefone.setText(restaurante.getTelefone());
                JOptionPane.showMessageDialog(this, "Restaurante encontrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Restaurante não encontrado!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar restaurante: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
