package entities;

public class SocialEconomicData {
  private int id;
  private boolean hasFamilyBenefits;
  private String familyBenefits;
  private boolean hasChildren;
  private int childrenQuantity;
  private String ages;
  private boolean hasDependents;
  private int dependentsQuantity;
  private boolean hasNeejaEducation;
  private boolean hasSocialAssistent;
  private Education education;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isHasFamilyBenefits() {
    return hasFamilyBenefits;
  }

  public void setHasFamilyBenefits(boolean hasFamilyBenefits) {
    this.hasFamilyBenefits = hasFamilyBenefits;
  }

  public String getFamilyBenefits() {
    return familyBenefits;
  }

  public void setFamilyBenefits(String familyBenefits) {
    this.familyBenefits = familyBenefits;
  }

  public boolean isHasChildren() {
    return hasChildren;
  }

  public void setHasChildren(boolean hasChildren) {
    this.hasChildren = hasChildren;
  }

  public int getChildrenQuantity() {
    return childrenQuantity;
  }

  public void setChildrenQuantity(int childrenQuantity) {
    this.childrenQuantity = childrenQuantity;
  }

  public String getAges() {
    return ages;
  }

  public void setAges(String ages) {
    this.ages = ages;
  }

  public boolean isHasDependents() {
    return hasDependents;
  }

  public void setHasDependents(boolean hasDependents) {
    this.hasDependents = hasDependents;
  }

  public int getDependentsQuantity() {
    return dependentsQuantity;
  }

  public void setDependentsQuantity(int dependentsQuantity) {
    this.dependentsQuantity = dependentsQuantity;
  }

  public boolean isHasNeejaEducation() {
    return hasNeejaEducation;
  }

  public void setHasNeejaEducation(boolean hasNeejaEducation) {
    this.hasNeejaEducation = hasNeejaEducation;
  }

  public boolean isHasSocialAssistent() {
    return hasSocialAssistent;
  }

  public void setHasSocialAssistent(boolean hasSocialAssistent) {
    this.hasSocialAssistent = hasSocialAssistent;
  }

  public Education getEducation() {
    return education;
  }

  public void setEducation(Education education) {
    this.education = education;
  }
}
