package service;

import configs.DBConnection;
import entities.WomenHealth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WomenHealthService {
  private DBConnection dbConnection;

  public WomenHealthService() {
    this.dbConnection = new DBConnection();
  }

  public int saveWomenHealth(boolean isPregnant, String pregnantAge,
                             String contraceptiveMethod,
                             boolean hasPreventiveExam, int preventiveExamYear,
                             boolean offerContraceptiveMethod,
                             boolean offerPreventiveExam,
                             boolean isPrenatal) {
    String sql = """
        INSERT INTO tb_women_health (
            is_pregant, pregnant_age,
            contraceptive_method,
            has_preventive_exam, preventive_exam_year,
            offer_contraceptive_method,
            offer_preventive_exam,
            is_prenatal
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, isPregnant);
      stmt.setString(2, pregnantAge);
      stmt.setString(3, contraceptiveMethod);
      stmt.setBoolean(4, hasPreventiveExam);
      stmt.setInt(5, preventiveExamYear);
      stmt.setBoolean(6, offerContraceptiveMethod);
      stmt.setBoolean(7, offerPreventiveExam);
      stmt.setBoolean(8, isPrenatal);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da saúde da mulher inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public boolean updateWomenHealth(int id,
                                   boolean isPregnant, String pregnantAge,
                                   String contraceptiveMethod,
                                   boolean hasPreventiveExam, int preventiveExamYear,
                                   boolean offerContraceptiveMethod,
                                   boolean offerPreventiveExam,
                                   boolean isPrenatal) {
    String sql = """
        UPDATE tb_women_health SET
            is_pregant = ?, pregnant_age = ?,
            contraceptive_method = ?,
            has_preventive_exam = ?, preventive_exam_year = ?,
            offer_contraceptive_method = ?,
            offer_preventive_exam = ?,
            is_prenatal = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
      stmt.setBoolean(1, isPregnant);
      stmt.setString(2, pregnantAge);
      stmt.setString(3, contraceptiveMethod);
      stmt.setBoolean(4, hasPreventiveExam);
      stmt.setInt(5, preventiveExamYear);
      stmt.setBoolean(6, offerContraceptiveMethod);
      stmt.setBoolean(7, offerPreventiveExam);
      stmt.setBoolean(8, isPrenatal);
      stmt.setInt(9, id);

      return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }



}
