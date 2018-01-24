package br.gov.go.saude.silt.portal.acesso_usuario.entidade;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.organograma_secretaria.entidade.OrganogramaSecretaria;
import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.portal.usuario_sistema.entidade.UsuarioSistema;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de entidade AcessoUsuario.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category categoria
 */
@Entity
@Table(name = "acessos_usuario", schema = "portal")
public class AcessoUsuario extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AcessoUsuarioPK pkAcessoUsuario;

	@ManyToOne
	@JoinColumn(name = "aces_osec_id", insertable = false, updatable = false)
	private OrganogramaSecretaria organogramaSecretaria;

	@Column(name = "aces_info_repasse")
	private String informacaoRepasse;

	// bi-directional many-to-one association to PerfisSistema
	@ManyToOne
	@JoinColumn(name = "aces_perf_codg", insertable = false, updatable = false)
	private PerfilSistema perfilSistema;

	// bi-directional many-to-one association to UsuariosSistema
	@ManyToOne
	@JoinColumn(name = "aces_usua_codg", insertable = false, updatable = false)
	private UsuarioSistema usuarioSistema;

	public AcessoUsuario() {
	}

	@Override
	public Long getId() {
		return null;
	}

	public PerfilSistema getPerfilSistema() {
		return perfilSistema;
	}

	public void setPerfilSistema(PerfilSistema perfilSistema) {
		this.perfilSistema = perfilSistema;
	}

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	public String getInformacaoRepasse() {
		if (informacaoRepasse == null) {
			informacaoRepasse = "N";
		}
		return informacaoRepasse;
	}

	public void setInformacaoRepasse(String informacaoRepasse) {
		this.informacaoRepasse = informacaoRepasse;
	}

	public OrganogramaSecretaria getOrganogramaSecretaria() {
		return organogramaSecretaria;
	}

	public void setOrganogramaSecretaria(OrganogramaSecretaria organogramaSecretaria) {
		this.organogramaSecretaria = organogramaSecretaria;
	}

	public AcessoUsuarioPK getPkAcessoUsuario() {
		if (pkAcessoUsuario == null) {
			pkAcessoUsuario = new AcessoUsuarioPK();
		}
		return pkAcessoUsuario;
	}

	public void setPkAcessoUsuario(AcessoUsuarioPK pkAcessoUsuario) {
		this.pkAcessoUsuario = pkAcessoUsuario;
	}

}