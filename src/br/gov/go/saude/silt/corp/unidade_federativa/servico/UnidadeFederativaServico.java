package br.gov.go.saude.silt.corp.unidade_federativa.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico UnidadeFederativa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class UnidadeFederativaServico extends Servico<UnidadeFederativa> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UnidadeFederativaDAO dao;

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param Long codigo
	 * @return UnidadeFederativa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public UnidadeFederativa consultarPorId(Long codigo) throws ServicoException {
		try {
			return (UnidadeFederativa) dao.consultarPorId(codigo);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<UnidadeFederativa>
	 * @throws ServicoException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UnidadeFederativa> consultarTodos() throws ServicoException {
		try {
			return dao.consultarTodos();
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<UnidadeFederativa> getDAO() {
		return dao;
	}

	public static UnidadeFederativaServico getInstancia() {
		return (UnidadeFederativaServico) SpringContainer.getInstancia().getBean("unidadeFederativaServico");
	}
}
