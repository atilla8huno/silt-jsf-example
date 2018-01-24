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
 * @author �tilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador Gen�rico
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
	 * Este m�todo salva ou atualiza a entidade na base de dados.
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
	 * Este m�todo altera o estado da tela para inclus�o.
	 */
	public void editar() {
		setEstadoConsultando(false);
	}

	/**
	 * Este m�todo exclui a entidade na base de dados.
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
	 * Este m�todo lista todos os registros do tipo da entidade.
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
	 * Este m�todo anula a entidade, refletindo no formul�rio.
	 */
	public void limpar() {
		getListaPaginada().limpar();
		setEntidade(null);
	}

	/**
	 * Este m�todo limpa o formul�rio e retorna para p�gina de cadastro.
	 */
	public void novo() {
		limpar();
		estadoConsultando = false;
	}

	/**
	 * Este m�todo limpa o formul�rio e retorna para p�gina de listagem.
	 */
	public void voltar() {
		limpar();
		estadoConsultando = true;
	}

	/**
	 * Este m�todo adiciona filtros e efetua a pesquisa.
	 */
	public void pesquisar() {
		try {
			addFiltrosPesquisa();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este m�todo verifica se o usu�rio logado possui permiss�o para inclus�es.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoInclusao() {
		return AutenticacaoUtil.getPermissaoInclusao();
	}

	/**
	 * Este m�todo verifica se o usu�rio logado possui permiss�o para altera��es.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoAlteracao() {
		return AutenticacaoUtil.getPermissaoAlteracao();
	}

	/**
	 * Este m�todo verifica se o usu�rio logado possui permiss�o para consultas.
	 * 
	 * @return boolean
	 */
	public boolean isPossuiPermissaoConsulta() {
		return AutenticacaoUtil.getPermissaoConsulta();
	}

	/**
	 * Este m�todo verifica se o usu�rio logado possui permiss�o para exclus�es.
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
	 * Este m�todo recebe um tipo, titulo e descri��o de uma mensagem e exibe na tela.
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
	 * Este m�todo recebe uma descri��o de uma mensagem de erro e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemErro(String msg) {
		addMensagem(ERROR, "ERRO", msg);
	}

	/**
	 * Este m�todo recebe uma descri��o de uma mensagem de informa��o e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemInfo(String msg) {
		addMensagem(INFO, "INFO", msg);
	}

	/**
	 * Este m�todo recebe uma descri��o de uma mensagem de aviso e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemAviso(String msg) {
		addMensagem(WARN, "AVISO", msg);
	}

	/**
	 * Este m�todo recebe uma descri��o de uma mensagem de erro grave e exibe na tela.
	 * 
	 * @param String msg
	 */
	public void addMensagemFatal(String msg) {
		addMensagem(FATAL, "GRAVE", msg);
	}

	/**
	 * Este m�todo exibe uma mensagem de inclus�o com sucesso na tela.
	 */
	public void addMensagemNenhumRegistroEncontrado() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_NENHUM_REGISTRO));
	}
	
	/**
	 * Este m�todo exibe uma mensagem de inclus�o com sucesso na tela.
	 */
	public void addMensagemInclusaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_INCLUSAO));
	}

	/**
	 * Este m�todo exibe uma mensagem de exclus�o com sucesso na tela.
	 */
	public void addMensagemExclusaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_EXCLUSAO));
	}

	/**
	 * Este m�todo exibe uma mensagem de altera��o com sucesso na tela.
	 */
	public void addMensagemAlteracaoSucesso() {
		addMensagemInfo(Mensagem.get(Mensagem.MSG_ALTERACAO));
	}

	/**
	 * Este m�todo retorna o ID do Usu�rio logado.
	 * 
	 * @return String
	 */
	public String getIdUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getIdUsuario();
	}

	/**
	 * Este m�todo retorna o ID da Pessoa logada.
	 * 
	 * @return String
	 */
	public String getIdPessoaSessao() {
		return AutenticacaoUtil.getAutenticacao().getIdPessoa();
	}

	/**
	 * Este m�todo retorna o nome completo do Usu�rio logado.
	 * 
	 * @return String
	 */
	public String getNomeUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getNomeUsuario();
	}

	/**
	 * Este m�todo retorna a data e hora que o login foi efetuado.
	 * 
	 * @return Date
	 */
	public Date getDataHoraLoginSessao() {
		return AutenticacaoUtil.getAutenticacao().getDataHoraLogin();
	}

	/**
	 * Este m�todo retorna o Usu�rio logado.
	 * 
	 * @return String
	 */
	public String getUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getUsuario();
	}

	/**
	 * Este m�todo retorna o UsuarioEstabelecimento logado.
	 * 
	 * @return UsuarioEstabelecimento
	 */
	public UsuarioEstabelecimento getUsuarioEstabelecimentoSessao() {
		return AutenticacaoSilt.getUsuarioEstabelecimento();
	}
	
	/**
	 * Este m�todo recebe o nome de um Bean Geranciado pelo Spring e retorna uma inst�ncia do mesmo.
	 * 
	 * @param String nome
	 * @return Object
	 */
	public Object getBean(String nome) {
		return (Object) SpringContainer.getInstancia().getBean(nome);
	}

	/**
	 * Este m�todo retorna a inst�ncia do FacesContext (JSF)
	 * 
	 * @return FacesContext
	 */
	public static FacesContext getFacesContextInstance() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Este m�todo retorna a Sess�o do JSF no servidor
	 * 
	 * @return HttpSession
	 */
	public static HttpSession getSession() {
		FacesContext fc = getFacesContextInstance();
		ExternalContext ec = fc.getExternalContext();
		return (HttpSession) ec.getSession(false);
	}

	/**
	 * Limpa os dados dos componentes de edi��o e de seus filhos, recursivamente. Checa se o componente � inst�ncia de EditableValueHolder e 'reseta' suas
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
