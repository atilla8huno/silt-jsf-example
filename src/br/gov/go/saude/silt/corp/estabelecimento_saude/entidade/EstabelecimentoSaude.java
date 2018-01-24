package br.gov.go.saude.silt.corp.estabelecimento_saude.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.corp.atividade_estabelecimento.entidade.AtividadeEstabelecimento;
import br.gov.go.saude.silt.corp.endereco_estabelecimento_saude.entidade.EnderecoEstabelecimentoSaude;
import br.gov.go.saude.silt.corp.esfera_administrativa.entidade.EsferaAdministrativa;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.natureza_organizacional_estabelecimento.entidade.NaturezaOrganizacionalEstabelecimento;
import br.gov.go.saude.silt.corp.nivel_hierarquico_estabelecimento.entidade.NivelHierarquicoEstabelecimento;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.tipo_estabelecimento.entidade.TipoEstabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa EsferaAdministrativa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "estabelecimento_saude", schema = "corp")
public class EstabelecimentoSaude extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "esau_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "esau_cnes")
	private Long cnes;

	@Column(name = "esau_nome_fantasia")
	private String nomeFantasia;

	@Column(name = "esau_razao_social")
	private String razaoSocial;

	@Column(name = "esau_cpf_cnpj")
	private String cpfCnpj;

	@Type(type = "true_false")
	@Column(name = "esau_exclusao")
	private Boolean exclusao;

	// bi-directional many-to-one association to EnderecoEstabelecimentoSaude
	@OneToMany(mappedBy = "estabelecimentoSaude")
	private List<EnderecoEstabelecimentoSaude> enderecoEstabelecimentoSaudes;

	// bi-directional many-to-one association to AtividadeEstabelecimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_aesta_id")
	private AtividadeEstabelecimento atividadeEstabelecimento;

	// bi-directional many-to-one association to Esferaadministrativa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_eadm_id")
	private EsferaAdministrativa esferaAdministrativa;

	// bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "esau_chvmnc")
	private Municipio municipio;

	// bi-directional many-to-one association to
	// NaturezaOrganizacionalEstabelecimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_noes_id")
	private NaturezaOrganizacionalEstabelecimento naturezaOrganizacionalEstabelecimento;

	// bi-directional many-to-one association to NivelHierarquicoEstabelecimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_nhes_id")
	private NivelHierarquicoEstabelecimento nivelHierarquicoEstabelecimento;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_pess_iden")
	private Pessoa pessoa;

	// bi-directional many-to-one association to TipoEstabelecimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "esau_test_id")
	private TipoEstabelecimento tipoEstabelecimento;

	public EstabelecimentoSaude() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCnes() {
		return cnes;
	}

	public void setCnes(Long cnes) {
		this.cnes = cnes;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public List<EnderecoEstabelecimentoSaude> getEnderecoEstabelecimentoSaudes() {
		return enderecoEstabelecimentoSaudes;
	}

	public void setEnderecoEstabelecimentoSaudes(List<EnderecoEstabelecimentoSaude> enderecoEstabelecimentoSaudes) {
		this.enderecoEstabelecimentoSaudes = enderecoEstabelecimentoSaudes;
	}

	public AtividadeEstabelecimento getAtividadeEstabelecimento() {
		return this.atividadeEstabelecimento;
	}

	public void setAtividadeEstabelecimento(AtividadeEstabelecimento atividadeEstabelecimento) {
		this.atividadeEstabelecimento = atividadeEstabelecimento;
	}

	public EsferaAdministrativa getEsferaAdministrativa() {
		return esferaAdministrativa;
	}

	public void setEsferaAdministrativa(EsferaAdministrativa esferaAdministrativa) {
		this.esferaAdministrativa = esferaAdministrativa;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public NaturezaOrganizacionalEstabelecimento getNaturezaOrganizacionalEstabelecimento() {
		return this.naturezaOrganizacionalEstabelecimento;
	}

	public void setNaturezaOrganizacionalEstabelecimento(NaturezaOrganizacionalEstabelecimento naturezaOrganizacionalEstabelecimento) {
		this.naturezaOrganizacionalEstabelecimento = naturezaOrganizacionalEstabelecimento;
	}

	public NivelHierarquicoEstabelecimento getNivelHierarquicoEstabelecimento() {
		return this.nivelHierarquicoEstabelecimento;
	}

	public void setNivelHierarquicoEstabelecimento(NivelHierarquicoEstabelecimento nivelHierarquicoEstabelecimento) {
		this.nivelHierarquicoEstabelecimento = nivelHierarquicoEstabelecimento;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoEstabelecimento getTipoEstabelecimento() {
		return this.tipoEstabelecimento;
	}

	public void setTipoEstabelecimento(TipoEstabelecimento tipoEstabelecimento) {
		this.tipoEstabelecimento = tipoEstabelecimento;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
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
		EstabelecimentoSaude other = (EstabelecimentoSaude) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}