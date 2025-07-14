package entities;

public class WomenHealth {
  private int id;
  private boolean isPregnant;
  private String pregnantAge;
  private boolean useOralContraceptive;
  private boolean useInjectableContraceptive;
  private boolean useIUDImplant;
  private boolean useTubalLigation;
  private boolean useHysterectomy;

  public boolean isUseOralContraceptive() {
    return useOralContraceptive;
  }

  public void setUseOralContraceptive(boolean useOralContraceptive) {
    this.useOralContraceptive = useOralContraceptive;
  }

  public boolean isUseInjectableContraceptive() {
    return useInjectableContraceptive;
  }

  public void setUseInjectableContraceptive(boolean useInjectableContraceptive) {
    this.useInjectableContraceptive = useInjectableContraceptive;
  }

  public boolean isUseIUDImplant() {
    return useIUDImplant;
  }

  public void setUseIUDImplant(boolean useIUDImplant) {
    this.useIUDImplant = useIUDImplant;
  }

  public boolean isUseTubalLigation() {
    return useTubalLigation;
  }

  public void setUseTubalLigation(boolean useTubalLigation) {
    this.useTubalLigation = useTubalLigation;
  }

  public boolean isUseHysterectomy() {
    return useHysterectomy;
  }

  public void setUseHysterectomy(boolean useHysterectomy) {
    this.useHysterectomy = useHysterectomy;
  }

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
