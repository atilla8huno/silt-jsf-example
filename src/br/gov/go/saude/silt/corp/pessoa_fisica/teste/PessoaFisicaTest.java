package br.gov.go.saude.silt.corp.pessoa_fisica.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.corp.pessoa.entidade.Pessoa;
import br.gov.go.saude.silt.corp.pessoa.servico.PessoaServico;
import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.corp.pessoa_fisica.servico.PessoaFisicaServico;
import br.gov.go.saude.silt.util.Mensagem;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * Classe de Teste para Pessoa Fisica.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category categoria
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PessoaFisicaTest {

	@Autowired
	private PessoaFisicaServico pessoaFisicaServico;

	@Autowired
	private PessoaServico pessoaServico;

	private Calendar data = Calendar.getInstance();

	/**
	 * Mota objeto para ser utilizado nos métodos de teste.
	 * 
	 * @return
	 */
	private PessoaFisica montarObjeto() {

		PessoaFisica entidade = new PessoaFisica();

		data.set(1985, 01, 8);

		entidade.setDataCadastro(new Date());
		entidade.setDataNascimento(data.getTime());
		entidade.setNome("Teste Claudio Espindola");
		entidade.setNomeMae("Teste Nome Mae");
		entidade.setIndicadorDoadorOrgao("S");
		entidade.setCpf(71181316600L);

		return entidade;
	}

	/**
	 * Realiza o teste para salvar uma pessoa física.
	 * 
	 * @throws ServicoException
	 */
	@Test
	public void deveSalvarObjeto() throws ServicoException {

		PessoaFisica entidade = montarObjeto();

		entidade = pessoaFisicaServico.incluir(entidade);

		assertNotNull(entidade.getId());

	}

	/**
	 * Realiza se o teste de validacao do CPF da pessoa fisica esta funcionando.
	 */
	@Test
	public void deveVerificarCpfInvalidoEDispararExceptionDeValidacao() {
		try {
			PessoaFisica entidade = montarObjeto();
			entidade.setCpf(69689556312l);
			pessoaFisicaServico.incluir(entidade);
		} catch (Exception erro) {
			assertEquals(Mensagem.get(Mensagem.MSG_CPF_INVALIDO), erro.getMessage());
		}
	}

	/**
	 * Realiza o teste de validacao de cadastro existente.
	 */
	@Test
	public void deveVerificarCadastroExistenteEDispararExceptionDeValidacao() {
		try {
			PessoaFisica entidade = montarObjeto();
			pessoaFisicaServico.incluir(entidade);
		} catch (Exception erro) {
			assertEquals(Mensagem.get(Mensagem.MSG_CADASTRO_EXISTENTE), erro.getMessage());
		}
	}

	/**
	 * Realiza o teste de validacao da data de nascimento.
	 */
	@Test
	public void deveVerificarDataNascimentoInvalidaEDispararExceptionDeValidacao() {
		try {
			PessoaFisica entidade = montarObjeto();
			data.set(2015, 01, 8);
			entidade.setDataNascimento(data.getTime());
			pessoaFisicaServico.incluir(entidade);
		} catch (Exception erro) {
			assertEquals(Mensagem.get(Mensagem.MSG_DATA_NASCIMENTO_INVALIDA), erro.getMessage());
		}
	}

	/**
	 * Realiza o teste de validacao da obrigacao de informar o cpf para maiores de 18 anos.
	 */
	@Test
	public void deveVerificarCpfObrigatorioEDispararExceptionDeValidacao() {
		try {
			PessoaFisica entidade = montarObjeto();
			data.set(1990, 01, 8);
			entidade.setCpf(null);
			pessoaFisicaServico.incluir(entidade);
		} catch (Exception erro) {
			assertEquals(Mensagem.get(Mensagem.MSG_CPF_OBRIGATORIO), erro.getMessage());
		}
	}

	/**
	 * Realiza o teste de exclusao fisica do dado inserido no inicio do teste.
	 * 
	 * @throws ServicoException
	 */
	@Test
	public void deveExcluirPessoaFisica() throws ServicoException {
		PessoaFisica entidade = pessoaFisicaServico.consultarPorCPF(71181316600L);

		if (entidade != null) {
			Pessoa pessoa = pessoaServico.consultarPorId(entidade.getId());
			pessoaFisicaServico.excluir(entidade);
			pessoaServico.excluir(pessoa);
		}

		entidade = pessoaFisicaServico.consultarPorCPF(71181316600L);

		assertNull(entidade);
	}
}
