package br.gov.go.saude.silt.corp.estabelecimento_saude.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico EstabelecimentoSaude.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EstabelecimentoSaudeServico extends Servico<EstabelecimentoSaude> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstabelecimentoSaudeDAO dao;

	/**
	 * Este metodo retorna uma lista de EstabelecimentoSaude de acordo com o nomeFantasia informado com limite de 10 itens.
	 * 
	 * @param nomeFantasia
	 * @return List<EstabelecimentoSaude>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<EstabelecimentoSaude> consultarPorNomeFantasia(String nomeFantasia) throws ServicoException {
		try {
			return dao.consultarPorNomeFantasia(nomeFantasia);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<EstabelecimentoSaude> getDAO() {
		return dao;
	}

	public static EstabelecimentoSaudeServico getInstancia() {
		return (EstabelecimentoSaudeServico) SpringContainer.getInstancia().getBean("estabelecimentoSaudeServico");
	}

}
