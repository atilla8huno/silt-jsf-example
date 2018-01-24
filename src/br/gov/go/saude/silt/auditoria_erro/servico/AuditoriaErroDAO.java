package br.gov.go.saude.silt.auditoria_erro.servico;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.auditoria_erro.entidade.AuditoriaErro;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 306 $ $Author: atillabarros $ $Date: 2014-02-20 16:10:35 -0300 (Qui, 20 Fev 2014) $
 * @category DAO
 */
@Repository
public class AuditoriaErroDAO extends DAO<AuditoriaErro> {

	private static final long serialVersionUID = 1L;

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @return entidade
	 * @throws DAOException
	 */
	@Override
	protected AuditoriaErro salvarOuAtualizar(AuditoriaErro entidade) throws DAOException {
		getSession().save(entidade);
		getSession().flush();

		return entidade;
	}

	/**
	 * Este método realiza a consulta de Erros Auditados.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<AuditoriaErro>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<AuditoriaErro> consultarPorFiltro(FiltroPesquisa filtro) throws DAOException {
		try {
			Criteria criteria = createCriteria();

			if (filtro.getPosicaoPrimeiraLinha() != null) {
				criteria.setFirstResult(filtro.getPosicaoPrimeiraLinha());
			}

			if (filtro.getMaximoPorPagina() != null) {
				criteria.setMaxResults(filtro.getMaximoPorPagina());
			}

			Date dataInicio = null, dataFim = null;
			for (Map.Entry<String, Object> item : filtro.getCampos().entrySet()) {
				if (item != null && item.getValue() != null) {

					if (item.getValue() instanceof String) {
						if (item.getKey().equals("login")) {
							criteria.createAlias("usuarioEstabelecimento", "usuario");
							criteria.createAlias("usuario.usuarioSistema", "usuarioSistema");

							criteria.add(Restrictions.ilike("usuarioSistema.login", "%" + item.getValue() + "%"));
						} else {
							criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

						}
					} else if (item.getValue() instanceof String) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataCadastroInicio")) {
						dataInicio = (Date) item.getValue();

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataCadastroFim")) {
						dataFim = (Date) item.getValue();

					}
				}
			}

			if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
				Calendar dataFimComHora = Calendar.getInstance();
				dataFimComHora.setTime(dataFim);
				dataFimComHora.set(Calendar.HOUR_OF_DAY, 23);
				dataFimComHora.set(Calendar.MINUTE, 59);
				dataFimComHora.set(Calendar.SECOND, 59);

				criteria.add(Restrictions.between("dataCadastro", dataInicio, dataFimComHora.getTime()));
			}

			if (filtro.getCamposOrdenacaoPesquisa() != null && !filtro.getCamposOrdenacaoPesquisa().isEmpty()) {
				for (String item : filtro.getCamposOrdenacaoPesquisa()) {
					criteria.addOrder(Order.asc(item));
				}
			}

			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retorna a quantidade de linhas da consulta por filtros.
	 * 
	 * @param Map<String, Object> filtro
	 * @return Long
	 * @throws DAOException
	 */
	@Override
	protected Long getNumeroLinhas(Map<String, Object> filtro) throws DAOException {
		try {
			Criteria criteria = createCriteria();

			Date dataInicio = null, dataFim = null;
			for (Map.Entry<String, Object> item : filtro.entrySet()) {
				if (item != null && item.getValue() != null) {

					if (item.getValue() instanceof String) {
						if (item.getKey().equals("login")) {
							criteria.createAlias("usuarioEstabelecimento", "usuario");
							criteria.createAlias("usuario.usuarioSistema", "usuarioSistema");

							criteria.add(Restrictions.ilike("usuarioSistema.login", "%" + item.getValue() + "%"));
						} else {
							criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

						}
					} else if (item.getValue() instanceof String) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataCadastroInicio")) {
						dataInicio = (Date) item.getValue();

					} else if (item.getValue() instanceof Date && item.getKey().equals("dataCadastroFim")) {
						dataFim = (Date) item.getValue();

					}
				}
			}

			if ((dataInicio != null && dataFim != null) && (dataInicio.before(dataFim) || dataFim.equals(dataInicio))) {
				Calendar dataFimComHora = Calendar.getInstance();
				dataFimComHora.setTime(dataFim);
				dataFimComHora.set(Calendar.HOUR_OF_DAY, 23);
				dataFimComHora.set(Calendar.MINUTE, 59);
				dataFimComHora.set(Calendar.SECOND, 59);

				criteria.add(Restrictions.between("dataCadastro", dataInicio, dataFimComHora.getTime()));
			}

			criteria.setProjection(Projections.rowCount());

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
