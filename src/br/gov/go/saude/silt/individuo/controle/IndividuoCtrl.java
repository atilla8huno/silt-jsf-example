package br.gov.go.saude.silt.individuo.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoEndereco;
import br.gov.go.saude.corp.enumerador.EnumZonaEndereco;
import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.individuo.servico.IndividuoServico;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.SexoEnum;
import br.gov.go.saude.silt.util.template.Controlador;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 305 $ $Author: atillabarros $ $Date: 2014-02-19 17:09:26 -0300 (Qua, 19 Fev 2014) $
 * @category Controlador
 */
@ManagedBean
@Scope("view")
@Controller
public class IndividuoCtrl extends Controlador<Individuo> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IndividuoServico servico;

	private Individuo entidade;
	private EnderecoPessoa enderecoPessoa;
	private EnumTipoEndereco tipoEndereco;
	private ContatoPessoa contatoPessoa;
	private List<EnderecoPessoa> enderecos;
	private List<ContatoPessoa> contatos;

	/* Lista para comboboxs */
	private List<RacaCor> listaRacasCores;
	private List<Escolaridade> listaEscolaridades;
	private List<TipoContato> listaTiposContatos;
	private List<UnidadeFederativa> listaUnidadesFederativas;
	private List<Logradouro> listaLogradouros;
	private List<Municipio> listaMunicipios;
	private List<Bairro> listaBairros;

	/* Atributos de filtros da pesquisa */
	private String nome, nomeMae, numeroCartaoSus;
	private Long cpf;
	private Date dataNascimentoInicio, dataNascimentoFim;
	private Estabelecimento estabelecimento;

	/**
	 * Método responsável por adicionar filtros para pesquisa/listagem de registros.
	 */
	@Override
	public void addFiltrosPesquisa() {
		getListaPaginada().limpar();

		getListaPaginada().addFiltro("pessoa.nome", nome);
		getListaPaginada().addFiltro("pessoa.nomeMae", nomeMae);
		getListaPaginada().addFiltro("pessoa.cpf", cpf);
		getListaPaginada().addFiltro("dataNascimentoInicio", dataNascimentoInicio);
		getListaPaginada().addFiltro("dataNascimentoFim", dataNascimentoFim);
		getListaPaginada().addFiltro("numeroCartaoSus", numeroCartaoSus);
		getListaPaginada().addFiltro("estabelecimento", estabelecimento);

		getListaPaginada().addOrdenacao("pessoa.nome");
	}

	/**
	 * Método responsável pela limpeza da tela.
	 */
	@Override
	public void limpar() {
		entidade= null;
		nome	= null;
		nomeMae = null;
		cpf 	= null;
		dataNascimentoInicio= null;
		dataNascimentoFim 	= null;
		numeroCartaoSus 	= null;

		getContatos().clear();
		getEnderecos().clear();

		limparDadosResidenciais();
		limparDadosContato();

		/* Limpa resultados da pesquisa */
		getListaPaginada().limpar();
	}

	/**
	 * Este método prepara e altera o estado da tela para cadastro.
	 */
	@Override
	public void editar() {
		try {
			entidade	= servico.consultarPorId(getEntidade().getId());
			enderecos	= new ArrayList<EnderecoPessoa>(servico.consultarEnderecosPessoaFisica(getEntidade().getPessoaFisica()));
			contatos 	= new ArrayList<ContatoPessoa>(servico.consultarContatosPessoaFisica(getEntidade().getPessoaFisica()));
			
			setEstadoConsultando(Boolean.FALSE);
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método consulta Individuos e PessoasFisicas pelo CPF informado.
	 */
	public void consultarCPF() {
		try {
			Individuo individuo = servico.consultarPorCPF(getEntidade().getPessoaFisica().getCpf());

			if (individuo != null && !individuo.isTransient()) {
				setEntidade(individuo);
			} else {
				PessoaFisica pessoaFisica = servico.consultarPessoaFisicaPorCPF(getEntidade().getPessoaFisica().getCpf());

				if (pessoaFisica != null && !pessoaFisica.isTransient()) {
					getEntidade().setPessoaFisica(pessoaFisica);
				} else {
					addMensagemAviso("Não foram encontrados registros com o CPF informado.");
					limpar();
				}
			}
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
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
	 * Este método anula os objetos referentes a aba Dados Residenciais.
	 */
	public void limparDadosResidenciais() {
		try {
			enderecoPessoa = null;
			tipoEndereco = null;

			getListaMunicipios().clear();
			getListaBairros().clear();
			getListaLogradouros().clear();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método cria uma nova instância para contatoPessoa.
	 */
	public void limparDadosContato() {
		try {
			contatoPessoa = null;
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método adiciona o endereço preenchido na lista de endereços do Individuo.
	 */
	public void addEndereco() {
		try {
			enderecoPessoa.setTipoEndereco(tipoEndereco);

			if (!getEntidade().isTransient() || !getEntidade().getPessoaFisica().isTransient()) {
				if (getEntidade().getPessoaFisica().getPessoa().isTransient()) {
					getEntidade().getPessoaFisica().setPessoa(servico.consultarPessoaPorId(getEntidade().getPessoaFisica().getId()));
				}
				enderecoPessoa.setPessoa(getEntidade().getPessoaFisica().getPessoa());
				enderecoPessoa = servico.salvarEnderecoPessoa(enderecoPessoa);

				if (!getEnderecos().contains(enderecoPessoa)) {
					getEnderecos().add(enderecoPessoa);
				}
			} else if (enderecoPessoa.isTransient()) {
				enderecoPessoa.setId(getContatos().size() + 1L);
				getEnderecos().add(enderecoPessoa);
			}

			limparDadosResidenciais();
			addMensagemInfo(Mensagem.get(Mensagem.MSG_ENDERECO_INCLUSAO));
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método remove a entidade do banco de dados e da lista de endereços.
	 */
	public void excluirEndereco() {
		try {
			getEnderecos().remove(enderecoPessoa);

			if (!getEntidade().isTransient() || !getEntidade().getPessoaFisica().isTransient())
				servico.excluirEnderecoPessoa(enderecoPessoa);

			limparDadosResidenciais();
			addMensagemInfo(Mensagem.get(Mensagem.MSG_ENDERECO_EXCLUSAO));
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método prepara a entidade e a tela para efetuar alterações.
	 */
	public void editarEndereco() {
		try {
			tipoEndereco = enderecoPessoa.getTipoEndereco();
			// enderecoPessoa = servico.consultarEnderecoPessoaPorId(getEnderecoPessoa().getId());

			consultarMunicipiosPorEstado();
			consultarBairrosPorMunicipio();
			consultarLogradourosPorBairro();
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método prepara a entidade para exibir seus detalhes.
	 */
	public void detalhesEndereco() {
		editarEndereco();
	}

	/**
	 * Método responsável por adicionar o Telefone preenchido no formulário.
	 */
	public void addContato() {
		try {
			if (!getEntidade().isTransient() || !getEntidade().getPessoaFisica().isTransient()) {
				if (getEntidade().getPessoaFisica().getPessoa().isTransient())
					getEntidade().getPessoaFisica().setPessoa(servico.consultarPessoaPorId(getEntidade().getPessoaFisica().getId()));

				contatoPessoa.setPessoa(getEntidade().getPessoaFisica().getPessoa());
				contatoPessoa = servico.salvarContatoPessoa(contatoPessoa);

				if (!getContatos().contains(contatoPessoa))
					getContatos().add(contatoPessoa);

			} else if (contatoPessoa.isTransient()) {
				contatoPessoa.setId(getContatos().size() + 1L);
				getContatos().add(contatoPessoa);
			}

			limparDadosContato();
			addMensagemInfo(Mensagem.get(Mensagem.MSG_CONTATO_INCLUSAO));
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método remove a entidade do banco de dados e da lista de endereços.
	 */
	public void excluirContato() {
		try {
			getContatos().remove(contatoPessoa);

			if (!getEntidade().isTransient() || !getEntidade().getPessoaFisica().isTransient())
				servico.excluirContatoPessoa(contatoPessoa);

			limparDadosContato();
			addMensagemInfo(Mensagem.get(Mensagem.MSG_CONTATO_EXCLUSAO));
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
		}
	}

	/**
	 * Este método prepara a entidade e a tela para efetuar alterações.
	 */
	public void editarContato() {
		try {
			// contatoPessoa = servico.consultarContatoPessoaPorId(getContatoPessoa().getId());
		} catch (Exception e) {
			addMensagemErro(e.getMessage());
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

	public List<SexoEnum> getListaSexoEnum() {
		return EnumeradorUtil.getDominiosAtivos(SexoEnum.class);
	}

	public List<EnumZonaEndereco> getListaZonaEnderecoEnum() {
		return EnumeradorUtil.getDominiosAtivos(EnumZonaEndereco.class);
	}

	@Override
	public Servico<Individuo> getServico() {
		if (servico == null) {
			servico = IndividuoServico.getInstancia();
		}
		return servico;
	}

	@Override
	public Individuo getEntidade() {
		if (entidade == null) {
			entidade = new Individuo();
		}
		return entidade;
	}

	@Override
	public void setEntidade(Individuo entidade) {
		this.entidade = entidade;
	}

	public static IndividuoCtrl getInstancia() {
		return (IndividuoCtrl) SpringContainer.getInstancia().getBean("individuoCtrl");
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

	public ContatoPessoa getContatoPessoa() {
		if (contatoPessoa == null) {
			contatoPessoa = new ContatoPessoa();
		}
		return contatoPessoa;
	}

	public void setContatoPessoa(ContatoPessoa contatoPessoa) {
		this.contatoPessoa = contatoPessoa;
	}

	public List<EnderecoPessoa> getEnderecos() {
		if (enderecos == null) {
			enderecos = new ArrayList<EnderecoPessoa>();
		}
		return enderecos;
	}

	public void setEnderecos(List<EnderecoPessoa> enderecos) {
		this.enderecos = enderecos;
	}

	public List<ContatoPessoa> getContatos() {
		if (contatos == null) {
			contatos = new ArrayList<ContatoPessoa>();
		}
		return contatos;
	}

	public void setContatos(List<ContatoPessoa> contatos) {
		this.contatos = contatos;
	}

	public List<UnidadeFederativa> getListaUnidadesFederativas() {
		if (listaUnidadesFederativas == null || listaUnidadesFederativas.isEmpty()) {
			try {
				listaUnidadesFederativas = new ArrayList<UnidadeFederativa>(servico.consultarTodosUnidadeFederativa());
			} catch (Exception e) {
				addMensagemErro(e.getMessage());
			}
		}
		return listaUnidadesFederativas;
	}

	public void setListaUnidadesFederativas(List<UnidadeFederativa> listaUnidadesFederativas) {
		this.listaUnidadesFederativas = listaUnidadesFederativas;
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

	public List<Municipio> getListaMunicipios() {
		if (listaMunicipios == null) {
			listaMunicipios = new ArrayList<Municipio>();
		}
		return listaMunicipios;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
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

	public EnumTipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimentoInicio() {
		return dataNascimentoInicio;
	}

	public void setDataNascimentoInicio(Date dataNascimentoInicio) {
		this.dataNascimentoInicio = dataNascimentoInicio;
	}

	public Date getDataNascimentoFim() {
		return dataNascimentoFim;
	}

	public void setDataNascimentoFim(Date dataNascimentoFim) {
		this.dataNascimentoFim = dataNascimentoFim;
	}

	public String getNumeroCartaoSus() {
		return numeroCartaoSus;
	}

	public void setNumeroCartaoSus(String numeroCartaoSus) {
		this.numeroCartaoSus = numeroCartaoSus;
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
}
