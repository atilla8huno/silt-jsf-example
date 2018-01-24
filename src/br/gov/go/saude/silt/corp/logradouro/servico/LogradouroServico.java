package br.gov.go.saude.silt.corp.logradouro.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Logradouro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class LogradouroServico extends Servico<Logradouro> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LogradouroDAO dao;

	/**
	 * Este método pesquisa os logradouros de acordo com CEP recebido.
	 * 
	 * @param Long cep
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorCEP(Long cep) throws ServicoException {
		try {
			return dao.consultarLogradourosPorCEP(cep);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método pesquisa os logradouros de acordo com Bairro recebido.
	 * 
	 * @param Bairro bairro
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorBairro(Bairro bairro) throws ServicoException {
		try {
			return dao.consultarLogradourosPorBairro(bairro);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método pesquisa os logradouros de acordo com Bairro e Tipo recebido.
	 * 
	 * @param Bairro bairro
	 * @param TipoLogradouro tipoLogradouro
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorBairroETipo(Bairro bairro, TipoLogradouro tipoLogradouro) throws ServicoException {
		try {
			return dao.consultarLogradourosPorBairroETipo(bairro, tipoLogradouro);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<Logradouro> getDAO() {
		return dao;
	}

	public static LogradouroServico getInstancia() {
		return (LogradouroServico) SpringContainer.getInstancia().getBean("logradouroServico");
	}

}
