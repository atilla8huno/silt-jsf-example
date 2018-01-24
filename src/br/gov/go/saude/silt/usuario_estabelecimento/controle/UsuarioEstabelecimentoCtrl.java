package br.gov.go.saude.silt.usuario_estabelecimento.controle;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.nivel_acesso.entidade.NivelAcesso;
import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;
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
public class UsuarioEstabelecimentoCtrl extends Controlador<UsuarioEstabelecimento> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioEstabelecimentoServico servico;

	private UsuarioEstabelecimento entidade;
	private List<PerfilSistema> listaPerfilSistema;

	/* Atributos de filtros da pesquisa */
	private String nome;
	private String login;

	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();
		
		getListaPaginada().addFiltro("usuario.nome", nome);
		getListaPaginada().addFiltro("usuario.login", login);
		
		getListaPaginada().addOrdenacao("usuario.nome");
	}

	/**
	 * Metodo retorna uma lista para exibir no auto complete.
	 * 
	 * @param valorPesquisado
	 * @return List<EstabelecimentoSaude>
	 */
	public List<Estabelecimento> autoCompleteEstabelecimentoSaude(String valorPesquisado) {
		try {
			return servico.consultarPorNomeFantasia(valorPesquisado);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return null;
	}

	@Override
	public Servico<UsuarioEstabelecimento> getServico() {
		if (servico == null) {
			servico = UsuarioEstabelecimentoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public UsuarioEstabelecimento getEntidade() {
		if (entidade == null) {
			entidade = new UsuarioEstabelecimento();
		}
		return entidade;
	}

	@Override
	public void setEntidade(UsuarioEstabelecimento entidade) {
		this.entidade = entidade;
	}

	@Override
	public ListaPaginada<UsuarioEstabelecimento> getListaPaginada() {
		if (listaPaginada == null) {
			listaPaginada = new ListaPaginada<UsuarioEstabelecimento>(servico);
		}
		return listaPaginada;
	}

	public static UsuarioEstabelecimentoCtrl getInstancia() {
		return (UsuarioEstabelecimentoCtrl) SpringContainer.getInstancia().getBean("usuarioEstabelecimentoCtrl");
	}

	public List<PerfilSistema> getListaPerfilSistema() {
		if (listaPerfilSistema == null) {
			try {
				listaPerfilSistema = servico.consultarPorSistema("SILT");
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaPerfilSistema;
	}

	public List<NivelAcesso> getListaNivelAcesso() {
		try {
			return servico.consultarTodosNivelAcesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return null;
	}

	public void setListaPerfilSistema(List<PerfilSistema> listaPerfilSistema) {
		this.listaPerfilSistema = listaPerfilSistema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
