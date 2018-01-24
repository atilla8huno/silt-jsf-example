package br.gov.go.saude.silt.solicitacao_transferencia.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.solicitacao_transferencia.entidade.SolicitacaoTransferencia;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class SolicitacaoTransferenciaServico extends Servico<SolicitacaoTransferencia> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SolicitacaoTransferenciaDAO dao;

	@Override
	protected DAO<SolicitacaoTransferencia> getDAO() {
		return dao;
	}
	
	public static SolicitacaoTransferenciaServico getInstancia() {
		return (SolicitacaoTransferenciaServico) SpringContainer.getInstancia().getBean("solicitacaoTransferenciaServico");
	}
}
