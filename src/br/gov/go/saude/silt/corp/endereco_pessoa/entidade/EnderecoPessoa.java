package br.gov.go.saude.silt.corp.endereco_pessoa.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoEndereco;
import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa EnderecoPessoa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "enderecos_pessoas", schema = "corp")
public class EnderecoPessoa extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "epes_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "epes_domi_id_tend")
	private Long idTipoEndereco;

	@Transient
	private EnumTipoEndereco tipoEndereco;

	// bi-directional many-to-one association to Endereco
	@ManyToOne
	@JoinColumn(name = "epes_ende_id", insertable = true, updatable = true, nullable = false)
	private Endereco endereco;

	// bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name = "epes_pess_iden", nullable = false)
	private Pessoa pessoa;

	public EnderecoPessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdTipoEndereco() {
		idTipoEndereco = getTipoEndereco().getId();
		return idTipoEndereco;
	}

	public void setIdTipoEndereco(Long idTipoEndereco) {
		this.idTipoEndereco = idTipoEndereco;
		tipoEndereco = EnumeradorUtil.getConstante(EnumTipoEndereco.class, idTipoEndereco);
	}

	public EnumTipoEndereco getTipoEndereco() {
		if (tipoEndereco == null && idTipoEndereco != null) {
			tipoEndereco = EnumeradorUtil.getConstante(EnumTipoEndereco.class, idTipoEndereco);
		}
		return tipoEndereco;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
		if (this.tipoEndereco != null) {
			this.idTipoEndereco = tipoEndereco.getId();
		}
	}

	public Endereco getEndereco() {
		if (endereco == null) {
			endereco = new Endereco();
		}
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
		EnderecoPessoa other = (EnderecoPessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}