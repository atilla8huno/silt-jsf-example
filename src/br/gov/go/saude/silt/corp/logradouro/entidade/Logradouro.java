package br.gov.go.saude.silt.corp.logradouro.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco_estabelecimento_saude.entidade.EnderecoEstabelecimentoSaude;
import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa Logradouro
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 212 $ $Author: atillabarros $ $Date: 2013-10-11 17:52:06 -0300 (Sex, 11 Out 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "logradouros", schema = "corp")
public class Logradouro extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "logr_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "logr_codg_aganp")
	private BigDecimal codigoAganp;

	@Column(name = "logr_codg_cep")
	private Long cep;

	@Column(name = "logr_nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "logr_tlog_id")
	private TipoLogradouro tipoLogradouro;

	// bi-directional many-to-one association to EnderecoEstabelecimentoSaude
	@OneToMany(mappedBy = "logradouro")
	private List<EnderecoEstabelecimentoSaude> enderecoEstabelecimentosSaude;

	// bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy = "logradouro")
	private List<Endereco> enderecos;

	// bi-directional many-to-one association to Bairro
	@ManyToOne
	@JoinColumn(name = "logr_bair_id")
	private Bairro bairro;

	public Logradouro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getCodigoAganp() {
		return codigoAganp;
	}

	public void setCodigoAganp(BigDecimal codigoAganp) {
		this.codigoAganp = codigoAganp;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public List<EnderecoEstabelecimentoSaude> getEnderecoEstabelecimentosSaude() {
		return enderecoEstabelecimentosSaude;
	}

	public void setEnderecoEstabelecimentosSaude(List<EnderecoEstabelecimentoSaude> enderecoEstabelecimentosSaude) {
		this.enderecoEstabelecimentosSaude = enderecoEstabelecimentosSaude;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Bairro getBairro() {
		if (bairro == null) {
			bairro = new Bairro();
		}
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
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
		Logradouro other = (Logradouro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}