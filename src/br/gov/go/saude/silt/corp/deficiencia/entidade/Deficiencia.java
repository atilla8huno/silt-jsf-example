package br.gov.go.saude.silt.corp.deficiencia.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.grupo_deficiencia.entidade.GrupoDeficiencia;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de entidade Deficiencia.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "deficiencia", schema = "corp")
public class Deficiencia extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "defi_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "defi_descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "defi_gdef_id")
	private GrupoDeficiencia grupoDeficiencia;

	public Deficiencia() {
	}

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

	public GrupoDeficiencia getGrupoDeficiencia() {
		return grupoDeficiencia;
	}

	public void setGrupoDeficiencia(GrupoDeficiencia grupoDeficiencia) {
		this.grupoDeficiencia = grupoDeficiencia;
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
		Deficiencia other = (Deficiencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}