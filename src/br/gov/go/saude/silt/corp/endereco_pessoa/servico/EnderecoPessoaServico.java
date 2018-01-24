package br.gov.go.saude.silt.corp.endereco_pessoa.servico;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco.servico.EnderecoServico;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico EnderecoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Servico
 */
@Service
public class EnderecoPessoaServico extends Servico<EnderecoPessoa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EnderecoPessoaDAO dao;
	
	@Autowired
	private EnderecoServico enderecoServico;

	/**
	 * Este método insere uma Pessoa e um Endereco na entidade e a persiste.
	 * 
	 * @param Pessoa pessoa
	 * @param Endereco endereco
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvarOuAtualizar(Pessoa pessoa, Endereco endereco) throws ServicoException {
		try {
			if (!pessoa.isTransient() && !endereco.isTransient()) {
				EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
				enderecoPessoa.setEndereco(endereco);
				enderecoPessoa.setPessoa(pessoa);
				enderecoPessoa.setTipoEndereco(endereco.getTipoEndereco());
				super.salvarOuAtualizar(enderecoPessoa);
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	
	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param  EnderecoPessoa entidade
	 * @return EnderecoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public EnderecoPessoa salvarOuAtualizar(EnderecoPessoa entidade) throws ServicoException {
		verificarEntidade(entidade);
		try {
			if (entidade.getEndereco().isTransient()) {
				entidade.setEndereco(enderecoServico.salvarOuAtualizar(entidade.getEndereco()));
			}
			return super.salvarOuAtualizar(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}



	/**
	 * Este método persiste uma lista de endereços.
	 * 
	 * @param Pessoa pessoa
	 * @param Set<EnderecoPessoa> lista
	 * @throws ServicoException
	 * @return Set<EnderecoPessoa>
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Set<EnderecoPessoa> salvarEnderecos(Pessoa pessoa, Set<EnderecoPessoa> lista) throws ServicoException {
		try {
			if (lista != null && pessoa != null) {
				for (EnderecoPessoa item : lista) {
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
	 * Este método salva ou atualiza uma lista de endereços.
	 * 
	 * @param Set<EnderecoPessoa> enderecosPessoas
	 * @return Set<EnderecoPessoa>
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Set<EnderecoPessoa> salvarEnderecos(Set<EnderecoPessoa> enderecosPessoas) throws ServicoException {
		try {
			for (EnderecoPessoa enderecoPessoa : enderecosPessoas) {
				enderecoPessoa.setEndereco(enderecoServico.salvarOuAtualizar(enderecoPessoa.getEndereco()));
			}
			return enderecosPessoas;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
	 * Remove EnderecoPessoa por endereco.
	 * 
	 * @param endereco
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluirPorEndereco(Endereco endereco) throws ServicoException {
		try {
			if (endereco.isTransient()) {
				dao.excluirPorEndereco(endereco);
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
	 * Este método consulta todos os endereços relacionados a pessoa.
	 * 
	 * @param PessoaFisica pessoa
	 * @return List<EnderecoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<EnderecoPessoa> consultarPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		try {
			return dao.consultarPorPessoaFisica(pessoa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<EnderecoPessoa> getDAO() {
		return dao;
	}

	public static EnderecoPessoaServico getInstancia() {
		return (EnderecoPessoaServico) SpringContainer.getInstancia().getBean("enderecoPessoaServico");
	}
}
