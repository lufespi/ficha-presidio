package service;

import configs.DBConnection;

import java.sql.*;

public class SocialEconomicDataService {
  private DBConnection dbConnection;

  public SocialEconomicDataService() {
    this.dbConnection = new DBConnection();
  }


  public int saveSocialEconomicData(String education,
                                    boolean hasFamilyBenefits, String familyBenefits,
                                    boolean hasChildren, Integer childrenQuantity, String ages,
                                    boolean hasDependents, Integer dependentsQuantity,
                                    boolean hasNeejaEducation, boolean hasSocialAssistent) {
    PreparedStatement selectEduStmt = null;
    PreparedStatement insertStmt = null;
    ResultSet rs = null;

    try {
      String selectEduSql = "SELECT id FROM tb_education WHERE education = ?";
      selectEduStmt = dbConnection.getConnection().prepareStatement(selectEduSql);
      selectEduStmt.setString(1, education);
      rs = selectEduStmt.executeQuery();

      if (!rs.next()) {
        throw new SQLException("Educação não encontrada: " + education);
      }
      int educationId = rs.getInt("id");

      String insertSql = """
            INSERT INTO tb_social_economic_data (
                education_id, has_family_benefits, family_benefits,
                has_children, children_quantity, ages,
                has_dependents, dependents_quantity,
                has_neeja_education, has_social_assistent
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

      insertStmt = dbConnection.getConnection().prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, educationId);
      insertStmt.setBoolean(2, hasFamilyBenefits);
      insertStmt.setString(3, familyBenefits);
      insertStmt.setBoolean(4, hasChildren);
      insertStmt.setObject(5, childrenQuantity, Types.INTEGER);
      insertStmt.setString(6, ages);
      insertStmt.setBoolean(7, hasDependents);
      insertStmt.setObject(8, dependentsQuantity, Types.INTEGER);
      insertStmt.setBoolean(9, hasNeejaEducation);
      insertStmt.setBoolean(10, hasSocialAssistent);

      insertStmt.executeUpdate();

      rs = insertStmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Falha ao obter ID de tb_social_economic_data.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      try {
        if (rs != null) rs.close();
        if (selectEduStmt != null) selectEduStmt.close();
        if (insertStmt != null) insertStmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public int updateSocialEconomicData(int id,
                                          String education,
                                          boolean hasFamilyBenefits, String familyBenefits,
                                          boolean hasChildren, Integer childrenQuantity, String ages,
                                          boolean hasDependents, Integer dependentsQuantity,
                                          boolean hasNeejaEducation, boolean hasSocialAssistent) {
    PreparedStatement selectEduStmt = null;
    PreparedStatement updateStmt = null;
    ResultSet rs = null;

    try {
      dbConnection.getConnection();

      String selectEduSql = "SELECT id FROM tb_education WHERE education = ?";
      selectEduStmt = dbConnection.getConnection().prepareStatement(selectEduSql);
      selectEduStmt.setString(1, education);
      rs = selectEduStmt.executeQuery();

      if (!rs.next()) {
        throw new SQLException("Educação não encontrada: " + education);
      }
      int educationId = rs.getInt("id");

      String updateSql = """
            UPDATE tb_social_economic_data SET
                education_id = ?, has_family_benefits = ?, family_benefits = ?,
                has_children = ?, children_quantity = ?, ages = ?,
                has_dependents = ?, dependents_quantity = ?,
                has_neeja_education = ?, has_social_assistent = ?
            WHERE id = ?
        """;

      updateStmt = dbConnection.getConnection().prepareStatement(updateSql);
      updateStmt.setInt(1, educationId);
      updateStmt.setBoolean(2, hasFamilyBenefits);
      updateStmt.setString(3, familyBenefits);
      updateStmt.setBoolean(4, hasChildren);
      updateStmt.setObject(5, childrenQuantity, Types.INTEGER);
      updateStmt.setString(6, ages);
      updateStmt.setBoolean(7, hasDependents);
      updateStmt.setObject(8, dependentsQuantity, Types.INTEGER);
      updateStmt.setBoolean(9, hasNeejaEducation);
      updateStmt.setBoolean(10, hasSocialAssistent);
      updateStmt.setInt(11, id);

      updateStmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    } finally {
      try {
        if (rs != null) rs.close();
        if (selectEduStmt != null) selectEduStmt.close();
        if (updateStmt != null) updateStmt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }


}
