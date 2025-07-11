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

  public int saveMentalHealth(
          boolean hasLinkCaps,String capsCity, boolean hasAnxiety, boolean hasDepression,
          boolean hasBipolarity, boolean hasSchizophrenia, boolean hasAutism, String otherMentalDisorder,
          boolean useControlledMedicine, String controlledMedicines, boolean wasAccompaniment, String reasonAccompaniment,
          boolean useAlcohol, boolean useCigarettes, boolean useMarijuana, boolean useCrack,
          boolean useCocaine, boolean useAmphetamines, boolean useKDrugs, String otherSubstances,
          boolean hasTreatment, String substanceTreatment, boolean wannaTreatment, String wannaTreatmentSubstance,
          boolean offerPsychology, boolean offerPsychiatrist, boolean revenueRenewal, boolean supportGroups) {

    String sql = """
        INSERT INTO tb_mental_health (
            has_link_caps, caps_city, has_anxiety, has_depression, has_bipolarity,
            has_schizophrenia, has_autism, other_mental_disorder, use_controlled_medicine,
            controlled_medicines, was_accompaniment, reason_accompaniment, use_alcohol,
            use_cigarettes, use_marijuana, use_crack, use_cocaine, use_amphetamines,
            use_k_drugs, other_substances, has_treatment, substance_treatment, wanna_treatment,
            wanna_treatment_substance, offer_psychology, offer_psychiatrist, revenue_renewal,
            support_groups
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, hasLinkCaps);
      stmt.setString(2, capsCity);
      stmt.setBoolean(3, hasAnxiety);
      stmt.setBoolean(4, hasDepression);
      stmt.setBoolean(5, hasBipolarity);
      stmt.setBoolean(6, hasSchizophrenia);
      stmt.setBoolean(7, hasAutism);
      stmt.setString(8, otherMentalDisorder);
      stmt.setBoolean(9, useControlledMedicine);
      stmt.setString(10, controlledMedicines);
      stmt.setBoolean(11, wasAccompaniment);
      stmt.setString(12, reasonAccompaniment);
      stmt.setBoolean(13, useAlcohol);
      stmt.setBoolean(14, useCigarettes);
      stmt.setBoolean(15, useMarijuana);
      stmt.setBoolean(16, useCrack);
      stmt.setBoolean(17, useCocaine);
      stmt.setBoolean(18, useAmphetamines);
      stmt.setBoolean(19, useKDrugs);
      stmt.setString(20, otherSubstances);
      stmt.setBoolean(21, hasTreatment);
      stmt.setString(22, substanceTreatment);
      stmt.setBoolean(23, wannaTreatment);
      stmt.setString(24, wannaTreatmentSubstance);
      stmt.setBoolean(25, offerPsychology);
      stmt.setBoolean(26, offerPsychiatrist);
      stmt.setBoolean(27, revenueRenewal);
      stmt.setBoolean(28, supportGroups);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID de tb_mental_health inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateMentalHealth(
          int id, boolean hasLinkCaps, String capsCity,
          boolean hasAnxiety, boolean hasDepression, boolean hasBipolarity, boolean hasSchizophrenia,
          boolean hasAutism, String otherMentalDisorder, boolean useControlledMedicine, String controlledMedicines,
          boolean wasAccompaniment, String reasonAccompaniment, boolean useAlcohol, boolean useCigarettes,
          boolean useMarijuana, boolean useCrack, boolean useCocaine, boolean useAmphetamines,
          boolean useKDrugs, String otherSubstances, boolean hasTreatment, String substanceTreatment,
          boolean wannaTreatment, String wannaTreatmentSubstance,
          boolean offerPsychology, boolean offerPsychiatrist, boolean revenueRenewal, boolean supportGroups) {

    String sql = """
        UPDATE tb_mental_health SET
            has_link_caps = ?, caps_city = ?, has_anxiety = ?, has_depression = ?, has_bipolarity = ?,
            has_schizophrenia = ?, has_autism = ?, other_mental_disorder = ?, use_controlled_medicine = ?,
            controlled_medicines = ?, was_accompaniment = ?, reason_accompaniment = ?, use_alcohol = ?,
            use_cigarettes = ?, use_marijuana = ?, use_crack = ?, use_cocaine = ?, use_amphetamines = ?,
            use_k_drugs = ?, other_substances = ?, has_treatment = ?, substance_treatment = ?, wanna_treatment = ?,
            wanna_treatment_substance = ?, offer_psychology = ?, offer_psychiatrist = ?, revenue_renewal = ?,
            support_groups = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setBoolean(1, hasLinkCaps);
      stmt.setString(2, capsCity);
      stmt.setBoolean(3, hasAnxiety);
      stmt.setBoolean(4, hasDepression);
      stmt.setBoolean(5, hasBipolarity);
      stmt.setBoolean(6, hasSchizophrenia);
      stmt.setBoolean(7, hasAutism);
      stmt.setString(8, otherMentalDisorder);
      stmt.setBoolean(9, useControlledMedicine);
      stmt.setString(10, controlledMedicines);
      stmt.setBoolean(11, wasAccompaniment);
      stmt.setString(12, reasonAccompaniment);
      stmt.setBoolean(13, useAlcohol);
      stmt.setBoolean(14, useCigarettes);
      stmt.setBoolean(15, useMarijuana);
      stmt.setBoolean(16, useCrack);
      stmt.setBoolean(17, useCocaine);
      stmt.setBoolean(18, useAmphetamines);
      stmt.setBoolean(19, useKDrugs);
      stmt.setString(20, otherSubstances);
      stmt.setBoolean(21, hasTreatment);
      stmt.setString(22, substanceTreatment);
      stmt.setBoolean(23, wannaTreatment);
      stmt.setString(24, wannaTreatmentSubstance);
      stmt.setBoolean(25, offerPsychology);
      stmt.setBoolean(26, offerPsychiatrist);
      stmt.setBoolean(27, revenueRenewal);
      stmt.setBoolean(28, supportGroups);
      stmt.setInt(29, id);

      stmt.executeUpdate();
      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }



}
