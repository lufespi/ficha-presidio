package controller;

import service.InformationService;
import service.TreatmentService;
import utils.CPFValidator;
import view.JFtreatment;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreatmentController {
  private TreatmentService treatmentService;
  private InformationService informationService;
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
    } else if (!CPFValidator.isValidCPF(cpf)) {
      erros.add("• CPF (inválido)");
    }

    if (treatment.txtDataNasc.getText().trim().isEmpty()) erros.add("• Data de Nascimento");
    if (treatment.txtDataEntrada.getText().trim().isEmpty()) erros.add("• Data de Entrada na Unidade");
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
    return 0;
  }

  public void savePrisoner () {
    if (!validateFields()) {
      return;
    }
    int id = saveTreatment();


    treatment.homeScreen.populateTable();
    treatment.dispose();

  }
}
