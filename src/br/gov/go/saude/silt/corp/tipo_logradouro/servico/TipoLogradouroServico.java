package br.gov.go.saude.silt.corp.tipo_logradouro.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico TipoLogradouro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class TipoLogradouroServico extends Servico<TipoLogradouro> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoLogradouroDAO dao;

	@Override
	protected DAO<TipoLogradouro> getDAO() {
		return dao;
	}

	public static TipoLogradouroServico getInstancia() {
		return (TipoLogradouroServico) SpringContainer.getInstancia().getBean("tipoLogradouroServico");
	}

}
