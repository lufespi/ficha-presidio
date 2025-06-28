package entities;

import java.util.Date;

public class Treatment {
  private int id;
  private User user;
  private Date treatmentDate;
  private Date entryDate;
  private boolean isTransfer;
  private String sourceTransfer;

  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Date getTreatmentDate() {
    return treatmentDate;
  }
  public void setTreatmentDate(Date treatmentDate) {
    this.treatmentDate = treatmentDate;
  }
  public Date getEntryDate() {
    return entryDate;
  }
  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }
  public boolean isTransfer() {
    return isTransfer;
  }
  public void setTransfer(boolean isTransfer) {
    this.isTransfer = isTransfer;
  }
  public String getSourceTransfer() {
    return sourceTransfer;
  }
  public void setSourceTransfer(String sourceTransfer) {
    this.sourceTransfer = sourceTransfer;
  }
}