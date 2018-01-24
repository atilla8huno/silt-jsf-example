package br.gov.go.saude.silt.corp.deficiencia.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.deficiencia.entidade.Deficiencia;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Deficiencia.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class DeficienciaServico extends Servico<Deficiencia> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DeficienciaDAO dao;

	@Override
	protected DAO<Deficiencia> getDAO() {
		return dao;
	}

	public static DeficienciaServico getInstancia() {
		return (DeficienciaServico) SpringContainer.getInstancia().getBean("deficienciaServico");
	}

}
