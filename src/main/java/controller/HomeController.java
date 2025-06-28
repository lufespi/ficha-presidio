package controller;

import entities.Information;
import service.InformationService;
import utils.CPFUtils;
import view.JFhome;

import java.util.List;

public class HomeController {
  private InformationService informationService;
  private List<Information> infos;

  public HomeController() {
    this.informationService = new InformationService();
  }

  public void populateTable(JFhome homePage, String filterName) {
    homePage.tableModel.setRowCount(0);
    infos = informationService.getAllInfos(filterName);

    infos.forEach(info -> {
      homePage.tableModel.addRow(new Object[]{
        info.getId(),
        info.getFullName(),
        CPFUtils.maskCPF(info.getCpf()),
        info.getAge(),
        info.getMotherName(),
        info.getTreatment().getEntryDate(),
      });
    });
  }

  public Information getInformationById(int id) {
    return infos.stream().filter(i -> i.getId() == id)
            .findFirst().orElse(null);
  }
}
