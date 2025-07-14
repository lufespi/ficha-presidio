package entities;
/*
* CREATE TABLE tb_mental_health (
	id INT AUTO_INCREMENT PRIMARY KEY,
    has_link_caps BOOLEAN NOT NULL,
    caps_city VARCHAR(255),
    has_anxiety BOOLEAN NOT NULL,
    has_depression BOOLEAN NOT NULL,
    has_bipolarity BOOLEAN NOT NULL,
    has_schizophrenia BOOLEAN NOT NULL,
    has_autism BOOLEAN NOT NULL,
    other_mental_disorder VARCHAR(100),
    use_controlled_medicine BOOLEAN NOT NULL,
    controlled_medicines VARCHAR(255),
    was_accompaniment BOOLEAN NOT NULL,
    reason_accompaniment VARCHAR(255),
    use_alcohol BOOLEAN NOT NULL,
    use_cigarettes BOOLEAN NOT NULL,
    use_marijuana BOOLEAN NOT NULL,
    use_crack BOOLEAN NOT NULL,
    use_cocaine BOOLEAN NOT NULL,
    use_amphetamines BOOLEAN NOT NULL,
    use_k_drugs BOOLEAN NOT NULL,
    other_substances VARCHAR(100),
    has_treatment BOOLEAN NOT NULL,
    substance_treatment VARCHAR(255),
    wanna_treatment BOOLEAN NOT NULL,
    wanna_treatment_substance VARCHAR(255),
    offer_psychology BOOLEAN NOT NULL,
    offer_psychiatrist BOOLEAN NOT NULL,
    revenue_renewal BOOLEAN NOT NULL,
    support_groups BOOLEAN NOT NULL
);*/
public class MentalHealth {
  private int id;
  private boolean hasLinkCaps;
  private String capsCity;
  private boolean useControlledMedicine;
  private String controlledMedicines;
  private boolean wasAccompaniment;
  private String reasonAccompaniment;
  private boolean hasAnxiety;
  private boolean hasDepression;
  private boolean hasBipolarity;
  private boolean hasSchizophrenia;
  private boolean hasAutism;
  private String otherMentalDisorder;

  public boolean isHasAnxiety() {
    return hasAnxiety;
  }

  public void setHasAnxiety(boolean hasAnxiety) {
    this.hasAnxiety = hasAnxiety;
  }

  public boolean isHasDepression() {
    return hasDepression;
  }

  public void setHasDepression(boolean hasDepression) {
    this.hasDepression = hasDepression;
  }

  public boolean isHasBipolarity() {
    return hasBipolarity;
  }

  public void setHasBipolarity(boolean hasBipolarity) {
    this.hasBipolarity = hasBipolarity;
  }

  public boolean isHasSchizophrenia() {
    return hasSchizophrenia;
  }

  public void setHasSchizophrenia(boolean hasSchizophrenia) {
    this.hasSchizophrenia = hasSchizophrenia;
  }

  public boolean isHasAutism() {
    return hasAutism;
  }

  public void setHasAutism(boolean hasAutism) {
    this.hasAutism = hasAutism;
  }

  public String getOtherMentalDisorder() {
    return otherMentalDisorder;
  }

  public void setOtherMentalDisorder(String otherMentalDisorder) {
    this.otherMentalDisorder = otherMentalDisorder;
  }

  private boolean useAlcohol;
  private boolean useCigarettes;
  private boolean useMarijuana;
  private boolean useCrack;
  private boolean useCocaine;
  private boolean useAmphetamines;
  private boolean useKDrugs;
  private String otherSubstances;
  private boolean hasTreatment;
  private String substanceTreatment;
  private boolean wannaTreatment;
  private String wannaTreatmentSubstance;
  private boolean offerPsychology;
  private boolean offerPsychiatrist;
  private boolean revenueRenewal;
  private boolean supportGroups;

  public boolean isUseAlcohol() {
    return useAlcohol;
  }

  public void setUseAlcohol(boolean useAlcohol) {
    this.useAlcohol = useAlcohol;
  }

  public boolean isUseCigarettes() {
    return useCigarettes;
  }

  public void setUseCigarettes(boolean useCigarettes) {
    this.useCigarettes = useCigarettes;
  }

  public boolean isUseMarijuana() {
    return useMarijuana;
  }

  public void setUseMarijuana(boolean useMarijuana) {
    this.useMarijuana = useMarijuana;
  }

  public boolean isUseCrack() {
    return useCrack;
  }

  public void setUseCrack(boolean useCrack) {
    this.useCrack = useCrack;
  }

  public boolean isUseCocaine() {
    return useCocaine;
  }

  public void setUseCocaine(boolean useCocaine) {
    this.useCocaine = useCocaine;
  }

  public boolean isUseAmphetamines() {
    return useAmphetamines;
  }

  public void setUseAmphetamines(boolean useAmphetamines) {
    this.useAmphetamines = useAmphetamines;
  }

  public boolean isUseKDrugs() {
    return useKDrugs;
  }

  public void setUseKDrugs(boolean useKDrugs) {
    this.useKDrugs = useKDrugs;
  }

  public String getOtherSubstances() {
    return otherSubstances;
  }

  public void setOtherSubstances(String otherSubstances) {
    this.otherSubstances = otherSubstances;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isHasLinkCaps() {
    return hasLinkCaps;
  }

  public void setHasLinkCaps(boolean hasLinkCaps) {
    this.hasLinkCaps = hasLinkCaps;
  }

  public String getCapsCity() {
    return capsCity;
  }

  public void setCapsCity(String capsCity) {
    this.capsCity = capsCity;
  }

  public boolean isUseControlledMedicine() {
    return useControlledMedicine;
  }

  public void setUseControlledMedicine(boolean useControlledMedicine) {
    this.useControlledMedicine = useControlledMedicine;
  }

  public String getControlledMedicines() {
    return controlledMedicines;
  }

  public void setControlledMedicines(String controlledMedicines) {
    this.controlledMedicines = controlledMedicines;
  }

  public boolean isWasAccompaniment() {
    return wasAccompaniment;
  }

  public void setWasAccompaniment(boolean wasAccompaniment) {
    this.wasAccompaniment = wasAccompaniment;
  }

  public String getReasonAccompaniment() {
    return reasonAccompaniment;
  }

  public void setReasonAccompaniment(String reasonAccompaniment) {
    this.reasonAccompaniment = reasonAccompaniment;
  }

  public boolean isHasTreatment() {
    return hasTreatment;
  }

  public void setHasTreatment(boolean hasTreatment) {
    this.hasTreatment = hasTreatment;
  }

  public String getSubstanceTreatment() {
    return substanceTreatment;
  }

  public void setSubstanceTreatment(String substanceTreatment) {
    this.substanceTreatment = substanceTreatment;
  }

  public boolean isWannaTreatment() {
    return wannaTreatment;
  }

  public void setWannaTreatment(boolean wannaTreatment) {
    this.wannaTreatment = wannaTreatment;
  }

  public String getWannaTreatmentSubstance() {
    return wannaTreatmentSubstance;
  }

  public void setWannaTreatmentSubstance(String wannaTreatmentSubstance) {
    this.wannaTreatmentSubstance = wannaTreatmentSubstance;
  }

  public boolean isOfferPsychology() {
    return offerPsychology;
  }

  public void setOfferPsychology(boolean offerPsychology) {
    this.offerPsychology = offerPsychology;
  }

  public boolean isOfferPsychiatrist() {
    return offerPsychiatrist;
  }

  public void setOfferPsychiatrist(boolean offerPsychiatrist) {
    this.offerPsychiatrist = offerPsychiatrist;
  }

  public boolean isRevenueRenewal() {
    return revenueRenewal;
  }

  public void setRevenueRenewal(boolean revenueRenewal) {
    this.revenueRenewal = revenueRenewal;
  }

  public boolean isSupportGroups() {
    return supportGroups;
  }

  public void setSupportGroups(boolean supportGroups) {
    this.supportGroups = supportGroups;
  }
}
