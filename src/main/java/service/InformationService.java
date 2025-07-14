package service;

import configs.DBConnection;
import entities.*;
import utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformationService {
  private DBConnection dbConnection;

  public InformationService() {
    this.dbConnection = new DBConnection();
  }

  public List<Information> getAllInfos(String filterName) {
    String filter;
    if (filterName == null || filterName.isEmpty()) {
      filter = ";";
    } else {
      filter = " WHERE i.full_name LIKE ? OR i.social_name LIKE ?;";
    }

    String query = "SELECT \n" +
                   "  info.id AS information_id,\n" +
                   "  info.social_name,\n" +
                   "  info.full_name,\n" +
                   "  info.born_date,\n" +
                   "  info.age,\n" +
                   "  info.cpf,\n" +
                   "  info.nationality,\n" +
                   "  info.mother_name,\n" +
                   "  info.father_name,\n" +
                   "\n" +
                   "  u.id AS user_id,\n" +
                   "  u.username,\n" +
                   "  \n" +
                   "  t.id AS treatment_id,\n" +
                   "  t.treatment_date,\n" +
                   "  t.entry_date,\n" +
                   "  t.is_transfer,\n" +
                   "  t.source_transfer,\n" +
                   "\n" +
                   "  ms.marital_status,\n" +
                   "  eth.etnia AS ethnicity,\n" +
                   "  bs.biology_sex,\n" +
                   "  so.sexual_orientation,\n" +
                   "  gi.gender_identity,\n" +
                   "\n" +
                   "  edu.education,\n" +
                   "  sed.has_family_benefits,\n" +
                   "  sed.family_benefits,\n" +
                   "  sed.has_children,\n" +
                   "  sed.children_quantity,\n" +
                   "  sed.ages,\n" +
                   "  sed.has_dependents,\n" +
                   "  sed.dependents_quantity,\n" +
                   "  sed.has_neeja_education,\n" +
                   "  sed.has_social_assistent,\n" +
                   "\n" +
                   "  hc.*,\n" +
                   "\n" +
                   "  -- mulheres\n" +
                   "  wh.is_pregnant,\n" +
                   "  wh.pregnant_age,\n" +
                   "  wh.use_oral_contraceptive,\n" +
                   "  wh.use_injectable_contraceptive,\n" +
                   "  wh.use_IUD_implant,\n" +
                   "  wh.use_tubal_ligation,\n" +
                   "  wh.use_hysterectomy,\n" +
                   "  wh.has_preventive_exam,\n" +
                   "  wh.preventive_exam_year,\n" +
                   "  wh.offer_contraceptive_method,\n" +
                   "  wh.offer_preventive_exam,\n" +
                   "  wh.is_prenatal,\n" +
                   "\n" +
                   "  -- homens\n" +
                   "  mh.has_prevent_exam,\n" +
                   "  mh.prevent_exam_year,\n" +
                   "  mh.has_prostate_cancer_family_history,\n" +
                   "  mh.prostate_cancer_family,\n" +
                   "  mh.vasectomy,\n" +
                   "  mh.pregnant_partner,\n" +
                   "  mh.prenatal_pregnant_partner,\n" +
                   "  mh.offer_vasectomy,\n" +
                   "  mh.offer_prenatal,\n" +
                   "\n" +
                   "  mth.*,\n" +
                   "  vs.*,\n" +
                   "  cc.*,\n" +
                   "  qt.*\n" +
                   "\n" +
                   "FROM tb_information info\n" +
                   "JOIN tb_treatment t ON info.treatment_id = t.id\n" +
                   "JOIN tb_user u ON t.user_id = u.id\n" +
                   "JOIN tb_marital_status ms ON info.marital_status_id = ms.id\n" +
                   "JOIN tb_ethnicity eth ON info.ethnicity_id = eth.id\n" +
                   "JOIN tb_biological_sex bs ON info.biological_sex_id = bs.id\n" +
                   "JOIN tb_sexual_orientation so ON info.sexual_orientation_id = so.id\n" +
                   "JOIN tb_gender_identity gi ON info.gender_identity_id = gi.id\n" +
                   "JOIN tb_social_economic_data sed ON info.social_economic_data_id = sed.id\n" +
                   "JOIN tb_education edu ON sed.education_id = edu.id\n" +
                   "JOIN tb_health_conditions hc ON info.health_conditions_id = hc.id\n" +
                   "LEFT JOIN tb_women_health wh ON info.women_health_id = wh.id\n" +
                   "LEFT JOIN tb_men_health mh ON info.men_health_id = mh.id\n" +
                   "JOIN tb_mental_health mth ON info.mental_health_id = mth.id\n" +
                   "JOIN tb_vaccination_status vs ON info.vaccination_status_id = vs.id\n" +
                   "JOIN tb_clinical_care cc ON info.clinical_care_id = cc.id\n" +
                   "JOIN tb_quick_tests qt ON info.quick_tests_id = qt.id" + filter;

    try (PreparedStatement st = dbConnection.getConnection().prepareStatement(query)) {
      if (filterName != null && !filterName.isEmpty()) {
        String searchPattern = "%" + filterName + "%";
        st.setString(1, searchPattern);
        st.setString(2, searchPattern);
      }
      try (ResultSet rs = st.executeQuery()) {
        List<Information> infos = new ArrayList<>();
        while (rs.next()) {
          Information info = new Information();
          Treatment treatment = new Treatment();
          GenderIdentity genderIdentity = new GenderIdentity();
          BiologicalSex biologicalSex = new BiologicalSex();
          Ethnicity ethnicity = new Ethnicity();
          MaritalStatus maritalStatus = new MaritalStatus();
          SexualOrientation sexualOrientation = new SexualOrientation();
          SocialEconomicData socialEconomicData = new SocialEconomicData();
          HealthConditions healthConditions = new HealthConditions();
          WomenHealth womenHealth = new WomenHealth();
          MenHealth menHealth = new MenHealth();
          MentalHealth mentalHealth = new MentalHealth();
          VaccinationStatus vaccinationStatus = new VaccinationStatus();
          ClinicalCare clinicalCare = new ClinicalCare();
          QuickTests quickTests = new QuickTests();
          Education education = new Education();
          User user = new User();

          info.setId(rs.getInt("information_id"));
          info.setSocialName(rs.getString("social_name"));
          info.setFullName(rs.getString("full_name"));
          info.setBornDate(rs.getDate("born_date"));
          info.setAge(rs.getInt("age"));
          info.setCpf(rs.getString("cpf"));
          info.setNationality(rs.getString("nationality"));
          info.setMotherName(rs.getString("mother_name"));
          info.setFatherName(rs.getString("father_name"));

          maritalStatus.setId(rs.getInt("marital_status_id"));
          maritalStatus.setMaritalStatus(rs.getString("marital_status"));

          ethnicity.setId(rs.getInt("ethnicity_id"));
          ethnicity.setEtnia(rs.getString("etnia"));

          biologicalSex.setId(rs.getInt("biological_id"));
          biologicalSex.setBiologySex(rs.getString("biology_sex"));

          sexualOrientation.setId(rs.getInt("sexual_orientation_id"));
          sexualOrientation.setSexualOrientation(rs.getString("sexual_orientation"));

          genderIdentity.setId(rs.getInt("gender_identity_id"));
          genderIdentity.setGenderIdentity(rs.getString("gender_identity"));

          treatment.setId(rs.getInt("treatment_id"));
          treatment.setTreatmentDate(rs.getDate("treatment_date"));
          treatment.setEntryDate(rs.getDate("entry_date"));
          treatment.setTransfer(rs.getBoolean("is_transfer"));
          treatment.setSourceTransfer(rs.getString("source_transfer"));

          user.setId(rs.getInt("user_id"));
          user.setUsername(rs.getString("username"));

          socialEconomicData.setId(rs.getInt("social_economic_data_id"));
          education.setId(rs.getInt("education_id"));
          education.setEducation(rs.getString("education"));
          socialEconomicData.setEducation(education);
          socialEconomicData.setHasFamilyBenefits(rs.getBoolean("has_family_benefits"));
          socialEconomicData.setFamilyBenefits(rs.getString("family_benefits"));
          socialEconomicData.setHasChildren(rs.getBoolean("has_children"));
          socialEconomicData.setChildrenQuantity(rs.getInt("children_quantity"));
          socialEconomicData.setAges(rs.getString("ages"));
          socialEconomicData.setHasDependents(rs.getBoolean("has_dependents"));
          socialEconomicData.setDependentsQuantity(rs.getInt("dependents_quantity"));
          socialEconomicData.setHasNeejaEducation(rs.getBoolean("has_neeja_education"));
          socialEconomicData.setHasSocialAssistent(rs.getBoolean("has_social_assistent"));

          healthConditions.setId(rs.getInt("health_conditions_id"));
          healthConditions.setHasDeficiency(rs.getBoolean("has_deficiency"));
          healthConditions.setDeficiency(rs.getString("deficiency"));
          healthConditions.setHasAllergies(rs.getBoolean("has_allergies"));
          healthConditions.setAllergies(rs.getString("allergies"));
          healthConditions.setHasSurgeries(rs.getBoolean("has_surgeries"));
          healthConditions.setSurgeries(rs.getString("surgeries"));
          healthConditions.setHasSkinDiseases(rs.getBoolean("has_skin_disease"));
          healthConditions.setSkinDiseases(rs.getString("skin_diseases"));
          healthConditions.setUseContinuousMedication(rs.getBoolean("use_continuous_medication"));
          healthConditions.setContinuousMedication(rs.getString("continuos_medication"));
          healthConditions.setBloodType(rs.getString("blood_type"));
          healthConditions.setHasHypertension(rs.getBoolean("has_hypertension"));
          healthConditions.setHasDiabetes(rs.getBoolean("has_diabetes"));
          healthConditions.setHasHiv(rs.getBoolean("has_hiv"));
          healthConditions.setHasAutoimmune(rs.getBoolean("has_autoimmune"));
          healthConditions.setHasSyphilis(rs.getBoolean("has_syphilis"));
          healthConditions.setHasHpv(rs.getBoolean("has_hpv"));
          healthConditions.setOtherChronicDisease(rs.getString("other_chronic_disease"));
          healthConditions.setHasTuberculosis(rs.getBoolean("has_tuberculosis"));
          healthConditions.setHasHepatitisB(rs.getBoolean("has_hepatitis_B"));
          healthConditions.setHasHepatitisC(rs.getBoolean("has_hepatitis_C"));
          healthConditions.setOtherInfectiousDisease(rs.getString("other_infectious_disease"));


          womenHealth.setId(rs.getInt("women_health_id"));
          womenHealth.setPregnant(rs.getBoolean("is_pregnant"));
          womenHealth.setPregnantAge(rs.getString("pregnant_age"));
          womenHealth.setHasPreventiveExam(rs.getBoolean("has_preventive_exam"));
          womenHealth.setPreventiveExamYear(rs.getInt("preventive_exam_year"));
          womenHealth.setUseOralContraceptive(rs.getBoolean("use_oral_contraceptive"));
          womenHealth.setUseInjectableContraceptive(rs.getBoolean("use_injectable_contraceptive"));
          womenHealth.setUseHysterectomy(rs.getBoolean("use_hysterectomy"));
          womenHealth.setUseIUDImplant(rs.getBoolean("use_iud_implant"));
          womenHealth.setUseTubalLigation(rs.getBoolean("use_tubal_ligation"));
          womenHealth.setOfferPreventiveExam(rs.getBoolean("offer_preventive_exam"));
          womenHealth.setPrenatal(rs.getBoolean("is_prenatal"));

          menHealth.setId(rs.getInt("men_health_id"));
          menHealth.setHasPreventExam(rs.getBoolean("has_prevent_exam"));
          menHealth.setPreventExamYear(rs.getInt("prevent_exam_year"));
          menHealth.setHasProstateCancerFamilyHistory(rs.getBoolean("has_prostate_cancer_family_history"));
          menHealth.setProstateCancerFamily(rs.getString("prostate_cancer_family"));
          menHealth.setVasectomy(rs.getBoolean("vasectomy"));
          menHealth.setPregnantPartner(rs.getBoolean("pregnant_partner"));
          menHealth.setPrenatalPregnantPartner(rs.getBoolean("prenatal_pregnant_partner"));
          menHealth.setOfferVasectomy(rs.getBoolean("offer_vasectomy"));
          menHealth.setOfferPrenatal(rs.getBoolean("offer_prenatal"));

          mentalHealth.setId(rs.getInt("mental_health_id"));
          mentalHealth.setHasLinkCaps(rs.getBoolean("has_link_caps"));
          mentalHealth.setCapsCity(rs.getString("caps_city"));
          mentalHealth.setMentalDisorder(rs.getString("mental_disorder"));
          mentalHealth.setUseControlledMedicine(rs.getBoolean("use_controlled_medicine"));
          mentalHealth.setControlledMedicines(rs.getString("controlled_medicines"));
          mentalHealth.setWasAccompaniment(rs.getBoolean("was_accompaniment"));
          mentalHealth.setReasonAccompaniment(rs.getString("reason_accompaniment"));
          mentalHealth.setSubstanceUse(rs.getString("substance_use"));
          mentalHealth.setHasTreatment(rs.getBoolean("has_treatment"));
          mentalHealth.setSubstanceTreatment(rs.getString("substance_treatment"));
          mentalHealth.setWannaTreatment(rs.getBoolean("wanna_treatment"));
          mentalHealth.setWannaTreatmentSubstance(rs.getString("wanna_treatment_substance"));
          mentalHealth.setOfferPsychology(rs.getBoolean("offer_psychology"));
          mentalHealth.setOfferPsychiatrist(rs.getBoolean("offer_psychiatrist"));
          mentalHealth.setRevenueRenewal(rs.getBoolean("revenue_renewal"));
          mentalHealth.setSupportGroups(rs.getBoolean("support_groups"));

          vaccinationStatus.setId(rs.getInt("vaccination_status_id"));
          vaccinationStatus.setCovid(rs.getBoolean("covid"));
          vaccinationStatus.setInfluenza(rs.getBoolean("influenza"));
          vaccinationStatus.setTetanus(rs.getBoolean("tetanus"));
          vaccinationStatus.setHepatitisB(rs.getBoolean("hepatitis_b"));
          vaccinationStatus.setOfferCovid(rs.getBoolean("offer_covid"));
          vaccinationStatus.setOfferHepatitis(rs.getBoolean("offer_hepatitis"));
          vaccinationStatus.setOfferInfluenza(rs.getBoolean("offer_influenza"));
          vaccinationStatus.setOfferFever(rs.getBoolean("offer_fever"));
          vaccinationStatus.setOfferAdultDuo(rs.getBoolean("offer_adult_duo"));
          vaccinationStatus.setOfferSrc(rs.getBoolean("offer_src"));
          vaccinationStatus.setOfferOtherVaccination(rs.getString("offer_other_vaccination"));
          vaccinationStatus.setOfferPortfolioCopy(rs.getBoolean("offer_portfolio_copy"));

          clinicalCare.setId(rs.getInt("clinical_care_id"));
          clinicalCare.setWeight(rs.getDouble("weight"));
          clinicalCare.setHeight(rs.getDouble("height"));
          clinicalCare.setImc(rs.getDouble("imc"));
          clinicalCare.setBloodPressure(rs.getDouble("blood_pressure"));
          clinicalCare.setHearRate(rs.getDouble("hear_rate"));
          clinicalCare.setSaturation(rs.getDouble("saturation"));
          clinicalCare.setTemperature(rs.getDouble("temperature"));
          clinicalCare.setStartDateRespiratorySymptoms(rs.getDate("start_date_respiratory_symptoms"));
          clinicalCare.setHasInjuries(rs.getBoolean("has_injuries"));
          clinicalCare.setInjuriesSites(rs.getString("injuries_sites"));
          clinicalCare.setHasCough(rs.getBoolean("has_cough"));
          clinicalCare.setHasRunnyNose(rs.getBoolean("has_runny_nose"));
          clinicalCare.setHasSneezing(rs.getBoolean("has_sneezing"));
          clinicalCare.setHasFever(rs.getBoolean("has_fever"));
          clinicalCare.setHasChills(rs.getBoolean("has_chills"));
          clinicalCare.setOtherSymptoms(rs.getString("other_symptoms"));

          quickTests.setId(rs.getInt("quick_tests_id"));
          quickTests.setPregnantTest(rs.getString("pregnant_test"));
          quickTests.setSputumCollection(rs.getBoolean("sputum_collection"));
          quickTests.setHasComplaint(rs.getBoolean("has_complaint"));
          quickTests.setComplaintDescription(rs.getString("complaint_description"));
          quickTests.setHasDentalComplaint(rs.getBoolean("has_dental_complaint"));
          quickTests.setDentalComplaint(rs.getString("dental_complaint"));
          quickTests.setNeedsDentalAssessment(rs.getBoolean("needs_dental_assessment"));

          info.setSocialEconomicData(socialEconomicData);
          info.setHealthConditions(healthConditions);
          info.setWomenHealth(womenHealth);
          info.setMenHealth(menHealth);
          info.setMentalHealth(mentalHealth);
          info.setVaccinationStatus(vaccinationStatus);
          info.setClinicalCare(clinicalCare);
          info.setQuickTests(quickTests);
          info.setTreatment(treatment);
          info.setMaritalStatus(maritalStatus);
          info.setEthnicity(ethnicity);
          info.setBiologicalSex(biologicalSex);
          info.setSexualOrientation(sexualOrientation);
          info.setGenderIdentity(genderIdentity);
          info.setUser(user);
          infos.add(info);
        }
        return infos;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public int saveInformation(String socialName, String fullName, java.util.Date bornDate,
                             int age, String cpf,
                             String nationality, String motherName, String fatherName,
                             String maritalStatus, String ethnicity, String biologicalSex,
                             String sexualOrientation, String genderIdentity, int treatmentId,
                             int socialEconomicDataId, int healthConditionsId,
                             Integer womenHealthId, Integer menHealthId,
                             int mentalHealthId, int vaccinationStatusId,
                             int clinicalCareId, int quickTestsId) {

    String sql = "INSERT INTO tb_information (" +
                 "social_name, full_name, born_date, age, cpf, nationality, mother_name, father_name, " +
                 "marital_status_id, ethnicity_id, biological_sex_id, sexual_orientation_id, gender_identity_id, treatment_id, " +
                 "social_economic_data_id, health_conditions_id, women_health_id, men_health_id, " +
                 "mental_health_id, vaccination_status_id, clinical_care_id, quick_tests_id) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, " +
                 "(SELECT id FROM tb_marital_status WHERE marital_status = ?), " +
                 "(SELECT id FROM tb_ethnicity WHERE etnia = ?), " +
                 "(SELECT id FROM tb_biological_sex WHERE biology_sex = ?), " +
                 "(SELECT id FROM tb_sexual_orientation WHERE sexual_orientation = ?), " +
                 "(SELECT id FROM tb_gender_identity WHERE gender_identity = ?), ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql,
            Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, socialName);
      stmt.setString(2, fullName);
      stmt.setString(3, DateUtils.getFormattedDate(bornDate));
      stmt.setInt(4, age);
      stmt.setString(5, cpf);
      stmt.setString(6, nationality);
      stmt.setString(7, motherName);
      stmt.setString(8, fatherName);
      stmt.setString(9, maritalStatus);
      stmt.setString(10, ethnicity);
      stmt.setString(11, biologicalSex);
      stmt.setString(12, sexualOrientation);
      stmt.setString(13, genderIdentity);
      stmt.setInt(14, treatmentId);
      stmt.setInt(15, socialEconomicDataId);
      stmt.setInt(16, healthConditionsId);
      if (womenHealthId != null) {
        stmt.setInt(17, womenHealthId);
      } else {
        stmt.setNull(17, Types.INTEGER);
      }
      if (menHealthId != null) {
        stmt.setInt(18, menHealthId);
      } else {
        stmt.setNull(18, Types.INTEGER);
      }
      stmt.setInt(19, mentalHealthId);
      stmt.setInt(20, vaccinationStatusId);
      stmt.setInt(21, clinicalCareId);
      stmt.setInt(22, quickTestsId);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID da informação inserida.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateInformation(int id, String socialName, String fullName, java.util.Date bornDate,
                               int age, String cpf,
                               String nationality, String motherName, String fatherName,
                               String maritalStatus, String ethnicity, String biologicalSex,
                               String sexualOrientation, String genderIdentity,
                               int socialEconomicDataId, int healthConditionsId,
                               Integer womenHealthId, Integer menHealthId,
                               int mentalHealthId, int vaccinationStatusId,
                               int clinicalCareId, int quickTestsId) {

    String query = "UPDATE tb_information SET " +
                   "social_name = ?, full_name = ?, born_date = ?, age = ?, cpf = ?, " +
                   "nationality = ?, mother_name = ?, father_name = ?, " +
                   "marital_status_id = (SELECT id FROM tb_marital_status WHERE marital_status = ?), " +
                   "ethnicity_id = (SELECT id FROM tb_ethnicity WHERE etnia = ?), " +
                   "biological_sex_id = (SELECT id FROM tb_biological_sex WHERE biology_sex = ?), " +
                   "sexual_orientation_id = (SELECT id FROM tb_sexual_orientation WHERE sexual_orientation = ?), " +
                   "gender_identity_id = (SELECT id FROM tb_gender_identity WHERE gender_identity = ?), " +
                   "social_economic_data_id = ?, health_conditions_id = ?, women_health_id = ?, men_health_id = ?, " +
                   "mental_health_id = ?, vaccination_status_id = ?, clinical_care_id = ?, quick_tests_id = ? " +
                   "WHERE id = ?";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
      stmt.setString(1, socialName);
      stmt.setString(2, fullName);
      stmt.setString(3, DateUtils.getFormattedDate(bornDate));
      stmt.setInt(4, age);
      stmt.setString(5, cpf);
      stmt.setString(6, nationality);
      stmt.setString(7, motherName);
      stmt.setString(8, fatherName);
      stmt.setString(9, maritalStatus);
      stmt.setString(10, ethnicity);
      stmt.setString(11, biologicalSex);
      stmt.setString(12, sexualOrientation);
      stmt.setString(13, genderIdentity);
      stmt.setInt(14, socialEconomicDataId);
      stmt.setInt(15, healthConditionsId);
      if (womenHealthId != null) {
        stmt.setInt(16, womenHealthId);
      } else {
        stmt.setNull(16, Types.INTEGER);
      }
      if (menHealthId != null) {
        stmt.setInt(17, menHealthId);
      } else {
        stmt.setNull(17, Types.INTEGER);
      }
      stmt.setInt(18, mentalHealthId);
      stmt.setInt(19, vaccinationStatusId);
      stmt.setInt(20, clinicalCareId);
      stmt.setInt(21, quickTestsId);
      stmt.setInt(22, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

}
