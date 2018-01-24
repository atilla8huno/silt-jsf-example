package br.gov.go.saude.silt.individuo.entidade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade
 */
@Entity
@Table(name = "individuo", schema = "silt")
public class Individuo extends Entidade {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "indi_id", nullable = false)
	private Long id;

	@Column(name = "indi_numero_cartao_sus")
	private String numeroCartaoSus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "indi_pfis_iden", nullable = false, insertable = true, updatable = true, unique = true)
	private PessoaFisica pessoaFisica;

	@OneToMany(mappedBy = "individuo")
	private List<CasoNotificacao> casoNotificacaos;

	@ManyToOne
	@JoinColumn(name = "indi_esta_id", nullable = false)
	private Estabelecimento estabelecimento;

	@Column(name = "indi_excluido")
	private boolean excluido;

	@Transient
	private EnderecoPessoa enderecoPessoa;
	
	@Transient
	private Set<EnderecoPessoa> enderecoPessoas;
	
	@Transient
	private ContatoPessoa contatoPessoa;
	
	@Transient
	private Set<ContatoPessoa> contatoPessoas;

	public Individuo() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroCartaoSus() {
		return numeroCartaoSus;
	}

	public void setNumeroCartaoSus(String numeroCartaoSus) {
		this.numeroCartaoSus = numeroCartaoSus;
	}

	public PessoaFisica getPessoaFisica() {
		if (pessoaFisica == null) {
			pessoaFisica = new PessoaFisica();
			pessoaFisica.setIndicadorDoadorOrgao("N");
		}
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<CasoNotificacao> getCasoNotificacaos() {
		return this.casoNotificacaos;
	}

	public void setCasoNotificacaos(List<CasoNotificacao> casoNotificacaos) {
		this.casoNotificacaos = casoNotificacaos;
	}

	public Estabelecimento getEstabelecimento() {
		return this.estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public boolean isExcluido() {
		return excluido;
	}

	@Override
	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public EnderecoPessoa getEnderecoPessoa() {
		if (enderecoPessoa == null) {
			enderecoPessoa = new EnderecoPessoa();
			enderecoPessoa.setEndereco(new Endereco());
			enderecoPessoa.setPessoa(this.getPessoaFisica().getPessoa());
		}
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(EnderecoPessoa enderecoPessoa) {
		this.enderecoPessoa = enderecoPessoa;
	}

	public Set<EnderecoPessoa> getEnderecoPessoas() {
		if (enderecoPessoas == null) {
			enderecoPessoas = new HashSet<EnderecoPessoa>();
		}
		return enderecoPessoas;
	}

	public void setEnderecoPessoas(Set<EnderecoPessoa> enderecoPessoas) {
		this.enderecoPessoas = enderecoPessoas;
	}

	public ContatoPessoa getContatoPessoa() {
		if (contatoPessoa == null) {
			contatoPessoa = new ContatoPessoa();
			contatoPessoa.setTipoContato(new TipoContato());
			contatoPessoa.setPessoa(new Pessoa());
		}
		return contatoPessoa;
	}

	public void setContatoPessoa(ContatoPessoa contatoPessoa) {
		this.contatoPessoa = contatoPessoa;
	}

	public Set<ContatoPessoa> getContatoPessoas() {
		if (contatoPessoas == null) {
			contatoPessoas = new HashSet<ContatoPessoa>();
		}
		return contatoPessoas;
	}

	public void setContatoPessoas(Set<ContatoPessoa> contatoPessoas) {
		this.contatoPessoas = contatoPessoas;
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
		Individuo other = (Individuo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}