package service;

import configs.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MentalHealthService {
  private DBConnection dbConnection;

  public MentalHealthService() {
    this.dbConnection = new DBConnection();
  }

  public int saveMentalHealth(boolean hasLinkCaps, String capsCity, String mentalDisorder,
                              boolean useControlledMedicine, String controlledMedicines,
                              boolean wasAccompaniment, String reasonAccompaniment,
                              String substanceUse, boolean hasTreatment, String substanceTreatment,
                              boolean wannaTreatment, String wannaTreatmentSubstance,
                              boolean offerPsychology, boolean offerPsychiatrist,
                              boolean revenueRenewal, boolean supportGroups) {

    String sql = """
        INSERT INTO tb_mental_health (
            has_link_caps, caps_city, mental_disorder,
            use_controlled_medicine, controlled_medicines,
            was_accompaniment, reason_accompaniment,
            substance_use, has_treatment, substance_treatment,
            wanna_treatment, wanna_treatment_substance,
            offer_psychology, offer_psychiatrist,
            revenue_renewal, support_groups
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, hasLinkCaps);
      stmt.setString(2, capsCity);
      stmt.setString(3, mentalDisorder);
      stmt.setBoolean(4, useControlledMedicine);
      stmt.setString(5, controlledMedicines);
      stmt.setBoolean(6, wasAccompaniment);
      stmt.setString(7, reasonAccompaniment);
      stmt.setString(8, substanceUse);
      stmt.setBoolean(9, hasTreatment);
      stmt.setString(10, substanceTreatment);
      stmt.setBoolean(11, wannaTreatment);
      stmt.setString(12, wannaTreatmentSubstance);
      stmt.setBoolean(13, offerPsychology);
      stmt.setBoolean(14, offerPsychiatrist);
      stmt.setBoolean(15, revenueRenewal);
      stmt.setBoolean(16, supportGroups);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da saúde mental inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public boolean updateMentalHealth(int id, boolean hasLinkCaps, String capsCity, String mentalDisorder,
                                    boolean useControlledMedicine, String controlledMedicines,
                                    boolean wasAccompaniment, String reasonAccompaniment,
                                    String substanceUse, boolean hasTreatment, String substanceTreatment,
                                    boolean wannaTreatment, String wannaTreatmentSubstance,
                                    boolean offerPsychology, boolean offerPsychiatrist,
                                    boolean revenueRenewal, boolean supportGroups) {

    String sql = """
        UPDATE tb_mental_health SET
            has_link_caps = ?, caps_city = ?, mental_disorder = ?,
            use_controlled_medicine = ?, controlled_medicines = ?,
            was_accompaniment = ?, reason_accompaniment = ?,
            substance_use = ?, has_treatment = ?, substance_treatment = ?,
            wanna_treatment = ?, wanna_treatment_substance = ?,
            offer_psychology = ?, offer_psychiatrist = ?,
            revenue_renewal = ?, support_groups = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
      stmt.setBoolean(1, hasLinkCaps);
      stmt.setString(2, capsCity);
      stmt.setString(3, mentalDisorder);
      stmt.setBoolean(4, useControlledMedicine);
      stmt.setString(5, controlledMedicines);
      stmt.setBoolean(6, wasAccompaniment);
      stmt.setString(7, reasonAccompaniment);
      stmt.setString(8, substanceUse);
      stmt.setBoolean(9, hasTreatment);
      stmt.setString(10, substanceTreatment);
      stmt.setBoolean(11, wannaTreatment);
      stmt.setString(12, wannaTreatmentSubstance);
      stmt.setBoolean(13, offerPsychology);
      stmt.setBoolean(14, offerPsychiatrist);
      stmt.setBoolean(15, revenueRenewal);
      stmt.setBoolean(16, supportGroups);
      stmt.setInt(17, id);

      return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }


}
