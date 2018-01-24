package br.gov.go.saude.silt.corp.endereco.entidade;

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
import javax.persistence.Transient;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoEndereco;
import br.gov.go.saude.corp.enumerador.EnumZonaEndereco;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa Endereco
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "enderecos", schema = "corp")
public class Endereco extends Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ende_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ende_info_complemento")
	private String infoComplemento;

	@Column(name = "ende_nome_lote")
	private String nomeLote;

	@Column(name = "ende_nome_quadra")
	private String nomeQuadra;

	@Column(name = "ende_numr_caixa_postal")
	private BigDecimal numeroCaixaPostal;

	@Column(name = "ende_numr_imovel")
	private String numeroImovel;

	// bi-directional many-to-one association to Logradouro
	@ManyToOne
	@JoinColumn(name = "ende_logr_id", nullable = false)
	private Logradouro logradouro;

	@Column(name = "ende_zona_endereco_domi_id")
	private Long idZonaEndereco;

	@Transient
	private EnumZonaEndereco zonaEndereco;

	// bi-directional many-to-one association to EnderecosPessoa
	@OneToMany(mappedBy = "endereco")
	private List<EnderecoPessoa> enderecosPessoas;

	@Transient
	private EnumTipoEndereco tipoEndereco;

	public Endereco() {
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

	public BigDecimal getNumeroCaixaPostal() {
		return numeroCaixaPostal;
	}

	public void setNumeroCaixaPostal(BigDecimal numeroCaixaPostal) {
		this.numeroCaixaPostal = numeroCaixaPostal;
	}

	public String getNumeroImovel() {
		return numeroImovel;
	}

	public void setNumeroImovel(String numeroImovel) {
		this.numeroImovel = numeroImovel;
	}

	public Logradouro getLogradouro() {
		if (logradouro == null) {
			logradouro = new Logradouro();
		}
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public List<EnderecoPessoa> getEnderecosPessoas() {
		return enderecosPessoas;
	}

	public void setEnderecosPessoas(List<EnderecoPessoa> enderecosPessoas) {
		this.enderecosPessoas = enderecosPessoas;
	}

	public EnumTipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Long getIdZonaEndereco() {
		if (zonaEndereco != null) {
			idZonaEndereco = zonaEndereco.getId();
		}
		return idZonaEndereco;
	}

	public void setIdZonaEndereco(Long idZonaEndereco) {
		this.idZonaEndereco = idZonaEndereco;
		if (idZonaEndereco != null) {
			zonaEndereco = EnumeradorUtil.getConstante(EnumZonaEndereco.class, idZonaEndereco);
		}
	}

	public EnumZonaEndereco getZonaEndereco() {
		if (zonaEndereco == null && idZonaEndereco != null) {
			zonaEndereco = EnumeradorUtil.getConstante(EnumZonaEndereco.class, idZonaEndereco);
		}
		return zonaEndereco;
	}

	public void setZonaEndereco(EnumZonaEndereco zonaEndereco) {
		this.zonaEndereco = zonaEndereco;
		if (this.zonaEndereco != null) {
			idZonaEndereco = zonaEndereco.getId();
		}
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}