package view;

import controller.HomeController;
import entities.Information;
import entities.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

public class JFhome extends JFrame {

    public JTextField txtPesquisaNome;
    public JButton btnBusca, btnCadastro;
    public JTable tabelaPresos;
    public DefaultTableModel tableModel;
    public User username;
    private HomeController homeController;

    public JFhome(User user) {
        this.username = user;
        homeController = new HomeController();

        setTitle("Tela Principal - Sistema de Controle");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.setBorder(new TitledBorder("Pesquisa"));
        panelNorte.add(new JLabel("Pesquisar por Nome:"));
        txtPesquisaNome = new JTextField(25);
        panelNorte.add(txtPesquisaNome);
        btnBusca = new JButton("Buscar");
        panelNorte.add(btnBusca);
        panelNorte.setBounds(10, 10, 785, 60);
        add(panelNorte);

        String[] colunas = {"ID", "Nome Completo", "CPF", "Idade", "Nome da mãe", "Data de Entrada"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaPresos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPresos);
        scrollPane.setBorder(new TitledBorder("Tabela Presos"));
        scrollPane.setBounds(10, 80, 785, 610);
        add(scrollPane);

        JPanel panelSul = new JPanel(new BorderLayout());
        panelSul.setBorder(new TitledBorder("Ações"));
        btnCadastro = new JButton("Cadastrar Novo Preso");
        btnCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        panelSul.add(btnCadastro, BorderLayout.EAST);

        JLabel lblUsuarioLogado = new JLabel("  Usuário logado: " + this.username.getUsername());
        lblUsuarioLogado.setForeground(Color.GRAY);
        panelSul.add(lblUsuarioLogado, BorderLayout.WEST);
        panelSul.setBounds(10, 700, 785, 60);
        add(panelSul);

        btnCadastro.addActionListener(e -> {
            JFtreatment telaCadastro = new JFtreatment(this, username, null);
            telaCadastro.setVisible(true);
        });

        tabelaPresos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tabelaPresos.getSelectedRow();
                    if (selectedRow != -1) {
                        int idPreso = (int) tableModel.getValueAt(selectedRow, 0);
                        Information information = homeController.getInformationById(idPreso);
                        if (information != null) {
                            JFtreatment editScreen = new JFtreatment(JFhome.this, username, information);
                            editScreen.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(JFhome.this, "Informação não encontrada para o ID: " + idPreso,
                                    "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        populateTable(null);
        btnBusca.addActionListener(e -> populateTable(txtPesquisaNome.getText()));

    }

    public void populateTable(String filterName) {
        homeController.populateTable(this, filterName);
    }
}