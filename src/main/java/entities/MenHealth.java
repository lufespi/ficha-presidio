package entities;


public class MenHealth {
  private int id;
  private boolean hasPreventExam;
  private int preventExamYear;
  private boolean hasProstateCancerFamilyHistory;
  private String prostateCancerFamily;
  private boolean vasectomy;
  private boolean pregnantPartner;
  private boolean prenatalPregnantPartner;
  private boolean offerVasectomy;
  private boolean offerPrenatal;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isHasPreventExam() {
    return hasPreventExam;
  }

  public void setHasPreventExam(boolean hasPreventExam) {
    this.hasPreventExam = hasPreventExam;
  }

  public int getPreventExamYear() {
    return preventExamYear;
  }

  public void setPreventExamYear(int preventExamYear) {
    this.preventExamYear = preventExamYear;
  }

  public boolean isHasProstateCancerFamilyHistory() {
    return hasProstateCancerFamilyHistory;
  }

  public void setHasProstateCancerFamilyHistory(boolean hasProstateCancerFamilyHistory) {
    this.hasProstateCancerFamilyHistory = hasProstateCancerFamilyHistory;
  }

  public String getProstateCancerFamily() {
    return prostateCancerFamily;
  }

  public void setProstateCancerFamily(String prostateCancerFamily) {
    this.prostateCancerFamily = prostateCancerFamily;
  }

  public boolean isVasectomy() {
    return vasectomy;
  }

  public void setVasectomy(boolean vasectomy) {
    this.vasectomy = vasectomy;
  }

  public boolean isPregnantPartner() {
    return pregnantPartner;
  }

  public void setPregnantPartner(boolean pregnantPartner) {
    this.pregnantPartner = pregnantPartner;
  }

  public boolean isPrenatalPregnantPartner() {
    return prenatalPregnantPartner;
  }

  public void setPrenatalPregnantPartner(boolean prenatalPregnantPartner) {
    this.prenatalPregnantPartner = prenatalPregnantPartner;
  }

  public boolean isOfferVasectomy() {
    return offerVasectomy;
  }

  public void setOfferVasectomy(boolean offerVasectomy) {
    this.offerVasectomy = offerVasectomy;
  }

  public boolean isOfferPrenatal() {
    return offerPrenatal;
  }

  public void setOfferPrenatal(boolean offerPrenatal) {
    this.offerPrenatal = offerPrenatal;
  }
}
