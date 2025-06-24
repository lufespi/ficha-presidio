package view;
import entities.*;
import service.*;
import view.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter; // Import necessário para a máscara
import java.awt.*;
import java.text.ParseException; // Import necessário para a exceção da máscara
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Tela de tratamento (Cadastro/Edição).
 * (Adicionada validação completa de CPF)
 */
public class JFtreatment extends JFrame {

    // --- Componentes da Interface ---
    // Alterado para JFormattedTextField
    private JFormattedTextField txtCPF;
    private JTextField txtNomeCompleto, txtDataEntrada, txtDataNasc, txtNomeSocial,
            txtIdade, txtPais, txtMae, txtPai, txtProcedencia;
    private JRadioButton radioTransfNao, radioTransfSim, radioBrasileira, radioNaturalizado,
            radioEstrangeiro, radioSolteiro, radioCasado, radioUniao;
    private ButtonGroup groupTransferencia, groupNacionalidade, groupEC;
    private JComboBox<String> comboRaca, comboSexo, comboGenero, comboOrientacao;
    private JCheckBox checkPaiDesconhecido;

    // --- Controle de Dados e Referência ---
    private Preso presoAtual;
    private JFhome telaHome;

    public JFtreatment(JFhome telaHome, String operador, Preso presoParaEditar) {
        this.telaHome = telaHome;
        this.presoAtual = presoParaEditar;

        setSize(775, 840);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                confirmarCancelamento();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(760, 800));
        scrollPane.setViewportView(mainPanel);

        // --- DADOS AUTOMÁTICOS ---
        LocalDate dataAtual = LocalDate.now();
        LocalTime horaAtual = LocalTime.now();
        DateTimeFormatter formatadorData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatadorHora = DateTimeFormatter.ofPattern("HH:mm");

        // PAINEL DE ATENDIMENTO
        JPanel panelAtendimento = new JPanel();
        panelAtendimento.setBorder(new TitledBorder("ATENDIMENTO"));
        panelAtendimento.setLayout(null);
        panelAtendimento.setBounds(10, 10, 750, 140);
        mainPanel.add(panelAtendimento);
        JLabel lblResponsavel = new JLabel("Responsável pelo atendimento:");
        lblResponsavel.setBounds(20, 30, 200, 25);
        panelAtendimento.add(lblResponsavel);
        JLabel lblResponsavelValor = new JLabel(operador);
        lblResponsavelValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblResponsavelValor.setBounds(220, 30, 200, 25);
        panelAtendimento.add(lblResponsavelValor);
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(20, 60, 40, 25);
        panelAtendimento.add(lblData);
        JLabel lblDataValor = new JLabel(dataAtual.format(formatadorData));
        lblDataValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblDataValor.setBounds(60, 60, 100, 25);
        panelAtendimento.add(lblDataValor);
        JLabel lblHorario = new JLabel("Horário:");
        lblHorario.setBounds(180, 60, 60, 25);
        panelAtendimento.add(lblHorario);
        JLabel lblHorarioValor = new JLabel(horaAtual.format(formatadorHora));
        lblHorarioValor.setFont(new Font("Arial", Font.BOLD, 12));
        lblHorarioValor.setBounds(240, 60, 100, 25);
        panelAtendimento.add(lblHorarioValor);
        JLabel lblDataEntrada = new JLabel("Data de entrada na Unidade Prisional:");
        lblDataEntrada.setBounds(380, 60, 250, 25);
        panelAtendimento.add(lblDataEntrada);
        txtDataEntrada = new JTextField();
        txtDataEntrada.setBounds(630, 60, 100, 25);
        panelAtendimento.add(txtDataEntrada);
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
        txtProcedencia = new JTextField();
        txtProcedencia.setBounds(590, 95, 140, 25);
        txtProcedencia.setEnabled(false);
        panelAtendimento.add(txtProcedencia);
        radioTransfSim.addActionListener(e -> txtProcedencia.setEnabled(true));
        radioTransfNao.addActionListener(e -> txtProcedencia.setEnabled(false));

        // PAINEL DE IDENTIFICAÇÃO
        JPanel panelIdentificacao = new JPanel();
        panelIdentificacao.setBorder(new TitledBorder("IDENTIFICAÇÃO"));
        panelIdentificacao.setLayout(null);
        panelIdentificacao.setBounds(10, 160, 750, 280);
        mainPanel.add(panelIdentificacao);

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

        // --- NOVO CAMPO DE CPF COM MÁSCARA ---
        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(270, 100, 40, 25);
        panelIdentificacao.add(lblCPF);
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            txtCPF = new JFormattedTextField(mascaraCpf);
        } catch (ParseException ex) {
            // Em caso de erro na máscara, usa um campo de texto normal como fallback
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
        txtPais = new JTextField();
        txtPais.setBounds(460, 135, 270, 25);
        txtPais.setEnabled(false);
        panelIdentificacao.add(txtPais);
        groupNacionalidade = new ButtonGroup();
        groupNacionalidade.add(radioBrasileira);
        groupNacionalidade.add(radioNaturalizado);
        groupNacionalidade.add(radioEstrangeiro);
        radioEstrangeiro.addActionListener(e -> txtPais.setEnabled(true));
        radioBrasileira.addActionListener(e -> { txtPais.setEnabled(false); txtPais.setText(""); });
        radioNaturalizado.addActionListener(e -> { txtPais.setEnabled(false); txtPais.setText(""); });
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

        // PAINEL AUTODECLARADO
        JPanel panelAutodeclarado = new JPanel();
        panelAutodeclarado.setBorder(new TitledBorder("INFORMAÇÕES AUTODECLARADAS"));
        panelAutodeclarado.setLayout(null);
        panelAutodeclarado.setBounds(10, 450, 750, 200);
        mainPanel.add(panelAutodeclarado);

        String[] racas = {"Selecione...", "Branco(a)", "Preto(a)", "Pardo(a)", "Amarelo(a)", "Indígena", "Não desejo informar"};
        String[] sexos = {"Selecione...", "Feminino", "Masculino", "Intersexo", "Não desejo informar"};
        String[] generos = {"Selecione...", "Mulher (cisgênero)", "Homem (cisgênero)", "Mulher (transgênero)", "Homem (transgênero)", "Travesti", "Não-binário", "Não desejo informar"};
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

        // BOTÕES DE AÇÃO
        JButton btnSalvar = new JButton("Salvar Cadastro");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setBounds(200, 670, 180, 40);
        mainPanel.add(btnSalvar);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCancelar.setBounds(420, 670, 180, 40);
        mainPanel.add(btnCancelar);

        btnCancelar.addActionListener(e -> confirmarCancelamento());
        btnSalvar.addActionListener(e -> salvarDados());

        popularFormulario();
    }

    private void popularFormulario() {
        if (presoAtual != null) {
            setTitle("Editar Cadastro de Preso");
            txtNomeCompleto.setText(presoAtual.getNomeCompleto());
            txtNomeSocial.setText(presoAtual.getNomeSocial());
            txtCPF.setValue(presoAtual.getCpf()); // Usar setValue para JFormattedTextField
            txtDataNasc.setText(presoAtual.getDataNascimento());
            txtDataEntrada.setText(presoAtual.getDataEntrada());
            txtMae.setText(presoAtual.getNomeMae());
            txtPai.setText(presoAtual.getNomePai());

            switch (presoAtual.getNacionalidade() != null ? presoAtual.getNacionalidade() : "") {
                case "Brasileira": radioBrasileira.setSelected(true); break;
                case "Naturalizado": radioNaturalizado.setSelected(true); break;
                case "Estrangeiro": radioEstrangeiro.setSelected(true); break;
            }

            switch (presoAtual.getEstadoCivil() != null ? presoAtual.getEstadoCivil() : "") {
                case "Solteiro": radioSolteiro.setSelected(true); break;
                case "Casado": radioCasado.setSelected(true); break;
                case "União Estável": radioUniao.setSelected(true); break;
            }

            comboRaca.setSelectedItem(presoAtual.getRaca());
            comboSexo.setSelectedItem(presoAtual.getSexo());
            comboGenero.setSelectedItem(presoAtual.getIdentidadeGenero());
            comboOrientacao.setSelectedItem(presoAtual.getOrientacaoSexual());
        } else {
            setTitle("Novo Cadastro de Preso");
        }
    }

    private void salvarDados() {
        if (!validarCampos()) {
            return;
        }

        if (presoAtual == null) {
            presoAtual = new Preso();
        }

        presoAtual.setNomeCompleto(txtNomeCompleto.getText().trim());
        presoAtual.setNomeSocial(txtNomeSocial.getText().trim());
        presoAtual.setCpf((String) txtCPF.getValue()); // Usar getValue para JFormattedTextField
        presoAtual.setDataNascimento(txtDataNasc.getText().trim());
        presoAtual.setDataEntrada(txtDataEntrada.getText().trim());
        presoAtual.setNomeMae(txtMae.getText().trim());
        presoAtual.setNomePai(checkPaiDesconhecido.isSelected() ? "Desconhecido" : txtPai.getText().trim());
        presoAtual.setNacionalidade(getSelectedButtonText(groupNacionalidade));
        presoAtual.setEstadoCivil(getSelectedButtonText(groupEC));

        presoAtual.setRaca((String) comboRaca.getSelectedItem());
        presoAtual.setSexo((String) comboSexo.getSelectedItem());
        presoAtual.setIdentidadeGenero((String) comboGenero.getSelectedItem());
        presoAtual.setOrientacaoSexual((String) comboOrientacao.getSelectedItem());

        PresoDAO.salvar(presoAtual);
        JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
        telaHome.carregarTabela(null);
        this.dispose();
    }

    private void confirmarCancelamento() {
        int resposta = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja cancelar? As informações não salvas serão perdidas.",
                "Confirmar Cancelamento", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private boolean validarCampos() {
        List<String> erros = new ArrayList<>();
        if (txtNomeCompleto.getText().trim().isEmpty()) erros.add("• Nome Completo");

        // --- VALIDAÇÃO DO CPF ATUALIZADA ---
        String cpf = (String) txtCPF.getValue(); // Pega o valor do campo formatado
        // A máscara pode deixar o campo com placeholder, então precisamos verificar se está preenchido
        if (cpf == null || cpf.contains("_")) {
            erros.add("• CPF (incompleto)");
        } else if (!isCPFValido(cpf)) {
            erros.add("• CPF (inválido)");
        }

        if (txtDataNasc.getText().trim().isEmpty()) erros.add("• Data de Nascimento");
        if (txtDataEntrada.getText().trim().isEmpty()) erros.add("• Data de Entrada na Unidade");
        if (groupTransferencia.getSelection() == null) erros.add("• Transferência de outra Unidade");
        if (groupNacionalidade.getSelection() == null) erros.add("• Nacionalidade");
        if (groupEC.getSelection() == null) erros.add("• Estado Civil");
        if (comboRaca.getSelectedIndex() <= 0) erros.add("• Raça/Cor");
        if (comboSexo.getSelectedIndex() <= 0) erros.add("• Sexo Biológico");
        if (comboGenero.getSelectedIndex() <= 0) erros.add("• Identidade de Gênero");
        if (comboOrientacao.getSelectedIndex() <= 0) erros.add("• Orientação Sexual");

        if (erros.isEmpty()) {
            return true;
        } else {
            StringBuilder mensagemErro = new StringBuilder("Os seguintes campos são obrigatórios ou inválidos:\n\n");
            for (String erro : erros) {
                mensagemErro.append(erro).append("\n");
            }
            JOptionPane.showMessageDialog(this, mensagemErro.toString(), "Campos Obrigatórios", JOptionPane.WARNING_MESSAGE);
            return false;
        }
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

    /**
     * Valida um CPF.
     * @param cpf O CPF como String. Pode conter pontos e traço.
     * @return true se o CPF for válido, false caso contrário.
     */
    private static boolean isCPFValido(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o tamanho é 11
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (ex: 111.111.111-11), o que é inválido
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // --- Cálculo do 1º dígito verificador ---
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (Character.getNumericValue(cpf.charAt(i)) * (10 - i));
            }
            int resto = soma % 11;
            int digitoVerificador1 = (resto < 2) ? 0 : 11 - resto;

            // Verifica se o 1º dígito verificador está correto
            if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
                return false;
            }

            // --- Cálculo do 2º dígito verificador ---
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (Character.getNumericValue(cpf.charAt(i)) * (11 - i));
            }
            resto = soma % 11;
            int digitoVerificador2 = (resto < 2) ? 0 : 11 - resto;

            // Verifica se o 2º dígito verificador está correto
            if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        // Se passou por todas as verificações, o CPF é válido
        return true;
    }
}