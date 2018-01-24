package br.gov.go.saude.silt.dose.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category DAO
 */
@Repository
public class DoseDAO extends DAO<Dose> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de doses por Caso Notificacao.
	 * 
	 * @param 	CasoNotificacao casoNotificacao
	 * @return 	List<Dose>
	 * @throws 	DAOException
	 */
	@SuppressWarnings("all")
	protected List<Dose> consultarPorCasoNotificacao(CasoNotificacao casoNotificacao) throws DAOException {
		try {
			Criteria criteria = createCriteria().add(Restrictions.eq("casoNotificacao.id", casoNotificacao.getId()));
			criteria.addOrder(Order.asc("dataCadastro"));

			return (List<Dose>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
