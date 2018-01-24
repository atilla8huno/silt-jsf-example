package br.gov.go.saude.silt.corp.pessoa_fisica.servico;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.gov.go.saude.arquitetura.util.DataUtil;
import br.gov.go.saude.arquitetura.util.NumeroUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoLog;
import br.gov.go.saude.silt.corp.contato_pessoa.entidade.ContatoPessoa;
import br.gov.go.saude.silt.corp.contato_pessoa.servico.ContatoPessoaServico;
import br.gov.go.saude.silt.corp.endereco.servico.EnderecoServico;
import br.gov.go.saude.silt.corp.endereco_pessoa.entidade.EnderecoPessoa;
import br.gov.go.saude.silt.corp.endereco_pessoa.servico.EnderecoPessoaServico;
import br.gov.go.saude.silt.corp.fonetico_pessoa.servico.FoneticoPessoaServico;
import br.gov.go.saude.silt.corp.log_pessoa.servico.LogPessoaServico;
import br.gov.go.saude.silt.corp.pessoa.servico.PessoaServico;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.SpringContainer;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;
import br.gov.go.saude.silt.util.template.infra_estrutura.DAO;
import br.gov.go.saude.silt.util.template.infra_estrutura.Servico;

/**
 * Classe de serviço PessoaFisica.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 291 $ $Author: atillabarros $ $Date: 2014-01-21 17:15:45 -0200 (Ter, 21 Jan 2014) $
 * @category Servico
 */
@Service
public class PessoaFisicaServico extends Servico<PessoaFisica> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PessoaFisicaDAO dao;
	@Autowired
	private PessoaServico pessoaServico;
	@Autowired
	private LogPessoaServico logPessoaServico;
	@Autowired
	private ContatoPessoaServico contatoPessoaServico;
	@Autowired
	private EnderecoPessoaServico enderecoPessoaServico;
	@Autowired
	private EnderecoServico enderecoServico;

	@Autowired
	private FoneticoPessoaServico foneticoPessoaServico;

	@Override
	protected DAO<PessoaFisica> getDAO() {
		return dao;
	}

	@Override
	public boolean verificarEntidade(PessoaFisica entidade) throws ServicoException {

		Date dataAtual = new Date();

		super.verificarEntidade(entidade);

		if (entidade.getCpf() != null) {
			if (!NumeroUtil.validarCPF(entidade.getCpf().toString())) {
				throw new ServicoException(Mensagem.MSG_CPF_INVALIDO);
			}
		}

		if (entidade.isTransient()) {
			try {
				if (!this.consultarPorCPFOuNomeNomeMaeNascimentoSemFonetica(entidade).isEmpty()) {
					throw new ServicoException(Mensagem.MSG_CADASTRO_EXISTENTE);
				}
			} catch (Exception erro) {
				throw new ServicoException(Mensagem.MSG_ERRO_PERSISTENCIA);
			}
		}

		Integer diferencaDias = DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), dataAtual);

		if (diferencaDias < 0) {
			throw new ServicoException(Mensagem.MSG_DATA_NASCIMENTO_INVALIDA);
		}

		if (diferencaDias >= DataUtil.NUMERO_DIAS_18_ANOS && entidade.getCpf() == null) {
			throw new ServicoException(Mensagem.MSG_CPF_OBRIGATORIO);
		}
		// Validacoes da Data de Expedicao da RG
		if (entidade.getDataExpedicaoRg() != null) {
			if (DataUtil.calcularDiferencaDias(entidade.getDataExpedicaoRg(), dataAtual) < 0) {
				throw new ServicoException("negocio.geral.dataXMenorQueDataY", "Emissão do RG", "atual");
			}

			if (DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataExpedicaoRg()) < 0) {
				throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Expedição da RG", "de Nascimento");
			}
		}
		// Validacoes da Data de Emissao da Certidado de Nascimento
		if (entidade.getDataEmissaoCertidaoNascimento() != null) {
			if (DataUtil.calcularDiferencaDias(entidade.getDataEmissaoCertidaoNascimento(), dataAtual) < 0) {
				throw new ServicoException("negocio.geral.dataXMenorQueDataY", "Emissão da Certidão de Nascimento", "atual");
			}
			if (DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataEmissaoCertidaoNascimento()) < 0) {
				throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Certidão de Nascimento", "de Nascimento");
			}
		}
		// Validacoes da Data de Emissao da Certidao de Obito
		if (entidade.getDataEmissaoCertidaoObito() != null) {
			if (DataUtil.calcularDiferencaDias(entidade.getDataEmissaoCertidaoObito(), dataAtual) < 0) {
				throw new ServicoException("negocio.geral.dataXMenorQueDataY", "Emissão da Certidão de Óbito", "atual");
			}

			if (DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataEmissaoCertidaoObito()) < 0) {
				throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Emissão da Certidão de Óbito", "de Nascimento");
			}

			if (DataUtil.calcularDiferencaDias(entidade.getDataEmissaoCertidaoNascimento(), entidade.getDataEmissaoCertidaoObito()) < 0) {
				throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Emissão da Certidão de Óbito", "de Emissão da Certidão de Nascimento");
			}
		}
		// Validacoes da Data de Emissao do NIT
		if (entidade.getDataEmissaoNit() != null && DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataEmissaoNit()) < 0) {
			throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Emissão do NIT", "de Nascimento");
		}
		// Validacoes da Data de Emissao Reservista
		if (entidade.getDataEmissaoReservista() != null
				&& DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataEmissaoReservista()) < 0) {
			throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Emissão da Reservista", "de Nascimento");
		}
		// Validacoes da Data Emissao Titulo Eleitor
		if (entidade.getDataEmissaoTituloEleitor() != null
				&& DataUtil.calcularDiferencaDias(entidade.getDataNascimento(), entidade.getDataEmissaoTituloEleitor()) < 0) {
			throw new ServicoException("negocio.geral.dataXMaiorQueDataY", "Emissão do Titulo de Eleitor", "de Nascimento");
		}

		return true;
	}

	/**
	 * Consulta PessoaFisicas ativas com parametros sem fonetizacao.
	 * 
	 * @param pessoaFisica
	 * @return
	 * @throws DAOException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PessoaFisica> consultarPorCPFOuNomeNomeMaeNascimentoSemFonetica(PessoaFisica pessoaFisica) throws ServicoException {
		try {
			return dao.consultarPorCPFOuNomeNomeMaeNascimentoSemFonetica(pessoaFisica);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo retorna uma PessoaFisica populando o relacionamento fraco de pessoa.
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PessoaFisica consultarPorId(Long codigo) throws ServicoException {
		try {
			PessoaFisica pessoaFisica = super.consultarPorId(codigo);
			if (pessoaFisica != null) {
				pessoaFisica.setPessoa(pessoaServico.consultarPorId(codigo));
			}
			return pessoaFisica;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Metodo retorna uma PessoaFisica ativa por cpf.
	 * 
	 * @param Long cpf
	 * @return PessoaFisica
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PessoaFisica consultarPorCPF(Long cpf) throws ServicoException {
		try {
			return dao.consultarPorCPF(cpf);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Este método recebe uma entidade por parâmetro e a persiste no banco de dados.
	 * 
	 * @param PessoaFisica entidade
	 * @return PessoaFisica
	 * @throws ServicoException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public PessoaFisica salvarOuAtualizar(PessoaFisica entidade) throws ServicoException {
		if (entidade.isTransient()) {
			return incluir(entidade);
		} else {
			return alterar(entidade);
		}
	}

	/**
	 * Realiza a inclusao de uma pessoa fisica inserindo um log de auditoria.
	 * 
	 * @param entidade
	 * @param tipoLog
	 * @return PessoaFisica
	 * @throws ServicoException
	 * @throws DAOException
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PessoaFisica incluir(PessoaFisica entidade) throws ServicoException {
		try {
			verificarEntidade(entidade);
			Set<ContatoPessoa> contatos = new HashSet<ContatoPessoa>(entidade.getPessoa().getContatosPessoas());
			Set<EnderecoPessoa> enderecos = new HashSet<EnderecoPessoa>(enderecoPessoaServico.salvarEnderecos(entidade.getPessoa().getEnderecosPessoas()));
			entidade.setNome(entidade.getNome().toUpperCase());
			entidade = super.salvarOuAtualizar(entidade);

			entidade.setPessoa(pessoaServico.consultarPorId(entidade.getId()));

			if (entidade.getPessoa() != null) {
				contatoPessoaServico.incluirContatos(entidade.getPessoa(), contatos);
				enderecoPessoaServico.salvarEnderecos(entidade.getPessoa(), enderecos);
				// Solicita a criacao da fonetizacao conforme codigo legado
				foneticoPessoaServico.incluirFonetizacaoPessoaFisica(entidade);
			}

			// Registrar Log de insercao conforme codigo legado.
			logPessoaServico.incluir(entidade.getPessoa(), EnumTipoLog.INCLUINDO_PF);

			return entidade;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Realiza a alteracao de uma pessoa fisica inserido o log da alteracao
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PessoaFisica alterar(PessoaFisica entidade) throws ServicoException {
		try {
			verificarEntidade(entidade);

			// Registrar Log das alteracoes conforme codigo legado.
			logPessoaServico.incluir(entidade.getPessoa(), EnumTipoLog.ALTERANDO_PF);
			entidade.setNome(entidade.getNome().toUpperCase());
			entidade = super.alterar(entidade);

			return entidade;
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	/**
	 * Realiza a exclusao logica de uma pessoa fisica gerando um log de auditoria.
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void excluir(PessoaFisica entidade) throws ServicoException {
		try {
			super.excluir(entidade);
			logPessoaServico.incluir(entidade.getPessoa(), EnumTipoLog.EXCLUINDO_PF);
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}

	public static PessoaFisicaServico getInstancia() {
		return (PessoaFisicaServico) SpringContainer.getInstancia().getBean("pessoaFisicaServico");
	}
}
