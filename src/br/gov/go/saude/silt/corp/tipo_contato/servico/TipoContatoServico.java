package br.gov.go.saude.silt.corp.tipo_contato.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico TipoContato.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class TipoContatoServico extends Servico<TipoContato> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoContatoDAO dao;

	@Override
	protected DAO<TipoContato> getDAO() {
		return dao;
	}

	public static TipoContatoServico getInstancia() {
		return (TipoContatoServico) SpringContainer.getInstancia().getBean("tipoContatoServico");
	}

}
