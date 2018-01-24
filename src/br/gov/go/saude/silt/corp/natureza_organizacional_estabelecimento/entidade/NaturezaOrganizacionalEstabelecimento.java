package br.gov.go.saude.silt.corp.natureza_organizacional_estabelecimento.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa NaturezaOrganizacionalEstabelecimento
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "natureza_organizacional_estabelecimento", schema = "corp")
public class NaturezaOrganizacionalEstabelecimento extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "noes_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "noes_natureza")
	private String natureza;

	// bi-directional many-to-one association to EstabelecimentoSaude
	@OneToMany(mappedBy = "naturezaOrganizacionalEstabelecimento")
	private List<EstabelecimentoSaude> estabelecimentosSaude;

	public NaturezaOrganizacionalEstabelecimento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public List<EstabelecimentoSaude> getEstabelecimentosSaude() {
		return estabelecimentosSaude;
	}

	public void setEstabelecimentosSaude(List<EstabelecimentoSaude> estabelecimentosSaude) {
		this.estabelecimentosSaude = estabelecimentosSaude;
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
		NaturezaOrganizacionalEstabelecimento other = (NaturezaOrganizacionalEstabelecimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}