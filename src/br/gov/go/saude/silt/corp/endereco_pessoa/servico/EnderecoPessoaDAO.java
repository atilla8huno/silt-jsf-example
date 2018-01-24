package br.gov.go.saude.silt.corp.endereco_pessoa.servico;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.endereco.entidade.Endereco;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia EnderecoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class EnderecoPessoaDAO extends DAO<EnderecoPessoa> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Remove EnderecoPessoa por endereco.
	 * 
	 * @param endereco
	 * @throws DAOException
	 */
	protected void excluirPorEndereco(Endereco endereco) throws DAOException {
		try {
			Query q = entityManager.createQuery("delete from EnderecoPessoa e where e.endereco.id= :idEndereco");
			q.setParameter("idEndereco", String.valueOf(endereco.getId())).executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Este método consulta todos os endereços relacionados a pessoa.
	 * 
	 * @param  PessoaFisica pessoa
	 * @return List<EnderecoPessoa>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<EnderecoPessoa> consultarPorPessoaFisica(PessoaFisica pessoa) throws DAOException {
		try {
			if (pessoa != null && !pessoa.isTransient()) {
				Criteria criteria = createCriteria().add(Restrictions.eq("pessoa.id", pessoa.getId()));
				
				return (List<EnderecoPessoa>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
