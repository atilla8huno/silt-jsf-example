package br.gov.go.saude.silt.util.template;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.go.saude.arquitetura.util.AutenticacaoUtil;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.AutenticacaoSilt;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador Genérico
 */
@SuppressWarnings("all")
public abstract class Controlador<T extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract T getEntidade();

	public abstract Servico<T> getServico();

	public abstract void setEntidade(T entidade);

	public abstract void addFiltrosPesquisa();

	public ListaPaginada<T> listaPaginada;

	private boolean estadoConsultando = true;

	private static Severity ERROR	= FacesMessage.SEVERITY_ERROR;
	private static Severity INFO	= FacesMessage.SEVERITY_INFO;
	private static Severity FATAL	= FacesMessage.SEVERITY_FATAL;
	private static Severity WARN	= FacesMessage.SEVERITY_WARN;

	private Class<T> classeDaEntidade;

	public Controlador() {
		setClasseDaEntidade((Class<T>) getClasseGenerica(this.getClass()));
	}
	
	/**
	 * Este método salva ou atualiza a entidade na base de dados.
	 */
	public void salvar() {
		try {
			if (getEntidade().isTransient()) {
				getServico().salvarOuAtualizar(getEntidade());

				limpar();
				addMensagemInclusaoSucesso();
			} else {
				getServico().salvarOuAtualizar(getEntidade());

				estadoConsultando = true;
				addMensagemAlteracaoSucesso();
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método altera o estado da tela para inclusão.
	 */
	public void editar() {
		setEstadoConsultando(false);
	}

	/**
	 * Este método exclui a entidade na base de dados.
	 */
	public void excluir() {
		try {
			getServico().excluir(getEntidade());

			estadoConsultando = true;
			addMensagemExclusaoSucesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método lista todos os registros do tipo da entidade.
	 * 
	 * @return List<T>
	 */
	public List<T> listarTodos() {
		try {
			return getServico().consultarTodos();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return null;
	}

	/**
	 * Este método anula a entidade, refletindo no formulário.
	 */
	public void limpar() {
		getListaPaginada().limpar();
		setEntidade(null);
	}

	/**
	 * Este método limpa o formulário e retorna para página de cadastro.
	 */
	public void novo() {
		limpar();
		estadoConsultando = false;
	}

	/**
	 * Este método limpa o formulário e retorna para página de listagem.
	 */
	public void voltar() {
		limpar();
		estadoConsultando = true;
	}

	/**
	 * Este método adiciona filtros e efetua a pesquisa.
	 */
	public void pesquisar() {
		try {
			addFiltrosPesquisa();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método verifica se o usuário logado possui permissão para inclusões.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoInclusao() {
		return AutenticacaoUtil.getPermissaoInclusao();
	}

	/**
	 * Este método verifica se o usuário logado possui permissão para alterações.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoAlteracao() {
		return AutenticacaoUtil.getPermissaoAlteracao();
	}

	/**
	 * Este método verifica se o usuário logado possui permissão para consultas.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoConsulta() {
		return AutenticacaoUtil.getPermissaoConsulta();
	}

	/**
	 * Este método verifica se o usuário logado possui permissão para exclusões.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoExclusao() {
		return AutenticacaoUtil.getPermissaoExclusao();
	}

	/* Atributos para pesquisa paginada. */
	public ListaPaginada<T> getListaPaginada() {
		if (listaPaginada == null) {
			listaPaginada = new ListaPaginada(getServico());
		}
		return listaPaginada;
	}

	public void setListaPaginada(ListaPaginada<T> listaPaginada) {
		this.listaPaginada = listaPaginada;
	}

	/**
	 * Este método recebe um tipo, titulo e descrição de uma mensagem e exibe na tela.
	 * 
	 * @param Severity severity
	 * @param String titulo
	 * @param String msg
	 */
	public void addMensagem(Severity severity, String titulo, String msg) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);

		context.addMessage(null, new FacesMessage(severity, titulo, msg));
	}

	/**
	 * Este método recebe uma descrição de uma mensagem de erro e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemErro(String msg) {
		addMensagem(ERROR, "ERRO", msg);
	}

	/**
	 * Este método recebe uma descrição de uma mensagem de informação e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemInfo(String msg) {
		addMensagem(INFO, "INFO", msg);
	}

	/**
	 * Este método recebe uma descrição de uma mensagem de aviso e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemAviso(String msg) {
		addMensagem(WARN, "AVISO", msg);
	}

	/**
	 * Este método recebe uma descrição de uma mensagem de erro grave e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemFatal(String msg) {
		addMensagem(FATAL, "GRAVE", msg);
	}

	/**
	 * Este método exibe uma mensagem de inclusão com sucesso na tela.
	 */
	public void addMensagemNenhumRegistroEncontrado() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_NENHUM_REGISTRO));
	}
	
	/**
	 * Este método exibe uma mensagem de inclusão com sucesso na tela.
	 */
	public void addMensagemInclusaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_INCLUSAO));
	}

	/**
	 * Este método exibe uma mensagem de exclusão com sucesso na tela.
	 */
	public void addMensagemExclusaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_EXCLUSAO));
	}

	/**
	 * Este método exibe uma mensagem de alteração com sucesso na tela.
	 */
	public void addMensagemAlteracaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_ALTERACAO));
	}

	/**
	 * Este método retorna o ID do Usuário logado.
	 * 
	 * @return String
	 */
	public String getIdUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getIdUsuario();
	}

	/**
	 * Este método retorna o ID da Pessoa logada.
	 * 
	 * @return String
	 */
	public String getIdPessoaSessao() {
		return AutenticacaoUtil.getAutenticacao().getIdPessoa();
	}

	/**
	 * Este método retorna o nome completo do Usuário logado.
	 * 
	 * @return String
	 */
	public String getNomeUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getNomeUsuario();
	}

	/**
	 * Este método retorna a data e hora que o login foi efetuado.
	 * 
	 * @return Date
	 */
	public Date getDataHoraLoginSessao() {
		return AutenticacaoUtil.getAutenticacao().getDataHoraLogin();
	}

	/**
	 * Este método retorna o Usuário logado.
	 * 
	 * @return String
	 */
	public String getUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getUsuario();
	}

	/**
	 * Este método retorna o UsuarioEstabelecimento logado.
	 * 
	 * @return UsuarioEstabelecimento
	 */
	public UsuarioEstabelecimento getUsuarioEstabelecimentoSessao() {
		return AutenticacaoSilt.getUsuarioEstabelecimento();
	}
	
	/**
	 * Este método recebe o nome de um Bean Geranciado pelo Spring e retorna uma instância do mesmo.
	 * 
	 * @param String nome
	 * @return Object
	 */
	public Object getBean(String nome) {
		return (Object) SpringContainer.getInstancia().getBean(nome);
	}

	/**
	 * Este método retorna a instância do FacesContext (JSF)
	 * 
	 * @return FacesContext
	 */
	public static FacesContext getFacesContextInstance() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Este método retorna a Sessão do JSF no servidor
	 * 
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		FacesContext fc = getFacesContextInstance();
		ExternalContext ec = fc.getExternalContext();
		return (HttpSession) ec.getSession(false);
	}

	/**
	 * Limpa os dados dos componentes de edição e de seus filhos, recursivamente. Checa se o componente é instância de EditableValueHolder e 'reseta' suas
	 * propriedades.
	 */
	public void limparCampos(UIComponent component) {
		if (component instanceof EditableValueHolder) {
			EditableValueHolder evh = (EditableValueHolder) component;
			evh.setSubmittedValue(null);
			evh.setValue(null);
			evh.setLocalValueSet(false);
			evh.setValid(true);
		}
		if (component.getChildCount() > 0) {
			for (UIComponent child : component.getChildren()) {
				component.getChildren().clear();
			}
		}
	}

	private static Class<?> getClasseGenerica(Class<?> classe) {
		if (classe == null)
			return null;

		if (classe.getGenericSuperclass() instanceof ParameterizedType)
			return (Class<?>) ((ParameterizedType) classe.getGenericSuperclass()).getActualTypeArguments()[0];

		return null;
	}
	
	private Class<T> getClasseDaEntidade() {
		return classeDaEntidade;
	}

	private void setClasseDaEntidade(Class<T> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}

	public boolean isEstadoConsultando() {
		return estadoConsultando;
	}

	public void setEstadoConsultando(boolean estadoConsultando) {
		this.estadoConsultando = estadoConsultando;
	}
	
}
