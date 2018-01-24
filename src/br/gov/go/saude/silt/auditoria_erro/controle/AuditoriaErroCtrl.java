package br.gov.go.saude.silt.auditoria_erro.controle;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.auditoria_erro.entidade.AuditoriaErro;
import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 306 $ $Author: atillabarros $ $Date: 2014-02-20 16:10:35 -0300 (Qui, 20 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class AuditoriaErroCtrl extends Controlador<AuditoriaErro> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AuditoriaErroServico servico;

	/* Atributos de filtros da pesquisa */
	private String codigo;
	private String pagina;
	private String login;
	private Date dataCadastroInicio;
	private Date dataCadastroFim;

	private AuditoriaErro entidade;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();

		getListaPaginada().addFiltro("codigo", codigo);
		getListaPaginada().addFiltro("pagina", pagina);
		getListaPaginada().addFiltro("dataCadastroInicio", dataCadastroInicio);
		getListaPaginada().addFiltro("dataCadastroFim", dataCadastroFim);
		getListaPaginada().addFiltro("login", login);
		
		getListaPaginada().addOrdenacao("dataCadastro");
	}

	@Override
	public void limpar() {
		entidade = null;
		codigo	 = null;
		pagina	 = null;
		login	 = null;
		dataCadastroInicio	 = null;
		dataCadastroFim		 = null;

		/* Limpa resultados da pesquisa */
		getListaPaginada().limpar();
	}

	public static AuditoriaErroCtrl getInstancia() {
		return (AuditoriaErroCtrl) SpringContainer.getInstancia().getBean("auditoriaErroCtrl");
	}

	@Override
	public Servico<AuditoriaErro> getServico() {
		if (servico == null) {
			servico = AuditoriaErroServico.getInstancia();
		}
		return servico;
	}

	@Override
	public AuditoriaErro getEntidade() {
		if (entidade == null) {
			entidade = new AuditoriaErro();
		}
		return entidade;
	}

	@Override
	public void setEntidade(AuditoriaErro entidade) {
		this.entidade = entidade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public Date getDataCadastroInicio() {
		return dataCadastroInicio;
	}

	public void setDataCadastroInicio(Date dataCadastroInicio) {
		this.dataCadastroInicio = dataCadastroInicio;
	}

	public Date getDataCadastroFim() {
		return dataCadastroFim;
	}

	public void setDataCadastroFim(Date dataCadastroFim) {
		this.dataCadastroFim = dataCadastroFim;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
