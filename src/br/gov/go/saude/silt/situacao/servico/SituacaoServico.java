package br.gov.go.saude.silt.situacao.servico;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.SituacaoCasoNotificacaoEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category Servico
 */
@Service
public class SituacaoServico extends Servico<Situacao> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SituacaoDAO dao;

	/**
	 * Metodo realiza a consulta de uma determinada entidade podendo ser um consulta paginada ou nao.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<Situacao>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Situacao> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws ServicoException {
		try {
			return dao.consultarPorFiltro(filtroPesquisa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Retorna a quantidade de linhas de uma determinada consulta.
	 * 
	 * @param Map<String, Object> filtros
	 * @return Long
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Long getNumeroLinhas(Map<String, Object> filtros) throws ServicoException {
		try {
			return dao.getNumeroLinhas(filtros);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * 
	 * @param situacao
	 * @return
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Situacao consultarPorDescricao(SituacaoCasoNotificacaoEnum situacao) throws ServicoException {
		try {
			return dao.consultarPorDescricao(situacao);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<Situacao> getDAO() {
		return dao;
	}

	public static SituacaoServico getInstancia() {
		return (SituacaoServico) SpringContainer.getInstancia().getBean("situacaoServico");
	}
}
