package br.gov.go.saude.silt.tipo_tratamento.controle;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.tipo_tratamento.servico.TipoTratamentoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class TipoTratamentoCtrl extends Controlador<TipoTratamento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TipoTratamentoServico servico;

	private TipoTratamento entidade;

	/* Atributos de filtros da pesquisa */
	private String codigo;
	private String descricao;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();

		getListaPaginada().addFiltro("codigo", codigo);
		getListaPaginada().addFiltro("descricao", descricao);
		getListaPaginada().addOrdenacao("descricao");
	}

	@Override
	public void limpar() {
		entidade = null;
		codigo = null;
		descricao = null;

		/* Limpa resultados da pesquisa */
		getListaPaginada().limpar();
	}

	@Override
	public Servico<TipoTratamento> getServico() {
		if (servico == null) {
			servico = TipoTratamentoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public TipoTratamento getEntidade() {
		if (entidade == null) {
			entidade = new TipoTratamento();
		}
		return entidade;
	}

	@Override
	public void setEntidade(TipoTratamento entidade) {
		this.entidade = entidade;
	}

	public static TipoTratamentoCtrl getInstancia() {
		return (TipoTratamentoCtrl) SpringContainer.getInstancia().getBean("tipoTratamentoCtrl");
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
