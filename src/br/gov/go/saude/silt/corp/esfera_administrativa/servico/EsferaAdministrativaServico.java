package br.gov.go.saude.silt.corp.esfera_administrativa.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.esfera_administrativa.entidade.EsferaAdministrativa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico EsferaAdministrativa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EsferaAdministrativaServico extends Servico<EsferaAdministrativa> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EsferaAdministrativaDAO dao;

	@Override
	protected DAO<EsferaAdministrativa> getDAO() {
		return dao;
	}

	public static EsferaAdministrativaServico getInstancia() {
		return (EsferaAdministrativaServico) SpringContainer.getInstancia().getBean("esferaAdministrativaServico");
	}

}
