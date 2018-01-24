package br.gov.go.saude.silt.corp.unidade_federativa.servico;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia UnidadeFederativa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class UnidadeFederativaDAO extends DAO<UnidadeFederativa> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param	Long codigo
	 * @return	UnidadeFederativa
	 * @throws	DAOException
	 */
	@Override
	protected UnidadeFederativa consultarPorId(Long codigo) throws DAOException {
		try {
        	Criteria criterio = createCriteria();
        	
        	if (codigo != null && codigo > 0L) {
        		criterio.add(Restrictions.eq("codigo", new BigDecimal(codigo)));
        	}
        	
            return (UnidadeFederativa) criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
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
	@Override
	@SuppressWarnings("unchecked")
	protected List<UnidadeFederativa> consultarTodos() throws DAOException {
		try {
			Criteria criteria = createCriteria();
					
			criteria.add(Restrictions.ne("codigo", new BigDecimal(1)));
			criteria.addOrder(Order.asc("sigla"));

			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
