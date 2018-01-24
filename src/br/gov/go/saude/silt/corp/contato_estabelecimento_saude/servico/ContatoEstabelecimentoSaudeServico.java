package br.gov.go.saude.silt.corp.contato_estabelecimento_saude.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.contato_estabelecimento_saude.entidade.ContatoEstabelecimentoSaude;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico ContatoEstabelecimentoSaude.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class ContatoEstabelecimentoSaudeServico extends Servico<ContatoEstabelecimentoSaude> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContatoEstabelecimentoSaudeDAO dao;

	@Override
	protected DAO<ContatoEstabelecimentoSaude> getDAO() {
		return dao;
	}

	public static ContatoEstabelecimentoSaudeServico getInstancia() {
		return (ContatoEstabelecimentoSaudeServico) SpringContainer.getInstancia().getBean("contatoEstabelecimentoSaudeServico");
	}

}
