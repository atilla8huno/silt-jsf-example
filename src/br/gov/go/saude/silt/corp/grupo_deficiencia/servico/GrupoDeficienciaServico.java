package br.gov.go.saude.silt.corp.grupo_deficiencia.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.grupo_deficiencia.entidade.GrupoDeficiencia;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico GrupoDeficiencia.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class GrupoDeficienciaServico extends Servico<GrupoDeficiencia> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private GrupoDeficienciaDAO dao;

	@Override
	protected DAO<GrupoDeficiencia> getDAO() {
		return dao;
	}

	public static GrupoDeficienciaServico getInstancia() {
		return (GrupoDeficienciaServico) SpringContainer.getInstancia().getBean("grupoDeficienciaServico");
	}

}
