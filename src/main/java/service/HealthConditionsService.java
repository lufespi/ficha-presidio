package service;

import configs.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HealthConditionsService {
  private DBConnection dbConnection;

  public HealthConditionsService() {
    this.dbConnection = new DBConnection();
  }

  public int saveHealthConditions(
          boolean hasDeficiency, String deficiency,
          boolean hasAllergies, String allergies,
          boolean hasSurgeries, String surgeries,
          boolean hasHypertension, boolean hasDiabetes,
          boolean hasHiv, boolean hasAutoimmune,
          boolean hasSyphilis, boolean hasHpv,
          String otherChronicDisease,
          boolean hasTuberculosis, boolean hasHepatitisB,
          boolean hasHepatitisC, String otherInfectiousDisease,
          boolean hasSkinDisease, String skinDiseases,
          boolean useContinuousMedication, String continuousMedication,
          String bloodType) {

    String sql = """
        INSERT INTO tb_health_conditions (
            has_deficiency, deficiency,
            has_allergies, allergies,
            has_surgeries, surgeries,
            has_hypertension, has_diabetes, has_hiv, has_autoimmune,
            has_syphilis, has_hpv, other_chronic_disease,
            has_tuberculosis, has_hepatitis_B, has_hepatitis_C, other_infectious_disease,
            has_skin_disease, skin_diseases,
            use_continuous_medication, continuos_medication,
            blood_type
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, hasDeficiency);
      stmt.setString(2, deficiency);
      stmt.setBoolean(3, hasAllergies);
      stmt.setString(4, allergies);
      stmt.setBoolean(5, hasSurgeries);
      stmt.setString(6, surgeries);
      stmt.setBoolean(7, hasHypertension);
      stmt.setBoolean(8, hasDiabetes);
      stmt.setBoolean(9, hasHiv);
      stmt.setBoolean(10, hasAutoimmune);
      stmt.setBoolean(11, hasSyphilis);
      stmt.setBoolean(12, hasHpv);
      stmt.setString(13, otherChronicDisease);
      stmt.setBoolean(14, hasTuberculosis);
      stmt.setBoolean(15, hasHepatitisB);
      stmt.setBoolean(16, hasHepatitisC);
      stmt.setString(17, otherInfectiousDisease);
      stmt.setBoolean(18, hasSkinDisease);
      stmt.setString(19, skinDiseases);
      stmt.setBoolean(20, useContinuousMedication);
      stmt.setString(21, continuousMedication);
      stmt.setString(22, bloodType);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID de tb_health_conditions inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateHealthConditions(
          int id,
          boolean hasDeficiency, String deficiency,
          boolean hasAllergies, String allergies,
          boolean hasSurgeries, String surgeries,
          boolean hasHypertension, boolean hasDiabetes,
          boolean hasHiv, boolean hasAutoimmune,
          boolean hasSyphilis, boolean hasHpv,
          String otherChronicDisease,
          boolean hasTuberculosis, boolean hasHepatitisB,
          boolean hasHepatitisC, String otherInfectiousDisease,
          boolean hasSkinDisease, String skinDiseases,
          boolean useContinuousMedication, String continuousMedication,
          String bloodType) {

    String sql = """
        UPDATE tb_health_conditions SET
            has_deficiency = ?, deficiency = ?,
            has_allergies = ?, allergies = ?,
            has_surgeries = ?, surgeries = ?,
            has_hypertension = ?, has_diabetes = ?, has_hiv = ?, has_autoimmune = ?,
            has_syphilis = ?, has_hpv = ?, other_chronic_disease = ?,
            has_tuberculosis = ?, has_hepatitis_B = ?, has_hepatitis_C = ?, other_infectious_disease = ?,
            has_skin_disease = ?, skin_diseases = ?,
            use_continuous_medication = ?, continuos_medication = ?,
            blood_type = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
      stmt.setBoolean(1, hasDeficiency);
      stmt.setString(2, deficiency);
      stmt.setBoolean(3, hasAllergies);
      stmt.setString(4, allergies);
      stmt.setBoolean(5, hasSurgeries);
      stmt.setString(6, surgeries);
      stmt.setBoolean(7, hasHypertension);
      stmt.setBoolean(8, hasDiabetes);
      stmt.setBoolean(9, hasHiv);
      stmt.setBoolean(10, hasAutoimmune);
      stmt.setBoolean(11, hasSyphilis);
      stmt.setBoolean(12, hasHpv);
      stmt.setString(13, otherChronicDisease);
      stmt.setBoolean(14, hasTuberculosis);
      stmt.setBoolean(15, hasHepatitisB);
      stmt.setBoolean(16, hasHepatitisC);
      stmt.setString(17, otherInfectiousDisease);
      stmt.setBoolean(18, hasSkinDisease);
      stmt.setString(19, skinDiseases);
      stmt.setBoolean(20, useContinuousMedication);
      stmt.setString(21, continuousMedication);
      stmt.setString(22, bloodType);
      stmt.setInt(23, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }



}
