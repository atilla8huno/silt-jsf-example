package br.gov.go.saude.silt.corp.contato_estabelecimento_saude.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa uma ContatoEstabelecimentoSaude
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "contato_estabelecimento_saude", schema = "corp")
public class ContatoEstabelecimentoSaude extends Entidade implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cesa_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cesa_esau_id")
	private EstabelecimentoSaude estabelecimentoSaude;

	@Column(name = "cesa_info")
	private String info;

	@Column(name = "cesa_info_1")
	private String info1;

	@Column(name = "cesa_info_2")
	private String info2;

	// bi-directional many-to-one association to TipoContato
	@ManyToOne
	@JoinColumn(name = "cesa_tipo")
	private TipoContato tipoContato;

	public ContatoEstabelecimentoSaude() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EstabelecimentoSaude getEstabelecimentoSaude() {
		return estabelecimentoSaude;
	}

	public void setEstabelecimentoSaude(
			EstabelecimentoSaude estabelecimentoSaude) {
		this.estabelecimentoSaude = estabelecimentoSaude;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public TipoContato getTipoContato() {
		return this.tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
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
		ContatoEstabelecimentoSaude other = (ContatoEstabelecimentoSaude) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}