package br.gov.go.saude.silt.portal.usuario_sistema.servico;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.gov.go.saude.silt.portal.usuario_sistema.entidade.UsuarioSistema;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;

/**
 * Classe de persistencia UsuarioSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Repository
public class UsuarioSistemaDAO extends DAO<UsuarioSistema> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Retorna um usuarioSistema por login informado.
	 * 
	 * @param  String login
	 * @return UsuarioSistema
	 * @throws DAOException
	 */
	protected UsuarioSistema consultarPorLogin(String login) throws DAOException {
		try {
			Criteria criteria = createCriteria().add(Restrictions.eq("login", login));
			
			return (UsuarioSistema) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Retorna um usuarioSistema pelos parametros: login, email e pessoaFisica informados.
	 * 
	 * @param  UsuarioSistema entidade
	 * @return UsuarioSistema
	 * @throws DAOException
	 */
	protected UsuarioSistema consultarPorLoginEmailPessoa(UsuarioSistema entidade) throws DAOException {
		try {
			if (entidade != null) {
				Criteria criteria = createCriteria();
				if (entidade.getPessoaFisica() != null && entidade.getPessoaFisica().getId() != null) {
					criteria.add(Restrictions.or(Restrictions.eq("login", entidade.getLogin()), Restrictions.eq("email", entidade.getEmail()),
							Restrictions.eq("pessoaFisica.id", entidade.getPessoaFisica().getId())));
				} else {
					criteria.add(Restrictions.or(Restrictions.eq("login", entidade.getLogin()), Restrictions.eq("email", entidade.getEmail())));
				}
				return (UsuarioSistema) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
			}
			return null;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param  Long id
	 * @return UsuarioSistema
	 * @throws DAOException
	 */
	@Override
	protected UsuarioSistema consultarPorId(Long id) throws DAOException {
		try {
			Criteria criteria = createCriteria().add(Restrictions.eq("idUsuario", id));
			
			return (UsuarioSistema) criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
