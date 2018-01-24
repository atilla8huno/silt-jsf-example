package br.gov.go.saude.silt.corp.endereco_estabelecimento_saude.entidade;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa EnderecoEstabelecimentoSaude
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 240 $ $Author: atillabarros $ $Date: 2013-11-19 14:33:33 -0200 (Ter, 19 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "endereco_estabelecimento_saude", schema = "corp")
public class EnderecoEstabelecimentoSaude extends Entidade implements
		Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "eesa_id")
	private Long id;

	@Column(name = "eesa_info_complemento")
	private String infoComplemento;

	@Column(name = "eesa_nome_lote")
	private String nomeLote;

	@Column(name = "eesa_nome_quadra")
	private String nomeQuadra;

	@Column(name = "eesa_numr_caixa_postal")
	private BigDecimal numrCaixaPostal;

	@Column(name = "eesa_numr_imovel")
	private String numrImovel;

	// bi-directional many-to-one association to EstabelecimentoSaude
	@ManyToOne
	@JoinColumn(name = "eesa_esau_id")
	private EstabelecimentoSaude estabelecimentoSaude;

	// bi-directional many-to-one association to Logradouro
	@ManyToOne
	@JoinColumn(name = "eesa_logr_id")
	private Logradouro logradouro;

	public EnderecoEstabelecimentoSaude() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInfoComplemento() {
		return infoComplemento;
	}

	public void setInfoComplemento(String infoComplemento) {
		this.infoComplemento = infoComplemento;
	}

	public String getNomeLote() {
		return nomeLote;
	}

	public void setNomeLote(String nomeLote) {
		this.nomeLote = nomeLote;
	}

	public String getNomeQuadra() {
		return nomeQuadra;
	}

	public void setNomeQuadra(String nomeQuadra) {
		this.nomeQuadra = nomeQuadra;
	}

	public BigDecimal getNumrCaixaPostal() {
		return numrCaixaPostal;
	}

	public void setNumrCaixaPostal(BigDecimal numrCaixaPostal) {
		this.numrCaixaPostal = numrCaixaPostal;
	}

	public String getNumrImovel() {
		return numrImovel;
	}

	public void setNumrImovel(String numrImovel) {
		this.numrImovel = numrImovel;
	}

	public EstabelecimentoSaude getEstabelecimentoSaude() {
		return this.estabelecimentoSaude;
	}

	public void setEstabelecimentoSaude(
			EstabelecimentoSaude estabelecimentoSaude) {
		this.estabelecimentoSaude = estabelecimentoSaude;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
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
		EnderecoEstabelecimentoSaude other = (EnderecoEstabelecimentoSaude) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}