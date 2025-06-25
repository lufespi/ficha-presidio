package view;

import javax.swing.*;
import java.time.LocalDateTime;

public class JFlogin extends JFrame {

    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCancelar;

    public JFlogin() {
        this.setTitle("Login do Sistema");
        this.setSize(380, 220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(50, 30, 80, 25);
        this.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 200, 25);
        this.add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 70, 80, 25);
        this.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 70, 200, 25);
        this.add(txtSenha);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 120, 90, 30);
        this.add(btnLogin);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(230, 120, 90, 30);
        this.add(btnCancelar);

        btnCancelar.addActionListener(e -> System.exit(0));

        btnLogin.addActionListener(e -> realizarLogin());
    }

    private void realizarLogin() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        final String USUARIO_VALIDO = "admin";
        final String SENHA_VALIDA = "12345";

        if (usuario.equals(USUARIO_VALIDO) && senha.equals(SENHA_VALIDA)) {
            LocalDateTime dataHoraLogin = LocalDateTime.now();

            JFhome telaHome = new JFhome(usuario, dataHoraLogin);
            telaHome.setVisible(true);

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
            txtUsuario.setText("");
            txtSenha.setText("");
            txtUsuario.requestFocus();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFlogin().setVisible(true));
    }
}