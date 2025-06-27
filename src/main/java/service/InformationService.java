package service;

import configs.DBConnection;
import entities.Information;
import entities.Treatment;
import utils.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class InformationService {
  private DBConnection dbConnection;

  public InformationService() {
    this.dbConnection = new DBConnection();
  }

  public List<Information> getAllInfos() {
    String query = "SELECT i.id, i.full_name, i.cpf, i.age, i.mother_name, " +
                   "t.id as treatment_id, t.entry_date FROM tb_information i" +
                   " JOIN tb_treatment t ON i.treatment_id = t.id;\n";

    try (PreparedStatement st = dbConnection.getConnection().prepareStatement(query)) {
      try (ResultSet rs = st.executeQuery()) {
        List<Information> infos = new ArrayList<>();
        while (rs.next()) {
          Information info = new Information();
          Treatment treatment = new Treatment();
          info.setId(rs.getInt("id"));
          info.setFullName(rs.getString("full_name"));
          info.setCpf(rs.getString("cpf"));
          info.setAge(rs.getInt("age"));
          info.setMotherName(rs.getString("mother_name"));
          treatment.setId(rs.getInt("treatment_id"));
          treatment.setEntryDate(rs.getDate("entry_date"));
          info.setTreatment(treatment);
          infos.add(info);
        }
        return infos;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public int saveInformation (String socialName, String fullName, java.util.Date bornDate,
                             int age, String cpf,
                             String nationality, String motherName, String fatherName,
                             String maritalStatus, String ethnicity, String biologicalSex,
                             String sexualOrientation, String genderIdentity, int treatmentId) {

    String sql = "INSERT INTO tb_information (" +
                 "social_name, full_name, born_date, age, cpf, nationality, mother_name, father_name, " +
                 "marital_status_id, ethnicity_id, biological_sex_id, sexual_orientation_id, gender_identity_id, treatment_id) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, " +
                 "(SELECT id FROM tb_marital_status WHERE marital_status = ?), " +
                 "(SELECT id FROM tb_ethnicity WHERE etnia = ?), " +
                 "(SELECT id FROM tb_biological_sex WHERE biology_sex = ?), " +
                 "(SELECT id FROM tb_sexual_orientation WHERE sexual_orientation = ?), " +
                 "(SELECT id FROM tb_gender_identity WHERE gender_identity = ?), ?)";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, socialName);
      stmt.setString(2, fullName);
      stmt.setString(3, DateUtils.getFormattedDate(bornDate));
      stmt.setInt(4, age);
      stmt.setString(5, cpf);
      stmt.setString(6, nationality);
      stmt.setString(7, motherName);
      stmt.setString(8, fatherName);
      stmt.setString(9, maritalStatus);
      stmt.setString(10, ethnicity);
      stmt.setString(11, biologicalSex);
      stmt.setString(12, sexualOrientation);
      stmt.setString(13, genderIdentity);
      stmt.setInt(14, treatmentId);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da informação inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


}
