package br.gov.go.saude.silt.corp.pessoa_fisica.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoCNH;
import br.gov.go.saude.silt.corp.deficiencia.entidade.Deficiencia;
import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.corp.estado_civil.entidade.EstadoCivil;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.pais.entidade.Pais;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.enumerador.SexoEnum;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa PessoaFisica
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 302 $ $Author: claudiocosta $ $Date: 2014-02-11 18:19:03 -0200 (Ter, 11 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "pessoas_fisicas", schema = "corp")
public class PessoaFisica extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pfis_iden")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codg_paciente")
	private String codigoPaciente;

	@Type(type = "true_false")
	private boolean exclusao;

	@Column(name = "marca_de_origem")
	private String marcaDeOrigem;

	@Column(name = "pfis_cartorio_cert_nasc")
	private String cartorioCertidaoNascimento;

	@Column(name = "pfis_cartorio_cert_obit")
	private String cartorioCertidaoObito;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_data_cadastro")
	private Date dataCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_data_exped_rg")
	private Date dataExpedicaoRg;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_data_nasc", nullable = false)
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_data_primeira_cnh")
	private Date dataPrimeiraCnh;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_data_validade_cnh")
	private Date dataValidadeCnh;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_dataemissao_cert_nasc")
	private Date dataEmissaoCertidaoNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_dataemissao_cert_obit")
	private Date dataEmissaoCertidaoObito;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_dataemissao_nit")
	private Date dataEmissaoNit;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_dataemissao_reservista")
	private Date dataEmissaoReservista;

	@Temporal(TemporalType.DATE)
	@Column(name = "pfis_dataemissao_titulo_eleitor")
	private Date dataEmissaoTituloEleitor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_defi_id")
	private Deficiencia deficiencia;

	@Column(name = "pfis_folha_cert_nasc")
	private String folhaCertidaoNascimento;

	@Column(name = "pfis_folha_cert_obit")
	private String folhaCertidaoObito;

	@Column(name = "pfis_gins_codg")
	private BigDecimal ginsCodg;

	@Column(name = "pfis_indi_doador_orgao", nullable = false)
	private String indicadorDoadorOrgao;

	@Column(name = "pfis_indi_doador_sangue")
	private String indicadorDoadorSangue;

	@Column(name = "pfis_livro_cert_nasc")
	private String livroCertidaoNascimento;

	@Column(name = "pfis_livro_cert_obit")
	private String livroCertidaoObito;

	@Column(name = "pfis_nit")
	private BigDecimal nit;

	@Column(name = "pfis_nome", nullable = false)
	private String nome;

	@Column(name = "pfis_nome_mae", nullable = false)
	private String nomeMae;

	@Column(name = "pfis_nome_pai")
	private String nomePai;

	@Column(name = "pfis_numero_cert_nasc")
	private String numeroCertidaoNascimento;

	@Column(name = "pfis_numero_cert_obit")
	private String numeroCertidaoObito;

	@Column(name = "pfis_numero_cnh")
	private String numeroCnh;

	@Column(name = "pfis_numero_reservista")
	private BigDecimal numeroReservista;

	@Column(name = "pfis_numero_titulo_eleitor")
	private BigDecimal numeroTituloEleitor;

	@Column(name = "pfis_numr_ano_chegada_brasil")
	private BigDecimal numeroAnoChegadaBrasil;

	@Column(name = "pfis_numr_cpf")
	private Long cpf;

	@Column(name = "pfis_numr_matricula_base")
	private BigDecimal numeroMatriculaBase;

	@Column(name = "pfis_numr_rg")
	private String numeroRg;

	@Column(name = "pfis_secao_titulo_eleitor")
	private BigDecimal secaoTituloEleitor;

	@Column(name = "pfis_sigl_orgao_rg")
	private String siglaOrgaoRg;

	@Column(name = "pfis_sigl_sexo", length = 1)
	private String siglaSexo;

	@Transient
	private SexoEnum sexo;

	@Transient
	private EnumTipoCNH tipoCNH;

	@Column(name = "pfis_tipo_cnh_domi_id")
	private Long idTipoCNH;

	@Column(name = "pfis_tipo_fator_rh")
	private String tipoFatorRh;

	@Column(name = "pfis_tipo_sanguineo")
	private String tipoSanguineo;

	@Column(name = "pfis_ufed_titulo_eleitor")
	private String unidadeFederacaoTituloEleitor;

	@Column(name = "pfis_zona_titulo_eleitor")
	private BigDecimal zonaTituloEleitor;

	// bi-directional many-to-one association to Escolaridade
	@ManyToOne
	@JoinColumn(name = "pfis_tipo_grau_instrucao")
	private Escolaridade escolaridade;

	// bi-directional many-to-one association to Estadocivil
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_tipo_estado_civil")
	private EstadoCivil estadoCivil;

	// bi-directional many-to-one association to Municipio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_muni_codg_naturalidade")
	private Municipio municipioNaturalidade;

	// bi-directional many-to-one association to Municipio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_chvmnc_cert_nasc")
	private Municipio municipioNascimento;

	// bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "pfis_chvmnc_cert_obit")
	private Municipio municipioCertObito;

	// bi-directional many-to-one association to Pais
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_pais_codg_nascimento")
	private Pais paisNascimento;

	// bi-directional many-to-one association to Pais
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_pais_codg_nacionalidade")
	private Pais paisNacionalidade;

	// bi-directional many-to-one association to RacasCore
	@ManyToOne
	@JoinColumn(name = "pfis_raca_id")
	private RacaCor racaCor;

	// bi-directional many-to-one association to UnidadesFederativa
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pfis_ufed_sigl_rg")
	private UnidadeFederativa unidadeFederativa;

	@Transient
	private Pessoa pessoa;

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public PessoaFisica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public boolean isExclusao() {
		return exclusao;
	}

	public void setExclusao(boolean exclusao) {
		this.exclusao = exclusao;
	}

	public String getMarcaDeOrigem() {
		return marcaDeOrigem;
	}

	public void setMarcaDeOrigem(String marcaDeOrigem) {
		this.marcaDeOrigem = marcaDeOrigem;
	}

	public String getCartorioCertidaoNascimento() {
		return cartorioCertidaoNascimento;
	}

	public void setCartorioCertidaoNascimento(String cartorioCertidaoNascimento) {
		this.cartorioCertidaoNascimento = cartorioCertidaoNascimento;
	}

	public String getCartorioCertaoObito() {
		return cartorioCertidaoObito;
	}

	public void setCartorioCertaoObito(String cartorioCertaoObito) {
		this.cartorioCertidaoObito = cartorioCertaoObito;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataExpedicaoRg() {
		return dataExpedicaoRg;
	}

	public void setDataExpedicaoRg(Date dataExpedicaoRg) {
		this.dataExpedicaoRg = dataExpedicaoRg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataPrimeiraCnh() {
		return dataPrimeiraCnh;
	}

	public void setDataPrimeiraCnh(Date dataPrimeiraCnh) {
		this.dataPrimeiraCnh = dataPrimeiraCnh;
	}

	public Date getDataValidadeCnh() {
		return dataValidadeCnh;
	}

	public void setDataValidadeCnh(Date dataValidadeCnh) {
		this.dataValidadeCnh = dataValidadeCnh;
	}

	public Date getDataEmissaoCertidaoNascimento() {
		return dataEmissaoCertidaoNascimento;
	}

	public void setDataEmissaoCertidaoNascimento(Date dataEmissaoCertidaoNascimento) {
		this.dataEmissaoCertidaoNascimento = dataEmissaoCertidaoNascimento;
	}

	public Date getDataEmissaoCertidaoObito() {
		return dataEmissaoCertidaoObito;
	}

	public void setDataEmissaoCertidaoObito(Date dataEmissaoCertidaoObito) {
		this.dataEmissaoCertidaoObito = dataEmissaoCertidaoObito;
	}

	public Date getDataEmissaoNit() {
		return dataEmissaoNit;
	}

	public void setDataEmissaoNit(Date dataEmissaoNit) {
		this.dataEmissaoNit = dataEmissaoNit;
	}

	public Date getDataEmissaoReservista() {
		return dataEmissaoReservista;
	}

	public void setDataEmissaoReservista(Date dataEmissaoReservista) {
		this.dataEmissaoReservista = dataEmissaoReservista;
	}

	public Date getDataEmissaoTituloEleitor() {
		return dataEmissaoTituloEleitor;
	}

	public void setDataEmissaoTituloEleitor(Date dataEmissaoTituloEleitor) {
		this.dataEmissaoTituloEleitor = dataEmissaoTituloEleitor;
	}

	public Deficiencia getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}

	public String getFolhaCertidaoNascimento() {
		return folhaCertidaoNascimento;
	}

	public void setFolhaCertidaoNascimento(String folhaCertidaoNascimento) {
		this.folhaCertidaoNascimento = folhaCertidaoNascimento;
	}

	public String getFolhaCertidaoObito() {
		return folhaCertidaoObito;
	}

	public void setFolhaCertidaoObito(String folhaCertidaoObito) {
		this.folhaCertidaoObito = folhaCertidaoObito;
	}

	public BigDecimal getGinsCodg() {
		return ginsCodg;
	}

	public void setGinsCodg(BigDecimal ginsCodg) {
		this.ginsCodg = ginsCodg;
	}

	public String getIndicadorDoadorOrgao() {
		return indicadorDoadorOrgao;
	}

	public void setIndicadorDoadorOrgao(String indicadorDoadorOrgao) {
		this.indicadorDoadorOrgao = indicadorDoadorOrgao;
	}

	public String getIndicadorDoadorSangue() {
		return indicadorDoadorSangue;
	}

	public void setIndicadorDoadorSangue(String indicadorDoadorSangue) {
		this.indicadorDoadorSangue = indicadorDoadorSangue;
	}

	public String getLivroCertidaoNascimento() {
		return livroCertidaoNascimento;
	}

	public void setLivroCertidaoNascimento(String livroCertidaoNascimento) {
		this.livroCertidaoNascimento = livroCertidaoNascimento;
	}

	public String getLivroCertidaoObito() {
		return livroCertidaoObito;
	}

	public void setLivroCertidaoObito(String livroCertidaoObito) {
		this.livroCertidaoObito = livroCertidaoObito;
	}

	public BigDecimal getNit() {
		return nit;
	}

	public void setNit(BigDecimal nit) {
		this.nit = nit;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNumeroCertidaoNascimento() {
		return numeroCertidaoNascimento;
	}

	public void setNumeroCertidaoNascimento(String numeroCertidaoNascimento) {
		this.numeroCertidaoNascimento = numeroCertidaoNascimento;
	}

	public String getNumeroCertidaoObito() {
		return numeroCertidaoObito;
	}

	public void setNumeroCertidaoObito(String numeroCertidaoObito) {
		this.numeroCertidaoObito = numeroCertidaoObito;
	}

	public String getNumeroCnh() {
		return numeroCnh;
	}

	public void setNumeroCnh(String numeroCnh) {
		this.numeroCnh = numeroCnh;
	}

	public BigDecimal getNumeroReservista() {
		return numeroReservista;
	}

	public void setNumeroReservista(BigDecimal numeroReservista) {
		this.numeroReservista = numeroReservista;
	}

	public BigDecimal getNumeroTituloEleitor() {
		return numeroTituloEleitor;
	}

	public void setNumeroTituloEleitor(BigDecimal numeroTituloEleitor) {
		this.numeroTituloEleitor = numeroTituloEleitor;
	}

	public BigDecimal getNumrAnoChegadaBrasil() {
		return numeroAnoChegadaBrasil;
	}

	public void setNumrAnoChegadaBrasil(BigDecimal numrAnoChegadaBrasil) {
		this.numeroAnoChegadaBrasil = numrAnoChegadaBrasil;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getNumerMatriculaBase() {
		return numeroMatriculaBase;
	}

	public void setNumerMatriculaBase(BigDecimal numerMatriculaBase) {
		this.numeroMatriculaBase = numerMatriculaBase;
	}

	public String getNumeroRg() {
		return numeroRg;
	}

	public void setNumeroRg(String numeroRg) {
		this.numeroRg = numeroRg;
	}

	public BigDecimal getSecaoTituloEleitor() {
		return secaoTituloEleitor;
	}

	public void setSecaoTituloEleitor(BigDecimal secaoTituloEleitor) {
		this.secaoTituloEleitor = secaoTituloEleitor;
	}

	public String getSiglaOrgaoRg() {
		return siglaOrgaoRg;
	}

	public void setSiglaOrgaoRg(String siglaOrgaoRg) {
		this.siglaOrgaoRg = siglaOrgaoRg;
	}

	public String getSiglaSexo() {
		if (siglaSexo == null && sexo != null) {
			siglaSexo = sexo.getSigla();
		}
		return siglaSexo;
	}

	public void setSiglaSexo(String siglaSexo) {
		this.siglaSexo = siglaSexo;
		if (sexo == null || (sexo.getSigla() != null && !sexo.getSigla().equals(siglaSexo))) {
			for (SexoEnum sexoEnum : SexoEnum.values())
				if (sexoEnum.getSigla().equals(siglaSexo)) {
					sexo = sexoEnum;
					break;
				}
		}
	}

	public SexoEnum getSexo() {
		if (sexo != null) {
			siglaSexo = sexo.getSigla();
		} else if (siglaSexo != null) {
			for (SexoEnum sexoEnum : SexoEnum.values())
				if (sexoEnum.getSigla().equals(siglaSexo)) {
					sexo = sexoEnum;
					break;
				}
		}
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
		if (this.sexo != null) {
			siglaSexo = sexo.getSigla();
		}
	}

	public String getTipoFatorRh() {
		return tipoFatorRh;
	}

	public void setTipoFatorRh(String tipoFatorRh) {
		this.tipoFatorRh = tipoFatorRh;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getUnidadeFederacaoTituloEleitor() {
		return unidadeFederacaoTituloEleitor;
	}

	public void setUnidadeFederacaoTituloEleitor(String unidadeFederacaoTituloEleitor) {
		this.unidadeFederacaoTituloEleitor = unidadeFederacaoTituloEleitor;
	}

	public BigDecimal getZonaTituloEleitor() {
		return zonaTituloEleitor;
	}

	public void setZonaTituloEleitor(BigDecimal zonaTituloEleitor) {
		this.zonaTituloEleitor = zonaTituloEleitor;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Municipio getMunicipioNaturalidade() {
		return municipioNaturalidade;
	}

	public void setMunicipioNaturalidade(Municipio municipioNaturalidade) {
		this.municipioNaturalidade = municipioNaturalidade;
	}

	public Municipio getMunicipioNascimento() {
		return municipioNascimento;
	}

	public void setMunicipioNascimento(Municipio municipioNascimento) {
		this.municipioNascimento = municipioNascimento;
	}

	public Municipio getMunicipioCertObito() {
		return municipioCertObito;
	}

	public void setMunicipioCertObito(Municipio municipioCertObito) {
		this.municipioCertObito = municipioCertObito;
	}

	public Pais getPaisNascimento() {
		return paisNascimento;
	}

	public void setPaisNascimento(Pais paisNascimento) {
		this.paisNascimento = paisNascimento;
	}

	public Pais getPaisNacionalidade() {
		return paisNacionalidade;
	}

	public void setPaisNacionalidade(Pais paisNacionalidade) {
		this.paisNacionalidade = paisNacionalidade;
	}

	public RacaCor getRacaCor() {
		return racaCor;
	}

	public void setRacaCor(RacaCor racaCor) {
		this.racaCor = racaCor;
	}

	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public String getCartorioCertidaoObito() {
		return cartorioCertidaoObito;
	}

	public void setCartorioCertidaoObito(String cartorioCertidaoObito) {
		this.cartorioCertidaoObito = cartorioCertidaoObito;
	}

	public BigDecimal getNumeroAnoChegadaBrasil() {
		return numeroAnoChegadaBrasil;
	}

	public void setNumeroAnoChegadaBrasil(BigDecimal numeroAnoChegadaBrasil) {
		this.numeroAnoChegadaBrasil = numeroAnoChegadaBrasil;
	}

	public BigDecimal getNumeroMatriculaBase() {
		return numeroMatriculaBase;
	}

	public void setNumeroMatriculaBase(BigDecimal numeroMatriculaBase) {
		this.numeroMatriculaBase = numeroMatriculaBase;
	}

	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public EnumTipoCNH getTipoCNH() {
		if (tipoCNH == null && idTipoCNH != null) {
			tipoCNH = EnumeradorUtil.getConstante(EnumTipoCNH.class, idTipoCNH);
		}

		return tipoCNH;
	}

	public void setTipoCNH(EnumTipoCNH tipoCNH) {
		this.tipoCNH = tipoCNH;
		this.idTipoCNH = tipoCNH.getId();
	}

	public Long getIdTipoCNH() {
		idTipoCNH = getTipoCNH().getId();
		return idTipoCNH;
	}

	public void setIdTipoCNH(Long idTipoCNH) {
		tipoCNH = EnumeradorUtil.getConstante(EnumTipoCNH.class, idTipoCNH);
		this.idTipoCNH = idTipoCNH;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisica other = (PessoaFisica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}