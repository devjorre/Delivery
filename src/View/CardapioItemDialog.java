package View;

import Model.CardapioModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CardapioItemDialog extends JDialog {

    private JTextField txtNome, txtDescricao, txtPreco;
    private JCheckBox chkDisponivel;
    private JButton btnSalvar, btnCancelar;
    private CardapioModel itemCardapio;
    private boolean salvo = false;

    public CardapioItemDialog(JFrame parent, CardapioModel itemCardapio) {
        super(parent, itemCardapio == null ? "Adicionar Item ao Cardápio" : "Editar Item do Cardápio", true);
        this.itemCardapio = itemCardapio;
        initComponents();
        preencherCampos();
    }

    private void initComponents() {
        setSize(400, 300);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        painelFormulario.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        painelFormulario.add(txtDescricao);

        painelFormulario.add(new JLabel("Preço (R$):"));
        txtPreco = new JTextField();
        painelFormulario.add(txtPreco);

        painelFormulario.add(new JLabel("Disponível:"));
        chkDisponivel = new JCheckBox();
        painelFormulario.add(chkDisponivel);

        add(painelFormulario, BorderLayout.CENTER);


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(this::salvar);
        btnCancelar.addActionListener(e -> dispose());

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void preencherCampos() {
        if (itemCardapio != null) {
            txtNome.setText(itemCardapio.getNome_item());
            txtDescricao.setText(itemCardapio.getDescricao());
            txtPreco.setText(String.valueOf(itemCardapio.getPreco()));
            chkDisponivel.setSelected(itemCardapio.isDisponivel());
        } else {

            chkDisponivel.setSelected(true);
        }
    }

    private void salvar(ActionEvent e) {
        if (!validarCampos()) {
            return;
        }

        try {
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            double preco = Double.parseDouble(txtPreco.getText().trim());
            boolean disponivel = chkDisponivel.isSelected();

            if (itemCardapio == null) {
                itemCardapio = new CardapioModel();
            }

            itemCardapio.setNome_item(nome);
            itemCardapio.setDescricao(descricao);
            itemCardapio.setPreco(preco);
            itemCardapio.setDisponivel(disponivel);

            salvo = true;
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço inválido. Use apenas números.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validarCampos() {
        if (txtNome.getText().trim().isEmpty() || txtDescricao.getText().trim().isEmpty() || txtPreco.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos (Nome, Descrição, Preço) são obrigatórios.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public CardapioModel getItemCardapio() {
        return itemCardapio;
    }

    public boolean isSalvo() {
        return salvo;
    }
}
