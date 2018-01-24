package br.gov.go.saude.silt.estabelecimento.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.estabelecimento_saude.servico.EstabelecimentoSaudeServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.ListaPaginada;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class EstabelecimentoCtrl extends Controlador<Estabelecimento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EstabelecimentoServico servico;

	@Autowired
	private EstabelecimentoSaudeServico estabelecimentoSaudeServico;

	private Estabelecimento entidade;

	/* Atributos de filtros da pesquisa */
	private String cnes;
	private String nome;
	private Estabelecimento estabelecimentoNucleo;

	@Override
	@SuppressWarnings("all")
	public ListaPaginada<Estabelecimento> getListaPaginada() {
		if (listaPaginada == null) {
			listaPaginada = new ListaPaginada(getServico());
		}
		return listaPaginada;
	}

	@Override
	public void limpar() {
		super.limpar();
		cnes = null;
		nome = null;
		estabelecimentoNucleo = null;
	}

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();
		if (cnes != null && !cnes.isEmpty()) {
			getListaPaginada().addFiltro("estabSaude.cnes", Long.valueOf(cnes));
		}
		if (estabelecimentoNucleo != null && estabelecimentoNucleo.getId() != null) {
			getListaPaginada().addFiltro("estabelecimentoNucleo.id", estabelecimentoNucleo.getId());
		}

		getListaPaginada().addFiltro("estabSaude.nomeFantasia", nome);
		getListaPaginada().addOrdenacao("estabSaude.nomeFantasia");
	}

	/**
	 * Metodo retorna uma lista para exibir no auto complete.
	 * 
	 * @param valorPesquisado
	 * @return List<EstabelecimentoSaude>
	 */
	public List<EstabelecimentoSaude> autoComplete(String valorPesquisado) {
		try {
			return estabelecimentoSaudeServico.consultarPorNomeFantasia(valorPesquisado);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return null;
	}

	/**
	 * Metodo retorna uma lista para exibir no auto complete.
	 * 
	 * @param valorPesquisado
	 * @return List<EstabelecimentoSaude>
	 */
	public List<Estabelecimento> autoCompleteEstabelecimentoNucleo(String valorPesquisado) {
		try {
			return servico.consultarEstabelecimentoNucleoPorNomeFantasia(valorPesquisado);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return null;
	}

	@Override
	public Servico<Estabelecimento> getServico() {
		if (servico == null) {
			servico = EstabelecimentoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public Estabelecimento getEntidade() {
		if (entidade == null) {
			entidade = new Estabelecimento();
			entidade.setEstabelecimentoSaude(new EstabelecimentoSaude());
		}
		return entidade;
	}

	@Override
	public void setEntidade(Estabelecimento entidade) {
		this.entidade = entidade;
	}

	public static EstabelecimentoCtrl getInstancia() {
		return (EstabelecimentoCtrl) SpringContainer.getInstancia().getBean("estabelecimentoCtrl");
	}

	public String getCnes() {
		return cnes;
	}

	public void setCnes(String cnes) {
		this.cnes = cnes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estabelecimento getEstabelecimentoNucleo() {
		return estabelecimentoNucleo;
	}

	public void setEstabelecimentoNucleo(Estabelecimento estabelecimentoNucleo) {
		this.estabelecimentoNucleo = estabelecimentoNucleo;
	}

}
