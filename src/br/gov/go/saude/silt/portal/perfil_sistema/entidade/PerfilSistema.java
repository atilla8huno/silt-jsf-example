package br.gov.go.saude.silt.portal.perfil_sistema.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de entidade PerfilSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "perfis_sistema", schema = "portal")
public class PerfilSistema extends Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "perf_codg")
	private Long codigo;

	@Column(name = "perf_desc")
	private String nomePerfil;

	@Column(name = "perf_id")
	private Long id;

	@Column(name = "perf_sist_sigl")
	private String siglaSistema;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public String getSiglaSistema() {
		return siglaSistema;
	}

	public void setSiglaSistema(String siglaSistema) {
		this.siglaSistema = siglaSistema;
	}
	
	
	public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PerfilSistema other = (PerfilSistema) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
