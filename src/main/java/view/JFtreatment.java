package view;

import controller.TreatmentController;
import entities.Information;
import entities.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JFtreatment extends JFrame {

    // --- Variáveis de Instância para UI ---
    public JPanel mainPanel;
    public JScrollPane scrollPane;

    // --- Componentes da Interface (Públicos) ---
    public JFormattedTextField txtCPF;
    public JTextField txtNomeCompleto, txtEntryDate, txtDataNasc, txtNomeSocial,
            txtIdade, txtMae, txtPai, txtSource;
    public JRadioButton radioTransfNao, radioTransfSim, radioBrasileira, radioNaturalizado,
            radioEstrangeiro, radioSolteiro, radioCasado, radioUniao;
    public ButtonGroup groupTransferencia, groupNacionalidade, groupEC, groupSaudeEspecifica;
    public JCheckBox checkPaiDesconhecido;
    public JComboBox<String> comboRaca, comboSexo, comboGenero, comboOrientacao;
    public JComboBox<String> comboEscolaridade;
    public JTextArea txtProfissao;
    public JRadioButton radioBeneficioNao, radioBeneficioSim;
    public JTextField txtBeneficioQual;
    public JRadioButton radioFilhosNao, radioFilhosSim;
    public JTextField txtFilhosQuantos, txtFilhosIdades;
    public JRadioButton radioDependentesNao, radioDependentesSim;
    public JTextField txtDependentesQuantos;
    public JRadioButton radioNeejaNao, radioNeejaSim, radioAssistenciaNao, radioAssistenciaSim;
    public JRadioButton radioDeficienciaNao, radioDeficienciaSim;
    public JTextArea txtDeficienciaQual;
    public JRadioButton radioAlergiaNao, radioAlergiaSim;
    public JTextArea txtAlergiaQual;
    public JRadioButton radioCirurgiaNao, radioCirurgiaSim;
    public JTextArea txtCirurgiaQual;
    public JCheckBox checkHipertensao, checkDiabetes, checkHIV, checkSifilis, checkHPV, checkTuberculose, checkPele;
    public JTextField txtPeleQual;
    public JCheckBox checkAutoimune, checkHepatiteB, checkHepatiteC, checkMedicamentoContinuo;
    public JTextField txtAutoimuneOutra;
    public JTextArea txtAutoimuneObs, txtHepatiteObs;
    public JTextField txtMedicamentoQual;
    public JComboBox<String> comboTipoSanguineo;
    public JRadioButton radioSaudeMulher, radioSaudeHomem;
    public JRadioButton radioGestacaoNaoSabe, radioGestacaoNao, radioGestacaoSim;
    public JTextField txtIdadeGestacional;
    public JCheckBox checkContraNao, checkContraOral, checkContraDIU, checkContraInjetavel, checkContraLigadura, checkContraHisterectomia;
    public JCheckBox checkPapanicolau;
    public JTextField txtPapanicolauAno;
    public JCheckBox checkEncaminhaContraceptivo, checkEncaminhaPreventivo, checkEncaminhaPreNatal;
    public JCheckBox checkExameProstata, checkHistFamiliarProstata, checkVasectomia, checkParceiraGestante, checkParticipaPreNatal, checkEncaminhaVasectomia, checkEncaminhaPreNatalParceiro;
    public JTextField txtExameProstataAno, txtHistFamiliarProstataQual;
    public JCheckBox checkCaps, checkAcompanhamentoMental;
    public JTextField txtCapsQual, txtAcompanhamentoMotivo;
    public JCheckBox checkAnsiedade, checkDepressao, checkBipolaridade, checkEsquizofrenia, checkAutismo;
    public JTextField txtTranstornoOutro;
    public JCheckBox checkUsaMedicamentoControlado;
    public JTextField txtMedicamentoControladoQual;
    public JCheckBox checkUsoAlcool, checkUsoCigarro, checkUsoMaconha, checkUsoCrack, checkUsoCocaina, checkUsoAnfetaminas, checkUsoDrogasK;
    public JTextField txtUsoOutrasDrogas;
    public JCheckBox checkTratamentoReducao, checkGostariaTratamento;
    public JTextField txtTratamentoReducaoQual, txtGostariaTratamentoQual;
    public JCheckBox checkEncaminhaPsicologia, checkEncaminhaPsiquiatra, checkEncaminhaReceitas, checkEncaminhaGruposApoio;

    // --- Variáveis de Controle ---
    public LocalDate actualDate;
    public LocalTime actualTime;
    public JFhome homeScreen;
    public User username;
    private TreatmentController treatmentController;
    public Information prisonerInformation;

    public JFtreatment(JFhome homeScreen, User username, Information prisonerInformation) {
        this.homeScreen = homeScreen;
        this.username = username;
        this.treatmentController = new TreatmentController(this);
        this.prisonerInformation = prisonerInformation;

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmarCancelamento();
            }
        });

        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(760, 2450)); // Altura total para todos os painéis
        scrollPane.setViewportView(mainPanel);

        this.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnSalvar = new JButton("Salvar Cadastro");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);
        this.add(panelBotoes, BorderLayout.SOUTH);

        actualDate = LocalDate.now();
        actualTime = LocalTime.now();
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        // --- PAINEL ATENDIMENTO ---
        JPanel panelAtendimento = new JPanel();
        panelAtendimento.setBorder(new TitledBorder("ATENDIMENTO"));
        panelAtendimento.setLayout(null);
        panelAtendimento.setBounds(10, 10, 740, 140);
        mainPanel.add(panelAtendimento);
        // ... (código do painel de atendimento)
        JLabel lblResponsavel = new JLabel("Responsável pelo atendimento:");
        lblResponsavel.setBounds(20, 30, 200, 25);
        panelAtendimento.add(lblResponsavel);
        JLabel lblResponsavelValor = new JLabel(username.getUsername());
        lblResponsavelValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblResponsavelValor.setBounds(220, 30, 200, 25);
        panelAtendimento.add(lblResponsavelValor);
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 60, 40, 25);
        panelAtendimento.add(lblData);
        JLabel lblDataValor = new JLabel(actualDate.format(formatadorData));
        lblDataValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblDataValor.setBounds(60, 60, 100, 25);
        panelAtendimento.add(lblDataValor);
        JLabel lblHorario = new JLabel("Horário:");
        lblHorario.setBounds(180, 60, 60, 25);
        panelAtendimento.add(lblHorario);
        JLabel lblHorarioValor = new JLabel(actualTime.format(formatadorHora));
        lblHorarioValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblHorarioValor.setBounds(240, 60, 100, 25);
        panelAtendimento.add(lblHorarioValor);
        JLabel lblDataEntrada = new JLabel("Data de entrada na Unidade Prisional:");
        lblDataEntrada.setBounds(380, 60, 250, 25);
        panelAtendimento.add(lblDataEntrada);
        txtEntryDate = new JTextField();
        txtEntryDate.setBounds(630, 60, 100, 25);
        panelAtendimento.add(txtEntryDate);
        JLabel lblTransferencia = new JLabel("Transferência de outra Unidade Prisional:");
        lblTransferencia.setBounds(20, 95, 250, 25);
        panelAtendimento.add(lblTransferencia);
        radioTransfNao = new JRadioButton("Não");
        radioTransfNao.setBounds(280, 95, 60, 25);
        panelAtendimento.add(radioTransfNao);
        radioTransfSim = new JRadioButton("Sim");
        radioTransfSim.setBounds(340, 95, 60, 25);
        panelAtendimento.add(radioTransfSim);
        groupTransferencia = new ButtonGroup();
        groupTransferencia.add(radioTransfNao);
        groupTransferencia.add(radioTransfSim);
        JLabel lblProcedencia = new JLabel("Se sim, qual a procedência:");
        lblProcedencia.setBounds(410, 95, 180, 25);
        panelAtendimento.add(lblProcedencia);
        txtSource = new JTextField();
        txtSource.setBounds(590, 95, 140, 25);
        txtSource.setEnabled(false);
        panelAtendimento.add(txtSource);
        radioTransfSim.addActionListener(e -> txtSource.setEnabled(true));
        radioTransfNao.addActionListener(e -> txtSource.setEnabled(false));

        // --- PAINEL IDENTIFICAÇÃO ---
        JPanel panelIdentificacao = new JPanel();
        panelIdentificacao.setBorder(new TitledBorder("IDENTIFICAÇÃO"));
        panelIdentificacao.setLayout(null);
        panelIdentificacao.setBounds(10, 160, 740, 280);
        mainPanel.add(panelIdentificacao);
        // ... (código do painel de identificação)
        JLabel lblNomeCompleto = new JLabel("Nome Completo:");
        lblNomeCompleto.setBounds(20, 30, 100, 25);
        panelIdentificacao.add(lblNomeCompleto);
        txtNomeCompleto = new JTextField();
        txtNomeCompleto.setBounds(130, 30, 600, 25);
        panelIdentificacao.add(txtNomeCompleto);
        JLabel lblNomeSocial = new JLabel("Nome Social:");
        lblNomeSocial.setBounds(20, 65, 100, 25);
        panelIdentificacao.add(lblNomeSocial);
        txtNomeSocial = new JTextField();
        txtNomeSocial.setBounds(130, 65, 600, 25);
        panelIdentificacao.add(txtNomeSocial);
        JLabel lblDataNasc = new JLabel("Data de Nascimento:");
        lblDataNasc.setBounds(20, 100, 120, 25);
        panelIdentificacao.add(lblDataNasc);
        txtDataNasc = new JTextField();
        txtDataNasc.setBounds(150, 100, 100, 25);
        panelIdentificacao.add(txtDataNasc);
        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(270, 100, 40, 25);
        panelIdentificacao.add(lblCPF);
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            txtCPF = new JFormattedTextField(mascaraCpf);
        } catch (ParseException ex) {
            System.err.println("Erro ao criar máscara de CPF. Usando JTextField padrão.");
            txtCPF = new JFormattedTextField();
        }
        txtCPF.setBounds(310, 100, 130, 25);
        panelIdentificacao.add(txtCPF);
        JLabel lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(460, 100, 40, 25);
        panelIdentificacao.add(lblIdade);
        txtIdade = new JTextField();
        txtIdade.setBounds(510, 100, 60, 25);
        panelIdentificacao.add(txtIdade);
        JLabel lblNacionalidade = new JLabel("Nacionalidade:");
        lblNacionalidade.setBounds(20, 135, 100, 25);
        panelIdentificacao.add(lblNacionalidade);
        radioBrasileira = new JRadioButton("Brasileira");
        radioBrasileira.setBounds(130, 135, 90, 25);
        panelIdentificacao.add(radioBrasileira);
        radioNaturalizado = new JRadioButton("Naturalizado");
        radioNaturalizado.setBounds(230, 135, 110, 25);
        panelIdentificacao.add(radioNaturalizado);
        radioEstrangeiro = new JRadioButton("Estrangeiro");
        radioEstrangeiro.setBounds(350, 135, 100, 25);
        panelIdentificacao.add(radioEstrangeiro);
        groupNacionalidade = new ButtonGroup();
        groupNacionalidade.add(radioBrasileira);
        groupNacionalidade.add(radioNaturalizado);
        groupNacionalidade.add(radioEstrangeiro);
        JLabel lblMae = new JLabel("Nome da mãe:");
        lblMae.setBounds(20, 170, 100, 25);
        panelIdentificacao.add(lblMae);
        txtMae = new JTextField();
        txtMae.setBounds(130, 170, 600, 25);
        panelIdentificacao.add(txtMae);
        JLabel lblPai = new JLabel("Nome do pai:");
        lblPai.setBounds(20, 205, 100, 25);
        panelIdentificacao.add(lblPai);
        txtPai = new JTextField();
        txtPai.setBounds(130, 205, 450, 25);
        panelIdentificacao.add(txtPai);
        checkPaiDesconhecido = new JCheckBox("Desconhecido");
        checkPaiDesconhecido.setBounds(590, 205, 120, 25);
        panelIdentificacao.add(checkPaiDesconhecido);
        checkPaiDesconhecido.addActionListener(e -> {
            txtPai.setEnabled(!checkPaiDesconhecido.isSelected());
            if (checkPaiDesconhecido.isSelected()) txtPai.setText("");
        });
        JLabel lblEstadoCivil = new JLabel("Estado Civil:");
        lblEstadoCivil.setBounds(20, 240, 100, 25);
        panelIdentificacao.add(lblEstadoCivil);
        radioSolteiro = new JRadioButton("Solteiro"); radioSolteiro.setBounds(130, 240, 80, 25); panelIdentificacao.add(radioSolteiro);
        radioCasado = new JRadioButton("Casado"); radioCasado.setBounds(210, 240, 80, 25); panelIdentificacao.add(radioCasado);
        radioUniao = new JRadioButton("União Estável"); radioUniao.setBounds(290, 240, 120, 25); panelIdentificacao.add(radioUniao);
        groupEC = new ButtonGroup();
        groupEC.add(radioSolteiro);
        groupEC.add(radioCasado);
        groupEC.add(radioUniao);

        // --- PAINEL AUTODECLARADO ---
        JPanel panelAutodeclarado = new JPanel();
        panelAutodeclarado.setBorder(new TitledBorder("INFORMAÇÕES AUTODECLARADAS"));
        panelAutodeclarado.setLayout(null);
        panelAutodeclarado.setBounds(10, 450, 740, 200);
        mainPanel.add(panelAutodeclarado);
        // ... (código do painel autodeclarado) ...
        String[] racas = {"Selecione...", "Branco(a)", "Preto(a)", "Pardo(a)", "Amarelo(a)", "Indígena", "Não desejo informar"};
        String[] sexos = {"Selecione...", "Feminino", "Masculino", "Intersexo", "Não desejo informar"};
        String[] generos = {"Selecione...", "Mulher (cisgênero)", "Homem (cisgênero)", "Mulher (transgênero) / Travesti", "Homem (transgênero)", "Não binário", "Não desejo informar"};
        String[] orientacoes = {"Selecione...", "Heterossexual", "Homossexual (Lésbica/Gay)", "Bissexual", "Assexual", "Pansexual", "Outra", "Não desejo informar"};
        JLabel lblRaca = new JLabel("Raça/Cor:");
        lblRaca.setBounds(20, 30, 150, 25);
        panelAutodeclarado.add(lblRaca);
        comboRaca = new JComboBox<>(racas);
        comboRaca.setBounds(180, 30, 550, 25);
        panelAutodeclarado.add(comboRaca);
        JLabel lblSexo = new JLabel("Sexo Biológico:");
        lblSexo.setBounds(20, 65, 150, 25);
        panelAutodeclarado.add(lblSexo);
        comboSexo = new JComboBox<>(sexos);
        comboSexo.setBounds(180, 65, 550, 25);
        panelAutodeclarado.add(comboSexo);
        JLabel lblGenero = new JLabel("Identidade de Gênero:");
        lblGenero.setBounds(20, 100, 150, 25);
        panelAutodeclarado.add(lblGenero);
        comboGenero = new JComboBox<>(generos);
        comboGenero.setBounds(180, 100, 550, 25);
        panelAutodeclarado.add(comboGenero);
        JLabel lblOrientacao = new JLabel("Orientação Sexual:");
        lblOrientacao.setBounds(20, 135, 150, 25);
        panelAutodeclarado.add(lblOrientacao);
        comboOrientacao = new JComboBox<>(orientacoes);
        comboOrientacao.setBounds(180, 135, 550, 25);
        panelAutodeclarado.add(comboOrientacao);

        // --- PAINEL DADOS SOCIAIS E ECONÔMICOS ---
        JPanel panelSocial = new JPanel();
        panelSocial.setBorder(new TitledBorder("DADOS SOCIAIS E ECONÔMICOS"));
        panelSocial.setLayout(null);
        panelSocial.setBounds(10, 660, 740, 320);
        mainPanel.add(panelSocial);
        // ... (código do painel social) ...
        JLabel lblEscolaridade = new JLabel("Escolaridade:");
        lblEscolaridade.setBounds(20, 30, 100, 25);
        panelSocial.add(lblEscolaridade);
        String[] escolaridades = {"Selecione...", "Não alfabetizado(a)", "Fundamental incompleto", "Fundamental completo", "Médio incompleto", "Médio completo", "Superior incompleto", "Superior completo", "Não desejo informar"};
        comboEscolaridade = new JComboBox<>(escolaridades);
        comboEscolaridade.setBounds(130, 30, 240, 25);
        panelSocial.add(comboEscolaridade);
        JLabel lblProfissao = new JLabel("Profissão/Ocupação/Renda:");
        lblProfissao.setBounds(20, 65, 200, 25);
        panelSocial.add(lblProfissao);
        txtProfissao = new JTextArea();
        JScrollPane scrollProfissao = new JScrollPane(txtProfissao);
        scrollProfissao.setBounds(20, 90, 350, 60);
        panelSocial.add(scrollProfissao);
        JLabel lblBeneficio = new JLabel("A família recebe algum benefício?");
        lblBeneficio.setBounds(380, 65, 250, 25);
        panelSocial.add(lblBeneficio);
        radioBeneficioNao = new JRadioButton("Não"); radioBeneficioNao.setBounds(400, 90, 60, 25); panelSocial.add(radioBeneficioNao);
        radioBeneficioSim = new JRadioButton("Sim"); radioBeneficioSim.setBounds(460, 90, 60, 25); panelSocial.add(radioBeneficioSim);
        ButtonGroup groupBeneficio = new ButtonGroup();
        groupBeneficio.add(radioBeneficioNao); groupBeneficio.add(radioBeneficioSim);
        JLabel lblBeneficioQual = new JLabel("Qual?"); lblBeneficioQual.setBounds(530, 90, 50, 25); panelSocial.add(lblBeneficioQual);
        txtBeneficioQual = new JTextField(); txtBeneficioQual.setBounds(580, 90, 150, 25); txtBeneficioQual.setEnabled(false); panelSocial.add(txtBeneficioQual);
        radioBeneficioSim.addActionListener(e -> txtBeneficioQual.setEnabled(true));
        radioBeneficioNao.addActionListener(e -> txtBeneficioQual.setEnabled(false));
        JLabel lblFilhos = new JLabel("Possui filhos?");
        lblFilhos.setBounds(20, 160, 100, 25);
        panelSocial.add(lblFilhos);
        radioFilhosNao = new JRadioButton("Não"); radioFilhosNao.setBounds(130, 160, 60, 25); panelSocial.add(radioFilhosNao);
        radioFilhosSim = new JRadioButton("Sim"); radioFilhosSim.setBounds(190, 160, 60, 25); panelSocial.add(radioFilhosSim);
        ButtonGroup groupFilhos = new ButtonGroup();
        groupFilhos.add(radioFilhosNao); groupFilhos.add(radioFilhosSim);
        JLabel lblFilhosQuantos = new JLabel("Quantos?"); lblFilhosQuantos.setBounds(260, 160, 70, 25); panelSocial.add(lblFilhosQuantos);
        txtFilhosQuantos = new JTextField(); txtFilhosQuantos.setBounds(330, 160, 50, 25); panelSocial.add(txtFilhosQuantos);
        JLabel lblFilhosIdades = new JLabel("Idade(s):"); lblFilhosIdades.setBounds(400, 160, 70, 25); panelSocial.add(lblFilhosIdades);
        txtFilhosIdades = new JTextField(); txtFilhosIdades.setBounds(470, 160, 260, 25); panelSocial.add(txtFilhosIdades);
        JLabel lblDependentes = new JLabel("Possui outros dependentes?");
        lblDependentes.setBounds(20, 195, 200, 25);
        panelSocial.add(lblDependentes);
        radioDependentesNao = new JRadioButton("Não"); radioDependentesNao.setBounds(220, 195, 60, 25); panelSocial.add(radioDependentesNao);
        radioDependentesSim = new JRadioButton("Sim"); radioDependentesSim.setBounds(280, 195, 60, 25); panelSocial.add(radioDependentesSim);
        ButtonGroup groupDependentes = new ButtonGroup();
        groupDependentes.add(radioDependentesNao); groupDependentes.add(radioDependentesSim);
        JLabel lblDependentesQuantos = new JLabel("Quantos?"); lblDependentesQuantos.setBounds(350, 195, 70, 25); panelSocial.add(lblDependentesQuantos);
        txtDependentesQuantos = new JTextField(); txtDependentesQuantos.setBounds(420, 195, 50, 25); panelSocial.add(txtDependentesQuantos);
        JLabel lblEncaminhamentos = new JLabel("Encaminhamentos Sociais:");
        lblEncaminhamentos.setFont(new Font("Arial", Font.BOLD, 12));
        lblEncaminhamentos.setBounds(20, 230, 200, 25);
        panelSocial.add(lblEncaminhamentos);
        JLabel lblNeeja = new JLabel("Ofertar encaminhamento para conhecer ou estudar no NEEJA:");
        lblNeeja.setBounds(40, 255, 400, 25);
        panelSocial.add(lblNeeja);
        radioNeejaNao = new JRadioButton("Não"); radioNeejaNao.setBounds(450, 255, 60, 25); panelSocial.add(radioNeejaNao);
        radioNeejaSim = new JRadioButton("Sim"); radioNeejaSim.setBounds(510, 255, 60, 25); panelSocial.add(radioNeejaSim);
        ButtonGroup groupNeeja = new ButtonGroup();
        groupNeeja.add(radioNeejaNao); groupNeeja.add(radioNeejaSim);
        JLabel lblAssistencia = new JLabel("Ofertar conversa com a Assistência Social:");
        lblAssistencia.setBounds(40, 285, 400, 25);
        panelSocial.add(lblAssistencia);
        radioAssistenciaNao = new JRadioButton("Não"); radioAssistenciaNao.setBounds(450, 285, 60, 25); panelSocial.add(radioAssistenciaNao);
        radioAssistenciaSim = new JRadioButton("Sim"); radioAssistenciaSim.setBounds(510, 285, 60, 25); panelSocial.add(radioAssistenciaSim);
        ButtonGroup groupAssistencia = new ButtonGroup();
        groupAssistencia.add(radioAssistenciaNao); groupAssistencia.add(radioAssistenciaSim);

        // --- PAINEL: CONDIÇÕES DE SAÚDE ---
        JPanel panelSaude = new JPanel();
        panelSaude.setBorder(new TitledBorder("CONDIÇÕES DE SAÚDE"));
        panelSaude.setLayout(null);
        panelSaude.setBounds(10, 990, 740, 520);
        mainPanel.add(panelSaude);
        // ... (código do painel de saúde)
        JLabel lblDeficiencia = new JLabel("Possui alguma deficiência?");
        lblDeficiencia.setBounds(20, 30, 200, 25);
        panelSaude.add(lblDeficiencia);
        radioDeficienciaNao = new JRadioButton("Não"); radioDeficienciaNao.setBounds(20, 55, 60, 25); panelSaude.add(radioDeficienciaNao);
        radioDeficienciaSim = new JRadioButton("Sim"); radioDeficienciaSim.setBounds(80, 55, 60, 25); panelSaude.add(radioDeficienciaSim);
        JLabel lblDeficienciaQual = new JLabel("Qual(is)?:"); lblDeficienciaQual.setBounds(20, 80, 70, 25); panelSaude.add(lblDeficienciaQual);
        txtDeficienciaQual = new JTextArea();
        JScrollPane scrollDeficiencia = new JScrollPane(txtDeficienciaQual);
        scrollDeficiencia.setBounds(20, 105, 210, 50);
        panelSaude.add(scrollDeficiencia);
        ButtonGroup groupDeficiencia = new ButtonGroup();
        groupDeficiencia.add(radioDeficienciaNao); groupDeficiencia.add(radioDeficienciaSim);
        txtDeficienciaQual.setEnabled(false);
        radioDeficienciaSim.addActionListener(e -> scrollDeficiencia.getViewport().getView().setEnabled(true));
        radioDeficienciaNao.addActionListener(e -> scrollDeficiencia.getViewport().getView().setEnabled(false));
        JLabel lblAlergia = new JLabel("Possui alergias ou intolerância alimentar?");
        lblAlergia.setBounds(260, 30, 260, 25);
        panelSaude.add(lblAlergia);
        radioAlergiaNao = new JRadioButton("Não"); radioAlergiaNao.setBounds(260, 55, 60, 25); panelSaude.add(radioAlergiaNao);
        radioAlergiaSim = new JRadioButton("Sim"); radioAlergiaSim.setBounds(320, 55, 60, 25); panelSaude.add(radioAlergiaSim);
        JLabel lblAlergiaQual = new JLabel("Qual(is)?:"); lblAlergiaQual.setBounds(260, 80, 70, 25); panelSaude.add(lblAlergiaQual);
        txtAlergiaQual = new JTextArea();
        JScrollPane scrollAlergia = new JScrollPane(txtAlergiaQual);
        scrollAlergia.setBounds(260, 105, 210, 50);
        panelSaude.add(scrollAlergia);
        ButtonGroup groupAlergia = new ButtonGroup();
        groupAlergia.add(radioAlergiaNao); groupAlergia.add(radioAlergiaSim);
        txtAlergiaQual.setEnabled(false);
        radioAlergiaSim.addActionListener(e -> scrollAlergia.getViewport().getView().setEnabled(true));
        radioAlergiaNao.addActionListener(e -> scrollAlergia.getViewport().getView().setEnabled(false));
        JLabel lblCirurgia = new JLabel("Realizou cirurgias?");
        lblCirurgia.setBounds(500, 30, 200, 25);
        panelSaude.add(lblCirurgia);
        radioCirurgiaNao = new JRadioButton("Não"); radioCirurgiaNao.setBounds(500, 55, 60, 25); panelSaude.add(radioCirurgiaNao);
        radioCirurgiaSim = new JRadioButton("Sim"); radioCirurgiaSim.setBounds(560, 55, 60, 25); panelSaude.add(radioCirurgiaSim);
        JLabel lblCirurgiaQual = new JLabel("Qual(is)?:"); lblCirurgiaQual.setBounds(500, 80, 70, 25); panelSaude.add(lblCirurgiaQual);
        txtCirurgiaQual = new JTextArea();
        JScrollPane scrollCirurgia = new JScrollPane(txtCirurgiaQual);
        scrollCirurgia.setBounds(500, 105, 210, 50);
        panelSaude.add(scrollCirurgia);
        ButtonGroup groupCirurgia = new ButtonGroup();
        groupCirurgia.add(radioCirurgiaNao);
        groupCirurgia.add(radioCirurgiaSim);
        txtCirurgiaQual.setEnabled(false);
        radioCirurgiaSim.addActionListener(e -> scrollCirurgia.getViewport().getView().setEnabled(true));
        radioCirurgiaNao.addActionListener(e -> scrollCirurgia.getViewport().getView().setEnabled(false));
        JSeparator separator1 = new JSeparator();
        separator1.setBounds(10, 165, 720, 5);
        panelSaude.add(separator1);
        JLabel lblCronicas = new JLabel("Condições crônicas:");
        lblCronicas.setBounds(20, 180, 200, 25);
        panelSaude.add(lblCronicas);
        checkHipertensao = new JCheckBox("Hipertensão"); checkHipertensao.setBounds(30, 205, 150, 25); panelSaude.add(checkHipertensao);
        checkDiabetes = new JCheckBox("Diabetes"); checkDiabetes.setBounds(30, 230, 150, 25); panelSaude.add(checkDiabetes);
        checkHIV = new JCheckBox("HIV"); checkHIV.setBounds(30, 255, 150, 25); panelSaude.add(checkHIV);
        checkAutoimune = new JCheckBox("Doença Autoimune"); checkAutoimune.setBounds(30, 280, 150, 25); panelSaude.add(checkAutoimune);
        JLabel lblAutoimuneOutra = new JLabel("Outra:"); lblAutoimuneOutra.setBounds(190, 280, 50, 25); panelSaude.add(lblAutoimuneOutra);
        txtAutoimuneOutra = new JTextField(); txtAutoimuneOutra.setBounds(240, 280, 100, 25); panelSaude.add(txtAutoimuneOutra);
        JLabel lblAutoimuneObs = new JLabel("Obs:"); lblAutoimuneObs.setBounds(30, 310, 50, 25); panelSaude.add(lblAutoimuneObs);
        txtAutoimuneObs = new JTextArea(); JScrollPane scrollAutoimune = new JScrollPane(txtAutoimuneObs); scrollAutoimune.setBounds(30, 335, 310, 40); panelSaude.add(scrollAutoimune);
        JLabel lblInfecciosas = new JLabel("Histórico doenças infecciosas:");
        lblInfecciosas.setBounds(360, 180, 200, 25);
        panelSaude.add(lblInfecciosas);
        checkSifilis = new JCheckBox("Sífilis"); checkSifilis.setBounds(370, 205, 150, 25); panelSaude.add(checkSifilis);
        checkHPV = new JCheckBox("HPV"); checkHPV.setBounds(370, 230, 150, 25); panelSaude.add(checkHPV);
        checkTuberculose = new JCheckBox("Tuberculose"); checkTuberculose.setBounds(370, 255, 150, 25); panelSaude.add(checkTuberculose);
        checkHepatiteB = new JCheckBox("Hepatite B"); checkHepatiteB.setBounds(370, 280, 150, 25); panelSaude.add(checkHepatiteB);
        checkHepatiteC = new JCheckBox("Hepatite C"); checkHepatiteC.setBounds(370, 305, 150, 25); panelSaude.add(checkHepatiteC);
        JLabel lblHepatiteObs = new JLabel("Obs:"); lblHepatiteObs.setBounds(370, 335, 50, 25); panelSaude.add(lblHepatiteObs);
        txtHepatiteObs = new JTextArea(); JScrollPane scrollHepatite = new JScrollPane(txtHepatiteObs); scrollHepatite.setBounds(370, 360, 360, 40); panelSaude.add(scrollHepatite);
        checkPele = new JCheckBox("Possui doença de pele?");
        checkPele.setBounds(540, 180, 200, 25);
        panelSaude.add(checkPele);
        JLabel lblPeleQual = new JLabel("Qual(is)?:");
        lblPeleQual.setBounds(550, 205, 70, 25);
        panelSaude.add(lblPeleQual);
        txtPeleQual = new JTextField();
        txtPeleQual.setBounds(550, 230, 180, 25);
        txtPeleQual.setEnabled(false);
        panelSaude.add(txtPeleQual);
        checkPele.addActionListener(e -> txtPeleQual.setEnabled(checkPele.isSelected()));
        JSeparator separator3 = new JSeparator();
        separator3.setBounds(10, 415, 720, 5);
        panelSaude.add(separator3);
        checkMedicamentoContinuo = new JCheckBox("Usa medicamento contínuo?");
        checkMedicamentoContinuo.setBounds(20, 430, 220, 25);
        panelSaude.add(checkMedicamentoContinuo);
        JLabel lblMedicamentoQual = new JLabel("Qual(is)?:");
        lblMedicamentoQual.setBounds(250, 430, 70, 25);
        panelSaude.add(lblMedicamentoQual);
        txtMedicamentoQual = new JTextField();
        txtMedicamentoQual.setBounds(320, 430, 410, 25);
        txtMedicamentoQual.setEnabled(false);
        panelSaude.add(txtMedicamentoQual);
        checkMedicamentoContinuo.addActionListener(e -> txtMedicamentoQual.setEnabled(checkMedicamentoContinuo.isSelected()));
        JLabel lblTipoSanguineo = new JLabel("Tipo sanguíneo:");
        lblTipoSanguineo.setBounds(20, 465, 120, 25);
        panelSaude.add(lblTipoSanguineo);
        String[] tiposSanguineos = {"Selecione...", "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-", "Não sabe"};
        comboTipoSanguineo = new JComboBox<>(tiposSanguineos);
        comboTipoSanguineo.setBounds(140, 465, 150, 25);
        panelSaude.add(comboTipoSanguineo);

        // --- PAINEL: SAÚDE MENTAL E USO DE SUBSTÂNCIAS ---
        JPanel panelSaudeMental = new JPanel();
        panelSaudeMental.setBorder(new TitledBorder("SAÚDE MENTAL E USO DE SUBSTÂNCIAS"));
        panelSaudeMental.setLayout(null);
        panelSaudeMental.setBounds(10, 1520, 740, 490);
        mainPanel.add(panelSaudeMental);
        // ... (código do painel de saúde mental) ...
        checkCaps = new JCheckBox("Possui vínculo com CAPS?");
        checkCaps.setBounds(20, 30, 200, 25);
        panelSaudeMental.add(checkCaps);
        JLabel lblCapsQual = new JLabel("Qual o nome e o município desse CAPS:");
        lblCapsQual.setBounds(230, 30, 280, 25);
        panelSaudeMental.add(lblCapsQual);
        txtCapsQual = new JTextField();
        txtCapsQual.setBounds(20, 55, 710, 25);
        txtCapsQual.setEnabled(false);
        panelSaudeMental.add(txtCapsQual);
        checkAcompanhamentoMental = new JCheckBox("Estava em acompanhamento em Saúde Mental no momento da prisão?");
        checkAcompanhamentoMental.setBounds(20, 90, 500, 25);
        panelSaudeMental.add(checkAcompanhamentoMental);
        JLabel lblAcompanhamentoMotivo = new JLabel("Qual o motivo?");
        lblAcompanhamentoMotivo.setBounds(20, 115, 120, 25);
        panelSaudeMental.add(lblAcompanhamentoMotivo);
        txtAcompanhamentoMotivo = new JTextField();
        txtAcompanhamentoMotivo.setBounds(140, 115, 590, 25);
        txtAcompanhamentoMotivo.setEnabled(false);
        panelSaudeMental.add(txtAcompanhamentoMotivo);
        JSeparator separatorMental1 = new JSeparator();
        separatorMental1.setBounds(10, 150, 720, 5);
        panelSaudeMental.add(separatorMental1);
        JLabel lblTranstorno = new JLabel("Possui transtorno mental:");
        lblTranstorno.setBounds(20, 160, 200, 25);
        panelSaudeMental.add(lblTranstorno);
        checkAnsiedade = new JCheckBox("Ansiedade"); checkAnsiedade.setBounds(30, 185, 120, 25); panelSaudeMental.add(checkAnsiedade);
        checkDepressao = new JCheckBox("Depressão"); checkDepressao.setBounds(160, 185, 120, 25); panelSaudeMental.add(checkDepressao);
        checkBipolaridade = new JCheckBox("Bipolaridade"); checkBipolaridade.setBounds(290, 185, 120, 25); panelSaudeMental.add(checkBipolaridade);
        checkEsquizofrenia = new JCheckBox("Esquizofrenia"); checkEsquizofrenia.setBounds(30, 210, 120, 25); panelSaudeMental.add(checkEsquizofrenia);
        checkAutismo = new JCheckBox("Autismo"); checkAutismo.setBounds(160, 210, 120, 25); panelSaudeMental.add(checkAutismo);
        JLabel lblTranstornoOutro = new JLabel("Outro:"); lblTranstornoOutro.setBounds(290, 210, 50, 25); panelSaudeMental.add(lblTranstornoOutro);
        txtTranstornoOutro = new JTextField(); txtTranstornoOutro.setBounds(340, 210, 150, 25); panelSaudeMental.add(txtTranstornoOutro);
        checkUsaMedicamentoControlado = new JCheckBox("Usa medicamento controlado:");
        checkUsaMedicamentoControlado.setBounds(500, 160, 230, 25);
        panelSaudeMental.add(checkUsaMedicamentoControlado);
        JLabel lblMedicamentoControladoQual = new JLabel("Qual?:");
        lblMedicamentoControladoQual.setBounds(510, 185, 50, 25);
        panelSaudeMental.add(lblMedicamentoControladoQual);
        txtMedicamentoControladoQual = new JTextField();
        txtMedicamentoControladoQual.setBounds(560, 185, 170, 25);
        txtMedicamentoControladoQual.setEnabled(false);
        panelSaudeMental.add(txtMedicamentoControladoQual);
        JSeparator separatorMental2 = new JSeparator();
        separatorMental2.setBounds(10, 245, 720, 5);
        panelSaudeMental.add(separatorMental2);
        JLabel lblUsoSubstancias = new JLabel("Faz uso de:");
        lblUsoSubstancias.setBounds(20, 255, 100, 25);
        panelSaudeMental.add(lblUsoSubstancias);
        checkUsoAlcool = new JCheckBox("Álcool"); checkUsoAlcool.setBounds(30, 280, 100, 25); panelSaudeMental.add(checkUsoAlcool);
        checkUsoCigarro = new JCheckBox("Cigarro"); checkUsoCigarro.setBounds(140, 280, 100, 25); panelSaudeMental.add(checkUsoCigarro);
        checkUsoMaconha = new JCheckBox("Maconha"); checkUsoMaconha.setBounds(250, 280, 100, 25); panelSaudeMental.add(checkUsoMaconha);
        checkUsoCrack = new JCheckBox("Crack"); checkUsoCrack.setBounds(360, 280, 100, 25); panelSaudeMental.add(checkUsoCrack);
        checkUsoCocaina = new JCheckBox("Cocaína"); checkUsoCocaina.setBounds(30, 305, 100, 25); panelSaudeMental.add(checkUsoCocaina);
        checkUsoAnfetaminas = new JCheckBox("Anfetaminas"); checkUsoAnfetaminas.setBounds(140, 305, 120, 25); panelSaudeMental.add(checkUsoAnfetaminas);
        checkUsoDrogasK = new JCheckBox("Drogas 'k'"); checkUsoDrogasK.setBounds(270, 305, 120, 25); panelSaudeMental.add(checkUsoDrogasK);
        JLabel lblUsoOutras = new JLabel("Outra(s):"); lblUsoOutras.setBounds(400, 305, 70, 25); panelSaudeMental.add(lblUsoOutras);
        txtUsoOutrasDrogas = new JTextField(); txtUsoOutrasDrogas.setBounds(470, 305, 260, 25); panelSaudeMental.add(txtUsoOutrasDrogas);
        checkTratamentoReducao = new JCheckBox("Já realizou algum tratamento para cessar ou reduzir o uso?");
        checkTratamentoReducao.setBounds(20, 340, 450, 25);
        panelSaudeMental.add(checkTratamentoReducao);
        JLabel lblTratamentoReducaoQual = new JLabel("De qual substância?");
        lblTratamentoReducaoQual.setBounds(480, 340, 150, 25);
        panelSaudeMental.add(lblTratamentoReducaoQual);
        txtTratamentoReducaoQual = new JTextField();
        txtTratamentoReducaoQual.setBounds(630, 340, 100, 25);
        txtTratamentoReducaoQual.setEnabled(false);
        panelSaudeMental.add(txtTratamentoReducaoQual);
        checkGostariaTratamento = new JCheckBox("Gostaria de realizar algum tratamento para cessar ou reduzir o uso?");
        checkGostariaTratamento.setBounds(20, 370, 450, 25);
        panelSaudeMental.add(checkGostariaTratamento);
        JLabel lblGostariaTratamentoQual = new JLabel("De qual substância?");
        lblGostariaTratamentoQual.setBounds(480, 370, 150, 25);
        panelSaudeMental.add(lblGostariaTratamentoQual);
        txtGostariaTratamentoQual = new JTextField();
        txtGostariaTratamentoQual.setBounds(630, 370, 100, 25);
        txtGostariaTratamentoQual.setEnabled(false);
        panelSaudeMental.add(txtGostariaTratamentoQual);
        JLabel lblEncaminhamentosMental = new JLabel("Encaminhamentos:");
        lblEncaminhamentosMental.setFont(new Font("Arial", Font.BOLD, 12));
        lblEncaminhamentosMental.setBounds(20, 405, 200, 25);
        panelSaudeMental.add(lblEncaminhamentosMental);
        checkEncaminhaPsicologia = new JCheckBox("Ofertar consulta com a psicologia"); checkEncaminhaPsicologia.setBounds(30, 430, 300, 25); panelSaudeMental.add(checkEncaminhaPsicologia);
        checkEncaminhaPsiquiatra = new JCheckBox("Ofertar consulta com psiquiatra"); checkEncaminhaPsiquiatra.setBounds(340, 430, 300, 25); panelSaudeMental.add(checkEncaminhaPsiquiatra);
        checkEncaminhaReceitas = new JCheckBox("Encaminhar para renovação de receitas"); checkEncaminhaReceitas.setBounds(30, 455, 300, 25); panelSaudeMental.add(checkEncaminhaReceitas);
        checkEncaminhaGruposApoio = new JCheckBox("Ofertar encaminhamento para grupos de apoio"); checkEncaminhaGruposApoio.setBounds(340, 455, 350, 25); panelSaudeMental.add(checkEncaminhaGruposApoio);

        // --- PAINEL DE SELEÇÃO DE SAÚDE ESPECÍFICA ---
        JPanel panelSelecao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSelecao.setBorder(new TitledBorder("SAÚDE ESPECÍFICA"));
        panelSelecao.setBounds(10, 2020, 740, 60);
        mainPanel.add(panelSelecao);

        JLabel lblSaude = new JLabel("Saúde:");
        panelSelecao.add(lblSaude);
        radioSaudeMulher = new JRadioButton("Mulher");
        radioSaudeHomem = new JRadioButton("Homem");
        groupSaudeEspecifica = new ButtonGroup();
        groupSaudeEspecifica.add(radioSaudeMulher);
        groupSaudeEspecifica.add(radioSaudeHomem);
        panelSelecao.add(radioSaudeMulher);
        panelSelecao.add(radioSaudeHomem);

        // --- CONTAINER DOS PAINÉIS DINÂMICOS ---
        JPanel panelContainerDinamico = new JPanel();
        CardLayout cardLayout = new CardLayout();
        panelContainerDinamico.setLayout(cardLayout);
        panelContainerDinamico.setBounds(10, 2090, 740, 350);
        mainPanel.add(panelContainerDinamico);

        // --- Painel Saúde da Mulher ---
        JPanel panelSaudeMulher = new JPanel();
        panelSaudeMulher.setBorder(new TitledBorder("SAÚDE DA MULHER"));
        panelSaudeMulher.setLayout(null);
        // ... (conteúdo do painel da mulher)
        JLabel lblGestacao = new JLabel("Gestação no momento:");
        lblGestacao.setBounds(20, 30, 150, 25);
        panelSaudeMulher.add(lblGestacao);
        radioGestacaoNaoSabe = new JRadioButton("Não sabe"); radioGestacaoNaoSabe.setBounds(180, 30, 100, 25); panelSaudeMulher.add(radioGestacaoNaoSabe);
        radioGestacaoNao = new JRadioButton("Não"); radioGestacaoNao.setBounds(280, 30, 60, 25); panelSaudeMulher.add(radioGestacaoNao);
        radioGestacaoSim = new JRadioButton("Sim"); radioGestacaoSim.setBounds(340, 30, 60, 25); panelSaudeMulher.add(radioGestacaoSim);
        ButtonGroup groupGestacao = new ButtonGroup();
        groupGestacao.add(radioGestacaoNaoSabe); groupGestacao.add(radioGestacaoNao); groupGestacao.add(radioGestacaoSim);
        JLabel lblIdadeGestacional = new JLabel("Qual a idade gestacional?");
        lblIdadeGestacional.setBounds(410, 30, 180, 25);
        panelSaudeMulher.add(lblIdadeGestacional);
        txtIdadeGestacional = new JTextField(); txtIdadeGestacional.setBounds(590, 30, 120, 25); txtIdadeGestacional.setEnabled(false); panelSaudeMulher.add(txtIdadeGestacional);
        JLabel lblContraceptivo = new JLabel("Faz uso de método contraceptivo?"); lblContraceptivo.setBounds(20, 65, 250, 25); panelSaudeMulher.add(lblContraceptivo);
        checkContraNao = new JCheckBox("Não"); checkContraNao.setBounds(30, 90, 60, 25); panelSaudeMulher.add(checkContraNao);
        checkContraOral = new JCheckBox("Anticoncepcional oral"); checkContraOral.setBounds(100, 90, 200, 25); panelSaudeMulher.add(checkContraOral);
        checkContraDIU = new JCheckBox("DIU/implante"); checkContraDIU.setBounds(310, 90, 150, 25); panelSaudeMulher.add(checkContraDIU);
        checkContraInjetavel = new JCheckBox("Anticoncepcional injetável"); checkContraInjetavel.setBounds(100, 115, 200, 25); panelSaudeMulher.add(checkContraInjetavel);
        checkContraLigadura = new JCheckBox("Ligadura de trompas"); checkContraLigadura.setBounds(470, 90, 200, 25); panelSaudeMulher.add(checkContraLigadura);
        checkContraHisterectomia = new JCheckBox("Histerectomia"); checkContraHisterectomia.setBounds(470, 115, 200, 25); panelSaudeMulher.add(checkContraHisterectomia);
        checkPapanicolau = new JCheckBox("Já realizou exame preventivo/Papanicolau?"); checkPapanicolau.setBounds(20, 150, 350, 25); panelSaudeMulher.add(checkPapanicolau);
        JLabel lblPapanicolauAno = new JLabel("Qual foi o ano?"); lblPapanicolauAno.setBounds(380, 150, 120, 25); panelSaudeMulher.add(lblPapanicolauAno);
        txtPapanicolauAno = new JTextField(); txtPapanicolauAno.setBounds(500, 150, 80, 25); txtPapanicolauAno.setEnabled(false); panelSaudeMulher.add(txtPapanicolauAno);
        JLabel lblEncaminhamentosMulher = new JLabel("Encaminhamentos:"); lblEncaminhamentosMulher.setFont(new Font("Arial", Font.BOLD, 12)); lblEncaminhamentosMulher.setBounds(20, 185, 200, 25); panelSaudeMulher.add(lblEncaminhamentosMulher);
        checkEncaminhaContraceptivo = new JCheckBox("Ofertar continuidade ou início do uso de método contraceptivo"); checkEncaminhaContraceptivo.setBounds(30, 210, 500, 25); panelSaudeMulher.add(checkEncaminhaContraceptivo);
        checkEncaminhaPreventivo = new JCheckBox("Ofertar consulta para realizar exame preventivo"); checkEncaminhaPreventivo.setBounds(30, 235, 500, 25); panelSaudeMulher.add(checkEncaminhaPreventivo);
        checkEncaminhaPreNatal = new JCheckBox("Encaminhar para pré-natal"); checkEncaminhaPreNatal.setBounds(30, 260, 500, 25); checkEncaminhaPreNatal.setEnabled(false); panelSaudeMulher.add(checkEncaminhaPreNatal);


        // --- Painel Saúde do Homem ---
        JPanel panelSaudeHomem = new JPanel();
        panelSaudeHomem.setBorder(new TitledBorder("SAÚDE DO HOMEM"));
        panelSaudeHomem.setLayout(null);
        // ... (código do painel do homem)
        checkExameProstata = new JCheckBox("Já realizou exame preventivo para o câncer de próstata?");
        checkExameProstata.setBounds(20, 30, 400, 25);
        panelSaudeHomem.add(checkExameProstata);
        JLabel lblExameProstataAno = new JLabel("Qual foi o ano?");
        lblExameProstataAno.setBounds(430, 30, 120, 25);
        panelSaudeHomem.add(lblExameProstataAno);
        txtExameProstataAno = new JTextField();
        txtExameProstataAno.setBounds(550, 30, 80, 25);
        txtExameProstataAno.setEnabled(false);
        panelSaudeHomem.add(txtExameProstataAno);
        checkHistFamiliarProstata = new JCheckBox("Possui histórico de câncer de próstata na família?");
        checkHistFamiliarProstata.setBounds(20, 65, 400, 25);
        panelSaudeHomem.add(checkHistFamiliarProstata);
        JLabel lblHistFamiliarProstataQual = new JLabel("Qual foi o familiar?");
        lblHistFamiliarProstataQual.setBounds(430, 65, 120, 25);
        panelSaudeHomem.add(lblHistFamiliarProstataQual);
        txtHistFamiliarProstataQual = new JTextField();
        txtHistFamiliarProstataQual.setBounds(550, 65, 160, 25);
        txtHistFamiliarProstataQual.setEnabled(false);
        panelSaudeHomem.add(txtHistFamiliarProstataQual);
        checkVasectomia = new JCheckBox("Você realizou vasectomia?");
        checkVasectomia.setBounds(20, 100, 400, 25);
        panelSaudeHomem.add(checkVasectomia);
        checkParceiraGestante = new JCheckBox("Sua parceira está gestante?");
        checkParceiraGestante.setBounds(20, 135, 250, 25);
        panelSaudeHomem.add(checkParceiraGestante);
        checkParticipaPreNatal = new JCheckBox("Você está participando do pré-natal?");
        checkParticipaPreNatal.setBounds(280, 135, 300, 25);
        checkParticipaPreNatal.setEnabled(false);
        panelSaudeHomem.add(checkParticipaPreNatal);
        JLabel lblEncaminhamentosHomem = new JLabel("Encaminhamentos:");
        lblEncaminhamentosHomem.setFont(new Font("Arial", Font.BOLD, 12));
        lblEncaminhamentosHomem.setBounds(20, 170, 200, 25);
        panelSaudeHomem.add(lblEncaminhamentosHomem);
        checkEncaminhaVasectomia = new JCheckBox("Ofertar encaminhamento para realizar vasectomia");
        checkEncaminhaVasectomia.setBounds(30, 195, 500, 25);
        panelSaudeHomem.add(checkEncaminhaVasectomia);
        checkEncaminhaPreNatalParceiro = new JCheckBox("Encaminhar para realizar pré-natal do parceiro");
        checkEncaminhaPreNatalParceiro.setBounds(30, 220, 500, 25);
        checkEncaminhaPreNatalParceiro.setEnabled(false);
        panelSaudeHomem.add(checkEncaminhaPreNatalParceiro);

        panelContainerDinamico.add(new JPanel(), "VAZIO");
        panelContainerDinamico.add(panelSaudeMulher, "MULHER");
        panelContainerDinamico.add(panelSaudeHomem, "HOMEM");

        // --- Lógica de controle ---
        radioSaudeMulher.addActionListener(e -> cardLayout.show(panelContainerDinamico, "MULHER"));
        radioSaudeHomem.addActionListener(e -> cardLayout.show(panelContainerDinamico, "HOMEM"));

        checkCaps.addActionListener(e -> txtCapsQual.setEnabled(checkCaps.isSelected()));
        checkAcompanhamentoMental.addActionListener(e -> txtAcompanhamentoMotivo.setEnabled(checkAcompanhamentoMental.isSelected()));
        checkUsaMedicamentoControlado.addActionListener(e -> txtMedicamentoControladoQual.setEnabled(checkUsaMedicamentoControlado.isSelected()));
        checkTratamentoReducao.addActionListener(e -> txtTratamentoReducaoQual.setEnabled(checkTratamentoReducao.isSelected()));
        checkGostariaTratamento.addActionListener(e -> txtGostariaTratamentoQual.setEnabled(checkGostariaTratamento.isSelected()));
        radioGestacaoSim.addActionListener(e -> {
            txtIdadeGestacional.setEnabled(true);
            checkEncaminhaPreNatal.setEnabled(true);
        });
        radioGestacaoNao.addActionListener(e -> {
            txtIdadeGestacional.setEnabled(false);
            checkEncaminhaPreNatal.setEnabled(false);
        });
        radioGestacaoNaoSabe.addActionListener(e -> {
            txtIdadeGestacional.setEnabled(false);
            checkEncaminhaPreNatal.setEnabled(false);
        });
        checkPapanicolau.addActionListener(e -> txtPapanicolauAno.setEnabled(checkPapanicolau.isSelected()));
        checkContraNao.addActionListener(e -> {
            boolean selecionado = checkContraNao.isSelected();
            checkContraOral.setEnabled(!selecionado);
            checkContraDIU.setEnabled(!selecionado);
            checkContraInjetavel.setEnabled(!selecionado);
            checkContraLigadura.setEnabled(!selecionado);
            checkContraHisterectomia.setEnabled(!selecionado);
        });
        checkExameProstata.addActionListener(e -> txtExameProstataAno.setEnabled(checkExameProstata.isSelected()));
        checkHistFamiliarProstata.addActionListener(e -> txtHistFamiliarProstataQual.setEnabled(checkHistFamiliarProstata.isSelected()));
        checkParceiraGestante.addActionListener(e -> {
            boolean selecionado = checkParceiraGestante.isSelected();
            checkParticipaPreNatal.setEnabled(selecionado);
            checkEncaminhaPreNatalParceiro.setEnabled(selecionado);
        });

        btnSalvar.addActionListener(e -> saveData());
        btnCancelar.addActionListener(e -> confirmarCancelamento());

        treatmentController.populateFields();
    }

    private void confirmarCancelamento() {
        int resposta = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja cancelar?", "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private void saveData() {
        JOptionPane.showMessageDialog(this, "Lógica de salvamento a ser implementada.");
    }
}