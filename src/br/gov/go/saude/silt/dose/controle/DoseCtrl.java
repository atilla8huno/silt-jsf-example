package br.gov.go.saude.silt.dose.controle;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.dose.servico.DoseServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe controle para cadastro de Dose.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class DoseCtrl extends Controlador<Dose> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DoseServico servico;

	private Dose entidade;
	private CasoNotificacao casoNotificacao;

	/**
	 * Este método cadastra uma Dose para um Caso de Notificação.
	 */
	@Override
	public void salvar() {
		try {
			servico.incluir(entidade);
			limpar();
			addMensagemInclusaoSucesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Metodo exibe componente para cadastro de dose.
	 */
	public void exibirComponente() {
		try {
			casoNotificacao = servico.consultarCasoNotificacaoPorId(casoNotificacao.getId());
			if (casoNotificacao != null) {
				entidade = new Dose();
				entidade.setCasoNotificacao(casoNotificacao);
				entidade.setUsuarioEstabelecimento(getUsuarioEstabelecimentoSessao());
				entidade.setEstabelecimento(getUsuarioEstabelecimentoSessao().getEstabelecimento());
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	@Override
	public void limpar() {
		try {
			entidade.setQuantidade(null);
			entidade.setDataEntrega(null);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	@Override
	public Dose getEntidade() {
		if (entidade == null) {
			entidade = new Dose();
		}
		return entidade;
	}

	@Override
	public Servico<Dose> getServico() {
		if (servico == null) {
			servico = DoseServico.getInstancia();
		}
		return servico;
	}

	@Override
	public void setEntidade(Dose entidade) {
		this.entidade = entidade;
	}

	@Override
	public void addFiltrosPesquisa() {
		// TODO Auto-generated method stub
	}

	public static DoseCtrl getInstancia() {
		return (DoseCtrl) SpringContainer.getInstancia().getBean("doseCtrl");
	}

	public CasoNotificacao getCasoNotificacao() {
		return casoNotificacao;
	}

	public void setCasoNotificacao(CasoNotificacao casoNotificacao) {
		this.casoNotificacao = casoNotificacao;
	}
}
