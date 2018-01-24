package br.gov.go.saude.silt.portal.acesso_usuario.entidade;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the acessos_usuario database table.
 * 
 */
@Embeddable
public class AcessoUsuarioPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "aces_usua_codg", insertable = false, updatable = false)
	private String idUsuarioSistema;

	@Column(name = "aces_perf_codg", insertable = false, updatable = false)
	private Long idPerfilSistema;

	@Column(name = "aces_osec_id", insertable = false, updatable = false)
	private Long idOganogramaSecretaria;

	public AcessoUsuarioPK() {
	}

	public String getIdUsuarioSistema() {
		return idUsuarioSistema;
	}

	public void setIdUsuarioSistema(String idUsuarioSistema) {
		this.idUsuarioSistema = idUsuarioSistema;
	}

	public Long getIdPerfilSistema() {
		return idPerfilSistema;
	}

	public void setIdPerfilSistema(Long idPerfilSistema) {
		this.idPerfilSistema = idPerfilSistema;
	}

	public Long getIdOganogramaSecretaria() {
		return idOganogramaSecretaria;
	}

	public void setIdOganogramaSecretaria(Long idOganogramaSecretaria) {
		this.idOganogramaSecretaria = idOganogramaSecretaria;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AcessoUsuarioPK)) {
			return false;
		}
		AcessoUsuarioPK castOther = (AcessoUsuarioPK) other;
		return this.idUsuarioSistema.equals(castOther.idUsuarioSistema) && this.idPerfilSistema.equals(castOther.idPerfilSistema)
				&& this.idOganogramaSecretaria.equals(castOther.idOganogramaSecretaria);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idUsuarioSistema.hashCode();
		hash = hash * prime + this.idPerfilSistema.hashCode();
		hash = hash * prime + this.idOganogramaSecretaria.hashCode();

		return hash;
	}
}