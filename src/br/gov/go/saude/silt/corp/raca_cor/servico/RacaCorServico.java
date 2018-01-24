package br.gov.go.saude.silt.corp.raca_cor.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico RacaCor.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class RacaCorServico extends Servico<RacaCor> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RacaCorDAO dao;

	@Override
	protected DAO<RacaCor> getDAO() {
		return dao;
	}

	public static RacaCorServico getInstancia() {
		return (RacaCorServico) SpringContainer.getInstancia().getBean("racaCorServico");
	}

}
