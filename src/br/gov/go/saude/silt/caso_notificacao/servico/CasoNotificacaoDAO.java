package br.gov.go.saude.silt.caso_notificacao.servico;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category DAO
 */
@Repository
public class CasoNotificacaoDAO extends DAO<CasoNotificacao> {

	private static final long serialVersionUID = 1L;

	/**
	 * Este método realiza a consulta de Casos de Notificação, podendo ser paginada ou nao.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<CasoNotificacao>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<CasoNotificacao> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws DAOException {
		try {
			if (filtroPesquisa != null) {
				Criteria criteria = createCriteria().add(Restrictions.eq("excluido", Boolean.FALSE));

				if (filtroPesquisa.getPosicaoPrimeiraLinha() != null) {
					criteria.setFirstResult(filtroPesquisa.getPosicaoPrimeiraLinha());
				}

				if (filtroPesquisa.getMaximoPorPagina() != null) {
					criteria.setMaxResults(filtroPesquisa.getMaximoPorPagina());
				}

				Date dataInicio = null, dataFim = null;
				for (Map.Entry<String, Object> item : filtroPesquisa.getCampos().entrySet()) {
					if (item != null && item.getValue() != null) {

						if (item.getValue() instanceof String)
							criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

						else if (item.getValue() instanceof Estabelecimento && !((Estabelecimento) item.getValue()).isTransient())
							criteria.add(Restrictions.eq("estabelecimento.id", ((Estabelecimento) item.getValue()).getId()));

						else if (item.getValue() instanceof Individuo && !((Individuo) item.getValue()).isTransient())
							criteria.add(Restrictions.eq("individuo.id", ((Individuo) item.getValue()).getId()));

						else if (item.getValue() instanceof Situacao && !((Situacao) item.getValue()).isTransient())
							criteria.add(Restrictions.eq("situacao.id", ((Situacao) item.getValue()).getId()));

						else if (item.getValue() instanceof Date && item.getKey().equals("dataNotificacaoInicio"))
							dataInicio = (Date) item.getValue();

						else if (item.getValue() instanceof Date && item.getKey().equals("dataNotificacaoFim"))
							dataFim = (Date) item.getValue();
					}
				}

				if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
					criteria.add(Restrictions.between("dataNotificacao", dataInicio, dataFim));
				}

				if (filtroPesquisa.getCamposOrdenacaoPesquisa() != null && !filtroPesquisa.getCamposOrdenacaoPesquisa().isEmpty()) {
					for (String item : filtroPesquisa.getCamposOrdenacaoPesquisa()) {
						criteria.addOrder(Order.asc(item));
					}
				}

				// Adiciona o filtro por Estabelecimento
				// addRestricaoCriteriaPorEstabelecimento(criteria);

				return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			}
			return null;
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
			Criteria criteria = createCriteria().add(Restrictions.eq("excluido", Boolean.FALSE));

			Date dataInicio = null, dataFim = null;
			for (Map.Entry<String, Object> item : filtros.entrySet()) {
				if (item != null && item.getValue() != null) {
					if (item.getValue() instanceof String)
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

					else if (item.getValue() instanceof Estabelecimento && !((Estabelecimento) item.getValue()).isTransient())
						criteria.add(Restrictions.eq("estabelecimento.id", ((Estabelecimento) item.getValue()).getId()));

					else if (item.getValue() instanceof Individuo && !((Individuo) item.getValue()).isTransient())
						criteria.add(Restrictions.eq("individuo.id", ((Individuo) item.getValue()).getId()));

					else if (item.getValue() instanceof Situacao && !((Situacao) item.getValue()).isTransient())
						criteria.add(Restrictions.eq("situacao.id", ((Situacao) item.getValue()).getId()));

					else if (item.getValue() instanceof Date && item.getKey().equals("dataNotificacaoInicio"))
						dataInicio = (Date) item.getValue();

					else if (item.getValue() instanceof Date && item.getKey().equals("dataNotificacaoFim"))
						dataFim = (Date) item.getValue();
				}
			}

			if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
				criteria.add(Restrictions.between("dataNotificacao", dataInicio, dataFim));
			}

			criteria.setProjection(Projections.rowCount());

			// Realiza o filtro por Estabelecimento
			// addRestricaoCriteriaPorEstabelecimento(criteria);

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
