package br.gov.go.saude.silt.tipo_tratamento.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa TipoTratamento
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "tipo_tratamento", schema = "silt")
public class TipoTratamento extends Entidade {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ttra_id")
	private Long id;

	@Column(name = "ttra_codigo", nullable = false)
	private String codigo;

	@Column(name = "ttra_descricao", nullable = false)
	private String descricao;

	@Column(name = "ttra_excluido")
	private boolean excluido;

	// bi-directional many-to-one association to CasoNotificacao
	@OneToMany(mappedBy = "tipoTratamento")
	private List<CasoNotificacao> casoNotificacaos;

	public TipoTratamento() {
		this.excluido = Boolean.FALSE;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<CasoNotificacao> getCasoNotificacaos() {
		return this.casoNotificacaos;
	}

	public void setCasoNotificacaos(List<CasoNotificacao> casoNotificacaos) {
		this.casoNotificacaos = casoNotificacaos;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
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
		TipoTratamento other = (TipoTratamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}