package br.gov.go.saude.silt.caso_notificacao.servico;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.condicao_risco.servico.CondicaoRiscoServico;
import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.bairro.servico.BairroServico;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.contato_pessoa.servico.ContatoPessoaServico;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.endereco_pessoa.servico.EnderecoPessoaServico;
import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.corp.escolaridade.servico.EscolaridadeServico;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.logradouro.servico.LogradouroServico;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.municipio.servico.MunicipioServico;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.pessoa_fisica.servico.PessoaFisicaServico;
import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.raca_cor.servico.RacaCorServico;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.corp.tipo_contato.servico.TipoContatoServico;
import br.gov.go.saude.silt.corp.tipo_endereco.entidade.TipoEndereco;
import br.gov.go.saude.silt.corp.tipo_endereco.servico.TipoEnderecoServico;
import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.corp.tipo_logradouro.servico.TipoLogradouroServico;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.corp.unidade_federativa.servico.UnidadeFederativaServico;
import br.gov.go.saude.silt.dose.servico.DoseServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.historico_caso_notificacao.servico.HistoricoCasoNotificacaoServico;
import br.gov.go.saude.silt.historico_transferencia.servico.HistoricoTransferenciaServico;
import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.indicacao_tratamento.servico.IndicacaoTratamentoServico;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.individuo.servico.IndividuoServico;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.situacao.servico.SituacaoServico;
import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.tipo_tratamento.servico.TipoTratamentoServico;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;
import br.gov.go.saude.silt.util.FiltroPesquisa;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.SituacaoCasoNotificacaoEnum;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.enumerador.TipoAcaoUsuarioEnum;
import br.gov.go.saude.silt.util.enumerador.TipoEntradaEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 309 $ $Author: atillabarros $ $Date: 2014-03-10 14:45:35 -0300 (Seg, 10 Mar 2014) $
 * @category Servico
 */
@Service
public class CasoNotificacaoServico extends Servico<CasoNotificacao> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SituacaoServico situacaoServico;
	@Autowired
	private PessoaFisicaServico pessoaFisicaServico;
	@Autowired
	private RacaCorServico racaCorServico;
	@Autowired
	private EscolaridadeServico escolaridadeServico;
	@Autowired
	private LogradouroServico logradouroServico;
	@Autowired
	private TipoEnderecoServico tipoEnderecoServico;
	@Autowired
	private UnidadeFederativaServico unidadeFederativaServico;
	@Autowired
	private MunicipioServico municipioServico;
	@Autowired
	private BairroServico bairroServico;
	@Autowired
	private TipoContatoServico tipoContatoServico;
	@Autowired
	private TipoTratamentoServico tipoTratamentoServico;
	@Autowired
	private TipoLogradouroServico tipoLogradouroServico;
	@Autowired
	private EstabelecimentoServico estabelecimentoServico;
	@Autowired
	private CondicaoRiscoServico condicaoRiscoServico;
	@Autowired
	private IndicacaoTratamentoServico indicacaoTratamentoServico;
	@Autowired
	private UsuarioEstabelecimentoServico usuarioEstabelecimentoServico;
	@Autowired
	private IndividuoServico individuoServico;
	@Autowired
	private ContatoPessoaServico contatoPessoaServico;
	@Autowired
	private EnderecoPessoaServico enderecoPessoaServico;
	@Autowired
	private HistoricoCasoNotificacaoServico historicoCasoNotificacaoServico;
	@Autowired
	private HistoricoTransferenciaServico historicoTransferenciaServico;
	@Autowired
	private DoseServico doseServico;

	@Autowired
	private CasoNotificacaoDAO dao;

	/**
	 * Realiza as consultas de alguns historicos a serem exibidos no detalhamento para o usuário.
	 * 
	 * @param CasoNotificacao casoNotificacao
	 * @return CasoNotificacao
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CasoNotificacao consultarHistoricos(CasoNotificacao casoNotificacao) throws ServicoException {
		try {
			casoNotificacao.setHistoricoCasoNotificacoes(historicoCasoNotificacaoServico.consultarPorCasoNotificacao(casoNotificacao));
			casoNotificacao.setHistoricoTransferencias(historicoTransferenciaServico.consultarPorCasoNotificacao(casoNotificacao));
			casoNotificacao.setDoses(doseServico.consultarPorCasoNotificacao(casoNotificacao));
			return casoNotificacao;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe uma entidade (CasoNotificacao) por parâmetro e a persiste no banco de dados.
	 * 
	 * @param CasoNotificacao entidade
	 * @throws ServicoException
	 * @return CasoNotificacao
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public CasoNotificacao salvarOuAtualizar(CasoNotificacao entidade) throws ServicoException {
		try {
			entidade.setUsuarioEstabelecimento(usuarioEstabelecimentoServico.consultarPorUsuarioSistema(getUsuarioSessao()));
			entidade.setExcluido(Boolean.FALSE);
			entidade.setDataCadastro(Calendar.getInstance().getTime());

			if (entidade.isTransient()) {
				entidade.getIndividuo().setEstabelecimento(entidade.getEstabelecimento());
				entidade.getIndividuo().getPessoaFisica().getNome().toUpperCase();
				entidade.getIndividuo().getPessoaFisica().getNomeMae().toUpperCase();
				entidade.setNumeroCasoNotificacao(gerarNumeroCasoNotificacao());
				entidade.setTipoEntrada(TipoEntradaEnum.NOVO_TRATAMENTO);
				entidade.setSituacao(situacaoServico.consultarPorDescricao(SituacaoCasoNotificacaoEnum.EM_TRATAMENTO));
				entidade.setStatus(entidade.getSituacao().getStatus());
				entidade.setIndividuo(individuoServico.salvarOuAtualizar(entidade.getIndividuo()));

				entidade = super.salvarOuAtualizar(entidade);

				historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade, TipoAcaoUsuarioEnum.CADASTRO,
						Mensagem.get(Mensagem.MSG_CASO_NOTIFICACAO_CADASTRO));
			} else {
				entidade.setStatus(entidade.getSituacao().getStatus());
				entidade = super.salvarOuAtualizar(entidade);
				historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade, TipoAcaoUsuarioEnum.ALTERACAO,
						Mensagem.get(Mensagem.MSG_CASO_NOTIFICACAO_ALTERACAO));
			}

			return entidade;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo realiza o encerramento de um caso de notificacao solicitando ao final a inclusao de um historico da alteracao.
	 * 
	 * @param CasoNotificacao entidade
	 * @throws ServicoException
	 */
	public void encerrarCasoNotificacao(CasoNotificacao entidade) throws ServicoException {
		try {
			if (entidade != null) {
				entidade.setDataFimTratamento(new Date());
				this.salvarOuAtualizar(entidade);
				historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade, TipoAcaoUsuarioEnum.ENCERRAMENTO,
						Mensagem.get(Mensagem.MSG_CASO_NOTIFICACAO_ENCERRAMENTO));
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo realiza o retorno de um caso de notificacao que estava encerrado.
	 * 
	 * @param CasoNotificacao entidade
	 * @throws ServicoException
	 */
	public void retornarCasoNotificacao(CasoNotificacao entidade) throws ServicoException {
		try {
			if (entidade != null) {
				entidade.setDataFimTratamento(null);
				this.salvarOuAtualizar(entidade);
				historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade, TipoAcaoUsuarioEnum.RETORNO,
						Mensagem.get(Mensagem.MSG_CASO_NOTIFICACAO_RETORNO));
			}
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe uma uma entidade por parâmetro e a exclui no banco de dados.
	 * 
	 * @param CasoNotificacao entidade
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void excluir(CasoNotificacao entidade) throws ServicoException {
		try {
			super.excluir(entidade);
			historicoCasoNotificacaoServico.addHistoricoCasoNotificacao(entidade, TipoAcaoUsuarioEnum.EXCLUSAO,
					Mensagem.get(Mensagem.MSG_CASO_NOTIFICACAO_EXCLUSAO));
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	@Override
	protected DAO<CasoNotificacao> getDAO() {
		return dao;
	}

	public static CasoNotificacaoServico getInstancia() {
		return (CasoNotificacaoServico) SpringContainer.getInstancia().getBean("casoNotificacaoServico");
	}

	/**
	 * Este método retorna uma PessoaFisica ativa por CPF.
	 * 
	 * @param Long cpf
	 * @return PessoaFisica
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PessoaFisica consultarPessoaFisicaPorCPF(Long cpf) throws ServicoException {
		return pessoaFisicaServico.consultarPorCPF(cpf);
	}

	/**
	 * Este método recebe uma PessoaFisica como argumento e pesquisa na tabela Individuo
	 * 
	 * @param PessoaFisica pessoa
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Individuo consultarIndividuoPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		return individuoServico.consultarPorPessoaFisica(pessoa);
	}

	/**
	 * Este método recebe um CPF como argumento e pesquisa na tabela Individuo
	 * 
	 * @param Long cpf
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Individuo consultarIndividuoPorCPF(Long cpf) throws ServicoException {
		return individuoServico.consultarPorCPF(cpf);
	}

	/**
	 * Este método retorna uma lista de Estabelecimento de acordo com o nomeFantasia informado com limite de 10 itens.
	 * 
	 * @param String nomeFantasia
	 * @return List<Estabelecimento>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Estabelecimento> consultarEstabelecimentosPorNomeFantasia(String nomeFantasia) throws ServicoException {
		return estabelecimentoServico.consultarPorNomeFantasia(nomeFantasia);
	}

	/**
	 * Este método retorna uma lista de Indivíduos de acordo com o nome informado com limite de 10 itens.
	 * 
	 * @param String nome
	 * @return List<Individuo>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Individuo> consultarIndividuosPorNome(String nome) throws ServicoException {
		return individuoServico.consultarPorNome(nome);
	}

	/**
	 * Este método pesquisa os logradouros de acordo com CEP recebido.
	 * 
	 * @param Long cep
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorCEP(Long cep) throws ServicoException {
		return logradouroServico.consultarLogradourosPorCEP(cep);
	}

	/**
	 * Este método pesquisa os municipios de acordo com Estado recebido.
	 * 
	 * @param UnidadeFederativa estado
	 * @return List<Municipio>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Municipio> consultarMunicipiosPorUnidadeFederativa(UnidadeFederativa estado) throws ServicoException {
		return municipioServico.consultarPorUnidadeFederativa(estado);
	}

	/**
	 * Este método pesquisa os bairros de acordo com Municipio recebido.
	 * 
	 * @param Municipio municipio
	 * @return List<Bairro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bairro> consultarBairrosPorMunicipio(Municipio municipio) throws ServicoException {
		return bairroServico.consultarBairrosPorMunicipio(municipio);
	}

	/**
	 * Este método pesquisa os logradouros de acordo com Bairro recebido.
	 * 
	 * @param Bairro bairro
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorBairro(Bairro bairro) throws ServicoException {
		return logradouroServico.consultarLogradourosPorBairro(bairro);
	}

	/**
	 * Este método pesquisa os logradouros de acordo com Bairro e Tipo recebido.
	 * 
	 * @param Bairro bairro
	 * @param TipoLogradouro tipoLogradouro
	 * @return List<Logradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorBairroETipo(Bairro bairro, TipoLogradouro tipoLogradouro) throws ServicoException {
		return logradouroServico.consultarLogradourosPorBairroETipo(bairro, tipoLogradouro);
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<IndicacaoTratamento>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<IndicacaoTratamento> consultarTodosIndicacaoTratamento() throws ServicoException {
		return indicacaoTratamentoServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<CondicaoRisco>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CondicaoRisco> consultarTodosCondicaoRisco() throws ServicoException {
		return condicaoRiscoServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<TipoTratamento>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoTratamento> consultarTodosTipoTratamento() throws ServicoException {
		return tipoTratamentoServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<RacaCor>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<RacaCor> consultarTodosRacaCor() throws ServicoException {
		return racaCorServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<Escolaridade>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Escolaridade> consultarTodosEscolaridade() throws ServicoException {
		return escolaridadeServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<UnidadeFederativa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UnidadeFederativa> consultarTodosUnidadeFederativa() throws ServicoException {
		return unidadeFederativaServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<TipoEndereco>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoEndereco> consultarTodosTipoEndereco() throws ServicoException {
		return tipoEnderecoServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<TipoContato>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoContato> consultarTodosTipoContato() throws ServicoException {
		return tipoContatoServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<TipoLogradouro>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoLogradouro> consultarTodosTipoLogradouro() throws ServicoException {
		return tipoLogradouroServico.consultarTodos();
	}

	/**
	 * Este método pesquisa e retorna todos os dados da tabela da respectiva entidade.
	 * 
	 * @return List<Situacao>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Situacao> consultarTodasSituacoes() throws ServicoException {
		return situacaoServico.consultarTodos();
	}

	/**
	 * Realiza a consulta de situacoes por StatusEnum informado.
	 * 
	 * @param StatusEnum status
	 * @return List<Situacao>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Situacao> consultarSituacoesPorStatus(StatusEnum status) throws ServicoException {
		try {
			FiltroPesquisa filtro = new FiltroPesquisa();
			filtro.getCampos().put("idStatus", status);
			filtro.getCamposOrdenacaoPesquisa().add("descricao");

			return situacaoServico.consultarPorFiltro(filtro);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método consulta todos os contatos relacionados a pessoa.
	 * 
	 * @param PessoaFisica pessoa
	 * @return List<ContatoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ContatoPessoa> consultarContatosPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		return contatoPessoaServico.consultarPorPessoaFisica(pessoa);
	}

	/**
	 * Este método consulta todos os endereços relacionados a pessoa.
	 * 
	 * @param PessoaFisica pessoa
	 * @return List<EnderecoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<EnderecoPessoa> consultarEnderecosPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		return enderecoPessoaServico.consultarPorPessoaFisica(pessoa);
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param Long id
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Individuo consultarIndividuoPorId(Long id) throws ServicoException {
		return individuoServico.consultarPorId(id);
	}

	/**
	 * Retorna um numero para um determinado Caso Notificacao. A rotina concatena algumas informacoes como: dia, mes, ano, milesegudos e um numero randomico.
	 * 
	 * @return String
	 * @throws ServicoException
	 */
	@SuppressWarnings("all")
	public String gerarNumeroCasoNotificacao() throws ServicoException {
		try {
			StringBuilder numero = new StringBuilder();
			Random radom = new Random();
			Calendar data = Calendar.getInstance();

			numero.append("CN").append(data.get(Calendar.DAY_OF_MONTH)).append(data.get(Calendar.MONTH)).append(data.get(Calendar.HOUR_OF_DAY))
					.append(data.get(Calendar.MINUTE)).append(radom.nextInt(data.get(Calendar.MILLISECOND) * 10)).append("/").append(data.get(Calendar.YEAR));

			return numero.toString();
		} catch (Exception e) {
			throw new ServicoException(Mensagem.MSG_CASO_NOTIFICACAO_ERRO_GERAR_NUMERO);
		}
	}
}
