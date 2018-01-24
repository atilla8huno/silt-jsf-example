package br.gov.go.saude.silt.tipo_tratamento.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class TipoTratamentoDAO extends DAO<TipoTratamento> {

	private static final long serialVersionUID = 1L;
	
	/**
     * Este método pesquisa os registros de Tipo Tratamento de acordo com filtros recebidos.
     * 
     * @param 	String codigo
     * @param	String descricao
     * @return	List<TipoTratamento>
     * @throws 	DAOException
     */
    @SuppressWarnings("unchecked")
    protected List<TipoTratamento> consultarPorCodigoDescricao(String codigo, String descricao) throws DAOException {
        try {
        	Criteria criteria = createCriteria().addOrder(Order.asc("codigo"));
        	
        	if (codigo != null && !codigo.isEmpty()) {
        		criteria.add(Restrictions.ilike("codigo", "%"+codigo+"%"));
        	}
        	
        	if (descricao != null && !descricao.isEmpty()) {
        		criteria.add(Restrictions.ilike("descricao", "%"+descricao+"%"));
        	}
        	
            return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
