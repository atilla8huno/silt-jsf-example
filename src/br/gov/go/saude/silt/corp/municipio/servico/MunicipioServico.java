package br.gov.go.saude.silt.corp.municipio.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de servico Municipio.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category DAO
 */
@Service
public class MunicipioServico extends Servico<Municipio> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MunicipioDAO dao;

	/**
     * Este método pesquisa os municipios de acordo com Estado recebido.
     * 
     * @param	Long cep
     * @return	List<Municipio>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Municipio> consultarPorUnidadeFederativa(UnidadeFederativa estado) throws ServicoException {
		try {
			return dao.consultarPorUnidadeFederativa(estado);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	@Override
	protected DAO<Municipio> getDAO() {
		return dao;
	}

	public static MunicipioServico getInstancia() {
		return (MunicipioServico) SpringContainer.getInstancia().getBean("municipioServico");
	}

}
