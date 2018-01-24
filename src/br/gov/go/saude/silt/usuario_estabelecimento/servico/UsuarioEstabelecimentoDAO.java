package br.gov.go.saude.silt.usuario_estabelecimento.servico;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.AutenticacaoSilt;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category DAO
 */
@Repository
@SuppressWarnings("all")
public class UsuarioEstabelecimentoDAO extends DAO<UsuarioEstabelecimento> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Este método consulta um UsuarioEstabelecimento pelo código.
	 * 
	 * @param  Long codigo
	 * @return UsuarioEstabelecimento
	 * @throws DAOException
	 */
	@Override
	protected UsuarioEstabelecimento consultarPorId(Long codigo) throws DAOException {
		try {
			UsuarioEstabelecimento entidade = entityManager.find(UsuarioEstabelecimento.class, codigo);
			return entidade;
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	/**
	 * Recupera um usuarioEstabelecimento pelo login informado.
	 * 
	 * @param String login
	 * @return UsuarioEstabelecimento
	 * @throws DAOException
	 */
	protected UsuarioEstabelecimento consultarPorUsuarioSistema(String login) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("usuarioSistema", "usuario");

			criteria.add(Restrictions.eq("usuario.login", login));
			criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

			return (UsuarioEstabelecimento) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	@Override
	protected List<UsuarioEstabelecimento> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws DAOException {
		try {
			if (filtroPesquisa != null) {
				Criteria criteria = createCriteria().createAlias("usuarioSistema", "usuario");

				criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

				if (filtroPesquisa.getPosicaoPrimeiraLinha() != null) {
					criteria.setFirstResult(filtroPesquisa.getPosicaoPrimeiraLinha());
				}

				if (filtroPesquisa.getMaximoPorPagina() != null) {
					criteria.setMaxResults(filtroPesquisa.getMaximoPorPagina());
				}

				for (Map.Entry<String, Object> item : filtroPesquisa.getCampos().entrySet()) {
					if (item != null && item.getValue() != null && !item.getValue().equals("")) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
					}
				}

				if (filtroPesquisa.getCamposOrdenacaoPesquisa() != null && !filtroPesquisa.getCamposOrdenacaoPesquisa().isEmpty()) {
					for (String item : filtroPesquisa.getCamposOrdenacaoPesquisa()) {
						criteria.addOrder(Order.asc(item));
					}
				}

				// Realiza o filtro por Estabelecimento
				// addRestricaoCriteriaPorEstabelecimento(criteria);

				return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	protected Long getNumeroLinhas(Map<String, Object> filtros) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("usuarioSistema", "usuario");

			criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

			for (Map.Entry<String, Object> item : filtros.entrySet()) {
				if (item != null && item.getValue() != null && !item.getValue().equals("")) {
					criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
				}
			}

			// Realiza o filtro por Estabelecimento
			// criteria.add(Restrictions.in("estabelecimento.id", getListaEstabelecimentosLiberados()));

			criteria.setProjection(Projections.rowCount());

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
