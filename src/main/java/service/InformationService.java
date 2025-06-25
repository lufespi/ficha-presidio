package service;

import configs.DBConnection;
import entities.Information;
import entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformationService {
  private DBConnection connection;

  public InformationService() {
    this.connection = new DBConnection();
  }

  public List<Information> getAllInfos() {
    String query = "SELECT id, full_name, cpf, age, mother_name FROM tb_information;";

    try (PreparedStatement st = connection.getConnection().prepareStatement(query)) {
      try (ResultSet rs = st.executeQuery()) {
        List<Information> infos = new ArrayList<>();
        while (rs.next()) {
          Information info = new Information();
          info.setId(rs.getInt("id"));
          info.setFullName(rs.getString("full_name"));
          info.setCpf(rs.getString("cpf"));
          info.setAge(rs.getInt("age"));
          info.setMotherName(rs.getString("mother_name"));
          infos.add(info);
        }
        return infos;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
