package br.gov.go.saude.silt.corp.contato_pessoa.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia ContatoPessoa.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class ContatoPessoaDAO extends DAO<ContatoPessoa> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Este método consulta todos os contatos relacionados a pessoa.
	 * 
	 * @param  PessoaFisica pessoa
	 * @return List<ContatoPessoa>
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	protected List<ContatoPessoa> consultarPorPessoaFisica(PessoaFisica pessoa) throws DAOException {
		try {
			if (pessoa != null && !pessoa.isTransient()) {
				Criteria criteria = createCriteria().add(Restrictions.eq("pessoa.id", pessoa.getId()));
				
				return (List<ContatoPessoa>) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
