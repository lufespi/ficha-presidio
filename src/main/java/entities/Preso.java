package entities;

// entities.Preso.java
public class Preso {
    private Integer id;
    private String nomeCompleto;
    private String nomeSocial;
    private String cpf;
    private String dataNascimento;
    private String nomeMae;
    private String nomePai;
    private String estadoCivil; // <-- CAMPO ADICIONADO DE VOLTA
    private String nacionalidade;
    private String dataEntrada;
    private String raca;
    private String sexo;
    private String identidadeGenero;
    private String orientacaoSexual;

    public Preso() {}

    // Getters e Setters (com estadoCivil)
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getNomeSocial() { return nomeSocial; }
    public void setNomeSocial(String nomeSocial) { this.nomeSocial = nomeSocial; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNomeMae() { return nomeMae; }
    public void setNomeMae(String nomeMae) { this.nomeMae = nomeMae; }

    public String getNomePai() { return nomePai; }
    public void setNomePai(String nomePai) { this.nomePai = nomePai; }

    public String getEstadoCivil() { return estadoCivil; } // <-- MÉTODO ADICIONADO DE VOLTA
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; } // <-- MÉTODO ADICIONADO DE VOLTA

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(String dataEntrada) { this.dataEntrada = dataEntrada; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getIdentidadeGenero() { return identidadeGenero; }
    public void setIdentidadeGenero(String identidadeGenero) { this.identidadeGenero = identidadeGenero; }

    public String getOrientacaoSexual() { return orientacaoSexual; }
    public void setOrientacaoSexual(String orientacaoSexual) { this.orientacaoSexual = orientacaoSexual; }
}