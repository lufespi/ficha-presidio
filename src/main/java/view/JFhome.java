package view;

import controller.HomeController;

import javax.swing.*;
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
    public String usuarioLogado;
    private HomeController homeController;

    public JFhome(String usuario, LocalDateTime dataHoraLogin) {
        this.usuarioLogado = usuario;
        homeController = new HomeController();

        setTitle("Tela Principal - Sistema de Controle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Usando BorderLayout

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(new JLabel("Pesquisar por Nome:"));
        txtPesquisaNome = new JTextField(25);
        panelNorte.add(txtPesquisaNome);
        btnBusca = new JButton("Buscar");
        panelNorte.add(btnBusca);
        add(panelNorte, BorderLayout.NORTH);

        String[] colunas = {"ID", "Nome Completo", "CPF", "Idade", "Nome da mãe", "Data de Entrada"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaPresos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPresos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelSul = new JPanel(new BorderLayout());
        btnCadastro = new JButton("Cadastrar Novo Preso");
        btnCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        panelSul.add(btnCadastro, BorderLayout.EAST);

        JLabel lblUsuarioLogado = new JLabel("  Usuário logado: " + this.usuarioLogado);
        lblUsuarioLogado.setForeground(Color.GRAY);
        panelSul.add(lblUsuarioLogado, BorderLayout.WEST);
        add(panelSul, BorderLayout.SOUTH);

        btnCadastro.addActionListener(e -> {
            JFtreatment telaCadastro = new JFtreatment(this, null);
            telaCadastro.setVisible(true);
        });

        tabelaPresos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detecta clique duplo
                    int selectedRow = tabelaPresos.getSelectedRow();
                    if (selectedRow != -1) {
                        int idPreso = (int) tableModel.getValueAt(selectedRow, 0);
                    }
                }
            }
        });
        populateTable();

    }

    public void populateTable() {
        homeController.populateTable(this);
    }
}