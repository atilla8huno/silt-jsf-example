package br.gov.go.saude.silt.portal.perfil_sistema.servico;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia PerfilSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class PerfilSistemaDAO extends DAO<PerfilSistema> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param siglaSistema
	 * @return List<PerfilSistema>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<PerfilSistema> consultarPorSistema(String siglaSistema) throws DAOException {
		try {
			if (siglaSistema != null && !siglaSistema.isEmpty()) {

				StringBuilder hql = new StringBuilder();
				hql.append("SELECT perfil FROM PerfilSistema perfil WHERE perfil.siglaSistema LIKE :siglaSistema ").append("order by perfil.nomePerfil");

				Query query = entityManager.createQuery(hql.toString());

				query.setParameter("siglaSistema", siglaSistema.toUpperCase());

				return query.getResultList();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	protected PerfilSistema consultarPorId(Long id) throws DAOException {
		try {
			Criteria criteria = createCriteria();
			criteria.add(Restrictions.eq("id", id));
			return (PerfilSistema) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

}
