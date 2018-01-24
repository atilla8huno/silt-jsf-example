package br.gov.go.saude.silt.historico_caso_notificacao.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class HistoricoCasoNotificacaoDAO extends DAO<HistoricoCasoNotificacao> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de historico por caso de notificacao. Com limite de 50 registro em ordenacao decrecente.
	 * 
	 * @param casoNotificacao
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("all")
	protected List<HistoricoCasoNotificacao> consultarPorCasoNotificacao(CasoNotificacao casoNotificacao) throws DAOException {
		try {
			if (casoNotificacao != null && !casoNotificacao.isTransient()) {
				Criteria criteria = createCriteria().add(Restrictions.eq("casoNotificacao.id", casoNotificacao.getId()));
				criteria.addOrder(Order.desc("dataCadastro"));
				criteria.setMaxResults(50);

				return (List<HistoricoCasoNotificacao>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
