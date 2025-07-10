package entities;

public class WomenHealth {
  private int id;
  private boolean isPregnant;
  private String pregnantAge;
  private String contraceptiveMethod;
  private boolean hasPreventiveExam;
  private int preventiveExamYear;
  private boolean offerContraceptiveMethod;
  private boolean offerPreventiveExam;
  private boolean isPrenatal;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isPregnant() {
    return isPregnant;
  }

  public void setPregnant(boolean pregnant) {
    isPregnant = pregnant;
  }

  public String getPregnantAge() {
    return pregnantAge;
  }

  public void setPregnantAge(String pregnantAge) {
    this.pregnantAge = pregnantAge;
  }

  public String getContraceptiveMethod() {
    return contraceptiveMethod;
  }

  public void setContraceptiveMethod(String contraceptiveMethod) {
    this.contraceptiveMethod = contraceptiveMethod;
  }

  public boolean isHasPreventiveExam() {
    return hasPreventiveExam;
  }

  public void setHasPreventiveExam(boolean hasPreventiveExam) {
    this.hasPreventiveExam = hasPreventiveExam;
  }

  public int getPreventiveExamYear() {
    return preventiveExamYear;
  }

  public void setPreventiveExamYear(int preventiveExamYear) {
    this.preventiveExamYear = preventiveExamYear;
  }

  public boolean isOfferContraceptiveMethod() {
    return offerContraceptiveMethod;
  }

  public void setOfferContraceptiveMethod(boolean offerContraceptiveMethod) {
    this.offerContraceptiveMethod = offerContraceptiveMethod;
  }

  public boolean isOfferPreventiveExam() {
    return offerPreventiveExam;
  }

  public void setOfferPreventiveExam(boolean offerPreventiveExam) {
    this.offerPreventiveExam = offerPreventiveExam;
  }

  public boolean isPrenatal() {
    return isPrenatal;
  }

  public void setPrenatal(boolean prenatal) {
    isPrenatal = prenatal;
  }
}
