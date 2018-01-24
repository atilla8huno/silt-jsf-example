package br.gov.go.saude.silt.util.template.infra_estrutura;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.arquitetura.util.AutenticacaoUtil;
import br.gov.go.saude.arquitetura.util.ReflexaoUtil;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.AutenticacaoSilt;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.Permissao;
import br.gov.go.saude.silt.util.enumerador.PermissaoEnum;
import br.gov.go.saude.silt.util.exception.PermissaoException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.Entidade;

/**
 * @author Átilla Barros
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Servico Genérico
 */
@SuppressWarnings("all")
public abstract class Servico<T extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected abstract DAO<T> getDAO();

	private Class<T> classeDaEntidade;

	public Servico() {
		setClasseDaEntidade((Class<T>) getClasseGenerica(this.getClass()));
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param T entidade
	 * @return T
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public T salvarOuAtualizar(T entidade) throws ServicoException {
		verificarEntidade(entidade);
		try {
			entidade = getDAO().salvarOuAtualizar(entidade);
			return entidade;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 * @return entidade
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public T incluir(T entidade) throws ServicoException {
		return salvarOuAtualizar(entidade);
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 * @return entidade
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public T alterar(T entidade) throws ServicoException {
		return salvarOuAtualizar(entidade);
	}

	/**
	 * Este método recebe uma uma entidade por parâmetro e a exclui no banco de dados.
	 * 
	 * @param entidade
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluir(T entidade) throws ServicoException {
		try {
			getDAO().excluir(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param codigo
	 * @return entidade
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public T consultarPorId(Long codigo) throws ServicoException {
		try {
			return (T) getDAO().consultarPorId(codigo);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<T>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> consultarTodos() throws ServicoException {
		try {
			return (List<T>) getDAO().consultarTodos();
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo realiza a consulta de uma determinada entidade podendo ser um consulta paginada ou nao.
	 * 
	 * @param FiltroPesquisa filtroPesquisa
	 * @return List<T>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<T> consultarPorFiltro(FiltroPesquisa filtroPesquisa) throws ServicoException {
		try {
			return (List<T>) getDAO().consultarPorFiltro(filtroPesquisa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Retorna a quantidade de linhas de uma determinada consulta.
	 * 
	 * @param Map<String, Object> filtros
	 * @return Long
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Long getNumeroLinhas(Map<String, Object> filtros) throws ServicoException {
		try {
			return getDAO().getNumeroLinhas(filtros);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método verifica se o objeto atende às regras de negócio e returna verdadeiro ou falso.
	 * 
	 * @param entidade
	 * @return boolean
	 * @throws ServicoException
	 */
	public boolean verificarEntidade(T entidade) throws ServicoException {
		try {
			Field[] propriedades = getClasseDaEntidade().getDeclaredFields();

			for (Field propriedade : propriedades) {
				Column anotacaoColumn = propriedade.getAnnotation(Column.class);
				JoinColumn anotacaoJoinColumn = propriedade.getAnnotation(JoinColumn.class);
				Id anotacaoId = propriedade.getAnnotation(Id.class);

				if (((anotacaoColumn != null && anotacaoColumn.nullable() == false) || (anotacaoJoinColumn != null && anotacaoJoinColumn.nullable() == false))
						&& anotacaoId == null) {
					Object valor = null;

					try {
						valor = ReflexaoUtil.getValor(entidade, propriedade.getName());
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}

					if (valor == null) {
						throw new ServicoException(Mensagem.MSG_CAMPO_VAZIO, propriedade.getName());
					}
				}
			}
			return true;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Verifica se o usuário logado tem permissão de consulta
	 * 
	 * @throws PermissaoException
	 */
	public void verificarPermissaoConsulta() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoConsulta()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_PESQUISAR));
		}
	}

	/**
	 * Verifica se o usuário logado tem permissão de inclusão
	 * 
	 * @throws PermissaoException
	 */
	public void verificarPermissaoInclusao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoInclusao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_SALVAR));
		}
	}

	/**
	 * Verifica s o usuário logado tem permissão de alteração
	 * 
	 * @throws PermissaoException
	 */
	public void verificarPermissaoAlteracao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoAlteracao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_ALTERAR));
		}
	}

	/**
	 * Verifica s o usuário logado tem permissão de exclusão
	 * 
	 * @throws PermissaoException
	 */
	public void verificarPermissaoExclusao() throws PermissaoException {
		if (!AutenticacaoUtil.getPermissaoExclusao()) {
			throw new PermissaoException(Mensagem.get(Mensagem.MSG_PERMISSAO_EXCLUIR));
		}
	}

	/**
	 * Este método verifica se o objeto recebido por parâmetro não é nulo (true = não nulo | false = nulo).
	 * 
	 * @param Object objeto
	 * @return boolean
	 */
	public boolean isNotNull(Object objeto) {
		return objeto != null;
	}

	/**
	 * Este método verifica se o objeto recebido por parâmetro é nulo (true = nulo | false = não nulo).
	 * 
	 * @param Object objeto
	 * @return boolean
	 */
	public boolean isNull(Object objeto) {
		return objeto == null;
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
	 * Este método retorna o Usuário do portal logado.
	 * 
	 * @return String
	 */
	public String getUsuarioSessao() {
		return AutenticacaoUtil.getAutenticacao().getUsuario();
	}

	/**
	 * Este metodo retorna um UsuarioEstabelecimento(Usuario do SILT) logado.
	 * 
	 * @return UsuarioEstabelecimento
	 */
	public UsuarioEstabelecimento getUsuarioEstabelecimento() {
		return AutenticacaoSilt.getUsuarioEstabelecimento();
	}

	/**
	 * Este método verifica se a entidade possui um atributo com mesmo nome recebido por argumento.
	 * 
	 * @param Class<T> clazz
	 * @param String nomeAtributo
	 * @return boolean
	 */
	private boolean isTemAtributo(String nomeAtributo) {
		try {
			Field[] propriedades = getClasseDaEntidade().getDeclaredFields();

			for (Field propriedade : propriedades) {
				if (propriedade.getName().equals(nomeAtributo)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
}
