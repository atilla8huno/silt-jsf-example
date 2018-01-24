package br.gov.go.saude.silt.nivel_acesso.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe para entidade NivelAcesso.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category EntidadeF
 */
@Entity
@Table(name = "nivel_acesso", schema = "silt")
public class NivelAcesso extends Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nace_id")
	private Long id;

	@Column(name = "nace_descricao", nullable = false)
	private String descricao;

	@Column(name = "nace_sigla", nullable = false)
	private String sigla;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
