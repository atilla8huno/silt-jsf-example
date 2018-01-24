package br.gov.go.saude.silt.corp.fonetico_pessoa.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.corp.fonetico_pessoa.entidade.FoneticoPessoa;
import br.gov.go.saude.silt.corp.fonetico_pessoa.servico.FoneticoPessoaServico;
import br.gov.go.saude.silt.corp.pessoa_fisica.servico.PessoaFisicaServico;
import br.gov.go.saude.silt.util.exception.DAOException;
import br.gov.go.saude.silt.util.exception.ServicoException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FoneticoPessoaTest {

	@Autowired
	private FoneticoPessoaServico servico;

	@Autowired
	private PessoaFisicaServico pessoaFisicaServico;

	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() throws DAOException, ServicoException {

		FoneticoPessoa entidade = new FoneticoPessoa();

		entidade.setCodigoFonetico("AAAATESTE");
		entidade.setTipo("N");
		entidade.setPessoa(pessoaFisicaServico.consultarPorId(1974741l).getPessoa());
		entidade = servico.salvarOuAtualizar(entidade);

		// Testa o insert
		assertNotNull(entidade.getId());

		entidade.setTipo("M");

		entidade = servico.salvarOuAtualizar(entidade);

		// Testa o update
		assertEquals("M", entidade.getTipo());
	}
}
