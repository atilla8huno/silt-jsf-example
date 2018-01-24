package br.gov.go.saude.silt.condicao_risco.controle;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.condicao_risco.servico.CondicaoRiscoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class CondicaoRiscoCtrl extends Controlador<CondicaoRisco> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CondicaoRiscoServico servico;

	/* Atributos de filtros da pesquisa */
	private String descricao;

	private CondicaoRisco entidade;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();

		getListaPaginada().addFiltro("descricao", descricao);
		getListaPaginada().addOrdenacao("descricao");
	}

	@Override
	public void limpar() {
		entidade = null;
		descricao = null;

		/* Limpa resultados da pesquisa */
		getListaPaginada().limpar();
	}

	public static CondicaoRiscoCtrl getInstancia() {
		return (CondicaoRiscoCtrl) SpringContainer.getInstancia().getBean("condicaoRiscoCtrl");
	}

	@Override
	public Servico<CondicaoRisco> getServico() {
		if (servico == null) {
			servico = CondicaoRiscoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public CondicaoRisco getEntidade() {
		if (entidade == null) {
			entidade = new CondicaoRisco();
		}
		return entidade;
	}

	@Override
	public void setEntidade(CondicaoRisco entidade) {
		this.entidade = entidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
