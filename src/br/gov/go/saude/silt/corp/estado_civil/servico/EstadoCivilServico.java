package br.gov.go.saude.silt.corp.estado_civil.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.estado_civil.entidade.EstadoCivil;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico EstadoCivil.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EstadoCivilServico extends Servico<EstadoCivil> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EstadoCivilDAO dao;

	@Override
	protected DAO<EstadoCivil> getDAO() {
		return dao;
	}

	public static EstadoCivilServico getInstancia() {
		return (EstadoCivilServico) SpringContainer.getInstancia().getBean("estadoCivilServico");
	}

}
