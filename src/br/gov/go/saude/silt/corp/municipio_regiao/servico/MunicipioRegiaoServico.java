package br.gov.go.saude.silt.corp.municipio_regiao.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.municipio_regiao.entidade.MunicipioRegiao;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico MunicipioRegiao.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class MunicipioRegiaoServico extends Servico<MunicipioRegiao> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioRegiaoDAO dao;

	@Override
	protected DAO<MunicipioRegiao> getDAO() {
		return dao;
	}

	public static MunicipioRegiaoServico getInstancia() {
		return (MunicipioRegiaoServico) SpringContainer.getInstancia().getBean("municipioRegiaoServico");
	}

}
