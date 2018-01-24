package br.gov.go.saude.silt.corp.organograma_secretaria.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.organograma_secretaria.entidade.OrganogramaSecretaria;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico OrganogramaSecretaria.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Service
 */
@Service("organogramaSecretaria")
public class OrganogramaSecretariaServico extends Servico<OrganogramaSecretaria> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OrganogramaSecretariaDAO dao;

	@Override
	protected DAO<OrganogramaSecretaria> getDAO() {
		return dao;
	}

	/**
	 * Metodo retorna Organograma padrao (UNIDADE DE USO DA ADMINISTRACAO DO PORTAL) que e utilizado frequentemente em algumas rotinas.
	 * 
	 * @return OrganogramaSecretaria
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public OrganogramaSecretaria consultarPorOrganogramaPadrao() throws ServicoException {
		try {
			return super.consultarPorId(20L);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	public static OrganogramaSecretariaServico getInstancia() {
		return (OrganogramaSecretariaServico) SpringContainer.getInstancia().getBean("organogramaSecretariaServico");
	}

}
