package br.gov.go.saude.silt.corp.contato_pessoa.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa ContatoPessoa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 244 $ $Author: atillabarros $ $Date: 2013-11-20 18:08:08 -0200 (Qua, 20 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "contatos_pessoa", schema = "corp")
public class ContatoPessoa extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ctto_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ctto_info")
	private String info;

	@Column(name = "ctto_info_1")
	private String info1;

	@Column(name = "ctto_info_2")
	private String info2;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "ctto_pess_iden")
	private Pessoa pessoa;

	// bi-directional many-to-one association to TipoContato
	@ManyToOne
	@JoinColumn(name = "ctto_tipo")
	private TipoContato tipoContato;

	public ContatoPessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Pessoa getPessoa() {
		if (pessoa == null) {
			pessoa = new Pessoa();
		}
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoContato getTipoContato() {
		if (tipoContato == null) {
			tipoContato = new TipoContato();
		}
		return tipoContato;
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
		ContatoPessoa other = (ContatoPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}