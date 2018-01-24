package br.gov.go.saude.silt.corp.organograma_secretaria.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.categoria.entidade.Categoria;
import br.gov.go.saude.silt.corp.esfera_administrativa.entidade.EsferaAdministrativa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Classe de persistencia OrganogramaSecretaria.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category DAO
 */
@Entity
@Table(name = "organograma_secretaria", schema = "corp")
public class OrganogramaSecretaria extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "osec_id")
	private Long id;

	@Column(name = "osec_designacao")
	private String designacao;

	@Column(name = "osec_domi_id_esferaadministrativa")
	private Long idEsferaAdministrativa;

	@Column(name = "osec_exclusao")
	private String excluido;

	@Column(name = "osec_oficial")
	private String oficial;

	@ManyToOne
	@JoinColumn(name = "osec_pess_iden")
	private PessoaFisica pessoaFisica;

	@Column(name = "osec_sigla")
	private String sigla;

	// bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name = "osec_cate_id")
	private Categoria categoria;

	// bi-directional many-to-one association to Esferaadministrativa
	@ManyToOne
	@JoinColumn(name = "osec_cod_esfadm")
	private EsferaAdministrativa esferaAdministrativa;

	// bi-directional many-to-one association to OrganogramaSecretaria
	@ManyToOne
	@JoinColumn(name = "osec_osec_id")
	private OrganogramaSecretaria organogramaSecretaria;

	public OrganogramaSecretaria() {
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Long getIdEsferaAdministrativa() {
		return idEsferaAdministrativa;
	}

	public void setIdEsferaAdministrativa(Long idEsferaAdministrativa) {
		this.idEsferaAdministrativa = idEsferaAdministrativa;
	}

	public String getExcluido() {
		return excluido;
	}

	public void setExcluido(String excluido) {
		this.excluido = excluido;
	}

	public String getOficial() {
		return oficial;
	}

	public void setOficial(String oficial) {
		this.oficial = oficial;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public EsferaAdministrativa getEsferaAdministrativa() {
		return esferaAdministrativa;
	}

	public void setEsferaAdministrativa(EsferaAdministrativa esferaAdministrativa) {
		this.esferaAdministrativa = esferaAdministrativa;
	}

	public OrganogramaSecretaria getOrganogramaSecretaria() {
		return organogramaSecretaria;
	}

	public void setOrganogramaSecretaria(OrganogramaSecretaria organogramaSecretaria) {
		this.organogramaSecretaria = organogramaSecretaria;
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
		OrganogramaSecretaria other = (OrganogramaSecretaria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}