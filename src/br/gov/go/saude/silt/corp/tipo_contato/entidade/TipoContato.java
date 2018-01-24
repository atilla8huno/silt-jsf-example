package br.gov.go.saude.silt.corp.tipo_contato.entidade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.gov.go.saude.silt.corp.contato_estabelecimento_saude.entidade.ContatoEstabelecimentoSaude;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * Entidade que representa TipoContato
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 244 $ $Author: atillabarros $ $Date: 2013-11-20 18:08:08 -0200 (Qua, 20 Nov 2013) $
 * @category Entidade
 */
@Entity
@Table(name = "tipo_contatos", schema = "corp")
public class TipoContato extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final static String TELEFONE_RESIDENCIAL	= "TELEFONE RESIDENCIAL";
	public final static String TELEFONE_CELULAR		= "TELEFONE CELULAR";
	public final static String EMAIL 				= "E-MAIL";

	@Id
	@Column(name = "tcon_tipo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tcon_descr")
	private String descricao;

	// bi-directional many-to-one association to ContatoEstabelecimentoSaude
	@OneToMany(mappedBy = "tipoContato")
	private List<ContatoEstabelecimentoSaude> contatoEstabelecimentosSaude;

	// bi-directional many-to-one association to ContatosPessoa
	@OneToMany(mappedBy = "tipoContato")
	private List<ContatoPessoa> contatosPessoas;

	public TipoContato() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ContatoEstabelecimentoSaude> getContatoEstabelecimentosSaude() {
		return contatoEstabelecimentosSaude;
	}

	public void setContatoEstabelecimentosSaude(List<ContatoEstabelecimentoSaude> contatoEstabelecimentosSaude) {
		this.contatoEstabelecimentosSaude = contatoEstabelecimentosSaude;
	}

	public List<ContatoPessoa> getContatosPessoas() {
		return contatosPessoas;
	}

	public void setContatosPessoas(List<ContatoPessoa> contatosPessoas) {
		this.contatosPessoas = contatosPessoas;
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
		TipoContato other = (TipoContato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}