package br.gov.go.saude.silt.usuario_estabelecimento.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.historico_transferencia.entidade.HistoricoTransferencia;
import br.gov.go.saude.silt.nivel_acesso.entidade.NivelAcesso;
import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.portal.usuario_sistema.entidade.UsuarioSistema;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa UsuarioEstabelecimento
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "usuario_estabelecimento", schema = "silt")
public class UsuarioEstabelecimento extends Entidade {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uest_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uest_pfis_iden")
	private PessoaFisica pessoaFisica;

	@OneToOne
	@JoinColumn(name = "uest_usua_codg", nullable = false, insertable = true, updatable = true)
	private UsuarioSistema usuarioSistema;

	// bi-directional many-to-one association to CasoNotificacao
	@OneToMany(mappedBy = "usuarioEstabelecimento", fetch = FetchType.LAZY)
	private List<CasoNotificacao> casoNotificacaos;

	// bi-directional many-to-one association to HistoricoCasoNotificacao
	@OneToMany(mappedBy = "usuarioEstabelecimento", fetch = FetchType.LAZY)
	private List<HistoricoCasoNotificacao> historicoCasoNotificacaos;

	// bi-directional many-to-one association to HistoricoTransferencia
	@OneToMany(mappedBy = "usuarioEstabelecimento", fetch = FetchType.LAZY)
	private List<HistoricoTransferencia> historicoTransferencias;

	// bi-directional many-to-one association to Estabelecimento
	@ManyToOne
	@JoinColumn(name = "uest_esta_id", nullable = false)
	private Estabelecimento estabelecimento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uest_nace_id", nullable = false)
	private NivelAcesso nivelAcesso;

	@Column(name = "uest_excluido")
	private boolean excluido;

	@Transient
	private PerfilSistema perfilSistema;

	public UsuarioEstabelecimento() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisica getPessoaFisica() {
		if (pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
		}
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public UsuarioSistema getUsuarioSistema() {
		if (usuarioSistema == null) {
			usuarioSistema = new UsuarioSistema();
		}
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
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

	public List<HistoricoTransferencia> getHistoricoTransferencias() {
		return this.historicoTransferencias;
	}

	public void setHistoricoTransferencias(List<HistoricoTransferencia> historicoTransferencias) {
		this.historicoTransferencias = historicoTransferencias;
	}

	public Estabelecimento getEstabelecimento() {
		if (estabelecimento == null) {
			estabelecimento = new Estabelecimento();
		}
		return this.estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public PerfilSistema getPerfilSistema() {
		if (perfilSistema == null) {
			perfilSistema = new PerfilSistema();
		}
		return perfilSistema;
	}

	public void setPerfilSistema(PerfilSistema perfilSistema) {
		this.perfilSistema = perfilSistema;
	}

	public NivelAcesso getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(NivelAcesso nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
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
		UsuarioEstabelecimento other = (UsuarioEstabelecimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}