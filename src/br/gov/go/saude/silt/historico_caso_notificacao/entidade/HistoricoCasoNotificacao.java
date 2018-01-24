package br.gov.go.saude.silt.historico_caso_notificacao.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.enumerador.TipoAcaoUsuarioEnum;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "historico_caso_notificacao", schema = "silt")
public class HistoricoCasoNotificacao extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hnot_id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "hnot_data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "hnot_observacao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "hnot_tipo_acao")
	private TipoAcaoUsuarioEnum tipoAcao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hnot_cnot_id", nullable = false)
	private CasoNotificacao casoNotificacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hnot_situ_id", nullable = false)
	private Situacao situacao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hnot_uest_id", nullable = false)
	private UsuarioEstabelecimento usuarioEstabelecimento;

	public HistoricoCasoNotificacao() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public CasoNotificacao getCasoNotificacao() {
		return this.casoNotificacao;
	}

	public void setCasoNotificacao(CasoNotificacao casoNotificacao) {
		this.casoNotificacao = casoNotificacao;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return this.usuarioEstabelecimento;
	}

	public void setUsuarioEstabelecimento(UsuarioEstabelecimento usuarioEstabelecimento) {
		this.usuarioEstabelecimento = usuarioEstabelecimento;
	}

	public TipoAcaoUsuarioEnum getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcaoUsuarioEnum tipoAcao) {
		this.tipoAcao = tipoAcao;
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
		HistoricoCasoNotificacao other = (HistoricoCasoNotificacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}