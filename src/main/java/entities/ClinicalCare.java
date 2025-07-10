package entities;

import java.util.Date;

public class ClinicalCare {
  private int id;
  private double weight;
  private double height;
  private double imc;
  private double bloodPressure;
  private double hearRate;
  private double saturation;
  private double temperature;
  private String respiratorySymptoms;
  private Date startDateRespiratorySymptoms;
  private boolean hasInjuries;
  private String injuriesSites;

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

  public String getRespiratorySymptoms() {
    return respiratorySymptoms;
  }

  public void setRespiratorySymptoms(String respiratorySymptoms) {
    this.respiratorySymptoms = respiratorySymptoms;
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
