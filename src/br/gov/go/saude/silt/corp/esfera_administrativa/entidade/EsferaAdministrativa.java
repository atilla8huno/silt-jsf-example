package br.gov.go.saude.silt.corp.esfera_administrativa.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa EsferaAdministrativa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 178 $ $Author: claudiocosta $ $Date: 2013-09-12 11:02:30 -0300 (Qui, 12 Set 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "esferaadministrativa", schema = "corp")
public class EsferaAdministrativa extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "eadm_cod_esfadm")
	private String codEsfadm;

	@Column(name = "eadm_descricao")
	private String descricao;

	// bi-directional many-to-one association to EstabelecimentoSaude
	@OneToMany(mappedBy = "esferaAdministrativa")
	private List<EstabelecimentoSaude> estabelecimentosSaude;

	public EsferaAdministrativa() {
	}

	public String getCodEsfadm() {
		return codEsfadm;
	}

	public void setCodEsfadm(String codEsfadm) {
		this.codEsfadm = codEsfadm;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<EstabelecimentoSaude> getEstabelecimentosSaude() {
		return estabelecimentosSaude;
	}

	public void setEstabelecimentosSaude(List<EstabelecimentoSaude> estabelecimentosSaude) {
		this.estabelecimentosSaude = estabelecimentosSaude;
	}

	@Override
	public Long getId() {
		return Long.valueOf(codEsfadm);
	}
}