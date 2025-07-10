package entities;

import java.util.Date;

public class Information {
  private int id;
  private String socialName;
  private String fullName;
  private Date bornDate;
  private int age;
  private String cpf;
  private String nationality;
  private String motherName;
  private String fatherName;
  private MaritalStatus maritalStatus;
  private Ethnicity ethnicity;
  private BiologicalSex biologicalSex;
  private SexualOrientation sexualOrientation;
  private User user;
  private SocialEconomicData socialEconomicData;
  private HealthConditions healthConditions;
  private WomenHealth womenHealth;
  private MenHealth menHealth;
  private MentalHealth mentalHealth;
  private VaccinationStatus vaccinationStatus;
  private ClinicalCare clinicalCare;
  private QuickTests quickTests;

  public SocialEconomicData getSocialEconomicData() {
    return socialEconomicData;
  }

  public void setSocialEconomicData(SocialEconomicData socialEconomicData) {
    this.socialEconomicData = socialEconomicData;
  }

  public HealthConditions getHealthConditions() {
    return healthConditions;
  }

  public void setHealthConditions(HealthConditions healthConditions) {
    this.healthConditions = healthConditions;
  }

  public WomenHealth getWomenHealth() {
    return womenHealth;
  }

  public void setWomenHealth(WomenHealth womenHealth) {
    this.womenHealth = womenHealth;
  }

  public MenHealth getMenHealth() {
    return menHealth;
  }

  public void setMenHealth(MenHealth menHealth) {
    this.menHealth = menHealth;
  }

  public MentalHealth getMentalHealth() {
    return mentalHealth;
  }

  public void setMentalHealth(MentalHealth mentalHealth) {
    this.mentalHealth = mentalHealth;
  }

  public VaccinationStatus getVaccinationStatus() {
    return vaccinationStatus;
  }

  public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
    this.vaccinationStatus = vaccinationStatus;
  }

  public ClinicalCare getClinicalCare() {
    return clinicalCare;
  }

  public void setClinicalCare(ClinicalCare clinicalCare) {
    this.clinicalCare = clinicalCare;
  }

  public QuickTests getQuickTests() {
    return quickTests;
  }

  public void setQuickTests(QuickTests quickTests) {
    this.quickTests = quickTests;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public GenderIdentity getGenderIdentity() {
    return genderIdentity;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public void setGenderIdentity(GenderIdentity genderIdentity) {
    this.genderIdentity = genderIdentity;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public Ethnicity getEthnicity() {
    return ethnicity;
  }

  public void setEthnicity(Ethnicity ethnicity) {
    this.ethnicity = ethnicity;
  }

  public BiologicalSex getBiologicalSex() {
    return biologicalSex;
  }

  public void setBiologicalSex(BiologicalSex biologicalSex) {
    this.biologicalSex = biologicalSex;
  }

  public SexualOrientation getSexualOrientation() {
    return sexualOrientation;
  }

  public void setSexualOrientation(SexualOrientation sexualOrientation) {
    this.sexualOrientation = sexualOrientation;
  }

  public Treatment getTreatment() {
    return treatment;
  }

  public void setTreatment(Treatment treatment) {
    this.treatment = treatment;
  }

  private GenderIdentity genderIdentity;
  private Treatment treatment;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getSocialName() {
    return socialName;
  }
  public void setSocialName(String socialName) {
    this.socialName = socialName;
  }
  public String getFullName() {
    return fullName;
  }
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  public Date getBornDate() {
    return bornDate;
  }
  public void setBornDate(Date bornDate) {
    this.bornDate = bornDate;
  }
  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public String getNationality() {
    return nationality;
  }
  public void setNationality(String nationality) {
    this.nationality = nationality;
  }
  public String getMotherName() {
    return motherName;
  }
  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

}
