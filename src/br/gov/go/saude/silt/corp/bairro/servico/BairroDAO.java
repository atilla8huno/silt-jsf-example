package br.gov.go.saude.silt.corp.bairro.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia Bairro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class BairroDAO extends DAO<Bairro> {
	
	private static final long serialVersionUID = 1L;

	/**
     * Este método pesquisa os bairros de acordo com Municipio recebido.
     * 
     * @param	Long cep
     * @return	List<Bairro>
     * @throws 	DAOException
     */
	@SuppressWarnings("unchecked")
	protected List<Bairro> consultarBairrosPorMunicipio(Municipio municipio) throws DAOException {
        try {
        	Criteria criterio = createCriteria().addOrder(Order.asc("nome"));
        	
        	if (municipio != null && !municipio.isTransient()) {
        		criterio.add(Restrictions.eq("municipio.id", municipio.getId()));
        	}
        	
            return criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
