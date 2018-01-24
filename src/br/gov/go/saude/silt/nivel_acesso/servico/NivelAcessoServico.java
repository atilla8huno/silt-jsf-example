package br.gov.go.saude.silt.nivel_acesso.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.nivel_acesso.entidade.NivelAcesso;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Servico
 */
@Service
public class NivelAcessoServico extends Servico<NivelAcesso> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private NivelAcessoDAO dao;

	@Override
	protected DAO<NivelAcesso> getDAO() {
		return dao;
	}

	public static NivelAcessoServico getInstancia() {
		return (NivelAcessoServico) SpringContainer.getInstancia().getBean("nivelAcessoServico");
	}

}
