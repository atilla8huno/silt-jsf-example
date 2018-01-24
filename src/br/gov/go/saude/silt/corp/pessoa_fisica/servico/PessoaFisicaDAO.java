package br.gov.go.saude.silt.corp.pessoa_fisica.servico;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistência PessoaFisica.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
@SuppressWarnings("all")
public class PessoaFisicaDAO extends DAO<PessoaFisica> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Consulta PessoaFisicas ativas por: cpf ou nome da mae ou data de nascimento sem fonetizacao.
	 * 
	 * @param pessoaFisica
	 * @return List<PessoaFisica>
	 * @throws DAOException
	 */
	protected List<PessoaFisica> consultarPorCPFOuNomeNomeMaeNascimentoSemFonetica(PessoaFisica pessoaFisica) throws DAOException {
		try {
			Criteria criteria = createCriteria().addOrder(Order.asc("nome"));
			
			if (pessoaFisica.getCpf() != null) {
				criteria.add(Restrictions.eq("cpf", pessoaFisica.getCpf()));
			} else {
				
				if (pessoaFisica.getNome() != null) {
					criteria.add(Restrictions.ilike("nome", "%" + pessoaFisica.getNome() + "%"));
				}
				
				if (pessoaFisica.getNomeMae() != null) {
					criteria.add(Restrictions.ilike("nomeMae", "%" + pessoaFisica.getNomeMae() + "%"));
				}
				
				if (pessoaFisica.getDataNascimento() != null) {
					criteria.add(Restrictions.ge("dataNascimento", pessoaFisica.getDataNascimento()));
				}
			}
			
			criteria.add(Restrictions.eq("exclusao", Boolean.FALSE));
			
			return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Consultar uma PessoaFisica por cpf
	 * 
	 * @param cpf
	 * @return PessoaFisica
	 * @throws DAOException
	 */
	protected PessoaFisica consultarPorCPF(Long cpf) throws DAOException {
		try {
			Criteria criteria = createCriteria();
			criteria.add(Restrictions.eq("cpf", cpf)).add(Restrictions.eq("exclusao", Boolean.FALSE));
			return (PessoaFisica) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
