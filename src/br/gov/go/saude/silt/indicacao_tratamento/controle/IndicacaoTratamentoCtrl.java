package br.gov.go.saude.silt.indicacao_tratamento.controle;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.indicacao_tratamento.servico.IndicacaoTratamentoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class IndicacaoTratamentoCtrl extends Controlador<IndicacaoTratamento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndicacaoTratamentoServico servico;

	private IndicacaoTratamento entidade;

	/* Atributos de filtros da pesquisa */
	private String descricao;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();
		getListaPaginada().addFiltro("descricao", descricao);
		getListaPaginada().addOrdenacao("descricao");
	}

	@Override
	public Servico<IndicacaoTratamento> getServico() {
		if (servico == null) {
			servico = IndicacaoTratamentoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public IndicacaoTratamento getEntidade() {
		if (entidade == null) {
			entidade = new IndicacaoTratamento();
		}
		return entidade;
	}

	@Override
	public void setEntidade(IndicacaoTratamento entidade) {
		this.entidade = entidade;
	}

	public static IndicacaoTratamentoCtrl getInstancia() {
		return (IndicacaoTratamentoCtrl) SpringContainer.getInstancia().getBean("indicacaoTratamentoCtrl");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
