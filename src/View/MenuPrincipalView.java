package View;

import Model.CardapioModel;
import ViewCliente.ClienteHistoricoDePedidosView;
import ViewCliente.ClienteListagemRestaurantesView;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalView extends JFrame {

    private JButton btnAreaCliente, btnAreaRestaurante, btnGerenciamentoSistema, btnSair;
    private JLabel lblTitulo, lblSubtitulo;


    public MenuPrincipalView(CardapioModel restauranteSelecionado) {
        initComponents(restauranteSelecionado);
    }


    public MenuPrincipalView() {
        this(new CardapioModel());
    }



    private void initComponents(CardapioModel restauranteSelecionado) {
        setTitle("Sistema de Delivery - Menu Principal");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));


        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS));
        painelSuperior.setBackground(new Color(52, 73, 94));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo = new JLabel("🍔 Sistema de Delivery");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelSuperior.add(lblTitulo);

        painelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));

        lblSubtitulo = new JLabel("Bem-vindo! Selecione uma opção abaixo");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(236, 240, 241));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelSuperior.add(lblSubtitulo);

        add(painelSuperior, BorderLayout.NORTH);


        JPanel painelCentral = new JPanel(new GridLayout(2, 2, 20, 20));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        painelCentral.setBackground(new Color(236, 240, 241));


        btnAreaCliente = criarBotaoCard(
                "👤 Área do Cliente",
                "Faça pedidos e acompanhe entregas",
                new Color(52, 152, 219)
        );
        btnAreaCliente.addActionListener(e -> abrirAreaCliente(restauranteSelecionado));
        painelCentral.add(btnAreaCliente);


        btnAreaRestaurante = criarBotaoCard(
                "🍽️ Área do Restaurante",
                "Gerencie pedidos e cardápio",
                new Color(155, 89, 182)
        );
        btnAreaRestaurante.addActionListener(e -> abrirAreaRestaurante());
        painelCentral.add(btnAreaRestaurante);


        btnGerenciamentoSistema = criarBotaoCard(
                "⚙️ Gerenciamento",
                "Administração do sistema",
                new Color(230, 126, 34)
        );
        btnGerenciamentoSistema.addActionListener(e -> abrirGerenciamentoSistema());
        painelCentral.add(btnGerenciamentoSistema);


        btnSair = criarBotaoCard(
                "🚪 Sair",
                "Fechar o sistema",
                new Color(231, 76, 60)
        );
        btnSair.addActionListener(e -> sair());
        painelCentral.add(btnSair);

        add(painelCentral, BorderLayout.CENTER);


        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelInferior.setBackground(new Color(52, 73, 94));
        JLabel lblRodape = new JLabel("© 2025 Sistema de Delivery - Todos os direitos reservados");
        lblRodape.setForeground(new Color(189, 195, 199));
        lblRodape.setFont(new Font("Arial", Font.PLAIN, 12));
        painelInferior.add(lblRodape);
        add(painelInferior, BorderLayout.SOUTH);
    }

    private JButton criarBotaoCard(String titulo, String descricao, Color cor) {
        JButton botao = new JButton();
        botao.setLayout(new BorderLayout());
        botao.setBackground(cor);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(cor.darker(), 2));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBackground(cor);
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblTitulo);

        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDescricao.setForeground(new Color(236, 240, 241));
        lblDescricao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblDescricao);

        botao.add(painelConteudo, BorderLayout.CENTER);

        // Efeito hover
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor.brighter());
                painelConteudo.setBackground(cor.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                botao.setBackground(cor);
                painelConteudo.setBackground(cor);
            }
        });

        return botao;
    }

    private void abrirAreaCliente(CardapioModel restauranteSelecionado) {
        // Solicitar ID do cliente (simulação de login)
        String idClienteStr = JOptionPane.showInputDialog(this, "Digite o ID do Cliente:", "Login Cliente", JOptionPane.QUESTION_MESSAGE);

        if (idClienteStr != null && !idClienteStr.trim().isEmpty()) {
            try {
                int idCliente = Integer.parseInt(idClienteStr.trim());

                String[] opcoes = {"Ver Restaurantes", "Meus Pedidos"};
                int escolha = JOptionPane.showOptionDialog(
                        this,
                        "Selecione uma opção:",
                        "Área do Cliente",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoes,
                        opcoes[0]
                );

                if (escolha == 0) {
                    ClienteListagemRestaurantesView listagemView = new ClienteListagemRestaurantesView(idCliente);
                    listagemView.setVisible(true);
                } else if (escolha == 1) {
                    ClienteHistoricoDePedidosView historicoView = new ClienteHistoricoDePedidosView(idCliente, restauranteSelecionado);
                    historicoView.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void abrirAreaRestaurante() {RestauranteAreaView restauranteView = new RestauranteAreaView();
        restauranteView.setVisible(true);

    }

    private void abrirGerenciamentoSistema() {
        String[] opcoes = {"Clientes", "Restaurantes", "Pedidos", "Itens de Pedido"};
        int escolha = JOptionPane.showOptionDialog(
                this,
                "Selecione o módulo de gerenciamento:",
                "Gerenciamento do Sistema",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        switch (escolha) {
            case 0:
                ClienteView clienteView = new ClienteView();
                clienteView.setVisible(true);
                break;
            case 1:
                RestauranteView restauranteView = new RestauranteView();
                restauranteView.setVisible(true);
                break;
            case 2:
                PedidoView pedidoView = new PedidoView();
                pedidoView.setVisible(true);
                break;
            case 3:
                ItemPedidoView itemPedidoView = new ItemPedidoView();
                itemPedidoView.setVisible(true);
                break;
        }
    }

    private void sair() {
        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair do sistema?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args, CardapioModel restauranteSelecionado) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipalView menu = new MenuPrincipalView(restauranteSelecionado);
            menu.setVisible(true);
        });
    }
}

