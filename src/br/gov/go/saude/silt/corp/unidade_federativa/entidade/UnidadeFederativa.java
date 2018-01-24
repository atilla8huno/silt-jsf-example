package br.gov.go.saude.silt.corp.unidade_federativa.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa UnidadeFederativa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "unidades_federativas", schema = "corp")
public class UnidadeFederativa extends Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ufed_sigl")
	private String sigla;

	@Column(name = "codigo")
	private BigDecimal codigo;

	@Column(name = "ufed_codg_regiao")
	private String codigoRegiao;

	@Column(name = "ufed_nome")
	private String nome;

	// bi-directional many-to-one association to Municipio
	@OneToMany(mappedBy = "unidadeFederativa")
	private List<Municipio> municipios;

	public UnidadeFederativa() {
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Long getId() {
		return codigo != null ? codigo.longValue() : 0;
	}

	public String getCodigoRegiao() {
		return codigoRegiao;
	}

	public void setCodigoRegiao(String codigoRegiao) {
		this.codigoRegiao = codigoRegiao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getCodigo() {
		return this.codigo;
	}

	public void setCodigo(BigDecimal codigo) {
		this.codigo = codigo;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		UnidadeFederativa other = (UnidadeFederativa) obj;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
}