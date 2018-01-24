package br.gov.go.saude.silt.corp.municipio_regiao.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.regiao_saude.entidade.RegiaoSaude;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa MunicipioRegiao
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "municipio_regiao", schema = "corp")
public class MunicipioRegiao extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mreg_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "true_false")
	@Column(name = "mreg_exclusao")
	private Boolean exclusao;

	@ManyToOne
	@JoinColumn(name = "mreg_rsau_id")
	private RegiaoSaude regiaoSaude;

	@Column(name = "mreg_sede")
	private String sede;

	// bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "mreg_chvmnc")
	private Municipio municipio;

	public MunicipioRegiao() {
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}

	public RegiaoSaude getRegiaoSaude() {
		return regiaoSaude;
	}

	public void setRegiaoSaude(RegiaoSaude regiaoSaude) {
		this.regiaoSaude = regiaoSaude;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
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
		MunicipioRegiao other = (MunicipioRegiao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}