package br.gov.go.saude.silt.corp.categoria.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de entidade Categoria.
 * 
 * @author Cl�udio Esp�ndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "categoria", schema = "corp")
public class Categoria extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cate_id")
	private Long id;

	@Column(name = "cate_descricao")
	private String descricao;

	@Column(name = "cate_exclusao")
	private String excluido;

	public Categoria() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}