package entities;

import java.util.Date;
/*
*
* CREATE TABLE tb_clinical_care (
	id INT AUTO_INCREMENT PRIMARY KEY,
    weight DECIMAL(10,2) NOT NULL,
    height DECIMAL(10,2) NOT NULL,
    imc DECIMAL(10,2) NOT NULL,
    blood_pressure DECIMAL(10,2) NOT NULL,
    hear_rate DECIMAL(10,2) NOT NULL,
    saturation DECIMAL(10,2) NOT NULL,
    temperature DECIMAL(10,2) NOT NULL,
    has_cough BOOLEAN NOT NULL,
    has_runny_nose BOOLEAN NOT NULL,
    has_sneezing BOOLEAN NOT NULL,
    has_fever BOOLEAN NOT NULL,
    has_chills BOOLEAN NOT NULL,
    other_symptoms VARCHAR(100),
    start_date_respiratory_symptoms DATE,
    has_injuries BOOLEAN NOT NULL,
    injuries_sites VARCHAR(255)
);
* */
public class ClinicalCare {
  private int id;
  private double weight;
  private double height;
  private double imc;
  private double bloodPressure;
  private double hearRate;
  private double saturation;
  private double temperature;
  private Date startDateRespiratorySymptoms;
  private boolean hasInjuries;
  private String injuriesSites;
  private boolean hasCough;
  private boolean hasRunnyNose;
  private boolean hasSneezing;
  private boolean hasFever;
  private boolean hasChills;
  private String otherSymptoms;

  public boolean isHasCough() {
    return hasCough;
  }

  public void setHasCough(boolean hasCough) {
    this.hasCough = hasCough;
  }

  public boolean isHasRunnyNose() {
    return hasRunnyNose;
  }

  public void setHasRunnyNose(boolean hasRunnyNose) {
    this.hasRunnyNose = hasRunnyNose;
  }

  public boolean isHasSneezing() {
    return hasSneezing;
  }

  public void setHasSneezing(boolean hasSneezing) {
    this.hasSneezing = hasSneezing;
  }

  public boolean isHasFever() {
    return hasFever;
  }

  public void setHasFever(boolean hasFever) {
    this.hasFever = hasFever;
  }

  public boolean isHasChills() {
    return hasChills;
  }

  public void setHasChills(boolean hasChills) {
    this.hasChills = hasChills;
  }

  public String getOtherSymptoms() {
    return otherSymptoms;
  }

  public void setOtherSymptoms(String otherSymptoms) {
    this.otherSymptoms = otherSymptoms;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getImc() {
    return imc;
  }

  public void setImc(double imc) {
    this.imc = imc;
  }

  public double getBloodPressure() {
    return bloodPressure;
  }

  public void setBloodPressure(double bloodPressure) {
    this.bloodPressure = bloodPressure;
  }

  public double getHearRate() {
    return hearRate;
  }

  public void setHearRate(double hearRate) {
    this.hearRate = hearRate;
  }

  public double getSaturation() {
    return saturation;
  }

  public void setSaturation(double saturation) {
    this.saturation = saturation;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public Date getStartDateRespiratorySymptoms() {
    return startDateRespiratorySymptoms;
  }

  public void setStartDateRespiratorySymptoms(Date startDateRespiratorySymptoms) {
    this.startDateRespiratorySymptoms = startDateRespiratorySymptoms;
  }

  public boolean isHasInjuries() {
    return hasInjuries;
  }

  public void setHasInjuries(boolean hasInjuries) {
    this.hasInjuries = hasInjuries;
  }

  public String getInjuriesSites() {
    return injuriesSites;
  }

  public void setInjuriesSites(String injuriesSites) {
    this.injuriesSites = injuriesSites;
  }
}
