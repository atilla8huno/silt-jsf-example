package br.gov.go.saude.silt.estabelecimento.entidade;

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
import javax.persistence.Table;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "estabelecimento", schema = "silt")
public class Estabelecimento extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "esta_id")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "esta_esau_id", nullable = false)
	private EstabelecimentoSaude estabelecimentoSaude;

	@Column(name = "esta_nucleo", nullable = false)
	private Boolean nucleo;

	@Column(name = "esta_excluido")
	private boolean excluido;

	// bi-directional many-to-one association to CasoNotificacao
	@OneToMany(mappedBy = "estabelecimento")
	private List<CasoNotificacao> casoNotificacaos;

	// bi-directional many-to-one association to Dose
	@OneToMany(mappedBy = "estabelecimento")
	private List<Dose> doses;

	@ManyToOne
	@JoinColumn(name = "esta_esta_id")
	private Estabelecimento estabelecimentoNucleo;

	public Estabelecimento() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getNucleo() {
		if (nucleo == null) {
			nucleo = Boolean.FALSE;
		}
		return nucleo;
	}

	public void setNucleo(Boolean nucleo) {
		this.nucleo = nucleo;
	}

	public EstabelecimentoSaude getEstabelecimentoSaude() {
		if (estabelecimentoSaude == null) {
			estabelecimentoSaude = new EstabelecimentoSaude();
		}
		return estabelecimentoSaude;
	}

	public void setEstabelecimentoSaude(EstabelecimentoSaude estabelecimentoSaude) {
		this.estabelecimentoSaude = estabelecimentoSaude;
	}

	public List<CasoNotificacao> getCasoNotificacaos() {
		return this.casoNotificacaos;
	}

	public void setCasoNotificacaos(List<CasoNotificacao> casoNotificacaos) {
		this.casoNotificacaos = casoNotificacaos;
	}

	public List<Dose> getDoses() {
		return this.doses;
	}

	public void setDoses(List<Dose> doses) {
		this.doses = doses;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Estabelecimento getEstabelecimentoNucleo() {
		return estabelecimentoNucleo;
	}

	public void setEstabelecimentoNucleo(Estabelecimento estabelecimentoNucleo) {
		this.estabelecimentoNucleo = estabelecimentoNucleo;
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
		Estabelecimento other = (Estabelecimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}