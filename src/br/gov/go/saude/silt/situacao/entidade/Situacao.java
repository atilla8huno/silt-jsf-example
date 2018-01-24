package br.gov.go.saude.silt.situacao.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa Situacao
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "situacao", schema = "silt")
@SuppressWarnings("all")
public class Situacao extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "situ_id")
	private Long id;

	@Column(name = "situ_descricao", nullable = false)
	private String descricao;

	@Transient
	private StatusEnum status;
	
	@Column(name = "situ_dsil_id_status")
	private Long idStatus;
	
	@Column(name = "situ_excluido", nullable = false)
	private boolean excluido;

	// bi-directional many-to-one association to CasoNotificacao
	@OneToMany(mappedBy = "situacao")
	private List<CasoNotificacao> casoNotificacaos;

	// bi-directional many-to-one association to HistoricoCasoNotificacao
	@OneToMany(mappedBy = "situacao")
	private List<HistoricoCasoNotificacao> historicoCasoNotificacaos;

	public Situacao() {
	}

	private Long getIdStatus(){
		idStatus = getStatus().getId();
		return idStatus;
	}
	
	private void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
		status = EnumeradorUtil.getConstante(StatusEnum.class, idStatus);
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

	public StatusEnum getStatus() {
		if (status == null && idStatus != null) {
			status = EnumeradorUtil.getConstante(StatusEnum.class, idStatus);
		}
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
		this.idStatus = status.getId();
	}

	public List<CasoNotificacao> getCasoNotificacaos() {
		return this.casoNotificacaos;
	}

	public void setCasoNotificacaos(List<CasoNotificacao> casoNotificacaos) {
		this.casoNotificacaos = casoNotificacaos;
	}

	public List<HistoricoCasoNotificacao> getHistoricoCasoNotificacaos() {
		return this.historicoCasoNotificacaos;
	}

	public void setHistoricoCasoNotificacaos(List<HistoricoCasoNotificacao> historicoCasoNotificacaos) {
		this.historicoCasoNotificacaos = historicoCasoNotificacaos;
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
		Situacao other = (Situacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}