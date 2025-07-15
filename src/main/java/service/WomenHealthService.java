package service;

import configs.DBConnection;
import entities.WomenHealth;

import java.sql.*;

public class WomenHealthService {
  private DBConnection dbConnection;

  public WomenHealthService() {
    this.dbConnection = new DBConnection();
  }

  public int saveWomenHealth(
          boolean isPregnant,
          String pregnantAge,
          boolean useOralContraceptive,
          boolean useInjectableContraceptive,
          boolean useIUDImplant,
          boolean useTubalLigation,
          boolean useHysterectomy,
          boolean hasPreventiveExam,
          int preventiveExamYear,
          boolean offerContraceptiveMethod,
          boolean offerPreventiveExam,
          boolean isPrenatal) {

    String sql = """
        INSERT INTO tb_women_health (
            is_pregnant, pregnant_age,
            use_oral_contraceptive, use_injectable_contraceptive, use_IUD_implant,
            use_tubal_ligation, use_hysterectomy,
            has_preventive_exam, preventive_exam_year,
            offer_contraceptive_method, offer_preventive_exam,
            is_prenatal
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, isPregnant);
      stmt.setString(2, pregnantAge);
      stmt.setBoolean(3, useOralContraceptive);
      stmt.setBoolean(4, useInjectableContraceptive);
      stmt.setBoolean(5, useIUDImplant);
      stmt.setBoolean(6, useTubalLigation);
      stmt.setBoolean(7, useHysterectomy);
      stmt.setBoolean(8, hasPreventiveExam);
      stmt.setInt(9, preventiveExamYear);
      stmt.setBoolean(10, offerContraceptiveMethod);
      stmt.setBoolean(11, offerPreventiveExam);
      stmt.setBoolean(12, isPrenatal);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1); // retorna o ID gerado
      } else {
        throw new SQLException("Erro ao obter o ID de tb_women_health.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateWomenHealth(
          int id,
          boolean isPregnant,
          String pregnantAge,
          boolean useOralContraceptive,
          boolean useInjectableContraceptive,
          boolean useIUDImplant,
          boolean useTubalLigation,
          boolean useHysterectomy,
          boolean hasPreventiveExam,
          Integer preventiveExamYear,
          boolean offerContraceptiveMethod,
          boolean offerPreventiveExam,
          boolean isPrenatal) {

    String sql = """
        UPDATE tb_women_health SET
            is_pregnant = ?, pregnant_age = ?,
            use_oral_contraceptive = ?, use_injectable_contraceptive = ?, use_IUD_implant = ?,
            use_tubal_ligation = ?, use_hysterectomy = ?,
            has_preventive_exam = ?, preventive_exam_year = ?,
            offer_contraceptive_method = ?, offer_preventive_exam = ?,
            is_prenatal = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setBoolean(1, isPregnant);
      stmt.setString(2, pregnantAge);
      stmt.setBoolean(3, useOralContraceptive);
      stmt.setBoolean(4, useInjectableContraceptive);
      stmt.setBoolean(5, useIUDImplant);
      stmt.setBoolean(6, useTubalLigation);
      stmt.setBoolean(7, useHysterectomy);
      stmt.setBoolean(8, hasPreventiveExam);
      if (preventiveExamYear != null)
        stmt.setInt(9, preventiveExamYear);
      else
        stmt.setNull(9, Types.INTEGER);
      stmt.setBoolean(10, offerContraceptiveMethod);
      stmt.setBoolean(11, offerPreventiveExam);
      stmt.setBoolean(12, isPrenatal);
      stmt.setInt(13, id);

      stmt.executeUpdate();
      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }




}
