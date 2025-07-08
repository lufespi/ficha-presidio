package entities;

public class QuickTests {
  private int id;
  private String pregnantTest;
  private boolean sputumCollection;
  private boolean hasComplaint;
  private String complaintDescription;
  private boolean hasDentalComplaint;
  private String dentalComplaint;
  private boolean needsDentalAssessment;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPregnantTest() {
    return pregnantTest;
  }

  public void setPregnantTest(String pregnantTest) {
    this.pregnantTest = pregnantTest;
  }

  public boolean isSputumCollection() {
    return sputumCollection;
  }

  public void setSputumCollection(boolean sputumCollection) {
    this.sputumCollection = sputumCollection;
  }

  public boolean isHasComplaint() {
    return hasComplaint;
  }

  public void setHasComplaint(boolean hasComplaint) {
    this.hasComplaint = hasComplaint;
  }

  public String getComplaintDescription() {
    return complaintDescription;
  }

  public void setComplaintDescription(String complaintDescription) {
    this.complaintDescription = complaintDescription;
  }

  public boolean isHasDentalComplaint() {
    return hasDentalComplaint;
  }

  public void setHasDentalComplaint(boolean hasDentalComplaint) {
    this.hasDentalComplaint = hasDentalComplaint;
  }

  public String getDentalComplaint() {
    return dentalComplaint;
  }

  public void setDentalComplaint(String dentalComplaint) {
    this.dentalComplaint = dentalComplaint;
  }

  public boolean isNeedsDentalAssessment() {
    return needsDentalAssessment;
  }

  public void setNeedsDentalAssessment(boolean needsDentalAssessment) {
    this.needsDentalAssessment = needsDentalAssessment;
  }
}
