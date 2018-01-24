package br.gov.go.saude.silt.corp.estabelecimento_saude.servico;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia EstabelecimentoSaude.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class EstabelecimentoSaudeDAO extends DAO<EstabelecimentoSaude> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Este metodo retorna uma lista de EstabelecimentoSaude de acordo com o nomeFantasia informado com limite de 10 itens.
	 * 
	 * @param nomeFantasia
	 * @return List<EstabelecimentoSaude>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<EstabelecimentoSaude> consultarPorNomeFantasia(String nomeFantasia) throws DAOException {
		try {
			if (nomeFantasia != null && !nomeFantasia.isEmpty()) {

				StringBuilder hql = new StringBuilder();
				hql.append("SELECT est FROM EstabelecimentoSaude est WHERE est.id NOT IN (SELECT estabelecimentoSaude.id FROM Estabelecimento) ")
				   .append("and est.nomeFantasia LIKE :nomeFantasia and est.exclusao =:exclusao order by est.nomeFantasia");

				Query query = entityManager.createQuery(hql.toString());

				query.setParameter("nomeFantasia", "%" + nomeFantasia.toUpperCase() + "%");
				query.setParameter("exclusao", Boolean.FALSE);
				query.setMaxResults(10);

				return query.getResultList();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}
