package br.gov.go.saude.silt.portal.acesso_usuario.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.organograma_secretaria.servico.OrganogramaSecretariaServico;
import br.gov.go.saude.silt.portal.acesso_usuario.entidade.AcessoUsuario;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico AcessoUsuario.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class AcessoUsuarioServico extends Servico<AcessoUsuario> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AcessoUsuarioDAO dao;

	@Autowired
	private OrganogramaSecretariaServico organogramaSecretariaServico;

	/**
	 * Este método para ser excutado necessita das informacoes de : perfilSistema e usuarioSistema, alem da informacoes da propria classe.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 * @return entidade
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public AcessoUsuario incluir(AcessoUsuario entidade) throws ServicoException {
		try {
			if (entidade != null) {
				entidade.setExcluido(Boolean.FALSE);
				entidade.setOrganogramaSecretaria(organogramaSecretariaServico.consultarPorOrganogramaPadrao());
				// Setando informacoes da chave composta
				entidade.getPkAcessoUsuario().setIdOganogramaSecretaria(entidade.getOrganogramaSecretaria().getId());
				entidade.getPkAcessoUsuario().setIdPerfilSistema(entidade.getPerfilSistema().getCodigo());
				entidade.getPkAcessoUsuario().setIdUsuarioSistema(entidade.getUsuarioSistema().getLogin());
				entidade.setInformacaoRepasse("N");
			}
			return super.incluir(entidade);
		} catch (Exception erro) {
			throw new ServicoException(erro);
		}
	}

	@Override
	protected DAO<AcessoUsuario> getDAO() {
		return dao;
	}

	public static AcessoUsuarioServico getInstancia() {
		return (AcessoUsuarioServico) SpringContainer.getInstancia().getBean("acessoUsuarioServico");
	}

}
