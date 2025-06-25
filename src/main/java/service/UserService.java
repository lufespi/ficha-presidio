package service;

import configs.DBConnection;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
  private DBConnection connection;

  public UserService() {
    this.connection = new DBConnection();
  }

  public User getUser(String username) {
    String query = "SELECT * FROM tb_user WHERE username=?";

    try (PreparedStatement st = connection.getConnection().prepareStatement(query)) {
      st.setString(1, username);
      try (ResultSet rs = st.executeQuery()) {
        if (rs.next()) {
          int id = rs.getInt("id");
          String foundName = rs.getString("username");
          String password = rs.getString("senha");

          User user = new User();
          user.setId(id);
          user.setUsername(foundName);
          user.setSenha(password);
          return user;
        } else {
          return null;
        }
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }


}
