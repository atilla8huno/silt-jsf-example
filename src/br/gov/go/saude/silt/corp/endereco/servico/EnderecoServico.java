package br.gov.go.saude.silt.corp.endereco.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco_pessoa.servico.EnderecoPessoaServico;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Endereco.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EnderecoServico extends Servico<Endereco> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EnderecoDAO dao;

	@Autowired
	private EnderecoPessoaServico enderecoPessoaServico;

	/**
	 * Realiza a inclusao de um endereco solicitando que seja inserido o relacionamento EnderecoPessoa.
	 * 
	 * @param pessoa
	 * @param lista
	 * @throws ServicoException
	 * @throws DAOException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void incluir(Pessoa pessoa, List<Endereco> lista) throws ServicoException, DAOException {
		try {
			if (pessoa.isTransient() && lista != null) {
				for (Endereco item : lista) {
					item = super.incluir(item);
					enderecoPessoaServico.salvarOuAtualizar(pessoa, item);
				}
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Realiza a exclusao do endereco solicitado a exclusao do relacionamento EnderecoPessoa.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluir(Endereco entidade) throws ServicoException {
		try {
			enderecoPessoaServico.excluirPorEndereco(entidade);
			super.excluir(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<Endereco> getDAO() {
		return dao;
	}

	public static EnderecoServico getInstancia() {
		return (EnderecoServico) SpringContainer.getInstancia().getBean("enderecoServico");
	}
}
