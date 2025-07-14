package controller;

import service.*;
import utils.CPFUtils;
import utils.DateUtils;
import view.JFtreatment;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
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
  private ClinicalCareService clinicalCareService;
  private JFtreatment treatment;
  private VaccinationStatusService vaccinationStatusService;
  private QuickTestsService quickTestsService;
  private MenHealthService menHealthService;
  private WomenHealthService womenHealthService;

  public TreatmentController(JFtreatment treatment) {
    this.treatmentService = new TreatmentService();
    this.informationService = new InformationService();
    this.socialEconomicDataService = new SocialEconomicDataService();
    this.healthConditionsService = new HealthConditionsService();
    this.mentalHealthService = new MentalHealthService();
    this.clinicalCareService = new ClinicalCareService();
    this.vaccinationStatusService = new VaccinationStatusService();
    this.quickTestsService = new QuickTestsService();
    this.menHealthService = new MenHealthService();
    this.womenHealthService = new WomenHealthService();
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
    Integer quantidadeFilhos = null;
    if(!treatment.txtFilhosQuantos.getText().isEmpty()){
      quantidadeFilhos = Integer.parseInt(treatment.txtFilhosQuantos.getText().trim());
    }

    Integer quantidadeDependentes = null;
    if(!treatment.txtDependentesQuantos.getText().isEmpty()){
      quantidadeDependentes = Integer.parseInt(treatment.txtDependentesQuantos.getText().trim());
    }

    if (treatment.prisonerInformation != null) {
      return socialEconomicDataService.updateSocialEconomicData(
              treatment.prisonerInformation.getSocialEconomicData().getId(),
              treatment.comboEscolaridade.getSelectedItem().toString(),
              treatment.radioBeneficioSim.isSelected(),
              treatment.txtBeneficioQual.getText().trim(),
              treatment.radioFilhosSim.isSelected(),
              quantidadeFilhos,
              treatment.txtFilhosIdades.getText().trim(),
              treatment.radioDependentesSim.isSelected(),
              quantidadeDependentes,
              treatment.radioNeejaSim.isSelected(),
              treatment.radioAssistenciaSim.isSelected()
      );
    } else {
      return socialEconomicDataService.saveSocialEconomicData(
              treatment.comboEscolaridade.getSelectedItem().toString(),
              treatment.radioBeneficioSim.isSelected(),
              treatment.txtBeneficioQual.getText().trim(),
              treatment.radioFilhosSim.isSelected(),
              quantidadeFilhos,
              treatment.txtFilhosIdades.getText().trim(),
              treatment.radioDependentesSim.isSelected(),
              quantidadeDependentes,
              treatment.radioNeejaSim.isSelected(),
              treatment.radioAssistenciaSim.isSelected()
      );
    }
  }

  private int saveHealthConditions() {
    if (treatment.prisonerInformation != null) {
      return healthConditionsService.updateHealthConditions(
              treatment.prisonerInformation.getHealthConditions().getId(),
              treatment.radioDeficienciaSim.isSelected(),
              treatment.txtDeficienciaQual.getText().trim(),
              treatment.radioAlergiaSim.isSelected(),
              treatment.txtAlergiaQual.getText().trim(),
              treatment.radioCirurgiaSim.isSelected(),
              treatment.txtCirurgiaQual.getText().trim(),
              treatment.checkHipertensao.isSelected(),
              treatment.checkDiabetes.isSelected(),
              treatment.checkHIV.isSelected(),
              treatment.checkAutoimune.isSelected(),
              treatment.checkSifilis.isSelected(),
              treatment.checkHPV.isSelected(),
              treatment.txtAutoimuneOutra.getText().trim(),
              treatment.checkTuberculose.isSelected(),
              treatment.checkHepatiteB.isSelected(),
              treatment.checkHepatiteC.isSelected(),
              treatment.txtInfecciosaOutra.getText().trim(),
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
              treatment.checkHipertensao.isSelected(),
              treatment.checkDiabetes.isSelected(),
              treatment.checkHIV.isSelected(),
              treatment.checkAutoimune.isSelected(),
              treatment.checkSifilis.isSelected(),
              treatment.checkHPV.isSelected(),
              treatment.txtAutoimuneOutra.getText().trim(),
              treatment.checkTuberculose.isSelected(),
              treatment.checkHepatiteB.isSelected(),
              treatment.checkHepatiteC.isSelected(),
              treatment.txtInfecciosaOutra.getText().trim(),
              treatment.checkPele.isSelected(),
              treatment.txtPeleQual.getText().trim(),
              treatment.checkMedicamentoContinuo.isSelected(),
              treatment.txtMedicamentoQual.getText().trim(),
              treatment.comboTipoSanguineo.getSelectedItem().toString()
      );
    }
  }

  private int saveMentalHealth() {
    if (treatment.prisonerInformation != null) {
      return mentalHealthService.updateMentalHealth(
              treatment.prisonerInformation.getMentalHealth().getId(),
              treatment.checkCaps.isSelected(),
              treatment.txtCapsQual.getText().trim(),
              treatment.checkAnsiedade.isSelected(),
              treatment.checkDepressao.isSelected(),
              treatment.checkBipolaridade.isSelected(),
              treatment.checkEsquizofrenia.isSelected(),
              treatment.checkAutismo.isSelected(),
              treatment.txtUsoOutrasDrogas.getText().trim(),
              treatment.checkUsaMedicamentoControlado.isSelected(),
              treatment.txtMedicamentoControladoQual.getText().trim(),
              treatment.checkAcompanhamentoMental.isSelected(),
              treatment.txtAcompanhamentoMotivo.getText().trim(),
              treatment.checkUsoAlcool.isSelected(),
              treatment.checkUsoCigarro.isSelected(),
              treatment.checkUsoMaconha.isSelected(),
              treatment.checkUsoCrack.isSelected(),
              treatment.checkUsoCocaina.isSelected(),
              treatment.checkUsoAnfetaminas.isSelected(),
              treatment.checkUsoDrogasK.isSelected(),
              treatment.txtUsoOutrasDrogas.getText().trim(),
              treatment.checkTratamentoReducao.isSelected(),
              treatment.txtTratamentoReducaoQual.getText().trim(),
              treatment.checkGostariaTratamento.isSelected(),
              treatment.txtGostariaTratamentoQual.getText().trim(),
              treatment.checkEncaminhaPsicologia.isSelected(),
              treatment.checkEncaminhaPsiquiatra.isSelected(),
              treatment.checkEncaminhaReceitas.isSelected(),
              treatment.checkEncaminhaGruposApoio.isSelected()
      );
    } else {
      return mentalHealthService.saveMentalHealth(
              treatment.checkCaps.isSelected(),
              treatment.txtCapsQual.getText().trim(),
              treatment.checkAnsiedade.isSelected(),
              treatment.checkDepressao.isSelected(),
              treatment.checkBipolaridade.isSelected(),
              treatment.checkEsquizofrenia.isSelected(),
              treatment.checkAutismo.isSelected(),
              treatment.txtUsoOutrasDrogas.getText().trim(),
              treatment.checkUsaMedicamentoControlado.isSelected(),
              treatment.txtMedicamentoControladoQual.getText().trim(),
              treatment.checkAcompanhamentoMental.isSelected(),
              treatment.txtAcompanhamentoMotivo.getText().trim(),
              treatment.checkUsoAlcool.isSelected(),
              treatment.checkUsoCigarro.isSelected(),
              treatment.checkUsoMaconha.isSelected(),
              treatment.checkUsoCrack.isSelected(),
              treatment.checkUsoCocaina.isSelected(),
              treatment.checkUsoAnfetaminas.isSelected(),
              treatment.checkUsoDrogasK.isSelected(),
              treatment.txtUsoOutrasDrogas.getText().trim(),
              treatment.checkTratamentoReducao.isSelected(),
              treatment.txtTratamentoReducaoQual.getText().trim(),
              treatment.checkGostariaTratamento.isSelected(),
              treatment.txtGostariaTratamentoQual.getText().trim(),
              treatment.checkEncaminhaPsicologia.isSelected(),
              treatment.checkEncaminhaPsiquiatra.isSelected(),
              treatment.checkEncaminhaReceitas.isSelected(),
              treatment.checkEncaminhaGruposApoio.isSelected()
      );
    }
  }

  private int saveClinicalCare() {
    Date startDateRespiratorySymptoms;

    if (!treatment.txtDataInicioSintomas.getText().trim().isEmpty()) {
      try {
        startDateRespiratorySymptoms = DateUtils.parseToDate(treatment.txtDataInicioSintomas.getText().trim());
      } catch (Exception e) {
        JOptionPane.showMessageDialog(treatment, "Data inválida: ", "Erro", JOptionPane.ERROR_MESSAGE);
        return -1;
      }
    }
    else {
      startDateRespiratorySymptoms = null;
    }
    if(treatment.prisonerInformation != null) {
      return clinicalCareService.updateClinicalCare(
              treatment.prisonerInformation.getClinicalCare().getId(),
              new BigDecimal(treatment.txtPeso.getText().trim()),
              new BigDecimal(treatment.txtAltura.getText().trim()),
              new BigDecimal(treatment.txtIMC.getText().trim()),
              new BigDecimal(treatment.txtPA.getText().trim()),
              new BigDecimal(treatment.txtFC.getText().trim()),
              new BigDecimal(treatment.txtSAT.getText().trim()),
              new BigDecimal(treatment.txtTemp.getText().trim()),
              treatment.checkTosse.isSelected(),
              treatment.checkCoriza.isSelected(),
              treatment.checkEspirros.isSelected(),
              treatment.checkFebre.isSelected(),
              treatment.checkCalafrios.isSelected(),
              treatment.txtSintomasOutros.getText().trim(),
              startDateRespiratorySymptoms,
              treatment.checkLesoes.isSelected(),
              treatment.txtLesoesLocal.getText().trim()
      );
    }else {
      return clinicalCareService.saveClinicalCare(
              new BigDecimal(treatment.txtPeso.getText().trim()),
              new BigDecimal(treatment.txtAltura.getText().trim()),
              new BigDecimal(treatment.txtIMC.getText().trim()),
              new BigDecimal(treatment.txtPA.getText().trim()),
              new BigDecimal(treatment.txtFC.getText().trim()),
              new BigDecimal(treatment.txtSAT.getText().trim()),
              new BigDecimal(treatment.txtTemp.getText().trim()),
              treatment.checkTosse.isSelected(),
              treatment.checkCoriza.isSelected(),
              treatment.checkEspirros.isSelected(),
              treatment.checkFebre.isSelected(),
              treatment.checkCalafrios.isSelected(),
              treatment.txtSintomasOutros.getText().trim(),
              startDateRespiratorySymptoms,
              treatment.checkLesoes.isSelected(),
              treatment.txtLesoesLocal.getText().trim()
              );
    }
  }

  private int saveVaccinationStatus() {
    if(treatment.prisonerInformation != null) {
      return vaccinationStatusService.updateVaccinationStatus(
              treatment.prisonerInformation.getVaccinationStatus().getId(),
              treatment.checkVacinaCovid.isSelected(),
              treatment.checkVacinaInfluenza.isSelected(),
              treatment.checkVacinaTetano.isSelected(),
              treatment.checkVacinaHepatiteB.isSelected(),
              treatment.checkOfertaCovid.isSelected(),
              treatment.checkOfertaHepatiteB.isSelected(),
              treatment.checkOfertaInfluenza.isSelected(),
              treatment.checkOfertaFebreAmarela.isSelected(),
              treatment.checkOfertaDuplaAdulto.isSelected(),
              treatment.checkOfertaTripliceViral.isSelected(),
              treatment.txtOfertaVacinaOutra.getText().trim(),
              treatment.checkOfertarCopiaCarteira.isSelected()
      );
    }else {
      return vaccinationStatusService.saveVaccinationStatus(
              treatment.checkVacinaCovid.isSelected(),
              treatment.checkVacinaInfluenza.isSelected(),
              treatment.checkVacinaTetano.isSelected(),
              treatment.checkVacinaHepatiteB.isSelected(),
              treatment.checkOfertaCovid.isSelected(),
              treatment.checkOfertaHepatiteB.isSelected(),
              treatment.checkOfertaInfluenza.isSelected(),
              treatment.checkOfertaFebreAmarela.isSelected(),
              treatment.checkOfertaDuplaAdulto.isSelected(),
              treatment.checkOfertaTripliceViral.isSelected(),
              treatment.txtOfertaVacinaOutra.getText().trim(),
              treatment.checkOfertarCopiaCarteira.isSelected()
      );
    }
  }

  private int saveQuickTests() {
    if(treatment.prisonerInformation != null) {
      return quickTestsService.updateQuickTests(
              treatment.prisonerInformation.getQuickTests().getId(),
              treatment.radioGravidezPos.isSelected() ? "Positivo" :
                      treatment.radioGravidezNao.isSelected() ? "Não Realizado" : "Negativo",
              treatment.checkColetaEscarro.isSelected(),
              treatment.checkOutraQueixa.isSelected(),
              treatment.txtOutraQueixaQual.getText().trim(),
              treatment.checkQueixaOdonto.isSelected(),
              treatment.txtQueixaOdontoQual.getText().trim(),
              treatment.checkAvaliacaoDentista.isSelected()
      );
    }
    else {
      return quickTestsService.saveQuickTests(
              treatment.radioGravidezPos.isSelected() ? "Positivo" :
                      treatment.radioGravidezNao.isSelected() ? "Não Realizado" : "Negativo",
              treatment.checkColetaEscarro.isSelected(),
              treatment.checkOutraQueixa.isSelected(),
              treatment.txtOutraQueixaQual.getText().trim(),
              treatment.checkQueixaOdonto.isSelected(),
              treatment.txtQueixaOdontoQual.getText().trim(),
              treatment.checkAvaliacaoDentista.isSelected()
      );
    }
  }

  private int saveMenHealth() {
    Integer exameProstataAno = null;
    if(!treatment.txtExameProstataAno.getText().trim().isEmpty()){
      exameProstataAno = Integer.parseInt(treatment.txtExameProstataAno.getText().trim());
    }

    if(treatment.prisonerInformation != null) {
      return menHealthService.updateMenHealth(
              treatment.prisonerInformation.getMenHealth().getId(),
              treatment.checkExameProstata.isSelected(),
              exameProstataAno,
              treatment.checkHistFamiliarProstata.isSelected(),
              treatment.txtHistFamiliarProstataQual.getText().trim(),
              treatment.checkVasectomia.isSelected(),
              treatment.checkParceiraGestante.isSelected(),
              treatment.checkParticipaPreNatal.isSelected(),
              treatment.checkEncaminhaVasectomia.isSelected(),
              treatment.checkEncaminhaPreNatalParceiro.isSelected()
      );
    } else {
      return menHealthService.saveMenHealth(
              treatment.checkExameProstata.isSelected(),
              exameProstataAno,
              treatment.checkHistFamiliarProstata.isSelected(),
              treatment.txtHistFamiliarProstataQual.getText().trim(),
              treatment.checkVasectomia.isSelected(),
              treatment.checkParceiraGestante.isSelected(),
              treatment.checkParticipaPreNatal.isSelected(),
              treatment.checkEncaminhaVasectomia.isSelected(),
              treatment.checkEncaminhaPreNatalParceiro.isSelected()
      );
    }
  }

  private int saveWomenHealth() {
    Integer papanicolauAno = null;
    if(!treatment.txtPapanicolauAno.getText().trim().isEmpty()){
      papanicolauAno = Integer.parseInt(treatment.txtPapanicolauAno.getText().trim());
    }
    if(treatment.prisonerInformation != null){
      return womenHealthService.updateWomenHealth(
              treatment.prisonerInformation.getWomenHealth().getId(),
              treatment.radioGestacaoSim.isSelected(),
              treatment.txtIdadeGestacional.getText().trim(),
              treatment.checkContraOral.isSelected(),
              treatment.checkContraInjetavel.isSelected(),
              treatment.checkContraDIU.isSelected(),
              treatment.checkContraLigadura.isSelected(),
              treatment.checkContraHisterectomia.isSelected(),
              treatment.checkPapanicolau.isSelected(),
              papanicolauAno,
              treatment.checkEncaminhaContraceptivo.isSelected(),
              treatment.checkEncaminhaPreventivo.isSelected(),
              treatment.checkEncaminhaPreNatal.isSelected()
      );
    }else {
      return womenHealthService.saveWomenHealth(
              treatment.radioGestacaoSim.isSelected(),
              treatment.txtIdadeGestacional.getText().trim(),
              treatment.checkContraOral.isSelected(),
              treatment.checkContraInjetavel.isSelected(),
              treatment.checkContraDIU.isSelected(),
              treatment.checkContraLigadura.isSelected(),
              treatment.checkContraHisterectomia.isSelected(),
              treatment.checkPapanicolau.isSelected(),
              papanicolauAno,
              treatment.checkEncaminhaContraceptivo.isSelected(),
              treatment.checkEncaminhaPreventivo.isSelected(),
              treatment.checkEncaminhaPreNatal.isSelected()
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

    int mentalHealthId = saveMentalHealth();
    if (mentalHealthId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar os dados de saúde mental.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int clinicalCareId = saveClinicalCare();
    if (clinicalCareId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar os dados de atendimento clínico.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int vaccinationStatusId = saveVaccinationStatus();
    if (vaccinationStatusId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar o status de vacinação.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    int quickTestsId = saveQuickTests();
    if (quickTestsId <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar ao finalizar o atendimento.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }
    Integer menHealthId = null;
    Integer womenHealthId = null;
    if(treatment.radioSaudeHomem.isSelected()){
      menHealthId = saveMenHealth();
      if (menHealthId <= 0) {
        JOptionPane.showMessageDialog(treatment, "Erro ao salvar os dados de saúde do homem.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }else {
      womenHealthId = saveWomenHealth();
      if (womenHealthId <= 0) {
        JOptionPane.showMessageDialog(treatment, "Erro ao salvar os dados de saúde da mulher.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
    }


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
              treatment.comboGenero.getSelectedItem().toString(),
              socialEconomicDataId,
              healthConditionsId,
              womenHealthId,
              menHealthId,
              mentalHealthId,
              vaccinationStatusId,
              clinicalCareId,
              quickTestsId
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
              treatmentId,
              socialEconomicDataId,
              healthConditionsId,
              womenHealthId,
              menHealthId,
              mentalHealthId,
              vaccinationStatusId,
              clinicalCareId,
              quickTestsId
      );
    }

    if (returnedValidation <= 0) {
      JOptionPane.showMessageDialog(treatment, "Erro ao salvar as informações do preso.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }
    treatment.homeScreen.populateTable(null);
    treatment.dispose();
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

    treatment.comboEscolaridade.setSelectedItem(treatment.prisonerInformation.getSocialEconomicData().getEducation().getEducation());
    treatment.radioBeneficioSim.setSelected(treatment.prisonerInformation.getSocialEconomicData().isHasFamilyBenefits());
    treatment.radioBeneficioNao.setSelected(!treatment.prisonerInformation.getSocialEconomicData().isHasFamilyBenefits());
    treatment.txtBeneficioQual.setText(treatment.prisonerInformation.getSocialEconomicData().getFamilyBenefits());
    treatment.radioFilhosSim.setSelected(treatment.prisonerInformation.getSocialEconomicData().isHasChildren());
    treatment.radioFilhosNao.setSelected(!treatment.prisonerInformation.getSocialEconomicData().isHasChildren());
    treatment.txtFilhosQuantos.setText(String.valueOf(treatment.prisonerInformation.getSocialEconomicData().getChildrenQuantity()));
    treatment.txtFilhosIdades.setText(treatment.prisonerInformation.getSocialEconomicData().getAges());
    treatment.radioDependentesSim.setSelected(treatment.prisonerInformation.getSocialEconomicData().isHasDependents());
    treatment.radioDependentesNao.setSelected(!treatment.prisonerInformation.getSocialEconomicData().isHasDependents());
    treatment.txtDependentesQuantos.setText(String.valueOf(treatment.prisonerInformation.getSocialEconomicData().getDependentsQuantity()));
    treatment.radioNeejaSim.setSelected(treatment.prisonerInformation.getSocialEconomicData().isHasNeejaEducation());
    treatment.radioNeejaNao.setSelected(!treatment.prisonerInformation.getSocialEconomicData().isHasNeejaEducation());
    treatment.radioAssistenciaSim.setSelected(treatment.prisonerInformation.getSocialEconomicData().isHasSocialAssistent());
    treatment.radioAssistenciaNao.setSelected(!treatment.prisonerInformation.getSocialEconomicData().isHasSocialAssistent());

    treatment.txtPeso.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getWeight()));
    treatment.txtAltura.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getHeight()));
    treatment.txtIMC.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getImc()));
    treatment.txtPA.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getBloodPressure()));
    treatment.txtFC.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getHearRate()));
    treatment.txtSAT.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getSaturation()));
    treatment.txtTemp.setText(String.valueOf(treatment.prisonerInformation.getClinicalCare().getTemperature()));
    if(treatment.prisonerInformation.getClinicalCare().isHasCough() ||
       treatment.prisonerInformation.getClinicalCare().isHasRunnyNose() ||
       treatment.prisonerInformation.getClinicalCare().isHasSneezing() ||
       treatment.prisonerInformation.getClinicalCare().isHasFever() ||
       treatment.prisonerInformation.getClinicalCare().isHasChills()) {
      treatment.checkSintomasResp.setSelected(true);
      Component[] componentesSintomas = {treatment.checkTosse,
              treatment.checkCoriza, treatment.checkEspirros,
              treatment.checkFebre, treatment.checkCalafrios, treatment.txtSintomasOutros,
              treatment.txtDataInicioSintomas};
      for(Component c : componentesSintomas) {
        c.setEnabled(true);
      }
    }
    treatment.checkTosse.setSelected(treatment.prisonerInformation.getClinicalCare().isHasCough());
    treatment.checkCoriza.setSelected(treatment.prisonerInformation.getClinicalCare().isHasRunnyNose());
    treatment.checkEspirros.setSelected(treatment.prisonerInformation.getClinicalCare().isHasSneezing());
    treatment.checkFebre.setSelected(treatment.prisonerInformation.getClinicalCare().isHasFever());
    treatment.checkCalafrios.setSelected(treatment.prisonerInformation.getClinicalCare().isHasChills());
    treatment.txtSintomasOutros.setText(treatment.prisonerInformation.getClinicalCare().getOtherSymptoms());
    if (treatment.prisonerInformation.getClinicalCare().getStartDateRespiratorySymptoms() != null) {
      treatment.txtDataInicioSintomas.setText(new SimpleDateFormat("dd/MM/yyyy").format(treatment.prisonerInformation.getClinicalCare().getStartDateRespiratorySymptoms()));
    } else {
      treatment.txtDataInicioSintomas.setText("");
    }
    treatment.checkLesoes.setSelected(treatment.prisonerInformation.getClinicalCare().isHasInjuries());
    if (treatment.prisonerInformation.getClinicalCare().isHasInjuries()) {
      treatment.txtLesoesLocal.setEnabled(true);
    } else {
      treatment.txtLesoesLocal.setEnabled(false);
    }
    treatment.txtLesoesLocal.setText(treatment.prisonerInformation.getClinicalCare().getInjuriesSites());

    treatment.radioDeficienciaSim.setSelected(treatment.prisonerInformation.getHealthConditions().isHasDeficiency());
    treatment.radioDeficienciaNao.setSelected(!treatment.prisonerInformation.getHealthConditions().isHasDeficiency());
    treatment.txtDeficienciaQual.setText(treatment.prisonerInformation.getHealthConditions().getDeficiency());
    treatment.radioAlergiaSim.setSelected(treatment.prisonerInformation.getHealthConditions().isHasAllergies());
    treatment.radioAlergiaNao.setSelected(!treatment.prisonerInformation.getHealthConditions().isHasAllergies());
    treatment.radioCirurgiaSim.setSelected(treatment.prisonerInformation.getHealthConditions().isHasSurgeries());
    treatment.radioCirurgiaNao.setSelected(!treatment.prisonerInformation.getHealthConditions().isHasSurgeries());
    treatment.txtCirurgiaQual.setText(treatment.prisonerInformation.getHealthConditions().getSurgeries());
    treatment.txtAlergiaQual.setText(treatment.prisonerInformation.getHealthConditions().getAllergies());
    treatment.checkHipertensao.setSelected(treatment.prisonerInformation.getHealthConditions().isHasHypertension());
    treatment.checkDiabetes.setSelected(treatment.prisonerInformation.getHealthConditions().isHasDiabetes());
    treatment.checkHIV.setSelected(treatment.prisonerInformation.getHealthConditions().isHasHiv());
    treatment.checkAutoimune.setSelected(treatment.prisonerInformation.getHealthConditions().isHasAutoimmune());
    treatment.checkSifilis.setSelected(treatment.prisonerInformation.getHealthConditions().isHasSyphilis());
    treatment.checkHPV.setSelected(treatment.prisonerInformation.getHealthConditions().isHasHpv());
    treatment.txtAutoimuneOutra.setText(treatment.prisonerInformation.getHealthConditions().getOtherChronicDisease());
    treatment.checkTuberculose.setSelected(treatment.prisonerInformation.getHealthConditions().isHasTuberculosis());
    treatment.checkHepatiteB.setSelected(treatment.prisonerInformation.getHealthConditions().isHasHepatitisB());
    treatment.checkHepatiteC.setSelected(treatment.prisonerInformation.getHealthConditions().isHasHepatitisC());
    treatment.txtInfecciosaOutra.setText(treatment.prisonerInformation.getHealthConditions().getOtherInfectiousDisease());
    treatment.checkPele.setSelected(treatment.prisonerInformation.getHealthConditions().isHasSkinDiseases());
    if (treatment.prisonerInformation.getHealthConditions().isHasSkinDiseases()) {
      treatment.txtPeleQual.setEnabled(true);
    } else {
      treatment.txtPeleQual.setEnabled(false);
    }
    treatment.txtPeleQual.setText(treatment.prisonerInformation.getHealthConditions().getSkinDiseases());
    treatment.checkMedicamentoContinuo.setSelected(treatment.prisonerInformation.getHealthConditions().isUseContinuousMedication());
    if (treatment.prisonerInformation.getHealthConditions().isUseContinuousMedication()) {
      treatment.txtMedicamentoQual.setEnabled(true);
    } else {
      treatment.txtMedicamentoQual.setEnabled(false);
    }
    treatment.txtMedicamentoQual.setText(treatment.prisonerInformation.getHealthConditions().getContinuousMedication());
    treatment.comboTipoSanguineo.setSelectedItem(treatment.prisonerInformation.getHealthConditions().getBloodType());

    treatment.checkCaps.setSelected(treatment.prisonerInformation.getMentalHealth().isHasLinkCaps());
    treatment.txtCapsQual.setText(treatment.prisonerInformation.getMentalHealth().getCapsCity());
    treatment.checkAcompanhamentoMental.setSelected(treatment.prisonerInformation.getMentalHealth().isWasAccompaniment());
    if (treatment.prisonerInformation.getMentalHealth().isWasAccompaniment()) {
      treatment.txtAcompanhamentoMotivo.setEnabled(true);
    } else {
      treatment.txtAcompanhamentoMotivo.setEnabled(false);
    }
    treatment.txtAcompanhamentoMotivo.setText(treatment.prisonerInformation.getMentalHealth().getReasonAccompaniment());
    treatment.checkAnsiedade.setSelected(treatment.prisonerInformation.getMentalHealth().isHasAnxiety());
    treatment.checkDepressao.setSelected(treatment.prisonerInformation.getMentalHealth().isHasDepression());
    treatment.checkBipolaridade.setSelected(treatment.prisonerInformation.getMentalHealth().isHasBipolarity());
    treatment.checkEsquizofrenia.setSelected(treatment.prisonerInformation.getMentalHealth().isHasSchizophrenia());
    treatment.checkAutismo.setSelected(treatment.prisonerInformation.getMentalHealth().isHasAutism());
    treatment.txtTranstornoOutro.setText(treatment.prisonerInformation.getMentalHealth().getOtherMentalDisorder());
    treatment.checkUsaMedicamentoControlado.setSelected(treatment.prisonerInformation.getMentalHealth().isUseControlledMedicine());
    treatment.txtMedicamentoControladoQual.setText(treatment.prisonerInformation.getMentalHealth().getControlledMedicines());
    if (treatment.prisonerInformation.getMentalHealth().isUseControlledMedicine()) {
      treatment.txtMedicamentoControladoQual.setEnabled(true);
    } else {
      treatment.txtMedicamentoControladoQual.setEnabled(false);
    }
    treatment.checkUsoAlcool.setSelected(treatment.prisonerInformation.getMentalHealth().isUseAlcohol());
    treatment.checkUsoCigarro.setSelected(treatment.prisonerInformation.getMentalHealth().isUseCigarettes());
    treatment.checkUsoMaconha.setSelected(treatment.prisonerInformation.getMentalHealth().isUseMarijuana());
    treatment.checkUsoCrack.setSelected(treatment.prisonerInformation.getMentalHealth().isUseCrack());
    treatment.checkUsoCocaina.setSelected(treatment.prisonerInformation.getMentalHealth().isUseCocaine());
    treatment.checkUsoAnfetaminas.setSelected(treatment.prisonerInformation.getMentalHealth().isUseAmphetamines());
    treatment.checkUsoDrogasK.setSelected(treatment.prisonerInformation.getMentalHealth().isUseKDrugs());
    treatment.txtUsoOutrasDrogas.setText(treatment.prisonerInformation.getMentalHealth().getOtherSubstances());
    treatment.checkTratamentoReducao.setSelected(treatment.prisonerInformation.getMentalHealth().isHasTreatment());
    treatment.txtTratamentoReducaoQual.setText(treatment.prisonerInformation.getMentalHealth().getSubstanceTreatment());
    if (treatment.prisonerInformation.getMentalHealth().isHasTreatment()) {
      treatment.txtTratamentoReducaoQual.setEnabled(true);
    } else {
      treatment.txtTratamentoReducaoQual.setEnabled(false);
    }
    treatment.checkGostariaTratamento.setSelected(treatment.prisonerInformation.getMentalHealth().isWannaTreatment());
    treatment.txtGostariaTratamentoQual.setText(treatment.prisonerInformation.getMentalHealth().getWannaTreatmentSubstance());
    if (treatment.prisonerInformation.getMentalHealth().isWannaTreatment()) {
      treatment.txtGostariaTratamentoQual.setEnabled(true);
    } else {
      treatment.txtGostariaTratamentoQual.setEnabled(false);
    }
    treatment.checkEncaminhaPsicologia.setSelected(treatment.prisonerInformation.getMentalHealth().isOfferPsychology());
    treatment.checkEncaminhaPsiquiatra.setSelected(treatment.prisonerInformation.getMentalHealth().isOfferPsychiatrist());
    treatment.checkEncaminhaReceitas.setSelected(treatment.prisonerInformation.getMentalHealth().isRevenueRenewal());
    treatment.checkEncaminhaGruposApoio.setSelected(treatment.prisonerInformation.getMentalHealth().isSupportGroups());

    treatment.checkVacinaCovid.setSelected(treatment.prisonerInformation.getVaccinationStatus().isCovid());
    treatment.checkVacinaInfluenza.setSelected(treatment.prisonerInformation.getVaccinationStatus().isInfluenza());
    treatment.checkVacinaTetano.setSelected(treatment.prisonerInformation.getVaccinationStatus().isTetanus());
    treatment.checkVacinaHepatiteB.setSelected(treatment.prisonerInformation.getVaccinationStatus().isHepatitisB());
    treatment.checkOfertaCovid.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferCovid());
    treatment.checkOfertaHepatiteB.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferHepatitis());
    treatment.checkOfertaInfluenza.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferInfluenza());
    treatment.checkOfertaFebreAmarela.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferFever());
    treatment.checkOfertaDuplaAdulto.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferAdultDuo());
    treatment.checkOfertaTripliceViral.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferSrc());
    treatment.txtOfertaVacinaOutra.setText(treatment.prisonerInformation.getVaccinationStatus().getOfferOtherVaccination());
    treatment.checkOfertarCopiaCarteira.setSelected(treatment.prisonerInformation.getVaccinationStatus().isOfferPortfolioCopy());

    switch (treatment.prisonerInformation.getQuickTests().getPregnantTest()) {
      case "Positivo": treatment.radioGravidezPos.setSelected(true); break;
      case "Negativo": treatment.radioGravidezNeg.setSelected(true); break;
      case "Não Realizado": treatment.radioGravidezNao.setSelected(true); break;
    }
    treatment.checkColetaEscarro.setSelected(treatment.prisonerInformation.getQuickTests().isSputumCollection());
    treatment.checkOutraQueixa.setSelected(treatment.prisonerInformation.getQuickTests().isHasComplaint());
    if (treatment.prisonerInformation.getQuickTests().isHasComplaint()) {
      treatment.txtOutraQueixaQual.setEnabled(true);
    } else {
      treatment.txtOutraQueixaQual.setEnabled(false);
    }
    treatment.txtOutraQueixaQual.setText(treatment.prisonerInformation.getQuickTests().getComplaintDescription());
    treatment.checkQueixaOdonto.setSelected(treatment.prisonerInformation.getQuickTests().isHasDentalComplaint());
    if (treatment.prisonerInformation.getQuickTests().isHasDentalComplaint()) {
      treatment.txtQueixaOdontoQual.setEnabled(true);
    } else {
      treatment.txtQueixaOdontoQual.setEnabled(false);
    }
    treatment.txtQueixaOdontoQual.setText(treatment.prisonerInformation.getQuickTests().getDentalComplaint());
    if(treatment.prisonerInformation.getWomenHealth().getId() > 0) {
      treatment.radioSaudeMulher.setSelected(true);
      treatment.cardLayout.show(treatment.panelContainerDinamico, "MULHER");
      treatment.txtIdadeGestacional.setText(treatment.prisonerInformation.getWomenHealth().getPregnantAge());
      treatment.radioGestacaoSim.setSelected(treatment.prisonerInformation.getWomenHealth().isPregnant());
      treatment.radioGestacaoNao.setSelected(!treatment.prisonerInformation.getWomenHealth().isPregnant());
      treatment.checkContraOral.setSelected(treatment.prisonerInformation.getWomenHealth().isUseOralContraceptive());
      treatment.checkContraInjetavel.setSelected(treatment.prisonerInformation.getWomenHealth().isUseInjectableContraceptive());
      treatment.checkContraDIU.setSelected(treatment.prisonerInformation.getWomenHealth().isUseIUDImplant());
      treatment.checkContraLigadura.setSelected(treatment.prisonerInformation.getWomenHealth().isUseTubalLigation());
      treatment.checkContraHisterectomia.setSelected(treatment.prisonerInformation.getWomenHealth().isUseHysterectomy());
      treatment.checkPapanicolau.setSelected(treatment.prisonerInformation.getWomenHealth().isHasPreventiveExam());
      if (treatment.prisonerInformation.getWomenHealth().isHasPreventiveExam()) {
        treatment.txtPapanicolauAno.setEnabled(true);
      } else {
        treatment.txtPapanicolauAno.setEnabled(false);
      }
      treatment.txtPapanicolauAno.setText(String.valueOf(treatment.prisonerInformation.getWomenHealth().getPreventiveExamYear()));

      treatment.checkEncaminhaContraceptivo.setSelected(treatment.prisonerInformation.getWomenHealth().isOfferContraceptiveMethod());
      treatment.checkEncaminhaPreventivo.setSelected(treatment.prisonerInformation.getWomenHealth().isOfferPreventiveExam());
      treatment.checkEncaminhaPreNatal.setSelected(treatment.prisonerInformation.getWomenHealth().isPrenatal());
    } else {
      treatment.radioSaudeHomem.setSelected(true);
      treatment.cardLayout.show(treatment.panelContainerDinamico, "HOMEM");
      treatment.checkExameProstata.setSelected(treatment.prisonerInformation.getMenHealth().isHasPreventExam());
      if (treatment.prisonerInformation.getMenHealth().isHasPreventExam()) {
        treatment.txtExameProstataAno.setEnabled(true);
      } else {
        treatment.txtExameProstataAno.setEnabled(false);
      }
      treatment.txtExameProstataAno.setText(String.valueOf(treatment.prisonerInformation.getMenHealth().getPreventExamYear()));
      treatment.checkHistFamiliarProstata.setSelected(treatment.prisonerInformation.getMenHealth().isHasProstateCancerFamilyHistory());
      treatment.txtHistFamiliarProstataQual.setText(treatment.prisonerInformation.getMenHealth().getProstateCancerFamily());
      if (treatment.prisonerInformation.getMenHealth().isHasProstateCancerFamilyHistory()) {
        treatment.txtHistFamiliarProstataQual.setEnabled(true);
      } else {
        treatment.txtHistFamiliarProstataQual.setEnabled(false);
      }
      treatment.checkVasectomia.setSelected(treatment.prisonerInformation.getMenHealth().isVasectomy());
      treatment.checkParceiraGestante.setSelected(treatment.prisonerInformation.getMenHealth().isPregnantPartner());
      treatment.checkParticipaPreNatal.setSelected(treatment.prisonerInformation.getMenHealth().isPrenatalPregnantPartner());
      if(treatment.prisonerInformation.getMenHealth().isPrenatalPregnantPartner()) {
        treatment.checkParticipaPreNatal.setEnabled(true);
        treatment.checkEncaminhaPreNatalParceiro.setEnabled(true);
      } else {
        treatment.checkParticipaPreNatal.setEnabled(false);
        treatment.checkEncaminhaPreNatalParceiro.setEnabled(false);
      }
      treatment.checkEncaminhaVasectomia.setSelected(treatment.prisonerInformation.getMenHealth().isOfferVasectomy());
      treatment.checkEncaminhaPreNatalParceiro.setSelected(treatment.prisonerInformation.getMenHealth().isOfferPrenatal());

    }



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
