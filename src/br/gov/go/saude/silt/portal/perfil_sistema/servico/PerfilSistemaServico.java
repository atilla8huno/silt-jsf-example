package br.gov.go.saude.silt.portal.perfil_sistema.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.util.Permissao;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.PermissaoEnum;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico PerfilSistema.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 297 $ $Author: claudiocosta $ $Date: 2014-02-03 17:00:05 -0200 (Seg, 03 Fev 2014) $
 * @category Servico
 */
@Service
public class PerfilSistemaServico extends Servico<PerfilSistema> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PerfilSistemaDAO dao;

	/**
	 * Metodo retorna uma lista de PerfilSistema de acordo com a sigla do sistema informada.
	 * 
	 * @param siglaSistema
	 * @return List<PerfilSistema>
	 * @throws DAOException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PerfilSistema> consultarPorSistema(String siglaSistema) throws ServicoException {
		try {
			return dao.consultarPorSistema(siglaSistema);
		} catch (Exception e) {
			throw new ServicoException(e);
		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	@Permissao(permissao = PermissaoEnum.CONSULTAR)
	public PerfilSistema consultarPorId(Long id) throws ServicoException {
		try {
			return dao.consultarPorId(id);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<PerfilSistema> getDAO() {
		return dao;
	}

	public static PerfilSistemaServico getInstancia() {
		return (PerfilSistemaServico) SpringContainer.getInstancia().getBean("perfilSistemaServico");
	}

}
