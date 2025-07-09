package service;

import configs.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuickTestsService {
  private DBConnection dbConnection;
  public QuickTestsService() {
    this.dbConnection = new DBConnection();
  }

  public int saveQuickTests(String pregnantTest, boolean sputumCollection,
                            boolean hasComplaint, String complaintDescription,
                            boolean hasDentalComplaint, String dentalComplaint,
                            boolean needsDentalAssessment) {

    String sql = """
        INSERT INTO tb_quick_tests (
            pregnant_test, sputum_collection, has_complaint,
            complaint_description, has_dental_complaint,
            dental_complaint, needs_dental_assessment
        ) VALUES (?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, pregnantTest);
      stmt.setBoolean(2, sputumCollection);
      stmt.setBoolean(3, hasComplaint);
      stmt.setString(4, complaintDescription);
      stmt.setBoolean(5, hasDentalComplaint);
      stmt.setString(6, dentalComplaint);
      stmt.setBoolean(7, needsDentalAssessment);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID do teste rápido inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public boolean updateQuickTests(int id, String pregnantTest, boolean sputumCollection,
                                  boolean hasComplaint, String complaintDescription,
                                  boolean hasDentalComplaint, String dentalComplaint,
                                  boolean needsDentalAssessment) {

    String sql = """
        UPDATE tb_quick_tests SET
            pregnant_test = ?, sputum_collection = ?, has_complaint = ?,
            complaint_description = ?, has_dental_complaint = ?,
            dental_complaint = ?, needs_dental_assessment = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setString(1, pregnantTest);
      stmt.setBoolean(2, sputumCollection);
      stmt.setBoolean(3, hasComplaint);
      stmt.setString(4, complaintDescription);
      stmt.setBoolean(5, hasDentalComplaint);
      stmt.setString(6, dentalComplaint);
      stmt.setBoolean(7, needsDentalAssessment);
      stmt.setInt(8, id);

      return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }



}
