package br.gov.go.saude.silt.corp.pessoa.entidade;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.fonetico_pessoa.entidade.FoneticoPessoa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa Pessoa
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 256 $ $Author: atillabarros $ $Date: 2013-11-27 15:44:06 -0200 (Qua, 27 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "pessoas", schema = "corp")
public class Pessoa extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pess_iden")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Type(type = "true_false")
	private Boolean exclusao;

	@Column(name = "pess_tipo")
	private String tipo;

	// bi-directional many-to-one association to ContatosPessoa
	@OneToMany(mappedBy = "pessoa")
	private Set<ContatoPessoa> contatosPessoas;

	// bi-directional many-to-one association to EnderecosPessoa
	@OneToMany(mappedBy = "pessoa")
	private Set<EnderecoPessoa> enderecosPessoas;

	// bi-directional many-to-one association to EstabelecimentoSaude
	@OneToMany(mappedBy = "pessoa")
	private List<EstabelecimentoSaude> estabelecimentosSaude;

	// bi-directional many-to-one association to FoneticosPessoa
	@OneToMany(mappedBy = "pessoa")
	private List<FoneticoPessoa> foneticosPessoas;

	@Transient
	private List<Endereco> enderecos;

	public Pessoa() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getExclusao() {
		return exclusao;
	}

	public void setExclusao(Boolean exclusao) {
		this.exclusao = exclusao;
	}

	public Set<ContatoPessoa> getContatosPessoas() {
		if (contatosPessoas == null) {
			contatosPessoas = new HashSet<ContatoPessoa>();
		}
		return contatosPessoas;
	}

	public void setContatosPessoas(Set<ContatoPessoa> contatosPessoas) {
		this.contatosPessoas = contatosPessoas;
	}

	public Set<EnderecoPessoa> getEnderecosPessoas() {
		if (enderecosPessoas == null) {
			enderecosPessoas = new HashSet<EnderecoPessoa>();
		}
		return enderecosPessoas;
	}

	public void setEnderecosPessoas(Set<EnderecoPessoa> enderecosPessoas) {
		this.enderecosPessoas = enderecosPessoas;
	}

	public List<EstabelecimentoSaude> getEstabelecimentosSaude() {
		return estabelecimentosSaude;
	}

	public void setEstabelecimentosSaude(List<EstabelecimentoSaude> estabelecimentosSaude) {
		this.estabelecimentosSaude = estabelecimentosSaude;
	}

	public List<FoneticoPessoa> getFoneticosPessoas() {
		return foneticosPessoas;
	}

	public void setFoneticosPessoas(List<FoneticoPessoa> foneticosPessoas) {
		this.foneticosPessoas = foneticosPessoas;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}