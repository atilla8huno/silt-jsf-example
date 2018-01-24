package br.gov.go.saude.silt.solicitacao_transferencia.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa SolicitacaoTransferencia
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "solicitacao_transferencia", schema = "silt")
public class SolicitacaoTransferencia extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stra_id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "stra_data_cadastro", nullable = false)
	private Date dataCadastro;

	@Column(name = "stra_observacao", nullable = false)
	private String observacao;

	@Column(name = "stra_solicitacao_atendida", nullable = false)
	private Boolean solicitacaoAtendida;

	@Column(name = "stra_excluido")
	private boolean excluido;

	// bi-directional many-to-one association to CasoNotificacao
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stra_cnot_id", nullable = false)
	private CasoNotificacao casoNotificacao;

	// bi-directional many-to-one association to UsuarioEstabelecimento
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stra_uest_id_autorizador", nullable = false)
	private UsuarioEstabelecimento usuarioAutorizador;

	// bi-directional many-to-one association to UsuarioEstabelecimento
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stra_uest_id_solicitante", nullable = false)
	private UsuarioEstabelecimento usuarioSolicitante;

	public SolicitacaoTransferencia() { }

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

	public UsuarioEstabelecimento getUsuarioAutorizador() {
		return usuarioAutorizador;
	}

	public void setUsuarioAutorizador(UsuarioEstabelecimento usuarioAutorizador) {
		this.usuarioAutorizador = usuarioAutorizador;
	}

	public UsuarioEstabelecimento getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(UsuarioEstabelecimento usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Boolean getSolicitacaoAtendida() {
		return solicitacaoAtendida;
	}

	public void setSolicitacaoAtendida(Boolean solicitacaoAtendida) {
		this.solicitacaoAtendida = solicitacaoAtendida;
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
		SolicitacaoTransferencia other = (SolicitacaoTransferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}