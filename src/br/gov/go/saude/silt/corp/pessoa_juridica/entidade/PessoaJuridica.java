package br.gov.go.saude.silt.corp.pessoa_juridica.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa PesssoaJuridica
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "pessoas_juridicas", schema = "corp")
public class PessoaJuridica extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pjur_iden")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "true_false")
	private boolean exclusao;

	@Column(name = "marca_de_origem")
	private String marcaDeOrigem;

	@Temporal(TemporalType.DATE)
	@Column(name = "pjur_data_cadastro")
	private Date dataCadastro;

	@Column(name = "pjur_njur_codg")
	private BigDecimal numeroCodigoPessoaJuridica;

	@Column(name = "pjur_nome_fantasia")
	private String nomeFantasia;

	@Column(name = "pjur_nome_razao_social")
	private String nomeRazaoSocial;

	@Column(name = "pjur_numr_cnpj")
	private BigDecimal cnpj;

	@Column(name = "pjur_numr_insc_estadual")
	private String numeroInscricaocEstadual;

	@Column(name = "pjur_numr_insc_municipal")
	private String numeroInscricaoMunicipal;

	// bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "pjur_muni_codg")
	private Municipio municipio;

	// bi-directional many-to-one association to UnidadesFederativa
	@ManyToOne
	@JoinColumn(name = "pjur_ufed_sigl")
	private UnidadeFederativa unidadeFederativa;

	public PessoaJuridica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getNumeroCodigoPessoaJuridica() {
		return numeroCodigoPessoaJuridica;
	}

	public void setNumeroCodigoPessoaJuridica(BigDecimal numeroCodigoPessoaJuridica) {
		this.numeroCodigoPessoaJuridica = numeroCodigoPessoaJuridica;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public BigDecimal getCnpj() {
		return cnpj;
	}

	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	public String getNumeroInscricaocEstadual() {
		return numeroInscricaocEstadual;
	}

	public void setNumeroInscricaocEstadual(String numeroInscricaocEstadual) {
		this.numeroInscricaocEstadual = numeroInscricaocEstadual;
	}

	public String getNumeroInscricaoMunicipal() {
		return numeroInscricaoMunicipal;
	}

	public void setNumeroInscricaoMunicipal(String numeroInscricaoMunicipal) {
		this.numeroInscricaoMunicipal = numeroInscricaoMunicipal;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
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
		PessoaJuridica other = (PessoaJuridica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}