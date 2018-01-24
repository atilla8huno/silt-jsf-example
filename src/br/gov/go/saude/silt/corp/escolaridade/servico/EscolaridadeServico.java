package br.gov.go.saude.silt.corp.escolaridade.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Escolaridade.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EscolaridadeServico extends Servico<Escolaridade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EscolaridadeDAO dao;

	@Override
	protected DAO<Escolaridade> getDAO() {
		return dao;
	}

	public static EscolaridadeServico getInstancia() {
		return (EscolaridadeServico) SpringContainer.getInstancia().getBean("escolaridadeServico");
	}

}
