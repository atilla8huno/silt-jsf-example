package br.gov.go.saude.silt.usuario_estabelecimento.servico;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.pessoa_fisica.servico.PessoaFisicaServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.nivel_acesso.entidade.NivelAcesso;
import br.gov.go.saude.silt.nivel_acesso.servico.NivelAcessoServico;
import br.gov.go.saude.silt.portal.acesso_usuario.entidade.AcessoUsuario;
import br.gov.go.saude.silt.portal.acesso_usuario.servico.AcessoUsuarioServico;
import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.portal.perfil_sistema.servico.PerfilSistemaServico;
import br.gov.go.saude.silt.portal.usuario_sistema.servico.UsuarioSistemaServico;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 297 $ $Author: claudiocosta $ $Date: 2014-02-03 17:00:05 -0200 (Seg, 03 Fev 2014) $
 * @category Servico
 */
@Service("usuarioEstabelecimentoServico")
public class UsuarioEstabelecimentoServico extends Servico<UsuarioEstabelecimento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioEstabelecimentoDAO dao;
	@Autowired
	private UsuarioSistemaServico usuarioSistemaServico;
	@Autowired
	private AcessoUsuarioServico acessoUsuarioServico;
	@Autowired
	private PessoaFisicaServico pessoaFisicaServico;
	@Autowired
	private EstabelecimentoServico estabelecimentoServico;
	@Autowired
	private PerfilSistemaServico perfilSistemaServico;
	@Autowired
	private NivelAcessoServico nivelAcessoServico;

	/**
	 * Metodo realiza a inclusao de um usuarioEstabelecimento solicitando a verificacao da existencia de um usuario ja existente.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public UsuarioEstabelecimento incluir(UsuarioEstabelecimento entidade) throws ServicoException {
		try {
			if (entidade != null) {

				verificarSeUsuarioExiste(entidade);

				// Inclui a entidade UsuarioSistema
				entidade.setUsuarioSistema(usuarioSistemaServico.consultarUsuarioSistema(entidade.getUsuarioSistema()));

				if (entidade.getUsuarioSistema().getPessoaFisica() != null) {
					entidade.setPessoaFisica(entidade.getUsuarioSistema().getPessoaFisica());
				}

				AcessoUsuario acessoUsuario = new AcessoUsuario();
				acessoUsuario.setPerfilSistema(entidade.getPerfilSistema());
				acessoUsuario.setUsuarioSistema(entidade.getUsuarioSistema());

				acessoUsuarioServico.incluir(acessoUsuario);

				entidade.setExcluido(Boolean.FALSE);
			}
			return super.incluir(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo realiza a verificacao da existencia de um usuario cadastrado com o login informado, emitindo uma excecao no caso de ser verdadeiro.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 */
	private void verificarSeUsuarioExiste(UsuarioEstabelecimento entidade) throws ServicoException {
		try {
			UsuarioEstabelecimento usuario = dao.consultarPorUsuarioSistema(entidade.getUsuarioSistema().getLogin().toUpperCase());
			if (usuario != null) {
				throw new ServicoException("negocio.geral.campoExistente", "Usuário", "Login");
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	public List<UsuarioEstabelecimento> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws ServicoException {
		try {
			return dao.consultarPorFiltro(filtroPesquisa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	public Long getNumeroLinhas(Map<String, Object> filtros) throws ServicoException {
		try {
			return dao.getNumeroLinhas(filtros);
		} catch (Exception e) {
			throw new ServicoException(e);
		}

	}

	public List<Estabelecimento> consultarPorNomeFantasia(String nomeFantasia) throws ServicoException {
		return estabelecimentoServico.consultarPorNomeFantasia(nomeFantasia);
	}

	public List<PerfilSistema> consultarPorSistema(String siglaSistema) throws ServicoException {
		return perfilSistemaServico.consultarPorSistema(siglaSistema);
	}

	public List<NivelAcesso> consultarTodosNivelAcesso() throws ServicoException {
		return nivelAcessoServico.consultarTodos();
	}

	/**
	 * Recupera um usuarioEstabelecimento pelo login informado.
	 */
	public UsuarioEstabelecimento consultarPorUsuarioSistema(String login) throws ServicoException {
		try {
			return dao.consultarPorUsuarioSistema(login);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<UsuarioEstabelecimento> getDAO() {
		return dao;
	}

	public static UsuarioEstabelecimentoServico getInstancia() {
		return (UsuarioEstabelecimentoServico) SpringContainer.getInstancia().getBean("usuarioEstabelecimentoServico");
	}
}
