package br.gov.go.saude.silt.corp.tipo_endereco.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.corp.tipo_endereco.entidade.TipoEndereco;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico TipoEndereco.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class TipoEnderecoServico extends Servico<TipoEndereco> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoEnderecoDAO dao;

	@Override
	protected DAO<TipoEndereco> getDAO() {
		return dao;
	}

	public static TipoEnderecoServico getInstancia() {
		return (TipoEnderecoServico) SpringContainer.getInstancia().getBean("tipoEnderecoServico");
	}

}
