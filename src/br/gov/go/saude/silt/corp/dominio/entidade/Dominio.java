package br.gov.go.saude.silt.corp.dominio.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe entidade para tabela dominios.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "dominios", schema = "corp")
public class Dominio extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "domi_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long auxilio;

	@Column(name = "domi_chave")
	private String chave;

	@Column(name = "domi_conteudo")
	private String conteudo;

	@Column(name = "domi_descricao")
	private String descricao;

	@Type(type="true_false")
	@Column(name = "domi_exclusao")
	private Boolean exclusao;

	@Column(name = "domi_mostrar_linha")
	private String mostrarLinha;

	@Column(name = "domi_sigla")
	private String sigla;

	@Column(name = "domi_sigla_conteudo")
	private String siglaConteudo;

	public Dominio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuxilio() {
		return auxilio;
	}

	public void setAuxilio(Long auxilio) {
		this.auxilio = auxilio;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}

	public String getMostrarLinha() {
		return mostrarLinha;
	}

	public void setMostrarLinha(String mostrarLinha) {
		this.mostrarLinha = mostrarLinha;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSiglaConteudo() {
		return siglaConteudo;
	}

	public void setSiglaConteudo(String siglaConteudo) {
		this.siglaConteudo = siglaConteudo;
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
		Dominio other = (Dominio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}