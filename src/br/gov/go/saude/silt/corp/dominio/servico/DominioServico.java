package br.gov.go.saude.silt.corp.dominio.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.dominio.entidade.Dominio;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Dominio.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class DominioServico extends Servico<Dominio> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DominioDAO dao;

	@Override
	protected DAO<Dominio> getDAO() {
		return dao;
	}

	public static DominioServico getInstancia() {
		return (DominioServico) SpringContainer.getInstancia().getBean("dominioServico");
	}

}
