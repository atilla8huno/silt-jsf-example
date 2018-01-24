package br.gov.go.saude.silt.corp.endereco_estabelecimento_saude.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.endereco_estabelecimento_saude.entidade.EnderecoEstabelecimentoSaude;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico EnderecoEstabelecimentoSaude.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class EnderecoEstabelecimentoSaudeServico extends Servico<EnderecoEstabelecimentoSaude> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EnderecoEstabelecimentoSaudeDAO dao;

	@Override
	protected DAO<EnderecoEstabelecimentoSaude> getDAO() {
		return dao;
	}

	public static EnderecoEstabelecimentoSaudeServico getInstancia() {
		return (EnderecoEstabelecimentoSaudeServico) SpringContainer.getInstancia().getBean("enderecoEstabelecimentoSaudeServico");
	}

}
