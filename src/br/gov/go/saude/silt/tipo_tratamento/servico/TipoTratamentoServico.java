package br.gov.go.saude.silt.tipo_tratamento.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class TipoTratamentoServico extends Servico<TipoTratamento> {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private TipoTratamentoDAO dao;
	
	/**
     * Este método pesquisa os registros de Tipo Tratamento de acordo com filtros recebidos.
     * 
     * @param 	String codigo
     * @param	String descricao
     * @return	List<TipoTratamento>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoTratamento> consultarPorCodigoDescricao(String codigo, String descricao) throws ServicoException {
		try {
			return dao.consultarPorCodigoDescricao(codigo, descricao);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	@Override
	protected DAO<TipoTratamento> getDAO() {
		return dao;
	}
	
	public static TipoTratamentoServico getInstancia() {
		return (TipoTratamentoServico) SpringContainer.getInstancia().getBean("tipoTratamentoServico");
	}
}
