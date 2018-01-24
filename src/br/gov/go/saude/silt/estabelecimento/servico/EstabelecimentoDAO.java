package br.gov.go.saude.silt.estabelecimento.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category DAO
 */
@Repository
public class EstabelecimentoDAO extends DAO<Estabelecimento> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	@Override
	protected List<Estabelecimento> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws DAOException {
		try {
			if (filtroPesquisa != null) {
				Criteria criteria = createCriteria().createAlias("estabelecimentoSaude", "estabSaude");
				
				createCriteria().createAlias("estabelecimentoNucleo", "estabelecimentoNucleo");
				
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
						} else {
							criteria.add(Restrictions.eq(item.getKey(), item.getValue()));
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
	 * Retorna a quantidade de linhas de uma determinada consulta.
	 * 
	 * @param Map<String, Object> filtros
	 * @return Long
	 * @throws DAOException
	 */
	@Override
	protected Long getNumeroLinhas(Map<String, Object> filtros) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("estabelecimentoSaude", "estabSaude");

			criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

			for (Map.Entry<String, Object> item : filtros.entrySet()) {
				if (item != null && item.getValue() != null) {
					if (item.getValue() instanceof String) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
					} else {
						criteria.add(Restrictions.eq(item.getKey(), item.getValue()));
					}
				}
			}

			criteria.setProjection(Projections.rowCount());

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este metodo retorna uma lista de Estabelecimento de acordo com o nomeFantasia informado com limite de 10 itens.
	 * 
	 * @param String nomeFantasia
	 * @return List<Estabelecimento>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<Estabelecimento> consultarPorNomeFantasia(String nomeFantasia) throws DAOException {
		try {
			if (nomeFantasia != null && !nomeFantasia.isEmpty()) {

				StringBuilder hql = new StringBuilder();
				hql.append("SELECT est FROM Estabelecimento est WHERE est.estabelecimentoSaude.nomeFantasia LIKE :nomeFantasia and est.excluido =:excluido ")
						// .append(" and est.id in :lista ")
						.append(" order by est.estabelecimentoSaude.nomeFantasia");

				Query query = entityManager.createQuery(hql.toString());

				query.setParameter("nomeFantasia", "%" + nomeFantasia.toUpperCase() + "%");
				query.setParameter("excluido", Boolean.FALSE);
				// query.setParameter("lista", getListaEstabelecimentosLiberados());
				query.setMaxResults(20);

				return query.getResultList();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Metodo retorna uma lista de apenas estabelecimento que sejam nucleos.
	 * 
	 * @param String nomeFantasia
	 * @return List<Estabelecimento>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<Estabelecimento> consultarEstabelecimentoNucleoPorNomeFantasia(String nomeFantasia) throws DAOException {
		try {
			if (nomeFantasia != null && !nomeFantasia.isEmpty()) {

				StringBuilder hql = new StringBuilder();
				hql.append("SELECT est FROM Estabelecimento est WHERE est.estabelecimentoSaude.nomeFantasia LIKE :nomeFantasia and est.excluido =:excluido")
						.append(" and est.estabelecimentoNucleo is null order by est.estabelecimentoSaude.nomeFantasia");

				Query query = entityManager.createQuery(hql.toString());

				query.setParameter("nomeFantasia", "%" + nomeFantasia.toUpperCase() + "%");
				query.setParameter("excluido", Boolean.FALSE);
				query.setMaxResults(10);

				return query.getResultList();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Metodo retorna uma lista dos ids do estabelecimentos que serao utilizados para realizar os filtros de pesquisa para as listagens. Garantindo que o
	 * usuario nao veja dados que nao fazem parte do seu estabelecimento.
	 * 
	 * @param UsuarioEstabelecimento usuario
	 * @return List<Long>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<Long> consultarIdEstabelecimentos(UsuarioEstabelecimento usuario) throws DAOException {
		List<Long> lista = new ArrayList<Long>();
		try {
			if (usuario != null) {
				if (usuario.getEstabelecimento().getNucleo()) {
					Query query = entityManager
							.createQuery("SELECT est.id FROM Estabelecimento est WHERE est.estabelecimentoNucleo.id =:idNucleo and est.excluido =:excluido");

					query.setParameter("idNucleo", usuario.getEstabelecimento().getId());
					query.setParameter("excluido", Boolean.FALSE);

					lista = query.getResultList();
				}

				lista.add(usuario.getEstabelecimento().getId());
			}
			return lista;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
