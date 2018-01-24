package br.gov.go.saude.silt.dose.servico;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.caso_notificacao.servico.CasoNotificacaoServico;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.historico_caso_notificacao.servico.HistoricoCasoNotificacaoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.TipoAcaoUsuarioEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Servico
 */
@Service
public class DoseServico extends Servico<Dose> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HistoricoCasoNotificacaoServico historicoCasoNotificacaoServico;
	@Autowired
	private DoseDAO dao;
	@Autowired
	private CasoNotificacaoServico casoNotificacaoServico;

	/**
	 * Este método prepara e insere a entidade Dose no banco de dados.
	 * 
	 * @param  Dose entidade
	 * @return Dose
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Dose incluir(Dose entidade) throws ServicoException {
		try {
			if (entidade != null) {
				entidade.setDataCadastro(new Date());
				entidade.setExcluido(Boolean.FALSE);
				entidade.setObservacao(entidade.getObservacao().toUpperCase());
			}
			entidade = salvarOuAtualizar(entidade);
			historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade.getCasoNotificacao(), TipoAcaoUsuarioEnum.CADASTRO_DOSE,
				entidade.getObservacao());
			return entidade;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Retorna uma lista por casoNotificacao.
	 * 
	 * @param 	CasoNotificacao casoNotificacao
	 * @return 	List<Dose>
	 * @throws 	ServicoException
	 */
	public List<Dose> consultarPorCasoNotificacao(CasoNotificacao casoNotificacao) throws ServicoException {
		try {
			return dao.consultarPorCasoNotificacao(casoNotificacao);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param 	Long idCasoNotificacao
	 * @return 	CasoNotificacao
	 * @throws 	ServicoException
	 */
	public CasoNotificacao consultarCasoNotificacaoPorId(Long idCasoNotificacao) throws ServicoException {
		return casoNotificacaoServico.consultarPorId(idCasoNotificacao);
	}

	@Override
	protected DAO<Dose> getDAO() {
		return dao;
	}

	public static DoseServico getInstancia() {
		return (DoseServico) SpringContainer.getInstancia().getBean("doseServico");
	}
}
