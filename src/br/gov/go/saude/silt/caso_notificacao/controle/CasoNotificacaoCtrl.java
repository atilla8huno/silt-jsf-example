package br.gov.go.saude.silt.caso_notificacao.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.hibernate.Hibernate;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoEndereco;
import br.gov.go.saude.corp.enumerador.EnumZonaEndereco;
import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.caso_notificacao.servico.CasoNotificacaoServico;
import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.BaciloCgEnum;
import br.gov.go.saude.silt.util.enumerador.ExameEnum;
import br.gov.go.saude.silt.util.enumerador.QuantidadeDoseEnum;
import br.gov.go.saude.silt.util.enumerador.RaioXEnum;
import br.gov.go.saude.silt.util.enumerador.SexoEnum;
import br.gov.go.saude.silt.util.enumerador.SimNaoEnum;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.enumerador.TipoDrogaEnum;
import br.gov.go.saude.silt.util.enumerador.TipoEntradaEnum;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Controlador
 */
@ManagedBean @Scope("view") @Controller
public class CasoNotificacaoCtrl extends Controlador<CasoNotificacao> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CasoNotificacaoServico servico;

	private EnderecoPessoa enderecoPessoa;
	private List<EnderecoPessoa> enderecosPessoa;

	private ContatoPessoa contatoPessoa;
	private List<ContatoPessoa> contatosPessoa;

	/* Listas para ComboBox */
	private List<Logradouro> listaLogradouros;
	private List<Municipio> listaMunicipios;
	private List<Bairro> listaBairros;
	private List<TipoLogradouro> listaTiposLogradouros;
	private List<TipoTratamento> listaTiposTratamentos;
	private List<TipoContato> listaTiposContatos;
	private List<UnidadeFederativa> listaUnidadesFederativas;
	private List<Escolaridade> listaEscolaridades;
	private List<RacaCor> listaRacasCores;
	private List<Situacao> listaSituacoes;

	/* Listas para PickList */
	private List<CondicaoRisco> condicaoRiscosRepositorio;
	private DualListModel<CondicaoRisco> dualListCondicaoRiscos;

	private List<IndicacaoTratamento> indicacaoTratamentosRepositorio;
	private DualListModel<IndicacaoTratamento> dualListIndicacaoTratamentos;

	private CasoNotificacao entidade;

	private String nome;

	/* Atributos para contato */
	private String telefone;
	private String celular;
	private String email;

	/* Atributos de filtros da pesquisa */
	private Individuo individuo;
	private Estabelecimento estabelecimento;
	private Date dataNotificacaoInicio, dataNotificacaoFim;
	private Situacao situacao;

	/* Atributo para o alteracoes do status de um caso notificacao */
	private Situacao novaSituacao;
	private List<Situacao> listaNovaSituacoes;

	/**
	 * Este método persiste a entidade na base de dados.
	 */
	@Override
	public void salvar() {
		try {
			for (CondicaoRisco condicao : getCondicaoRiscosRepositorio()) {
				getEntidade().getCondicoesRiscos().remove(condicao);
			}

			for (IndicacaoTratamento indicacao : getIndicacaoTratamentosRepositorio()) {
				getEntidade().getIndicacoesTratamentos().remove(indicacao);
			}

			getEntidade().getIndividuo().getPessoaFisica().getPessoa().getEnderecosPessoas().add(enderecoPessoa);

			super.salvar();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método exclui a entidade na base de dados.
	 */
	@Override
	public void excluir() {
		try {
			servico.excluir(servico.consultarPorId(entidade.getId()));
			entidade = null;
			addFiltrosPesquisa();
			addMensagemExclusaoSucesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Faz uma nova pesquisa de alguns historicos relacionados.
	 */
	public void consultarHistoricos() {
		try {
			entidade = servico.consultarHistoricos(entidade);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Metodo prepara a entidade para ser exibida no dialog de encerramento do caso de notificacao.
	 */
	public void exibirEncerrarCasoNotificacao() {
		try {
			prepararCasoNotificacao();
			listaNovaSituacoes = servico.consultarSituacoesPorStatus(StatusEnum.ENCERRADO);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Metodo prepara a lista de situacoes a serem exibidas.
	 */
	public void exibirRetornarCasoNotificacao() {
		try {
			prepararCasoNotificacao();
			listaNovaSituacoes = servico.consultarSituacoesPorStatus(StatusEnum.ATIVO);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Metodo prepara a entidade para ser exibida.
	 */
	public void prepararCasoNotificacao() {
		try {
			entidade = servico.consultarPorId(entidade.getId());
			Hibernate.initialize(entidade.getCondicoesRiscos());
			Hibernate.initialize(entidade.getIndicacoesTratamentos());
			Hibernate.initialize(entidade.getSituacao());
			entidade.setObservacao(null);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Realiza o encerramento de um caso de notificacao.
	 */
	public void encerrarCasoNotificacao() {
		try {
			entidade.setSituacao(novaSituacao);
			servico.encerrarCasoNotificacao(entidade);
			entidade = null;
			novaSituacao = null;
			addFiltrosPesquisa();
			addMensagemAlteracaoSucesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Realiza o encerramento de um caso de notificacao.
	 */
	public void retornarCasoNotificacao() {
		try {
			entidade.setSituacao(novaSituacao);
			servico.retornarCasoNotificacao(entidade);
			entidade = null;
			novaSituacao = null;
			addFiltrosPesquisa();
			addMensagemAlteracaoSucesso();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método atualiza a entidade selecionada para mostrar seus detalhes.
	 */
	public void detalhes() {
		try {
			entidade = servico.consultarPorId(entidade.getId());

			setContatosPessoa(new ArrayList<ContatoPessoa>(servico.consultarContatosPorPessoaFisica(getEntidade().getIndividuo().getPessoaFisica())));
			if (getContatosPessoa() != null && !getContatosPessoa().isEmpty()) {
				for (ContatoPessoa contato : getContatosPessoa()) {
					if (contato != null && contato.getTipoContato() != null && contato.getTipoContato().getDescricao().equals(TipoContato.EMAIL)) {
						email = contato.getInfo();
					} else if (contato != null && contato.getTipoContato() != null
							&& contato.getTipoContato().getDescricao().equals(TipoContato.TELEFONE_CELULAR)) {
						celular = contato.getInfo();
					} else if (contato != null && contato.getTipoContato() != null
							&& contato.getTipoContato().getDescricao().equals(TipoContato.TELEFONE_RESIDENCIAL)) {
						telefone = contato.getInfo();
					}
				}
			}

			setEnderecosPessoa(new ArrayList<EnderecoPessoa>(servico.consultarEnderecosPorPessoaFisica(getEntidade().getIndividuo().getPessoaFisica())));
			if (getEnderecosPessoa() != null && !getEnderecosPessoa().isEmpty()) {
				enderecoPessoa = getEnderecosPessoa().get(0);
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Método responsável por adicionar os filtros da pesquisa na listagem.
	 */
	@Override
	public void addFiltrosPesquisa() {
		limpar();

		getListaPaginada().addFiltro("individuo", individuo);
		getListaPaginada().addFiltro("estabelecimento", estabelecimento);
		getListaPaginada().addFiltro("dataNotificacaoInicio", dataNotificacaoInicio);
		getListaPaginada().addFiltro("dataNotificacaoFim", dataNotificacaoFim);
		getListaPaginada().addFiltro("situacao", situacao);

		getListaPaginada().addOrdenacao("dataNotificacao");
	}

	@Override
	public void editar() {
		try {
			entidade = servico.consultarPorId(entidade.getId());

			setContatosPessoa(new ArrayList<ContatoPessoa>(servico.consultarContatosPorPessoaFisica(getEntidade().getIndividuo().getPessoaFisica())));
			if (getContatosPessoa() != null && !getContatosPessoa().isEmpty()) {
				for (ContatoPessoa contato : getContatosPessoa()) {
					if (contato != null && contato.getTipoContato() != null && contato.getTipoContato().getDescricao().equals(TipoContato.EMAIL)) {
						email = contato.getInfo();
					} else if (contato != null && contato.getTipoContato() != null
							&& contato.getTipoContato().getDescricao().equals(TipoContato.TELEFONE_CELULAR)) {
						celular = contato.getInfo();
					} else if (contato != null && contato.getTipoContato() != null
							&& contato.getTipoContato().getDescricao().equals(TipoContato.TELEFONE_RESIDENCIAL)) {
						telefone = contato.getInfo();
					}
				}
			}

			setEnderecosPessoa(new ArrayList<EnderecoPessoa>(servico.consultarEnderecosPorPessoaFisica(getEntidade().getIndividuo().getPessoaFisica())));
			if (getEnderecosPessoa() != null && !getEnderecosPessoa().isEmpty()) {
				enderecoPessoa = getEnderecosPessoa().get(0);
			}

			if (!enderecoPessoa.getEndereco().getLogradouro().getBairro().getMunicipio().getUnidadeFederativa().isTransient()) {
				consultarMunicipiosPorEstado();
				consultarBairrosPorMunicipio();
				consultarLogradourosPorBairro();
			}

			for (CondicaoRisco condicao : getEntidade().getCondicoesRiscos())
				getCondicaoRiscosRepositorio().remove(condicao);

			for (IndicacaoTratamento indicacao : getEntidade().getIndicacoesTratamentos())
				getIndicacaoTratamentosRepositorio().remove(indicacao);

			dualListCondicaoRiscos = new DualListModel<CondicaoRisco>(getCondicaoRiscosRepositorio(), getEntidade().getCondicoesRiscos());
			dualListIndicacaoTratamentos = new DualListModel<IndicacaoTratamento>(getIndicacaoTratamentosRepositorio(), getEntidade()
					.getIndicacoesTratamentos());
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}

		setEstadoConsultando(Boolean.FALSE);
	}

	/**
	 * Método responsável por adicionar condições de risco no repositório.
	 * 
	 * @param TransferEvent event
	 */
	public void addCondicaoRisco(TransferEvent event) {
		for (Object item : event.getItems()) {
			if (event.isAdd()) {
				if (!getEntidade().getCondicoesRiscos().contains((CondicaoRisco) item)) {
					getEntidade().getCondicoesRiscos().add((CondicaoRisco) item);
					getCondicaoRiscosRepositorio().remove((CondicaoRisco) item);
				}
			} else {
				if (!getCondicaoRiscosRepositorio().contains((CondicaoRisco) item)) {
					getCondicaoRiscosRepositorio().add((CondicaoRisco) item);
					getEntidade().getCondicoesRiscos().remove((CondicaoRisco) item);
				}
			}
		}
	}

	/**
	 * Método responsável por adicionar indicações de tratamento no repositório.
	 * 
	 * @param TransferEvent event
	 */
	public void addIndicacaoTratamento(TransferEvent event) {
		for (Object item : event.getItems()) {
			if (event.isAdd()) {
				if (!getEntidade().getIndicacoesTratamentos().contains((IndicacaoTratamento) item)) {
					getEntidade().getIndicacoesTratamentos().add((IndicacaoTratamento) item);
					getIndicacaoTratamentosRepositorio().remove((IndicacaoTratamento) item);
				}
			} else {
				if (!getIndicacaoTratamentosRepositorio().contains((IndicacaoTratamento) item)) {
					getIndicacaoTratamentosRepositorio().add((IndicacaoTratamento) item);
					getEntidade().getIndicacoesTratamentos().remove((IndicacaoTratamento) item);
				}
			}
		}
	}

	/**
	 * Este método retorna uma lista para exibir no autocomplete.
	 * 
	 * @param String valorPesquisado
	 * @return List<Estabelecimento>
	 */
	public List<Estabelecimento> autoCompleteEstabelecimento(String valorPesquisado) {
		List<Estabelecimento> estabelecimentos = null;
		try {
			estabelecimentos = servico.consultarEstabelecimentosPorNomeFantasia(valorPesquisado);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return estabelecimentos;
	}

	/**
	 * Este método retorna uma lista para exibir no autocomplete.
	 * 
	 * @param String nome
	 * @return List<Individuo>
	 */
	public List<Individuo> autoCompleteIndividuo(String nome) {
		List<Individuo> individuos = null;
		try {
			individuos = servico.consultarIndividuosPorNome(nome);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
		return individuos;
	}

	/**
	 * Método responsável por consultar endereços com o CEP informado.
	 */
	public void consultarCEP() {
		try {
			getListaLogradouros().clear();
			Long cep = getEnderecoPessoa().getEndereco().getLogradouro().getCep();

			if (cep != null && cep > 0L) {
				setListaLogradouros(servico.consultarLogradourosPorCEP(cep));

				if (getListaLogradouros().isEmpty()) {
					addMensagemInfo("Não foram encontrados Logradouros com o CEP informado.");
					limparDadosResidenciais();
				} else {
					getEnderecoPessoa().getEndereco().setLogradouro(getListaLogradouros().get(0));
					consultarMunicipiosPorEstado();
					consultarBairrosPorMunicipio();
					consultarLogradourosPorBairro();
				}
			} else {
				limparDadosResidenciais();
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta Individuos e PessoasFisicas pelo CPF informado.
	 */
	public void consultarCPF() {
		try {
			Individuo individuo = servico.consultarIndividuoPorCPF(getEntidade().getIndividuo().getPessoaFisica().getCpf());

			if (individuo != null && !individuo.isTransient()) {
				getEntidade().setIndividuo(individuo);
			} else {
				PessoaFisica pessoaFisica = servico.consultarPessoaFisicaPorCPF(getEntidade().getIndividuo().getPessoaFisica().getCpf());

				if (pessoaFisica != null && !pessoaFisica.isTransient()) {
					getEntidade().getIndividuo().setPessoaFisica(pessoaFisica);
				} else {
					addMensagemAviso("Não foram encontrados registros com o CPF informado.");
				}
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta todos os municípios pelo Estado selecionado.
	 */
	public void consultarMunicipiosPorEstado() {
		try {
			getListaMunicipios().clear();
			UnidadeFederativa estado = getEnderecoPessoa().getEndereco().getLogradouro().getBairro().getMunicipio().getUnidadeFederativa();

			if (estado != null && !estado.isTransient()) {
				setListaMunicipios(servico.consultarMunicipiosPorUnidadeFederativa(estado));

				if (getListaMunicipios().isEmpty()) {
					addMensagemInfo("Não existem municipios cadastrados para o estado escolhido.");
				}
			} else {
				addMensagemInfo("Selecione um Estado.");
				limparDadosResidenciais();
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta todos os bairros pelo Município selecionado.
	 */
	public void consultarBairrosPorMunicipio() {
		try {
			getListaBairros().clear();
			Municipio municipio = getEnderecoPessoa().getEndereco().getLogradouro().getBairro().getMunicipio();

			if (municipio != null && !municipio.isTransient()) {
				setListaBairros(servico.consultarBairrosPorMunicipio(municipio));

				if (getListaBairros().isEmpty()) {
					addMensagemInfo("Não existem bairros cadastrados para o municipio escolhido.");
				}
			} else {
				addMensagemInfo("Selecione uma Cidade.");
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta todos os logradouros pelo Bairro selecionado.
	 */
	public void consultarLogradourosPorBairro() {
		try {
			getListaLogradouros().clear();
			Bairro bairro = getEnderecoPessoa().getEndereco().getLogradouro().getBairro();

			if (bairro != null && !bairro.isTransient()) {
				setListaLogradouros(servico.consultarLogradourosPorBairro(bairro));

				if (getListaLogradouros().isEmpty()) {
					addMensagemInfo("Não existem logradouros cadastrados para o bairro escolhido.");
				}
			} else {
				addMensagemInfo("Selecione um Bairro.");
				limparDadosResidenciais();
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta todos os logradouros pelo Bairro e Tipo de Endereço selecionados.
	 */
	public void consultarLogradourosPorBairroETipo() {
		try {
			getListaLogradouros().clear();
			Bairro bairro = getEnderecoPessoa().getEndereco().getLogradouro().getBairro();
			TipoLogradouro tipoLogradouro = getEnderecoPessoa().getEndereco().getLogradouro().getTipoLogradouro();

			if ((bairro != null && !bairro.isTransient()) && (tipoLogradouro != null && !tipoLogradouro.isTransient())) {
				setListaLogradouros(servico.consultarLogradourosPorBairroETipo(bairro, tipoLogradouro));

				if (getListaLogradouros().isEmpty()) {
					addMensagemInfo("Não existem logradouros cadastrados para o filtro escolhido.");
				}
			} else {
				addMensagemInfo("Escolha o bairro antes de aplicar o filtro.");
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Método responsável por adicionar o Telefone preenchido no formulário.
	 */
	public void addTelefone() {
		ContatoPessoa contatoPessoa = new ContatoPessoa();
		contatoPessoa.setId(1L);
		contatoPessoa.setInfo(telefone);

		for (TipoContato tipoContato : getListaTiposContatos()) {
			if (tipoContato.getDescricao().equals(TipoContato.TELEFONE_RESIDENCIAL)) {
				contatoPessoa.setTipoContato(tipoContato);
				break;
			}
		}

		getEntidade().getIndividuo().getPessoaFisica().getPessoa().getContatosPessoas().add(contatoPessoa);
	}

	/**
	 * Método responsável por adicionar o Celular preenchido no formulário.
	 */
	public void addCelular() {
		ContatoPessoa contatoPessoa = new ContatoPessoa();
		contatoPessoa.setId(2L);
		contatoPessoa.setInfo(celular);

		for (TipoContato tipoContato : getListaTiposContatos()) {
			if (tipoContato.getDescricao().equals(TipoContato.TELEFONE_CELULAR)) {
				contatoPessoa.setTipoContato(tipoContato);
				break;
			}
		}

		getEntidade().getIndividuo().getPessoaFisica().getPessoa().getContatosPessoas().add(contatoPessoa);
	}

	/**
	 * Método responsável por adicionar o E-mail preenchido no formulário.
	 */
	public void addEmail() {
		ContatoPessoa contatoPessoa = new ContatoPessoa();
		contatoPessoa.setId(3L);
		contatoPessoa.setInfo(email);

		for (TipoContato tipoContato : getListaTiposContatos()) {
			if (tipoContato.getDescricao().equals(TipoContato.EMAIL)) {
				contatoPessoa.setTipoContato(tipoContato);
				break;
			}
		}

		getEntidade().getIndividuo().getPessoaFisica().getPessoa().getContatosPessoas().add(contatoPessoa);
	}

	/* Métodos auxiliares para limpeza da tela */

	/**
	 * Este método anula a entidade, refletindo no formulário.
	 */
	@Override
	public void limpar() {
		entidade = null;

		limparDadosPessoais();
		limparDadosResidenciais();

		if (!isEstadoConsultando()) {
			try {
				setCondicaoRiscosRepositorio(new ArrayList<CondicaoRisco>(servico.consultarTodosCondicaoRisco()));
				setIndicacaoTratamentosRepositorio(new ArrayList<IndicacaoTratamento>(servico.consultarTodosIndicacaoTratamento()));

				dualListCondicaoRiscos = new DualListModel<CondicaoRisco>(getCondicaoRiscosRepositorio(), getEntidade().getCondicoesRiscos());
				dualListIndicacaoTratamentos = new DualListModel<IndicacaoTratamento>(getIndicacaoTratamentosRepositorio(), getEntidade()
						.getIndicacoesTratamentos());
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}

		getListaPaginada().limpar();
	}

	/**
	 * Este método anula os objetos referentes a aba Dados Pessoais.
	 */
	private void limparDadosPessoais() {
		getEntidade().setIndividuo(null);
		telefone = null;
		email = null;
		celular = null;
	}

	/**
	 * Este método anula os objetos referentes a aba Dados Gerais.
	 */
	@SuppressWarnings("unused")
	private void limparDadosGerais() {
		getEntidade().setEstabelecimento(null);
		getEntidade().setTipoTratamento(null);
		getEntidade().setDataNotificacao(null);
		getEntidade().setDataInicioTratamento(null);
		getEntidade().setTipoEntrada(null);
		getEntidade().setSituacao(null);
	}

	/**
	 * Este método anula os objetos referentes a aba Dados Residenciais.
	 */
	private void limparDadosResidenciais() {
		enderecoPessoa = null;
		getListaMunicipios().clear();
		getListaBairros().clear();
		getListaLogradouros().clear();
	}

	/**
	 * Este método anula os objetos referentes a aba Dados Complementares.
	 */
	@SuppressWarnings("unused")
	private void limparDadosComplementares() {
		getEntidade().setBaciloCg(null);
		getEntidade().setBaciloNegativa(null);
		getEntidade().setCulturaNegativa(null);
		getEntidade().setTosse(null);
		getEntidade().setFebre(null);
		getEntidade().setRaioXTorax(null);
		getEntidade().setTratamentoPrevioTb(null);
		getEntidade().setContatoIndice(Boolean.TRUE);
		getEntidade().setNomeContato(null);
		getEntidade().setCodigoSinanContato(null);
		getEntidade().setDataTesteTuberculinico(null);
		getEntidade().setResultadoTesteTuberculinico(null);
		getEntidade().setConversaoTuberculinicaRecente(null);
		getEntidade().setCondicoesRiscos(null);

		try {
			setCondicaoRiscosRepositorio(new ArrayList<CondicaoRisco>(servico.consultarTodosCondicaoRisco()));
			dualListCondicaoRiscos = new DualListModel<CondicaoRisco>(getCondicaoRiscosRepositorio(), getEntidade().getCondicoesRiscos());
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método anula os objetos referentes a aba Esquema do Tratamento.
	 */
	@SuppressWarnings("unused")
	private void limparEsquemaTratamento() {
		getEntidade().setTipoDroga(null);
		getEntidade().setQuantidadeDose(null);
		getEntidade().setNomeMedicoResponsavel(null);
		getEntidade().setCrmMedicoResponsavel(null);
		getEntidade().setIndicacoesTratamentos(null);

		try {
			setIndicacaoTratamentosRepositorio(new ArrayList<IndicacaoTratamento>(servico.consultarTodosIndicacaoTratamento()));
			dualListIndicacaoTratamentos = new DualListModel<IndicacaoTratamento>(getIndicacaoTratamentosRepositorio(), getEntidade()
					.getIndicacoesTratamentos());
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	public List<StatusEnum> getListaStatusEnum() {
		List<StatusEnum> listaStatusEnum = EnumeradorUtil.getDominiosAtivos(StatusEnum.class);
		listaStatusEnum.remove(StatusEnum.TODOS);
		return listaStatusEnum;
	}

	public List<SimNaoEnum> getListaSimNaoEnum() {
		List<SimNaoEnum> listaSimNaoEnum = EnumeradorUtil.getDominiosAtivos(SimNaoEnum.class);
		listaSimNaoEnum.remove(SimNaoEnum.IGNORADO);
		return listaSimNaoEnum;
	}

	public List<SimNaoEnum> getListaSimNaoIgnoradoEnum() {
		return EnumeradorUtil.getDominiosAtivos(SimNaoEnum.class);
	}

	public List<ExameEnum> getListaExameEnum() {
		return EnumeradorUtil.getDominiosAtivos(ExameEnum.class);
	}

	public List<RaioXEnum> getListaRaioXEnum() {
		return EnumeradorUtil.getDominiosAtivos(RaioXEnum.class);
	}

	public List<BaciloCgEnum> getListaBaciloCgEnum() {
		return EnumeradorUtil.getDominiosAtivos(BaciloCgEnum.class);
	}

	public List<TipoEntradaEnum> getListaTipoEntradaEnum() {
		return EnumeradorUtil.getDominiosAtivos(TipoEntradaEnum.class);
	}

	public List<TipoDrogaEnum> getListaTipoDrogaEnum() {
		return TipoDrogaEnum.getConstantes();
	}

	public List<QuantidadeDoseEnum> getListaQuantidadeDoseEnum() {
		return QuantidadeDoseEnum.getConstantes();
	}

	public List<EnumZonaEndereco> getListaZonaEnderecoEnum() {
		return EnumeradorUtil.getDominiosAtivos(EnumZonaEndereco.class);
	}

	public DualListModel<IndicacaoTratamento> getDualListIndicacaoTratamentos() {
		if (dualListIndicacaoTratamentos == null) {
			dualListIndicacaoTratamentos = new DualListModel<IndicacaoTratamento>(getIndicacaoTratamentosRepositorio(), getEntidade()
					.getIndicacoesTratamentos());
		}
		return dualListIndicacaoTratamentos;
	}

	public void setDualListIndicacaoTratamentos(DualListModel<IndicacaoTratamento> dualListIndicacaoTratamentos) {
		this.dualListIndicacaoTratamentos = dualListIndicacaoTratamentos;
	}

	public DualListModel<CondicaoRisco> getDualListCondicaoRiscos() {
		if (dualListCondicaoRiscos == null) {
			dualListCondicaoRiscos = new DualListModel<CondicaoRisco>(getCondicaoRiscosRepositorio(), getEntidade().getCondicoesRiscos());
		}
		return dualListCondicaoRiscos;
	}

	public void setDualListCondicaoRiscos(DualListModel<CondicaoRisco> dualListCondicaoRiscos) {
		this.dualListCondicaoRiscos = dualListCondicaoRiscos;
	}

	public List<IndicacaoTratamento> getIndicacaoTratamentosRepositorio() {
		if (indicacaoTratamentosRepositorio == null) {
			try {
				indicacaoTratamentosRepositorio = servico.consultarTodosIndicacaoTratamento() != null ? servico.consultarTodosIndicacaoTratamento()
						: new ArrayList<IndicacaoTratamento>();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		for (IndicacaoTratamento indicacao : getEntidade().getIndicacoesTratamentos())
			indicacaoTratamentosRepositorio.remove(indicacao);

		return indicacaoTratamentosRepositorio;
	}

	public void setIndicacaoTratamentosRepositorio(List<IndicacaoTratamento> indicacaoTratamentosRepositorio) {
		this.indicacaoTratamentosRepositorio = indicacaoTratamentosRepositorio;
	}

	public List<CondicaoRisco> getCondicaoRiscosRepositorio() {
		if (condicaoRiscosRepositorio == null) {
			try {
				condicaoRiscosRepositorio = servico.consultarTodosCondicaoRisco() != null ? servico.consultarTodosCondicaoRisco()
						: new ArrayList<CondicaoRisco>();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		for (CondicaoRisco condicao : getEntidade().getCondicoesRiscos())
			condicaoRiscosRepositorio.remove(condicao);

		return condicaoRiscosRepositorio;
	}

	public void setCondicaoRiscosRepositorio(List<CondicaoRisco> condicaoRiscosRepositorio) {
		this.condicaoRiscosRepositorio = condicaoRiscosRepositorio;
	}

	public List<TipoTratamento> getListaTiposTratamentos() {
		if (listaTiposTratamentos == null) {
			try {
				listaTiposTratamentos = servico.consultarTodosTipoTratamento();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaTiposTratamentos;
	}

	public void setListaTiposTratamentos(List<TipoTratamento> listaTiposTratamentos) {
		this.listaTiposTratamentos = listaTiposTratamentos;
	}

	public List<RacaCor> getListaRacasCores() {
		if (listaRacasCores == null) {
			try {
				listaRacasCores = servico.consultarTodosRacaCor();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaRacasCores;
	}

	public List<Escolaridade> getListaEscolaridades() {
		if (listaEscolaridades == null) {
			try {
				listaEscolaridades = servico.consultarTodosEscolaridade();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaEscolaridades;
	}

	public List<UnidadeFederativa> getListaUnidadesFederativas() {
		if (listaUnidadesFederativas == null) {
			try {
				listaUnidadesFederativas = servico.consultarTodosUnidadeFederativa();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaUnidadesFederativas;
	}

	public List<SexoEnum> getListaSexoEnum() {
		return EnumeradorUtil.getDominiosAtivos(SexoEnum.class);
	}

	public List<EnumTipoEndereco> getListaEnumTipoEndereco() {
		return EnumeradorUtil.getDominiosAtivos(EnumTipoEndereco.class);
	}

	public List<TipoContato> getListaTiposContatos() {
		if (listaTiposContatos == null) {
			try {
				listaTiposContatos = servico.consultarTodosTipoContato();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaTiposContatos;
	}

	public List<TipoLogradouro> getListaTiposLogradouros() {
		if (listaTiposLogradouros == null) {
			try {
				listaTiposLogradouros = servico.consultarTodosTipoLogradouro();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaTiposLogradouros;
	}

	public List<Situacao> getListaSituacoes() {
		if (listaSituacoes == null) {
			try {
				listaSituacoes = servico.consultarTodasSituacoes();
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaSituacoes;
	}

	public void setListaTiposLogradouros(List<TipoLogradouro> listaTiposLogradouros) {
		this.listaTiposLogradouros = listaTiposLogradouros;
	}

	public ContatoPessoa getContatoPessoa() {
		if (contatoPessoa == null) {
			contatoPessoa = new ContatoPessoa();
		}
		return contatoPessoa;
	}

	public void setContatoPessoa(ContatoPessoa contatoPessoa) {
		this.contatoPessoa = contatoPessoa;
	}

	public List<ContatoPessoa> getContatosPessoa() {
		if (contatosPessoa == null) {
			contatosPessoa = new ArrayList<ContatoPessoa>();
		}
		return contatosPessoa;
	}

	public void setContatosPessoa(List<ContatoPessoa> contatosPessoa) {
		this.contatosPessoa = contatosPessoa;
	}

	public EnderecoPessoa getEnderecoPessoa() {
		if (enderecoPessoa == null) {
			enderecoPessoa = new EnderecoPessoa();
		}
		return enderecoPessoa;
	}

	public void setEnderecoPessoa(EnderecoPessoa enderecoPessoa) {
		this.enderecoPessoa = enderecoPessoa;
	}

	public List<EnderecoPessoa> getEnderecosPessoa() {
		if (enderecosPessoa == null) {
			enderecosPessoa = new ArrayList<EnderecoPessoa>();
		}
		return enderecosPessoa;
	}

	public void setEnderecosPessoa(List<EnderecoPessoa> enderecosPessoa) {
		this.enderecosPessoa = enderecosPessoa;
	}

	public List<Bairro> getListaBairros() {
		if (listaBairros == null) {
			listaBairros = new ArrayList<Bairro>();
		}
		return listaBairros;
	}

	public void setListaBairros(List<Bairro> listaBairros) {
		this.listaBairros = listaBairros;
	}

	public List<Municipio> getListaMunicipios() {
		if (listaMunicipios == null) {
			listaMunicipios = new ArrayList<Municipio>();
		}
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	public List<Logradouro> getListaLogradouros() {
		if (listaLogradouros == null) {
			listaLogradouros = new ArrayList<Logradouro>();
		}
		return listaLogradouros;
	}

	public void setListaLogradouros(List<Logradouro> listaLogradouros) {
		this.listaLogradouros = listaLogradouros;
	}

	@Override
	public Servico<CasoNotificacao> getServico() {
		if (servico == null) {
			servico = CasoNotificacaoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public CasoNotificacao getEntidade() {
		if (entidade == null) {
			entidade = new CasoNotificacao();
		}
		return entidade;
	}

	@Override
	public void setEntidade(CasoNotificacao entidade) {
		this.entidade = entidade;
	}

	public static CasoNotificacaoCtrl getInstancia() {
		return (CasoNotificacaoCtrl) SpringContainer.getInstancia().getBean("casoNotificacaoCtrl");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Individuo getIndividuo() {
		if (individuo == null) {
			individuo = new Individuo();
		}
		return individuo;
	}

	public void setIndividuo(Individuo individuo) {
		this.individuo = individuo;
	}

	public Estabelecimento getEstabelecimento() {
		if (estabelecimento == null) {
			estabelecimento = new Estabelecimento();
		}
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public Date getDataNotificacaoInicio() {
		return dataNotificacaoInicio;
	}

	public void setDataNotificacaoInicio(Date dataNotificacaoInicio) {
		this.dataNotificacaoInicio = dataNotificacaoInicio;
	}

	public Date getDataNotificacaoFim() {
		return dataNotificacaoFim;
	}

	public void setDataNotificacaoFim(Date dataNotificacaoFim) {
		this.dataNotificacaoFim = dataNotificacaoFim;
	}

	public Situacao getSituacao() {
		if (situacao == null) {
			situacao = new Situacao();
		}
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Situacao getNovaSituacao() {
		return novaSituacao;
	}

	public void setNovaSituacao(Situacao novaSituacao) {
		this.novaSituacao = novaSituacao;
	}

	public List<Situacao> getListaNovaSituacoes() {
		return listaNovaSituacoes;
	}

	public void setListaNovaSituacoes(List<Situacao> listaNovaSituacoes) {
		this.listaNovaSituacoes = listaNovaSituacoes;
	}

}
