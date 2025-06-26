package controller;

import service.InformationService;
import view.JFhome;

public class HomeController {
  private InformationService informationService;

  public HomeController() {
    this.informationService = new InformationService();
  }

  public void populateTable(JFhome homePage) {
    homePage.tableModel.setRowCount(0);
    informationService.getAllInfos().forEach(info -> {
      homePage.tableModel.addRow(new Object[]{
        info.getId(),
        info.getFullName(),
        info.getCpf(),
        info.getAge(),
        info.getMotherName(),
        info.getTreatment().getEntryDate(),
      });
    });
  }
}
