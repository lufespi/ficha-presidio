package entities;
/*CREATE TABLE tb_vaccination_status (
        id INT AUTO_INCREMENT PRIMARY KEY,
        covid BOOLEAN NOT NULL,
        influenza BOOLEAN NOT NULL,
        tetanus BOOLEAN NOT NULL,
        hepatitis_b BOOLEAN NOT NULL,
        offer_covid BOOLEAN NOT NULL,
        offer_hepatite BOOLEAN NOT NULL,
        offer_influenza BOOLEAN NOT NULL,
        offer_fever BOOLEAN NOT NULL,
        offer_adult_duo BOOLEAN NOT NULL,
        offer_src BOOLEAN NOT NULL,
        offer_other_vaccination VARCHAR(255),
offer_portfolio_copy BOOLEAN NOT NULL
);*/
public class VaccinationStatus {
  private int id;
  private boolean covid;
  private boolean influenza;
  private boolean tetanus;
  private boolean hepatitisB;
  private boolean offerCovid;
  private boolean offerHepatitis;
  private boolean offerInfluenza;
  private boolean offerFever;
  private boolean offerAdultDuo;
  private boolean offerSrc;
  private String offerOtherVaccination;

  public boolean isOfferCovid() {
    return offerCovid;
  }

  public void setOfferCovid(boolean offerCovid) {
    this.offerCovid = offerCovid;
  }

  public boolean isOfferHepatitis() {
    return offerHepatitis;
  }

  public void setOfferHepatitis(boolean offerHepatitis) {
    this.offerHepatitis = offerHepatitis;
  }

  public boolean isOfferInfluenza() {
    return offerInfluenza;
  }

  public void setOfferInfluenza(boolean offerInfluenza) {
    this.offerInfluenza = offerInfluenza;
  }

  public boolean isOfferFever() {
    return offerFever;
  }

  public void setOfferFever(boolean offerFever) {
    this.offerFever = offerFever;
  }

  public boolean isOfferAdultDuo() {
    return offerAdultDuo;
  }

  public void setOfferAdultDuo(boolean offerAdultDuo) {
    this.offerAdultDuo = offerAdultDuo;
  }

  public boolean isOfferSrc() {
    return offerSrc;
  }

  public void setOfferSrc(boolean offerSrc) {
    this.offerSrc = offerSrc;
  }

  public String getOfferOtherVaccination() {
    return offerOtherVaccination;
  }

  public void setOfferOtherVaccination(String offerOtherVaccination) {
    this.offerOtherVaccination = offerOtherVaccination;
  }

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

  public boolean isOfferPortfolioCopy() {
    return offerPortfolioCopy;
  }

  public void setOfferPortfolioCopy(boolean offerPortfolioCopy) {
    this.offerPortfolioCopy = offerPortfolioCopy;
  }
}
