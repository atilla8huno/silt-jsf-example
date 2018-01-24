package br.gov.go.saude.silt.portal.usuario_sistema.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.portal.usuario_sistema.entidade.UsuarioSistema;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico UsuarioSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class UsuarioSistemaServico extends Servico<UsuarioSistema> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioSistemaDAO dao;

	/**
	 * @param login
	 * @return UsuarioSistema
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public UsuarioSistema consultarPorLogin(String login) throws ServicoException {
		try {
			return dao.consultarPorLogin(login);
		} catch (Exception erro) {
			throw new ServicoException(erro);
		}
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 * @return UsuarioSistema
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsuarioSistema incluir(UsuarioSistema entidade) throws ServicoException {
		// Status Ativo
		entidade.setStatus("A");
		entidade.setNome(entidade.getNome().toUpperCase());
		entidade.setLogin(entidade.getLogin().toUpperCase());

		verificarSeUsuarioExiste(entidade);

		return super.incluir(entidade);
	}

	/**
	 * Metodo verifica a existencia de uma login
	 * 
	 * @param entidade
	 * @throws ServicoException
	 */
	private void verificarSeUsuarioExiste(UsuarioSistema entidade) throws ServicoException {
		UsuarioSistema usuario = null;
		try {
			usuario = dao.consultarPorLoginEmailPessoa(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
		if (usuario != null) {
			if (usuario.getLogin() != null && usuario.getLogin().equals(entidade.getLogin())) {
				throw new ServicoException("negocio.geral.campoExistente", "Usuário", "Login");
			}
			if (usuario.getEmail() != null && usuario.getEmail().equals(entidade.getEmail())) {
				throw new ServicoException("negocio.geral.campoExistente", "Usuário", "E-mail");
			}
		}
	}

	/**
	 * Metodo retorna um usuarioSistema. Se o usuarioSistema nao existir o metodo solicita sua inclusao. Existindo solicita sua consulta.
	 * 
	 * @param entidade
	 * @return UsuarioSistema
	 * @throws ServicoException
	 */
	public UsuarioSistema consultarUsuarioSistema(UsuarioSistema entidade) throws ServicoException {
		UsuarioSistema usuario = null;
		if (entidade != null) {
			entidade.setLogin(entidade.getLogin().toUpperCase());
			usuario = this.consultarPorLogin(entidade.getLogin());
			if (usuario == null) {
				return this.incluir(entidade);
			}
		}
		return usuario;
	}

	@Override
	protected DAO<UsuarioSistema> getDAO() {
		return dao;
	}

	public static UsuarioSistemaServico getInstancia() {
		return (UsuarioSistemaServico) SpringContainer.getInstancia().getBean("usuarioSistemaServico");
	}

}
