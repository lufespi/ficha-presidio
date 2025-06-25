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
  private int maritalStatusId;
  private int ethnicityId;
  private int biologicalSexId;
  private int sexualOrientationId;
  private int genderIdentityId;
  private int treatmentId;

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
  public int getMaritalStatusId() {
    return maritalStatusId;
  }
  public void setMaritalStatusId(int maritalStatusId) {
    this.maritalStatusId = maritalStatusId;
  }
  public int getEthnicityId() {
    return ethnicityId;
  }
  public void setEthnicityId(int ethnicityId) {
    this.ethnicityId = ethnicityId;
  }
  public int getBiologicalSexId() {
    return biologicalSexId;
  }
  public void setBiologicalSexId(int biologicalSexId) {
    this.biologicalSexId = biologicalSexId;
  }
  public int getSexualOrientationId() {
    return sexualOrientationId;
  }
  public void setSexualOrientationId(int sexualOrientationId) {
    this.sexualOrientationId = sexualOrientationId;
  }
  public int getGenderIdentityId() {
    return genderIdentityId;
  }
  public void setGenderIdentityId(int genderIdentityId) {
    this.genderIdentityId = genderIdentityId;
  }
  public int getTreatmentId() {
    return treatmentId;
  }
  public void setTreatmentId(int treatmentId) {
    this.treatmentId = treatmentId;
  }
}
