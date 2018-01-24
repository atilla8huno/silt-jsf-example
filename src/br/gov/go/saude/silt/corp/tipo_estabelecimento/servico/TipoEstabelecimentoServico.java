package br.gov.go.saude.silt.corp.tipo_estabelecimento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.tipo_estabelecimento.entidade.TipoEstabelecimento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico TipoEstabelecimento.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Service
 */
@Service
public class TipoEstabelecimentoServico extends Servico<TipoEstabelecimento> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoEstabelecimentoDAO dao;

	@Override
	protected DAO<TipoEstabelecimento> getDAO() {
		return dao;
	}

	public static TipoEstabelecimentoServico getInstancia() {
		return (TipoEstabelecimentoServico) SpringContainer.getInstancia().getBean("tipoEstabelecimentoServico");
	}

}
