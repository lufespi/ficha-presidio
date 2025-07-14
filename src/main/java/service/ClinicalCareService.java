package service;

import configs.DBConnection;
import utils.DateUtils;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ClinicalCareService {
  private DBConnection dbConnection;

  public ClinicalCareService() {
    this.dbConnection = new DBConnection();
  }

  public int saveClinicalCare(
          BigDecimal weight,
          BigDecimal height,
          BigDecimal imc,
          BigDecimal bloodPressure,
          BigDecimal hearRate,
          BigDecimal saturation,
          BigDecimal temperature,
          boolean hasCough,
          boolean hasRunnyNose,
          boolean hasSneezing,
          boolean hasFever,
          boolean hasChills,
          String otherSymptoms,
          Date startDateRespiratorySymptoms,
          boolean hasInjuries,
          String injuriesSites) {

    String sql = """
        INSERT INTO tb_clinical_care (
            weight, height, imc, blood_pressure, hear_rate,
            saturation, temperature,
            has_cough, has_runny_nose, has_sneezing, has_fever, has_chills,
            other_symptoms, start_date_respiratory_symptoms,
            has_injuries, injuries_sites
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBigDecimal(1, weight);
      stmt.setBigDecimal(2, height);
      stmt.setBigDecimal(3, imc);
      stmt.setBigDecimal(4, bloodPressure);
      stmt.setBigDecimal(5, hearRate);
      stmt.setBigDecimal(6, saturation);
      stmt.setBigDecimal(7, temperature);
      stmt.setBoolean(8, hasCough);
      stmt.setBoolean(9, hasRunnyNose);
      stmt.setBoolean(10, hasSneezing);
      stmt.setBoolean(11, hasFever);
      stmt.setBoolean(12, hasChills);
      stmt.setString(13, otherSymptoms);
      if (startDateRespiratorySymptoms != null)
        stmt.setString(14, DateUtils.getFormattedDate(startDateRespiratorySymptoms));
      else
        stmt.setNull(14, java.sql.Types.VARCHAR);
      stmt.setBoolean(15, hasInjuries);
      stmt.setString(16, injuriesSites);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID de tb_clinical_care inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateClinicalCare(
          int id,
          BigDecimal weight,
          BigDecimal height,
          BigDecimal imc,
          BigDecimal bloodPressure,
          BigDecimal hearRate,
          BigDecimal saturation,
          BigDecimal temperature,
          boolean hasCough,
          boolean hasRunnyNose,
          boolean hasSneezing,
          boolean hasFever,
          boolean hasChills,
          String otherSymptoms,
          Date startDateRespiratorySymptoms,
          boolean hasInjuries,
          String injuriesSites) {

    String sql = """
        UPDATE tb_clinical_care SET
            weight = ?, height = ?, imc = ?, blood_pressure = ?, hear_rate = ?,
            saturation = ?, temperature = ?,
            has_cough = ?, has_runny_nose = ?, has_sneezing = ?, has_fever = ?, has_chills = ?,
            other_symptoms = ?, start_date_respiratory_symptoms = ?,
            has_injuries = ?, injuries_sites = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setBigDecimal(1, weight);
      stmt.setBigDecimal(2, height);
      stmt.setBigDecimal(3, imc);
      stmt.setBigDecimal(4, bloodPressure);
      stmt.setBigDecimal(5, hearRate);
      stmt.setBigDecimal(6, saturation);
      stmt.setBigDecimal(7, temperature);
      stmt.setBoolean(8, hasCough);
      stmt.setBoolean(9, hasRunnyNose);
      stmt.setBoolean(10, hasSneezing);
      stmt.setBoolean(11, hasFever);
      stmt.setBoolean(12, hasChills);
      stmt.setString(13, otherSymptoms);
      if (startDateRespiratorySymptoms != null)
        stmt.setString(14, DateUtils.getFormattedDate(startDateRespiratorySymptoms));
      else
        stmt.setNull(14, java.sql.Types.VARCHAR);
      stmt.setBoolean(15, hasInjuries);
      stmt.setString(16, injuriesSites);
      stmt.setInt(17, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }



}
