package br.gov.go.saude.silt.condicao_risco.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Servico
 */
@Service
public class CondicaoRiscoServico extends Servico<CondicaoRisco> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CondicaoRiscoDAO dao;
	
	@Override
	protected DAO<CondicaoRisco> getDAO() {
		return dao;
	}

	public static CondicaoRiscoServico getInstancia() {
		return (CondicaoRiscoServico) SpringContainer.getInstancia().getBean("condicaoRiscoServico");
	}
}
