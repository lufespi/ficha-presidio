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

  public int saveHealthConditions(boolean hasDeficiency, String deficiency,
                                  boolean hasAllergies, String allergies,
                                  boolean hasSurgeries, String surgeries,
                                  String chronicDiseases, String infectiousDiseases,
                                  boolean hasSkinDisease, String skinDiseases,
                                  boolean useContinuousMedication, String continuousMedication,
                                  String bloodType) {
    String sql = """
        INSERT INTO tb_health_conditions (
            has_deficiency, deficiency,
            has_allergies, allergies,
            has_surgeries, surgeries,
            chronic_diseases, infectious_diseases,
            has_skin_disease, skin_diseases,
            use_continuous_medication, continuos_medication,
            blood_type
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, hasDeficiency);
      stmt.setString(2, deficiency);
      stmt.setBoolean(3, hasAllergies);
      stmt.setString(4, allergies);
      stmt.setBoolean(5, hasSurgeries);
      stmt.setString(6, surgeries);
      stmt.setString(7, chronicDiseases);
      stmt.setString(8, infectiousDiseases);
      stmt.setBoolean(9, hasSkinDisease);
      stmt.setString(10, skinDiseases);
      stmt.setBoolean(11, useContinuousMedication);
      stmt.setString(12, continuousMedication);
      stmt.setString(13, bloodType);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da condição de saúde inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int updateHealthConditions(int id,
                                        boolean hasDeficiency, String deficiency,
                                        boolean hasAllergies, String allergies,
                                        boolean hasSurgeries, String surgeries,
                                        String chronicDiseases, String infectiousDiseases,
                                        boolean hasSkinDisease, String skinDiseases,
                                        boolean useContinuousMedication, String continuousMedication,
                                        String bloodType) {
    String sql = """
        UPDATE tb_health_conditions SET
            has_deficiency = ?, deficiency = ?,
            has_allergies = ?, allergies = ?,
            has_surgeries = ?, surgeries = ?,
            chronic_diseases = ?, infectious_diseases = ?,
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
      stmt.setString(7, chronicDiseases);
      stmt.setString(8, infectiousDiseases);
      stmt.setBoolean(9, hasSkinDisease);
      stmt.setString(10, skinDiseases);
      stmt.setBoolean(11, useContinuousMedication);
      stmt.setString(12, continuousMedication);
      stmt.setString(13, bloodType);
      stmt.setInt(14, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }


}
