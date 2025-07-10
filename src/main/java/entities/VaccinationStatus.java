package entities;

public class VaccinationStatus {
  private int id;
  private boolean covid;
  private boolean influenza;
  private boolean tetanus;
  private boolean hepatitisB;
  private String offerVaccination;
  private boolean offerPortfolioCopy;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isCovid() {
    return covid;
  }

  public void setCovid(boolean covid) {
    this.covid = covid;
  }

  public boolean isInfluenza() {
    return influenza;
  }

  public void setInfluenza(boolean influenza) {
    this.influenza = influenza;
  }

  public boolean isTetanus() {
    return tetanus;
  }

  public void setTetanus(boolean tetanus) {
    this.tetanus = tetanus;
  }

  public boolean isHepatitisB() {
    return hepatitisB;
  }

  public void setHepatitisB(boolean hepatitisB) {
    this.hepatitisB = hepatitisB;
  }

  public String getOfferVaccination() {
    return offerVaccination;
  }

  public void setOfferVaccination(String offerVaccination) {
    this.offerVaccination = offerVaccination;
  }

  public boolean isOfferPortfolioCopy() {
    return offerPortfolioCopy;
  }

  public void setOfferPortfolioCopy(boolean offerPortfolioCopy) {
    this.offerPortfolioCopy = offerPortfolioCopy;
  }
}
