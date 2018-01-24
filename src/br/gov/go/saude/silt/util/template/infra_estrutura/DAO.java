package br.gov.go.saude.silt.util.template.infra_estrutura;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.gov.go.saude.silt.util.AutenticacaoSilt;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Átilla Barros
 * @version $Rev: 304 $ $Author: atillabarros $ $Date: 2014-02-17 17:19:49 -0300 (Seg, 17 Fev 2014) $
 * @category DAO Genérico
 */
public abstract class DAO<T extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Class<T> classeDaEntidade;

	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @return entidade
	 * @throws DAOException
	 */
	protected T salvarOuAtualizar(T entidade) throws DAOException {
		try {
			return (T) entityManager.merge(entidade);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método cria um objeto Criteria da entidade.
	 * 
	 * @return Criteria
	 * @throws DAOException
	 */
	protected Criteria createCriteria() throws DAOException {
		try {
			Session session = ((Session) entityManager.getDelegate());
			return session.createCriteria(getClasseDaEntidade());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método instância e retorna um objeto Session do Hibernate.
	 * 
	 * @return Session
	 */
	protected Session getSession() {
		return ((Session) entityManager.getDelegate());
	}

	/**
	 * Este método recebe uma uma entidade por parâmetro e a exclui no banco de dados.
	 * 
	 * @param entidade
	 * @throws DAOException
	 */
	protected void excluir(T entidade) throws DAOException {
		try {
			Query query = null;
			if (isTemAtributo("excluido") || isTemAtributo("exclusao")) {
				query = entityManager.createQuery("UPDATE " + getNomeDaEntidade() + " e SET excluido = TRUE WHERE e.id = :id");
			} else {
				query = entityManager.createQuery("DELETE FROM " + getNomeDaEntidade() + " e WHERE e.id = :id");
			}
			query.setParameter("id", entidade.getId());
			
			query.executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param codigo
	 * @return entidade
	 * @throws DAOException
	 */
	protected T consultarPorId(Long codigo) throws DAOException {
		try {
			return (T) entityManager.find(getClasseDaEntidade(), codigo);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<T>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<T> consultarTodos() throws DAOException {
		try {
			Criteria criteria = createCriteria();

			if (isTemAtributo("excluido")) {
				criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

			} else if (isTemAtributo("exclusao")) {
				criteria.add(Restrictions.eq("exclusao", Boolean.FALSE));
			}

			addRestricaoCriteriaPorEstabelecimento(criteria);

			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Metodo realiza a consulta de uma determinada entidade podendo ser um consulta paginada ou nao.
	 * 
	 * @param filtroPesquisa
	 * @return List<T>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<T> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws DAOException {
		try {
			if (filtroPesquisa != null) {
				Criteria criteria = createCriteria();

				if (filtroPesquisa.getPosicaoPrimeiraLinha() != null) {
					criteria.setFirstResult(filtroPesquisa.getPosicaoPrimeiraLinha());
				}

				if (filtroPesquisa.getMaximoPorPagina() != null) {
					criteria.setMaxResults(filtroPesquisa.getMaximoPorPagina());
				}

				if (isTemAtributo("excluido")) {
					criteria.add(Restrictions.eq("excluido", Boolean.FALSE));

				} else if (isTemAtributo("exclusao")) {
					criteria.add(Restrictions.eq("exclusao", Boolean.FALSE));
				}

				for (Map.Entry<String, Object> item : filtroPesquisa.getCampos().entrySet()) {
					if (item != null && item.getValue() != null) {
						criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
					}
				}

				if (filtroPesquisa.getCamposOrdenacaoPesquisa() != null && !filtroPesquisa.getCamposOrdenacaoPesquisa().isEmpty()) {
					for (String item : filtroPesquisa.getCamposOrdenacaoPesquisa()) {
						criteria.addOrder(Order.asc(item));
					}
				}

				addRestricaoCriteriaPorEstabelecimento(criteria);

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
	protected Long getNumeroLinhas(Map<String, Object> filtros) throws DAOException {
		try {
			Criteria criteria = createCriteria();

			if (isTemAtributo("exclusao")) {
				criteria.add(Restrictions.eq("exclusao", Boolean.FALSE));

			} else if (isTemAtributo("excluido")) {
				criteria.add(Restrictions.eq("excluido", Boolean.FALSE));
			}

			for (Map.Entry<String, Object> item : filtros.entrySet()) {
				if (item != null && item.getValue() != null) {
					criteria.add(Restrictions.ilike(item.getKey(), "%" + item.getValue() + "%"));
				}
			}

			addRestricaoCriteriaPorEstabelecimento(criteria);

			criteria.setProjection(Projections.rowCount());

			return (Long) criteria.uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método verifica se a entidade possui um atributo com mesmo nome recebido por argumento.
	 * 
	 * @param Class<T> clazz
	 * @param String nomeAtributo
	 * @return boolean
	 */
	protected boolean isTemAtributo(String nomeAtributo) {
		try {
			Field[] propriedades = getClasseDaEntidade().getDeclaredFields();
			for (Field propriedade : propriedades) {
				if (propriedade.getName().equals(nomeAtributo)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Adiciona o filtro por estabelecimento que o usuario pode ter acesso.
	 * 
	 * @param criteria
	 */
	protected void addRestricaoCriteriaPorEstabelecimento(Criteria criteria) {
		if (isTemAtributo("estabelecimento")) {
			criteria.add(Restrictions.in("estabelecimento.id", getListaEstabelecimentosLiberados()));
		}
	}

	/**
	 * Este metodo retorna uma lista de ids dos estabelecimentos liberados para consulta para cada usuario.
	 * 
	 * @return
	 */
	protected List<Long> getListaEstabelecimentosLiberados() {
		return AutenticacaoSilt.getListaEstabelecimentosLiberados();
	}

	/**
	 * Método construtor
	 */
	@SuppressWarnings("unchecked")
	public DAO() {
		setClasseDaEntidade((Class<T>) getClasseGenerica(this.getClass()));
	}

	private static Class<?> getClasseGenerica(Class<?> classe) {
		if (classe == null)
			return null;

		if (classe.getGenericSuperclass() instanceof ParameterizedType)
			return (Class<?>) ((ParameterizedType) classe.getGenericSuperclass()).getActualTypeArguments()[0];

		return null;
	}

	private String getNomeDaEntidade() {
		return getClasseDaEntidade().getSimpleName();
	}

	private Class<T> getClasseDaEntidade() {
		return classeDaEntidade;
	}

	private void setClasseDaEntidade(Class<T> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}
}
