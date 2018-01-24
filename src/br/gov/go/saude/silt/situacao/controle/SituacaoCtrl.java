package br.gov.go.saude.silt.situacao.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.situacao.servico.SituacaoServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class SituacaoCtrl extends Controlador<Situacao> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SituacaoServico servico;

	private Situacao entidade;

	/* Atributos de filtros da pesquisa */
	private StatusEnum status;
	private String descricao;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();

		getListaPaginada().addFiltro("status", status);
		getListaPaginada().addFiltro("descricao", descricao);
		getListaPaginada().addOrdenacao("descricao");
	}

	public List<StatusEnum> getListaStatusEnum() {
		List<StatusEnum> listaStatusEnum = EnumeradorUtil.getDominiosAtivos(StatusEnum.class);
		listaStatusEnum.remove(StatusEnum.TODOS);
		return listaStatusEnum;
	}

	@Override
	public void limpar() {
		entidade = null;
		status = null;
		descricao = null;

		/* Limpa resultados da pesquisa */
		getListaPaginada().limpar();
	}

	@Override
	public Servico<Situacao> getServico() {
		if (servico == null) {
			servico = SituacaoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public Situacao getEntidade() {
		if (entidade == null) {
			entidade = new Situacao();
		}
		return entidade;
	}

	@Override
	public void setEntidade(Situacao entidade) {
		this.entidade = entidade;
	}

	public static SituacaoCtrl getInstancia() {
		return (SituacaoCtrl) SpringContainer.getInstancia().getBean("situacaoCtrl");
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setServico(SituacaoServico servico) {
		this.servico = servico;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
