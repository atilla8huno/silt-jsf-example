package br.gov.go.saude.silt.corp.regiao_saude.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.regiao_saude.entidade.RegiaoSaude;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico RegiaoSaude.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class RegiaoSaudeServico extends Servico<RegiaoSaude> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RegiaoSaudeDAO dao;

	@Override
	protected DAO<RegiaoSaude> getDAO() {
		return dao;
	}

	public static RegiaoSaudeServico getInstancia() {
		return (RegiaoSaudeServico) SpringContainer.getInstancia().getBean("regiaoSaudeServico");
	}

}
