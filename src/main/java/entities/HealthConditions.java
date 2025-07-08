package entities;

public class HealthConditions {
  private int id;
  private boolean hasDeficiency;
  private String deficiency;
  private boolean hasAllergies;
  private String allergies;
  private boolean hasSurgeries;
  private String surgeries;
  private String chronicDiseases;
  private boolean hasSkinDiseases;
  private String skinDiseases;
  private boolean useContinuousMedication;
  private String continuousMedication;
  private String bloodType;
  private String referrals;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isHasDeficiency() {
    return hasDeficiency;
  }

  public void setHasDeficiency(boolean hasDeficiency) {
    this.hasDeficiency = hasDeficiency;
  }

  public String getDeficiency() {
    return deficiency;
  }

  public void setDeficiency(String deficiency) {
    this.deficiency = deficiency;
  }

  public boolean isHasAllergies() {
    return hasAllergies;
  }

  public void setHasAllergies(boolean hasAllergies) {
    this.hasAllergies = hasAllergies;
  }

  public String getAllergies() {
    return allergies;
  }

  public void setAllergies(String allergies) {
    this.allergies = allergies;
  }

  public boolean isHasSurgeries() {
    return hasSurgeries;
  }

  public void setHasSurgeries(boolean hasSurgeries) {
    this.hasSurgeries = hasSurgeries;
  }

  public String getSurgeries() {
    return surgeries;
  }

  public void setSurgeries(String surgeries) {
    this.surgeries = surgeries;
  }

  public String getChronicDiseases() {
    return chronicDiseases;
  }

  public void setChronicDiseases(String chronicDiseases) {
    this.chronicDiseases = chronicDiseases;
  }

  public boolean isHasSkinDiseases() {
    return hasSkinDiseases;
  }

  public void setHasSkinDiseases(boolean hasSkinDiseases) {
    this.hasSkinDiseases = hasSkinDiseases;
  }

  public String getSkinDiseases() {
    return skinDiseases;
  }

  public void setSkinDiseases(String skinDiseases) {
    this.skinDiseases = skinDiseases;
  }

  public boolean isUseContinuousMedication() {
    return useContinuousMedication;
  }

  public void setUseContinuousMedication(boolean useContinuousMedication) {
    this.useContinuousMedication = useContinuousMedication;
  }

  public String getContinuousMedication() {
    return continuousMedication;
  }

  public void setContinuousMedication(String continuousMedication) {
    this.continuousMedication = continuousMedication;
  }

  public String getBloodType() {
    return bloodType;
  }

  public void setBloodType(String bloodType) {
    this.bloodType = bloodType;
  }

  public String getReferrals() {
    return referrals;
  }

  public void setReferrals(String referrals) {
    this.referrals = referrals;
  }
}
