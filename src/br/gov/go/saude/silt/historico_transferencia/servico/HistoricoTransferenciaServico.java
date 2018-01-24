package br.gov.go.saude.silt.historico_transferencia.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.historico_transferencia.entidade.HistoricoTransferencia;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class HistoricoTransferenciaServico extends Servico<HistoricoTransferencia> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HistoricoTransferenciaDAO dao;

	/**
	 * 
	 * @param casoNotificacao
	 * @return
	 * @throws ServicoException
	 */
	public List<HistoricoTransferencia> consultarPorCasoNotificacao(CasoNotificacao casoNotificacao) throws ServicoException {
		try {
			return dao.consultarPorCasoNotificacao(casoNotificacao);
		} catch (Exception e) {
			throw new ServicoException(e);
		}

	}

	@Override
	protected DAO<HistoricoTransferencia> getDAO() {
		return dao;
	}

	public static HistoricoTransferenciaServico getInstancia() {
		return (HistoricoTransferenciaServico) SpringContainer.getInstancia().getBean("historicoTransferenciaServico");
	}
}
