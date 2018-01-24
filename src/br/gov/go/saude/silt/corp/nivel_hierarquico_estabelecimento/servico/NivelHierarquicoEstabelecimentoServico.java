package br.gov.go.saude.silt.corp.nivel_hierarquico_estabelecimento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.nivel_hierarquico_estabelecimento.entidade.NivelHierarquicoEstabelecimento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico NivelHierarquicoEstabelecimento.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class NivelHierarquicoEstabelecimentoServico extends Servico<NivelHierarquicoEstabelecimento> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private NivelHierarquicoEstabelecimentoDAO dao;

	@Override
	protected DAO<NivelHierarquicoEstabelecimento> getDAO() {
		return dao;
	}

	public static NivelHierarquicoEstabelecimentoServico getInstancia() {
		return (NivelHierarquicoEstabelecimentoServico) SpringContainer.getInstancia().getBean("nivelHierarquicoEstabelecimentoServico");
	}

}
