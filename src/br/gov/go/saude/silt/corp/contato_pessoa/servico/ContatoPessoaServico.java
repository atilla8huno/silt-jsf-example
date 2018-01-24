package br.gov.go.saude.silt.corp.contato_pessoa.servico;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico ContatoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class ContatoPessoaServico extends Servico<ContatoPessoa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContatoPessoaDAO dao;

	@Override
	protected DAO<ContatoPessoa> getDAO() {
		return dao;
	}

	/**
	 * Este método persiste uma lista de contatos.
	 * 
	 * @param Pessoa pessoa
	 * @param Set<ContatoPessoa> lista
	 * @throws ServicoException
	 * @return Set<ContatoPessoa>
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Set<ContatoPessoa> incluirContatos(Pessoa pessoa, Set<ContatoPessoa> lista) throws ServicoException {
		try {
			if (lista != null && pessoa != null) {
				for (ContatoPessoa item : lista) {
					item.setId(null);
					item.setPessoa(pessoa);
					item = salvarOuAtualizar(item);
				}
			}
			return lista;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
	 * Este método consulta todos os contatos relacionados a pessoa.
	 * 
	 * @param PessoaFisica pessoa
	 * @return List<ContatoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ContatoPessoa> consultarPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		try {
			return dao.consultarPorPessoaFisica(pessoa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	public static ContatoPessoaServico getInstancia() {
		return (ContatoPessoaServico) SpringContainer.getInstancia().getBean("contatoPessoaServico");
	}
}
