package br.gov.go.saude.silt.corp.bairro.entidade;

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

import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa um Bairro
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 212 $ $Author: atillabarros $ $Date: 2013-10-11 17:52:06 -0300 (Sex, 11 Out 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "bairros", schema = "corp")
public class Bairro extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bair_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "bair_codg_aganp")
	private BigDecimal codigoAganp;

	@Column(name = "bair_nome")
	private String nome;

	// bi-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "bair_chvmnc")
	private Municipio municipio;

	// bi-directional many-to-one association to Logradouro
	@OneToMany(mappedBy = "bairro")
	private List<Logradouro> logradouros;

	public Bairro() {
	}

	public Municipio getMunicipio() {
		if (municipio == null) {
			municipio = new Municipio();
		}
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return id;
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
		Bairro other = (Bairro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}