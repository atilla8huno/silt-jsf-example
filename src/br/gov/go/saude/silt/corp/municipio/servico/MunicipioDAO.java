package br.gov.go.saude.silt.corp.municipio.servico;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia Municipio.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class MunicipioDAO extends DAO<Municipio> {

	private static final long serialVersionUID = 1L;
	
	/**
     * Este método pesquisa os municipios de acordo com Estado recebido.
     * 
     * @param	Long cep
     * @return	List<Municipio>
     * @throws 	DAOException
     */
	@SuppressWarnings("unchecked")
	protected List<Municipio> consultarPorUnidadeFederativa(UnidadeFederativa estado) throws DAOException {
        try {
        	Criteria criterio = createCriteria().createAlias("unidadeFederativa", "estado").addOrder(Order.asc("nome"));
        	
        	if (estado != null && !estado.isTransient()) {
        		criterio.add(Restrictions.eq("estado.codigo", new BigDecimal(estado.getId())));
        	}
        	
            return criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
