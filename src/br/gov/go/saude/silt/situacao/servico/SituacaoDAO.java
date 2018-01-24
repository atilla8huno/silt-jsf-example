package br.gov.go.saude.silt.situacao.servico;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.enumerador.SituacaoCasoNotificacaoEnum;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category DAO
 */
@Repository
public class SituacaoDAO extends DAO<Situacao> {

	private static final long serialVersionUID = 1L;

	/**
	 * Metodo realiza a consulta de uma determinada entidade podendo ser um consulta paginada ou nao.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<Situacao>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Situacao> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws DAOException {
		try {
			if (filtroPesquisa != null) {
				Criteria criteria = createCriteria();

				criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

				if (filtroPesquisa.getPosicaoPrimeiraLinha() != null) {
					criteria.setFirstResult(filtroPesquisa.getPosicaoPrimeiraLinha());
				}

				if (filtroPesquisa.getMaximoPorPagina() != null) {
					criteria.setMaxResults(filtroPesquisa.getMaximoPorPagina());
				}

				for (Map.Entry<String, Object> item : filtroPesquisa.getCampos().entrySet()) {
					if (item != null && item.getValue() != null) {

						if (item.getValue() instanceof String) {

							criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
						} else if (item.getValue() instanceof StatusEnum && !item.getValue().equals(StatusEnum.TODOS)) {

							criteria.add(Restrictions.eq("idStatus", ((StatusEnum) item.getValue()).getId()));
						}
					}
				}

				if (filtroPesquisa.getCamposOrdenacaoPesquisa() != null && !filtroPesquisa.getCamposOrdenacaoPesquisa().isEmpty()) {
					for (String item : filtroPesquisa.getCamposOrdenacaoPesquisa()) {
						criteria.addOrder(Order.asc(item));
					}
				}

				return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * 
	 * @param situacao
	 * @return
	 * @throws DAOException
	 */
	protected Situacao consultarPorDescricao(SituacaoCasoNotificacaoEnum situacao) throws DAOException {
		try {
			Criteria criteria = null;
			if (situacao != null) {
				criteria = createCriteria();
				criteria.add(Restrictions.eq("descricao", situacao.getValue()));
			}
			return (Situacao) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	/**
	 * Retorna a quantidade de linhas de uma determinada consulta.
	 * 
	 * @param filtros
	 * @return
	 * @throws DAOException
	 */
	@Override
	protected Long getNumeroLinhas(Map<String, Object> filtros) throws DAOException {
		try {
			Criteria criteria = createCriteria();

			criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

			for (Map.Entry<String, Object> item : filtros.entrySet()) {
				if (item != null && item.getValue() != null) {

					if (item.getValue() instanceof String) {

						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
					} else if (item.getValue() instanceof StatusEnum && !item.getValue().equals(StatusEnum.TODOS)) {

						criteria.add(Restrictions.eq("idStatus", ((StatusEnum) item.getValue()).getId()));
					}
				}
			}

			criteria.setProjection(Projections.rowCount());

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
