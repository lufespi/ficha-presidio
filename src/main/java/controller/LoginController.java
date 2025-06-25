package controller;

import entities.User;
import service.UserService;
import utils.UserUtils;

import javax.swing.*;

public class LoginController {
  private UserService userService;
  private JFrame frame;

  public LoginController(JFrame frame) {
    this.userService = new UserService();
    this.frame = frame;
  }

  public User makeLogin(String username, String password) {
    User user = userService.getUser(username);
    if (user == null) {
      JOptionPane.showMessageDialog(frame, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
      return null;
    } else {
      String inputMd5 = UserUtils.md5(password);
      if (!inputMd5.equals(user.getSenha())) {
        JOptionPane.showMessageDialog(frame, "Senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
        return  null;
      }
      return user;
    }
  }
}
