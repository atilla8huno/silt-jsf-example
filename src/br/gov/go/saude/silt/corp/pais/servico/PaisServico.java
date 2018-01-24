package br.gov.go.saude.silt.corp.pais.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.pais.entidade.Pais;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Pais.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class PaisServico extends Servico<Pais> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PaisDAO dao;

	@Override
	protected DAO<Pais> getDAO() {
		return dao;
	}

	public static PaisServico getInstancia() {
		return (PaisServico) SpringContainer.getInstancia().getBean("paisServico");
	}

}
