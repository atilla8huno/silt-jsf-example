package br.gov.go.saude.silt.corp.logradouro.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia Logradouro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class LogradouroDAO extends DAO<Logradouro> {

	private static final long serialVersionUID = 1L;
	
	/**
     * Este método pesquisa os logradouros de acordo com CEP recebido.
     * 
     * @param	Long cep
     * @return	List<Logradouro>
     * @throws 	DAOException
     */
	@SuppressWarnings("unchecked")
	protected List<Logradouro> consultarLogradourosPorCEP(Long cep) throws DAOException {
        try {
        	Criteria criterio = createCriteria().addOrder(Order.asc("nome"));
        	
        	if (cep != null && cep > 0L) {
        		criterio.add(Restrictions.eq("cep", cep));
        	}
        	
            return criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
	
	/**
     * Este método pesquisa os logradouros de acordo com Bairro recebido.
     * 
     * @param	Bairro bairro
     * @return	List<Logradouro>
     * @throws 	DAOException
     */
	@SuppressWarnings("unchecked")
	protected List<Logradouro> consultarLogradourosPorBairro(Bairro bairro) throws DAOException {
        try {
        	Criteria criterio = createCriteria().addOrder(Order.asc("nome"));
        	
        	if (bairro != null && !bairro.isTransient()) {
        		criterio.add(Restrictions.eq("bairro.id", bairro.getId()));
        	}
        	
            return criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
	
	/**
     * Este método pesquisa os logradouros de acordo com Bairro recebido.
     * 
     * @param	Bairro bairro
     * @param	TipoLogradouro tipo
     * @return	List<Logradouro>
     * @throws 	DAOException
     */
	@SuppressWarnings("unchecked")
	protected List<Logradouro> consultarLogradourosPorBairroETipo(Bairro bairro, TipoLogradouro tipoLogradouro) throws DAOException {
        try {
        	Criteria criterio = createCriteria().addOrder(Order.asc("nome"));
        	
        	if (bairro != null && !bairro.isTransient()) {
        		criterio.add(Restrictions.eq("bairro.id", bairro.getId()));
        	}
        	
        	if (tipoLogradouro != null && !tipoLogradouro.isTransient()) {
        		criterio.add(Restrictions.eq("tipoLogradouro.id", tipoLogradouro.getId()));
        	}
        	
            return criterio.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
