package br.gov.go.saude.silt.corp.bairro.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Bairro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class BairroServico extends Servico<Bairro> {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private BairroDAO dao;

	/**
	 * Este método pesquisa os bairros de acordo com Municipio recebido.
	 * 
	 * @param Long cep
	 * @return List<Bairro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bairro> consultarBairrosPorMunicipio(Municipio municipio) throws ServicoException {
		try {
			return dao.consultarBairrosPorMunicipio(municipio);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<Bairro> getDAO() {
		return dao;
	}

	public static BairroServico getInstancia() {
		return (BairroServico) SpringContainer.getInstancia().getBean("bairroServico");
	}

}
