package view;// view.JFhome.java
import entities.Preso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;

public class JFhome extends JFrame {

    private JTextField txtPesquisaNome;
    private JButton btnBusca, btnCadastro;
    private JTable tabelaPresos;
    private DefaultTableModel tableModel;
    private String usuarioLogado;

    public JFhome(String usuario, LocalDateTime dataHoraLogin) {
        this.usuarioLogado = usuario;

        setTitle("Tela Principal - Sistema de Controle");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Usando BorderLayout

        // --- Painel Norte (Pesquisa) ---
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.add(new JLabel("Pesquisar por Nome:"));
        txtPesquisaNome = new JTextField(25);
        panelNorte.add(txtPesquisaNome);
        btnBusca = new JButton("Buscar");
        panelNorte.add(btnBusca);
        add(panelNorte, BorderLayout.NORTH);

        // --- Painel Central (Tabela) ---
        String[] colunas = {"ID", "Nome Completo", "CPF", "Data de Entrada"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna a tabela não editável diretamente
            }
        };
        tabelaPresos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaPresos);
        add(scrollPane, BorderLayout.CENTER);

        // --- Painel Sul (Botões e Status) ---
        JPanel panelSul = new JPanel(new BorderLayout());
        btnCadastro = new JButton("Cadastrar Novo Preso");
        btnCadastro.setFont(new Font("Arial", Font.BOLD, 14));
        panelSul.add(btnCadastro, BorderLayout.EAST);

        JLabel lblUsuarioLogado = new JLabel("  Usuário logado: " + this.usuarioLogado);
        lblUsuarioLogado.setForeground(Color.GRAY);
        panelSul.add(lblUsuarioLogado, BorderLayout.WEST);
        add(panelSul, BorderLayout.SOUTH);

        // --- Carregar Dados Iniciais ---
        carregarTabela(null);

        // --- Ações dos Botões e Tabela ---
        btnCadastro.addActionListener(e -> {
            // Abre a tela de tratamento para um NOVO cadastro (passa null)
            JFtreatment telaCadastro = new JFtreatment(this, usuarioLogado, null);
            telaCadastro.setVisible(true);
        });

        btnBusca.addActionListener(e -> carregarTabela(txtPesquisaNome.getText()));

        // Ação de clique duplo na tabela para EDITAR
        tabelaPresos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Detecta clique duplo
                    int selectedRow = tabelaPresos.getSelectedRow();
                    if (selectedRow != -1) {
                        int idPreso = (int) tableModel.getValueAt(selectedRow, 0);
                        Preso presoParaEditar = PresoDAO.buscarPorId(idPreso);

                        // Abre a tela de tratamento para EDIÇÃO, passando o objeto
                        JFtreatment telaEdicao = new JFtreatment(JFhome.this, usuarioLogado, presoParaEditar);
                        telaEdicao.setVisible(true);
                    }
                }
            }
        });
    }

    /**
     * Limpa e carrega a tabela com os dados dos presos.
     * @param nome O nome para filtrar a busca, ou null para buscar todos.
     */
    public void carregarTabela(String nome) {
        // Limpa a tabela
        tableModel.setRowCount(0);

        // Busca os dados no DAO
        List<Preso> listaDePresos = PresoDAO.buscarPorNome(nome);

        // Preenche a tabela
        for (Preso p : listaDePresos) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getNomeCompleto(),
                    p.getCpf(),
                    p.getDataEntrada()
            });
        }
    }
}