package controller;

import service.*;
import utils.CPFUtils;
import utils.DateUtils;
import view.JFtreatment;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class TreatmentController {
  private TreatmentService treatmentService;
  private InformationService informationService;
  private SocialEconomicDataService socialEconomicDataService;
  private HealthConditionsService healthConditionsService;
  private MentalHealthService mentalHealthService;
  private JFtreatment treatment;

  public TreatmentController(JFtreatment treatment) {
    this.treatmentService = new TreatmentService();
    this.informationService = new InformationService();
    this.treatment = treatment;
  }

  private boolean validateFields() {
    List<String> erros = new ArrayList<>();
    if (treatment.txtNomeCompleto.getText().trim().isEmpty()) erros.add("• Nome Completo");

    String cpf = (String) treatment.txtCPF.getValue();
    if (cpf == null || cpf.contains("_")) {
      erros.add("• CPF (incompleto)");
    } else if (!CPFUtils.isValidCPF(cpf)) {
      erros.add("• CPF (inválido)");
    }

    if (treatment.txtDataNasc.getText().trim().isEmpty()) erros.add("• Data de Nascimento");
    if (treatment.txtEntryDate.getText().trim().isEmpty()) erros.add("• Data de Entrada na Unidade");
    if (treatment.groupTransferencia.getSelection() == null) erros.add("• Transferência de outra Unidade");
    if (treatment.groupNacionalidade.getSelection() == null) erros.add("• Nacionalidade");
    if (treatment.groupEC.getSelection() == null) erros.add("• Estado Civil");
    if (treatment.comboRaca.getSelectedIndex() <= 0) erros.add("• Raça/Cor");
    if (treatment.comboSexo.getSelectedIndex() <= 0) erros.add("• Sexo Biológico");
    if (treatment.comboGenero.getSelectedIndex() <= 0) erros.add("• Identidade de Gênero");
    if (treatment.comboOrientacao.getSelectedIndex() <= 0) erros.add("• Orientação Sexual");

    if (erros.isEmpty()) {
      return true;
    } else {
      StringBuilder mensagemErro = new StringBuilder("Os seguintes campos são obrigatórios ou inválidos:\n\n");
      for (String erro : erros) {
        mensagemErro.append(erro).append("\n");
      }
      JOptionPane.showMessageDialog(treatment, mensagemErro.toString(), "Campos Obrigatórios",
              JOptionPane.WARNING_MESSAGE);
      return false;
    }
  }

  public int saveTreatment() {

    Date actualDate = Date.from(treatment.actualDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date entryDate;
    try {
      entryDate = DateUtils.parseToDate(treatment.txtEntryDate.getText().trim());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(treatment, "Data inválida: ", "Erro", JOptionPane.ERROR_MESSAGE);
      return -1;
    }

    boolean isTransfer = treatment.radioTransfSim.isSelected();

    if(treatment.prisonerInformation != null) {
      treatmentService.updateTreatment(treatment.prisonerInformation.getTreatment().getId(),
              treatment.username,
              actualDate,
              entryDate,
              isTransfer,
              treatment.txtSource.getText()
      );
      return treatment.prisonerInformation.getTreatment().getId();
    }

    return treatmentService.saveTreatment(
            treatment.username,
            actualDate,
            entryDate,
            isTransfer,
            treatment.txtSource.getText()
    );
  }

  private int saveSocialEconomicData () {
    if (treatment.prisonerInformation != null) {
      return socialEconomicDataService.updateSocialEconomicData(
              treatment.prisonerInformation.getSocialEconomicData().getId(),
              treatment.comboEscolaridade.getSelectedItem().toString(),
              treatment.radioBeneficioSim.isSelected(),
              treatment.txtBeneficioQual.getText().trim(),
              treatment.radioFilhosSim.isSelected(),
              Integer.parseInt(treatment.txtFilhosQuantos.getText()),
              treatment.txtFilhosIdades.getText().trim(),
              treatment.radioDependentesSim.isSelected(),
              Integer.parseInt(treatment.txtDependentesQuantos.getText()),
              treatment.radioNeejaSim.isSelected(),
              treatment.radioAssistenciaSim.isSelected()
      );
    } else {
      return socialEconomicDataService.saveSocialEconomicData(
              treatment.comboEscolaridade.getSelectedItem().toString(),
              treatment.radioBeneficioSim.isSelected(),
              treatment.txtBeneficioQual.getText().trim(),
              treatment.radioFilhosSim.isSelected(),
              Integer.parseInt(treatment.txtFilhosQuantos.getText()),
              treatment.txtFilhosIdades.getText().trim(),
              treatment.radioDependentesSim.isSelected(),
              Integer.parseInt(treatment.txtDependentesQuantos.getText()),
              treatment.radioNeejaSim.isSelected(),
              treatment.radioAssistenciaSim.isSelected()
      );
    }
  }

  private int saveHealthConditions() {

    String chronicDiseases = String.format("%s%s%s%s%s",treatment.checkHipertensao.isSelected() ? "Hipertensão, "
            : "", treatment.checkDiabetes.isSelected() ? "Diabetes, "
            : "", treatment.checkHIV.isSelected() ? "HIV, "
            : "", treatment.checkAutoimune.isSelected() ? "Doença Autoimune, " : "",
            treatment.txtAutoimuneOutra.getText().trim());
    String infecciousDiseases = String.format("%s%s%s%s%s%s", treatment.checkSifilis.isSelected() ? "Sífilis, "
            : "", treatment.checkHPV.isSelected() ? "HPV, "
            : "", treatment.checkTuberculose.isSelected() ? "Tuberculose, "
            : "", treatment.checkHepatiteB.isSelected() ? "Hepatite B, "
            : "", treatment.checkHepatiteC.isSelected() ? "Hepatite C, ": "",
            treatment.txtDoencasInfecciosas.getText().trim());

    if (treatment.prisonerInformation != null) {
      return healthConditionsService.updateHealthConditions(
              treatment.prisonerInformation.getHealthConditions().getId(),
              treatment.radioDeficienciaSim.isSelected(),
              treatment.txtDeficienciaQual.getText().trim(),
              treatment.radioAlergiaSim.isSelected(),
              treatment.txtAlergiaQual.getText().trim(),
              treatment.radioCirurgiaSim.isSelected(),
              treatment.txtCirurgiaQual.getText().trim(),
              chronicDiseases,
              infecciousDiseases,
              treatment.checkPele.isSelected(),
              treatment.txtPeleQual.getText().trim(),
              treatment.checkMedicamentoContinuo.isSelected(),
              treatment.txtMedicamentoQual.getText().trim(),
              treatment.comboTipoSanguineo.getSelectedItem().toString()
      );
    } else {
      return healthConditionsService.saveHealthConditions(
              treatment.radioDeficienciaSim.isSelected(),
              treatment.txtDeficienciaQual.getText().trim(),
              treatment.radioAlergiaSim.isSelected(),
              treatment.txtAlergiaQual.getText().trim(),
              treatment.radioCirurgiaSim.isSelected(),
              treatment.txtCirurgiaQual.getText().trim(),
              chronicDiseases,
              infecciousDiseases,
              treatment.checkPele.isSelected(),
              treatment.txtPeleQual.getText().trim(),
              treatment.checkMedicamentoContinuo.isSelected(),
              treatment.txtMedicamentoQual.getText().trim(),
              treatment.comboTipoSanguineo.getSelectedItem().toString()
      );
    }
  }

  public void savePrisoner () {
    if(!validateFields()) return;
    int treatmentId = saveTreatment();

    if (treatmentId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar o tratamento.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    Date bornDate;
    try {
      bornDate = DateUtils.parseToDate(treatment.txtDataNasc.getText().trim());
    } catch (Exception e) {
      JOptionPane.showMessageDialog(treatment, "Data inválida: ", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    if (bornDate.after(new Date())) {
      JOptionPane.showMessageDialog(treatment, "Data de nascimento não pode ser no futuro.",
              "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int socialEconomicDataId = saveSocialEconomicData();
    if (socialEconomicDataId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar os dados socais e econômicos.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int healthConditionsId = saveHealthConditions();

    if (healthConditionsId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar as condições de saúde.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }
 /*
    int returnedValidation;

    if (treatment.prisonerInformation != null) {
      if (treatment.radioTransfNao.isSelected()) {
        treatment.txtSource.setText("");
      }
     returnedValidation = informationService.updateInformation(treatment.prisonerInformation.getId(),
              treatment.txtNomeSocial.getText().trim(),
              treatment.txtNomeCompleto.getText().trim(),
              bornDate,
              Integer.parseInt(treatment.txtIdade.getText()),
              treatment.txtCPF.getValue().toString().replaceAll("[^0-9]", ""),
              getSelectedButtonText(treatment.groupNacionalidade),
              treatment.txtMae.getText().trim(),
              treatment.checkPaiDesconhecido.isSelected() ? "Desconhecido" :
                      treatment.txtPai.getText().trim(),
              getSelectedButtonText(treatment.groupEC),
              treatment.comboRaca.getSelectedItem().toString(),
              treatment.comboSexo.getSelectedItem().toString(),
              treatment.comboOrientacao.getSelectedItem().toString(),
              treatment.comboGenero.getSelectedItem().toString()
              );
    }else {

      returnedValidation = informationService.saveInformation(
              treatment.txtNomeSocial.getText().trim(),
              treatment.txtNomeCompleto.getText().trim(),
              bornDate,
              Integer.parseInt(treatment.txtIdade.getText()),
              treatment.txtCPF.getValue().toString().replaceAll("[^0-9]", ""),
              getSelectedButtonText(treatment.groupNacionalidade),
              treatment.txtMae.getText().trim(),
              treatment.checkPaiDesconhecido.isSelected() ? "Desconhecido" :
                      treatment.txtPai.getText().trim(),
              getSelectedButtonText(treatment.groupEC),
              treatment.comboRaca.getSelectedItem().toString(),
              treatment.comboSexo.getSelectedItem().toString(),
              treatment.comboOrientacao.getSelectedItem().toString(),
              treatment.comboGenero.getSelectedItem().toString(),
              treatmentId
      );
    }

    if (returnedValidation <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar as informações do preso.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }
    treatment.homeScreen.populateTable(null);
    treatment.dispose();*/
  }

  private String getSelectedButtonText(ButtonGroup buttonGroup) {
    for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
      AbstractButton button = buttons.nextElement();
      if (button.isSelected()) {
        return button.getText();
      }
    }
    return null;
  }

  public void populateFields() {
    if (treatment.prisonerInformation == null) {
      return;
    }
    treatment.txtNomeSocial.setText(treatment.prisonerInformation.getSocialName());
    treatment.txtNomeCompleto.setText(treatment.prisonerInformation.getFullName());
    treatment.txtDataNasc.setText(new SimpleDateFormat("dd/MM/yyyy").format(treatment.prisonerInformation.getBornDate()));
    treatment.txtIdade.setText(String.valueOf(treatment.prisonerInformation.getAge()));
    treatment.txtCPF.setValue(CPFUtils.formatCPF(treatment.prisonerInformation.getCpf()));
    treatment.txtMae.setText(treatment.prisonerInformation.getMotherName());
    treatment.txtPai.setText(treatment.prisonerInformation.getFatherName());
    treatment.checkPaiDesconhecido.setSelected("Desconhecido".equals(treatment.prisonerInformation.getFatherName()));
    treatment.comboRaca.setSelectedItem(treatment.prisonerInformation.getEthnicity().getEtnia());
    treatment.comboSexo.setSelectedItem(treatment.prisonerInformation.getBiologicalSex().getBiologySex());
    treatment.comboOrientacao.setSelectedItem(treatment.prisonerInformation.getSexualOrientation().getSexualOrientation());
    treatment.comboGenero.setSelectedItem(treatment.prisonerInformation.getGenderIdentity().getGenderIdentity());
    treatment.txtEntryDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(treatment.prisonerInformation.getTreatment().getEntryDate()));
    treatment.txtSource.setText(treatment.prisonerInformation.getTreatment().getSourceTransfer());
    treatment.txtSource.setEnabled(true);
    treatment.radioTransfSim.setSelected(treatment.prisonerInformation.getTreatment().isTransfer());
    treatment.radioTransfNao.setSelected(!treatment.prisonerInformation.getTreatment().isTransfer());

    switch (treatment.prisonerInformation.getNationality()) {
      case "Brasileira": treatment.radioBrasileira.setSelected(true); break;
      case "Naturalizado": treatment.radioNaturalizado.setSelected(true); break;
      case "Estrangeiro": treatment.radioEstrangeiro.setSelected(true); break;
    }

    switch (treatment.prisonerInformation.getMaritalStatus().getMaritalStatus()) {
      case "Solteiro": treatment.radioSolteiro.setSelected(true); break;
      case "Casado": treatment.radioCasado.setSelected(true); break;
      case "União Estável": treatment.radioUniao.setSelected(true); break;
    }
  }
}
