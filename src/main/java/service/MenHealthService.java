package service;

import configs.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MenHealthService {
  private DBConnection dbConnection;
  public MenHealthService() {
    this.dbConnection = new DBConnection();
  }

  public int saveMenHealth(boolean hasPreventExam, int preventExamYear,
                           boolean hasProstateCancerFamilyHistory, String prostateCancerFamily,
                           boolean vasectomy, boolean pregnantPartner,
                           boolean prenatalPregnantPartner, boolean offerVasectomy,
                           boolean offerPrenatal) {

    String sql = """
        INSERT INTO tb_men_health (
            has_prevent_exam, prevent_exam_year,
            has_prostate_cancer_family_history, prostate_cancer_family,
            vasectomy, pregnant_partner, prenatal_pregnant_partner,
            offer_vasectomy, offer_prenatal
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, hasPreventExam);
      stmt.setInt(2, preventExamYear);
      stmt.setBoolean(3, hasProstateCancerFamilyHistory);
      stmt.setString(4, prostateCancerFamily);
      stmt.setBoolean(5, vasectomy);
      stmt.setBoolean(6, pregnantPartner);
      stmt.setBoolean(7, prenatalPregnantPartner);
      stmt.setBoolean(8, offerVasectomy);
      stmt.setBoolean(9, offerPrenatal);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da saúde do homem inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int updateMenHealth(int id, boolean hasPreventExam, int preventExamYear,
                                 boolean hasProstateCancerFamilyHistory, String prostateCancerFamily,
                                 boolean vasectomy, boolean pregnantPartner,
                                 boolean prenatalPregnantPartner, boolean offerVasectomy,
                                 boolean offerPrenatal) {

    String sql = """
        UPDATE tb_men_health SET
            has_prevent_exam = ?, prevent_exam_year = ?,
            has_prostate_cancer_family_history = ?, prostate_cancer_family = ?,
            vasectomy = ?, pregnant_partner = ?, prenatal_pregnant_partner = ?,
            offer_vasectomy = ?, offer_prenatal = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
      stmt.setBoolean(1, hasPreventExam);
      stmt.setInt(2, preventExamYear);
      stmt.setBoolean(3, hasProstateCancerFamilyHistory);
      stmt.setString(4, prostateCancerFamily);
      stmt.setBoolean(5, vasectomy);
      stmt.setBoolean(6, pregnantPartner);
      stmt.setBoolean(7, prenatalPregnantPartner);
      stmt.setBoolean(8, offerVasectomy);
      stmt.setBoolean(9, offerPrenatal);
      stmt.setInt(10, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

}
