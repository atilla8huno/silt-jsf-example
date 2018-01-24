package br.gov.go.saude.silt.corp.municipio.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.municipio_regiao.entidade.MunicipioRegiao;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa Municipio
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 212 $ $Author: atillabarros $ $Date: 2013-10-11 17:52:06 -0300 (Sex, 11 Out 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "municipio", schema = "corp")
public class Municipio extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chvmnc")
	private Long id;

	@Column(name = "aganp_id")
	private BigDecimal codigoAganp;

	@Column(name = "codibge")
	private BigDecimal codigoIBGE;

	@Type(type = "true_false")
	@Column(name = "exclusao")
	private Boolean exclusao;

	@Column(name = "nommunicipio")
	private String nome;

	@Column(name = "numero_habitantes")
	private Integer numeroHabitantes;

	@ManyToOne
	@JoinColumn(name = "undfederacao")
	private UnidadeFederativa unidadeFederativa;

	// bi-directional many-to-one association to Bairro
	@OneToMany(mappedBy = "municipio")
	private List<Bairro> bairros;

	// bi-directional many-to-one association to EstabelecimentoSaude
	@OneToMany(mappedBy = "municipio")
	private List<EstabelecimentoSaude> estabelecimentosSaude;

	// bi-directional many-to-one association to MunicipioRegiao
	@OneToMany(mappedBy = "municipio")
	private List<MunicipioRegiao> municipioRegiaos;

	public Municipio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCodigoAganp() {
		return codigoAganp;
	}

	public void setCodigoAganp(BigDecimal codigoAganp) {
		this.codigoAganp = codigoAganp;
	}

	public BigDecimal getCodigoIBGE() {
		return codigoIBGE;
	}

	public void setCodigoIBGE(BigDecimal codigoIBGE) {
		this.codigoIBGE = codigoIBGE;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroHabitantes() {
		return numeroHabitantes;
	}

	public void setNumeroHabitantes(Integer numeroHabitantes) {
		this.numeroHabitantes = numeroHabitantes;
	}

	public UnidadeFederativa getUnidadeFederativa() {
		if (unidadeFederativa == null) {
			unidadeFederativa = new UnidadeFederativa();
		}
		return unidadeFederativa;
	}

	public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public List<EstabelecimentoSaude> getEstabelecimentosSaude() {
		return estabelecimentosSaude;
	}

	public void setEstabelecimentosSaude(List<EstabelecimentoSaude> estabelecimentosSaude) {
		this.estabelecimentosSaude = estabelecimentosSaude;
	}

	public List<MunicipioRegiao> getMunicipioRegiaos() {
		return municipioRegiaos;
	}

	public void setMunicipioRegiaos(List<MunicipioRegiao> municipioRegiaos) {
		this.municipioRegiaos = municipioRegiaos;
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
		Municipio other = (Municipio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}