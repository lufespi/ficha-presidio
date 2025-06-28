package service;

import configs.DBConnection;
import entities.*;
import utils.DateUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InformationService {
  private DBConnection dbConnection;

  public InformationService() {
    this.dbConnection = new DBConnection();
  }

  public List<Information> getAllInfos(String filterName) {
    String filter;
    if (filterName == null || filterName.isEmpty()) {
      filter = ";";
    } else {
      filter = " WHERE i.full_name LIKE ? OR i.social_name LIKE ?;";
    }

    String query = "SELECT \n" +
                   "    i.id AS information_id,\n" +
                   "    i.social_name,\n" +
                   "    i.full_name,\n" +
                   "    i.born_date,\n" +
                   "    i.age,\n" +
                   "    i.cpf,\n" +
                   "    i.nationality,\n" +
                   "    i.mother_name,\n" +
                   "    i.father_name,\n" +
                   "    \n" +
                   "    ms.id AS marital_status_id,\n" +
                   "    ms.marital_status,\n" +
                   "    e.id AS ethnicity_id,\n" +
                   "    e.etnia,\n" +
                   "    bs.id AS biological_id,\n" +
                   "    bs.biology_sex,\n" +
                    "    so.id AS sexual_orientation_id,\n" +
                   "    so.sexual_orientation,\n" +
                   "    gi.id AS gender_identity_id,\n" +
                   "    gi.gender_identity,\n" +
                   "    \n" +
                   "    t.id AS treatment_id,\n" +
                   "    t.treatment_date,\n" +
                   "    t.entry_date,\n" +
                   "    t.is_transfer,\n" +
                   "    t.source_transfer,\n" +
                   "    \n" +
                   "    u.id AS user_id,\n" +
                   "    u.username\n" +
                   "    \n" +
                   "FROM tb_information i\n" +
                   "JOIN tb_marital_status ms ON i.marital_status_id = ms.id\n" +
                   "JOIN tb_ethnicity e ON i.ethnicity_id = e.id\n" +
                   "JOIN tb_biological_sex bs ON i.biological_sex_id = bs.id\n" +
                   "JOIN tb_sexual_orientation so ON i.sexual_orientation_id = so.id\n" +
                   "JOIN tb_gender_identity gi ON i.gender_identity_id = gi.id\n" +
                   "JOIN tb_treatment t ON i.treatment_id = t.id\n" +
                   "JOIN tb_user u ON t.user_id = u.id" + filter;

    try (PreparedStatement st = dbConnection.getConnection().prepareStatement(query)) {
      if (filterName != null && !filterName.isEmpty()) {
        String searchPattern = "%" + filterName + "%";
        st.setString(1, searchPattern);
        st.setString(2, searchPattern);
      }
      try (ResultSet rs = st.executeQuery()) {
        List<Information> infos = new ArrayList<>();
        while (rs.next()) {
          Information info = new Information();
          Treatment treatment = new Treatment();
          GenderIdentity genderIdentity = new GenderIdentity();
          BiologicalSex biologicalSex = new BiologicalSex();
          Ethnicity ethnicity = new Ethnicity();
          MaritalStatus maritalStatus = new MaritalStatus();
          SexualOrientation sexualOrientation = new SexualOrientation();
          User user = new User();

          info.setId(rs.getInt("information_id"));
          info.setSocialName(rs.getString("social_name"));
          info.setFullName(rs.getString("full_name"));
          info.setBornDate(rs.getDate("born_date"));
          info.setAge(rs.getInt("age"));
          info.setCpf(rs.getString("cpf"));
          info.setNationality(rs.getString("nationality"));
          info.setMotherName(rs.getString("mother_name"));
          info.setFatherName(rs.getString("father_name"));
          maritalStatus.setId(rs.getInt("marital_status_id"));
          maritalStatus.setMaritalStatus(rs.getString("marital_status"));
          ethnicity.setId(rs.getInt("ethnicity_id"));
          ethnicity.setEtnia(rs.getString("etnia"));
          biologicalSex.setId(rs.getInt("biological_id"));
          biologicalSex.setBiologySex(rs.getString("biology_sex"));
          sexualOrientation.setId(rs.getInt("sexual_orientation_id"));
          sexualOrientation.setSexualOrientation(rs.getString("sexual_orientation"));
          genderIdentity.setId(rs.getInt("gender_identity_id"));
          genderIdentity.setGenderIdentity(rs.getString("gender_identity"));
          treatment.setId(rs.getInt("treatment_id"));
          treatment.setTreatmentDate(rs.getDate("treatment_date"));
          treatment.setEntryDate(rs.getDate("entry_date"));
          treatment.setTransfer(rs.getBoolean("is_transfer"));
          treatment.setSourceTransfer(rs.getString("source_transfer"));
          user.setId(rs.getInt("user_id"));
          user.setUsername(rs.getString("username"));
          info.setTreatment(treatment);
          info.setMaritalStatus(maritalStatus);
          info.setEthnicity(ethnicity);
          info.setBiologicalSex(biologicalSex);
          info.setSexualOrientation(sexualOrientation);
          info.setGenderIdentity(genderIdentity);
          info.setUser(user);
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

  public int updateInformation (int id, String socialName, String fullName, java.util.Date bornDate,
                                int age, String cpf,
                                String nationality, String motherName, String fatherName,
                                String maritalStatus, String ethnicity, String biologicalSex,
                                String sexualOrientation, String genderIdentity) {
    String query = "UPDATE tb_information SET\n" +
                   "  social_name = ?,\n" +
                   "  full_name = ?,\n" +
                   "  born_date = ?,\n" +
                   "  age = ?,\n" +
                   "  cpf = ?,\n" +
                   "  nationality = ?,\n" +
                   "  mother_name = ?,\n" +
                   "  father_name = ?,\n" +
                   "  marital_status_id = (SELECT id FROM tb_marital_status WHERE marital_status = ?),\n" +
                   "  ethnicity_id = (SELECT id FROM tb_ethnicity WHERE etnia = ?),\n" +
                   "  biological_sex_id = (SELECT id FROM tb_biological_sex WHERE biology_sex = ?),\n" +
                   "  sexual_orientation_id = (SELECT id FROM tb_sexual_orientation WHERE sexual_orientation = ?),\n" +
                   "  gender_identity_id = (SELECT id FROM tb_gender_identity WHERE gender_identity = ?) " +
                   "WHERE id = ?;";
    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
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
      stmt.setInt(14, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


}
