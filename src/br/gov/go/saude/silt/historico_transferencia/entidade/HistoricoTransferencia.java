package br.gov.go.saude.silt.historico_transferencia.entidade;

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
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "historico_transferencia", schema="silt")
public class HistoricoTransferencia extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "htra_id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "htra_data_cadastro", nullable=false)
	private Date dataCadastro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "htra_cnot_id", nullable=false)
	private CasoNotificacao casoNotificacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "htra_esta_id_destino", nullable=false)
	private Estabelecimento estabelecimentoDestino;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "htra_esta_id_origem", nullable=false)
	private Estabelecimento estabelecimentoOrigem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "htra_uest_id", nullable=false)
	private UsuarioEstabelecimento usuarioEstabelecimento;

	public HistoricoTransferencia() { }

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

	public Estabelecimento getEstabelecimentoDestino() {
		return estabelecimentoDestino;
	}

	public void setEstabelecimentoDestino(Estabelecimento estabelecimentoDestino) {
		this.estabelecimentoDestino = estabelecimentoDestino;
	}

	public Estabelecimento getEstabelecimentoOrigem() {
		return estabelecimentoOrigem;
	}

	public void setEstabelecimentoOrigem(Estabelecimento estabelecimentoOrigem) {
		this.estabelecimentoOrigem = estabelecimentoOrigem;
	}

	public CasoNotificacao getCasoNotificacao() {
		return this.casoNotificacao;
	}

	public void setCasoNotificacao(CasoNotificacao casoNotificacao) {
		this.casoNotificacao = casoNotificacao;
	}

	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return this.usuarioEstabelecimento;
	}

	public void setUsuarioEstabelecimento(
			UsuarioEstabelecimento usuarioEstabelecimento) {
		this.usuarioEstabelecimento = usuarioEstabelecimento;
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
		HistoricoTransferencia other = (HistoricoTransferencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}