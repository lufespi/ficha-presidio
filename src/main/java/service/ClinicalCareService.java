package service;

import configs.DBConnection;
import utils.DateUtils;

import java.math.BigDecimal;
import java.sql.*;

public class ClinicalCareService {
  private DBConnection dbConnection;

  public ClinicalCareService() {
    this.dbConnection = new DBConnection();
  }

  public int saveClinicalCare(BigDecimal weight, BigDecimal height, BigDecimal imc,
                              BigDecimal bloodPressure, BigDecimal heartRate, BigDecimal saturation,
                              BigDecimal temperature, String respiratorySymptoms,
                              java.util.Date startDateRespiratorySymptoms,
                              boolean hasInjuries, String injuriesSites) {

    String sql = """
        INSERT INTO tb_clinical_care (
            weight, height, imc, blood_pressure, hear_rate,
            saturation, temperature, respiratory_symptoms,
            start_date_respiratory_symptoms, has_injuries, injuries_sites
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBigDecimal(1, weight);
      stmt.setBigDecimal(2, height);
      stmt.setBigDecimal(3, imc);
      stmt.setBigDecimal(4, bloodPressure);
      stmt.setBigDecimal(5, heartRate);
      stmt.setBigDecimal(6, saturation);
      stmt.setBigDecimal(7, temperature);
      stmt.setString(8, respiratorySymptoms);

      if (startDateRespiratorySymptoms != null) {
        stmt.setString(9, DateUtils.getFormattedDate(startDateRespiratorySymptoms));
      } else {
        stmt.setNull(9, Types.DATE);
      }

      stmt.setBoolean(10, hasInjuries);
      stmt.setString(11, injuriesSites);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID do atendimento clínico inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public boolean updateClinicalCare(int id, BigDecimal weight, BigDecimal height, BigDecimal imc,
                                    BigDecimal bloodPressure, BigDecimal heartRate, BigDecimal saturation,
                                    BigDecimal temperature, String respiratorySymptoms,
                                    java.util.Date startDateRespiratorySymptoms,
                                    boolean hasInjuries, String injuriesSites) {

    String sql = """
        UPDATE tb_clinical_care SET
            weight = ?, height = ?, imc = ?, blood_pressure = ?, hear_rate = ?,
            saturation = ?, temperature = ?, respiratory_symptoms = ?,
            start_date_respiratory_symptoms = ?, has_injuries = ?, injuries_sites = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setBigDecimal(1, weight);
      stmt.setBigDecimal(2, height);
      stmt.setBigDecimal(3, imc);
      stmt.setBigDecimal(4, bloodPressure);
      stmt.setBigDecimal(5, heartRate);
      stmt.setBigDecimal(6, saturation);
      stmt.setBigDecimal(7, temperature);
      stmt.setString(8, respiratorySymptoms);

      if (startDateRespiratorySymptoms != null) {
        stmt.setString(9, DateUtils.getFormattedDate(startDateRespiratorySymptoms));
      } else {
        stmt.setNull(9, Types.DATE);
      }

      stmt.setBoolean(10, hasInjuries);
      stmt.setString(11, injuriesSites);
      stmt.setInt(12, id);

      return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }


}
