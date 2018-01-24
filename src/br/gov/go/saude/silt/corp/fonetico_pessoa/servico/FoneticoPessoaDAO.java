package br.gov.go.saude.silt.corp.fonetico_pessoa.servico;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.fonetico_pessoa.entidade.FoneticoPessoa;
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia FoneticoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
@SuppressWarnings("all")
public class FoneticoPessoaDAO extends DAO<FoneticoPessoa> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Excluir as fonetizações de uma pessoa especifica
	 * 
	 * @param pessoa
	 * @throws DAOException
	 */
	protected void excluirPorPessoa(Pessoa pessoa) throws DAOException {
		try {
			Query q = entityManager.createQuery("delete from FoneticoPessoa f where f.pessoa.id= :idPessoa");
			q.setParameter("idPessoa", String.valueOf(pessoa.getId())).executeUpdate();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Metodo para pesquisar as foneticas de uma pessoa especifica.
	 * 
	 * @param pessoa
	 * @return
	 * @throws DAOException
	 */
	protected List<FoneticoPessoa> consultarPorPessoa(Pessoa pessoa) throws DAOException {
		try {
			Criteria criteria = createCriteria().createAlias("foneticoPessoa", "fonetico");
			criteria.add(Restrictions.eq("fonetico.pessoa.id", pessoa.getId()));

			// eliminando resultados repetidos
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

	/**
	 * Metodo interno de inclusao de fonetica de uma pessoa especifica.
	 * 
	 * @param pessoa
	 * @param vetNomes
	 * @param tipo
	 * @throws DAOException
	 */
	protected void incluirFonemas(Pessoa pessoa, String vetNomes[], String tipo) throws DAOException {
		try {
			FoneticoPessoa fonetico = new FoneticoPessoa();
			for (int i = 0; i < vetNomes.length; i++) {
				if (vetNomes[i] != null) {
					fonetico.setId(null);
					fonetico.setCodigoFonetico(vetNomes[i]);
					fonetico.setPessoa(pessoa);
					fonetico.setTipo(tipo);
					entityManager.merge(fonetico);
				}
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}

	}

}
