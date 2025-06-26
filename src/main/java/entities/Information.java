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
