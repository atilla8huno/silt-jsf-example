package br.gov.go.saude.silt.individuo.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.pessoa.servico.PessoaServico;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.pessoa_fisica.servico.PessoaFisicaServico;
import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.raca_cor.servico.RacaCorServico;
import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.corp.tipo_contato.servico.TipoContatoServico;
import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.corp.unidade_federativa.servico.UnidadeFederativaServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.enumerador.SexoEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Servico
 */
@Service("individuoServico")
public class IndividuoServico extends Servico<Individuo> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PessoaFisicaServico pessoaFisicaServico;
	@Autowired
	private RacaCorServico racaCorServico;
	@Autowired
	private EscolaridadeServico escolaridadeServico;
	@Autowired
	private TipoContatoServico tipoContatoServico;
	@Autowired
	private LogradouroServico logradouroServico;
	@Autowired
	private MunicipioServico municipioServico;
	@Autowired
	private BairroServico bairroServico;
	@Autowired
	private EnderecoPessoaServico enderecoPessoaServico;
	@Autowired
	private ContatoPessoaServico contatoPessoaServico;
	@Autowired
	private UnidadeFederativaServico unidadeFederativaServico;
	@Autowired
	private PessoaServico pessoaServico;
	@Autowired
	private EstabelecimentoServico estabelecimentoServico;
	
	@Autowired
	private IndividuoDAO dao;

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param  Individuo entidade
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public Individuo salvarOuAtualizar(Individuo entidade) throws ServicoException {
		try {
			if (entidade.getPessoaFisica().isTransient()) {
				if (entidade.getPessoaFisica().getSiglaSexo() != null && entidade.getPessoaFisica().getSiglaSexo().length() > 1) {
					String sexo = null;
					for (SexoEnum sex : SexoEnum.values()) {
						if (sex.getDescricao() != null && sex.getDescricao().equals(entidade.getPessoaFisica().getSiglaSexo())) {
							sexo = sex.getSigla();
							break;
						}
					}
					entidade.getPessoaFisica().setSiglaSexo(sexo);
				}
				
				entidade.setPessoaFisica(pessoaFisicaServico.salvarOuAtualizar(entidade.getPessoaFisica()));
			}
			return super.salvarOuAtualizar(entidade);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe uma PessoaFisica como argumento e pesquisa na tabela Individuo
	 * 
	 * @param  PessoaFisica pessoa
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Individuo consultarPorPessoaFisica(PessoaFisica pessoa) throws ServicoException {
		try {
			return dao.consultarPorPessoaFisica(pessoa);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
	 * Este método recebe um CPF como argumento e pesquisa na tabela Individuo
	 * 
	 * @param  Long cpf
	 * @return Individuo
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Individuo consultarPorCPF(Long cpf) throws ServicoException {
		try {
			return dao.consultarPorCPF(cpf);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
     * Este método pesquisa os logradouros de acordo com CEP recebido.
     * 
     * @param	Long cep
     * @return	List<Logradouro>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorCEP(Long cep) throws ServicoException {
		return logradouroServico.consultarLogradourosPorCEP(cep);
	}
	
	/**
	 * Este método retorna uma PessoaFisica ativa por CPF.
	 * 
	 * @param  Long cpf
	 * @return PessoaFisica
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PessoaFisica consultarPessoaFisicaPorCPF(Long cpf) throws ServicoException {
		return pessoaFisicaServico.consultarPorCPF(cpf);
	}
	
	/**
	 * Este método recebe um CPF como argumento e pesquisa na tabela Individuo
	 * 
	 * @param  String nome
	 * @return List<Individuo>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Individuo> consultarPorNome(String nome) throws ServicoException {
		try {
			return dao.consultarPorNome(nome);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
	
	/**
     * Este método pesquisa os municipios de acordo com Estado recebido.
     * 
     * @param	Long cep
     * @return	List<Municipio>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Municipio> consultarMunicipiosPorUnidadeFederativa(UnidadeFederativa estado) throws ServicoException {
		return municipioServico.consultarPorUnidadeFederativa(estado);
	}

	/**
     * Este método pesquisa os bairros de acordo com Municipio recebido.
     * 
     * @param	Long cep
     * @return	List<Bairro>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Bairro> consultarBairrosPorMunicipio(Municipio municipio) throws ServicoException {
		return bairroServico.consultarBairrosPorMunicipio(municipio);
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
     * Este método pesquisa os logradouros de acordo com Bairro recebido.
     * 
     * @param	Bairro bairro
     * @return	List<Logradouro>
     * @throws 	ServicoException
     */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<Logradouro> consultarLogradourosPorBairro(Bairro bairro) throws ServicoException {
		return logradouroServico.consultarLogradourosPorBairro(bairro);
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
	 * @return List<TipoContato>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<TipoContato> consultarTodosTipoContato() throws ServicoException {
		return tipoContatoServico.consultarTodos();
	}
	

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param  EnderecoPessoa enderecoPessoa
	 * @return EnderecoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public EnderecoPessoa salvarEnderecoPessoa(EnderecoPessoa enderecoPessoa) throws ServicoException {
		return enderecoPessoaServico.salvarOuAtualizar(enderecoPessoa);
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a exclui no banco de dados.
	 * 
	 * @param  EnderecoPessoa enderecoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluirEnderecoPessoa(EnderecoPessoa enderecoPessoa) throws ServicoException {
		enderecoPessoaServico.excluir(enderecoPessoa);
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param  ContatoPessoa contatoPessoa
	 * @return ContatoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ContatoPessoa salvarContatoPessoa(ContatoPessoa contatoPessoa) throws ServicoException {
		return contatoPessoaServico.salvarOuAtualizar(contatoPessoa);
	}
	
	/**
	 * Este método recebe uma entidade por parâmetro e a exclui no banco de dados.
	 * 
	 * @param  ContatoPessoa contatoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluirContatoPessoa(ContatoPessoa contatoPessoa) throws ServicoException {
		contatoPessoaServico.excluir(contatoPessoa);
	}

	/**
	 * Este método recebe uma PessoaFisica por parâmetro e retorna uma lista de endereços referentes.
	 * 
	 * @param  PessoaFisica pessoaFisica
	 * @return List<EnderecoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<EnderecoPessoa> consultarEnderecosPessoaFisica(PessoaFisica pessoaFisica) throws ServicoException {
		return enderecoPessoaServico.consultarPorPessoaFisica(pessoaFisica);
	}

	/**
	 * Este método recebe uma PessoaFisica por parâmetro e retorna uma lista de contatos referentes.
	 * 
	 * @param  PessoaFisica pessoaFisica
	 * @return List<ContatoPessoa>
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ContatoPessoa> consultarContatosPessoaFisica(PessoaFisica pessoaFisica) throws ServicoException {
		return contatoPessoaServico.consultarPorPessoaFisica(pessoaFisica);
	}
	
	@Override
	protected DAO<Individuo> getDAO() {
		return dao;
	}
	
	public static IndividuoServico getInstancia() {
		return (IndividuoServico) SpringContainer.getInstancia().getBean("individuoServico");
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
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param  Long id
	 * @return EnderecoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public EnderecoPessoa consultarEnderecoPessoaPorId(Long id) throws ServicoException {
		return enderecoPessoaServico.consultarPorId(id);
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param  Long id
	 * @return ContatoPessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public ContatoPessoa consultarContatoPessoaPorId(Long id) throws ServicoException {
		return contatoPessoaServico.consultarPorId(id);
	}

	/**
	 * Este método recebe um código por parâmetro e pesquisa no banco de dados.
	 * 
	 * @param  Long id
	 * @return Pessoa
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Pessoa consultarPessoaPorId(Long id) throws ServicoException {
		return pessoaServico.consultarPorId(id);
	}
}
