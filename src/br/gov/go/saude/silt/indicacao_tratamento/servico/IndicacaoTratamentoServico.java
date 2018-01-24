package br.gov.go.saude.silt.indicacao_tratamento.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class IndicacaoTratamentoServico extends Servico<IndicacaoTratamento> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IndicacaoTratamentoDAO dao;

	@Override
	protected DAO<IndicacaoTratamento> getDAO() {
		return dao;
	}
	
	public static IndicacaoTratamentoServico getInstancia() {
		return (IndicacaoTratamentoServico) SpringContainer.getInstancia().getBean("indicacaoTratamentoServico");
	}
}
