package br.gov.go.saude.silt.corp.atividade_estabelecimento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.atividade_estabelecimento.entidade.AtividadeEstabelecimento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico AtividadeEstabelecimento.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class AtividadeEstabelecimentoServico extends Servico<AtividadeEstabelecimento> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private AtividadeEstabelecimentoDAO dao;

	@Override
	protected DAO<AtividadeEstabelecimento> getDAO() {
		return dao;
	}

	public static AtividadeEstabelecimentoServico getInstancia() {
		return (AtividadeEstabelecimentoServico) SpringContainer.getInstancia().getBean("atividadeEstabelecimentoServico");
	}

}
