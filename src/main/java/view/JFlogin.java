package view;


import controller.LoginController;
import entities.User;

import javax.swing.*;
import java.time.LocalDateTime;

public class JFlogin extends JFrame {

    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JButton btnCancelar;
    private LoginController loginController;

    public JFlogin() {
        this.setTitle("Login do Sistema");
        this.setSize(380, 220);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.loginController = new LoginController(this);

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
        String username = txtUsuario.getText();
        String password = new String(txtSenha.getPassword());

        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user =  loginController.makeLogin(username, password);

        if (user != null) {
            new JFhome(user).setVisible(true);
            this.dispose();
        }
    }
}