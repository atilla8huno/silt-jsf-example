package br.gov.go.saude.silt.historico_caso_notificacao.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.TipoAcaoUsuarioEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class HistoricoCasoNotificacaoServico extends Servico<HistoricoCasoNotificacao> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HistoricoCasoNotificacaoDAO dao;

	/**
	 * Metodo adiciona um historico de qualquer alteracao realizada em um caso de notificacao.
	 * 
	 * @param casoNotificacao
	 * @param tipoAlteracaoEnum
	 * @param observacao
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addHistoricoCasoNotificacao(CasoNotificacao casoNotificacao, TipoAcaoUsuarioEnum tipoAcao, String observacao) throws ServicoException {
		try {
			HistoricoCasoNotificacao historico = new HistoricoCasoNotificacao();
			historico.setCasoNotificacao(casoNotificacao);
			historico.setDataCadastro(new Date());
			historico.setExcluido(Boolean.FALSE);
			historico.setUsuarioEstabelecimento(getUsuarioEstabelecimento());
			historico.setTipoAcao(tipoAcao);
			if (observacao != null) {
				historico.setObservacao(observacao.toUpperCase());
			}
			historico.setSituacao(casoNotificacao.getSituacao());

			super.salvarOuAtualizar(historico);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * 
	 * @param casoNotificacao
	 * @return
	 * @throws ServicoException
	 */
	public List<HistoricoCasoNotificacao> consultarPorCasoNotificacao(CasoNotificacao casoNotificacao) throws ServicoException {
		try {
			return dao.consultarPorCasoNotificacao(casoNotificacao);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<HistoricoCasoNotificacao> getDAO() {
		return dao;
	}

	public static HistoricoCasoNotificacaoServico getInstancia() {
		return (HistoricoCasoNotificacaoServico) SpringContainer.getInstancia().getBean("historicoCasoNotificacaoServico");
	}
}
