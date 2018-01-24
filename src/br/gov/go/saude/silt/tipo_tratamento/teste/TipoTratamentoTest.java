package br.gov.go.saude.silt.tipo_tratamento.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.tipo_tratamento.servico.TipoTratamentoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TipoTratamentoTest {

	@Autowired
	private TipoTratamentoServico tipoTratamentoServico;

	/**
	 * Verifica os casos de teste: salvar, atualizar e excluir a entidade
	 */
	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() {
		try {
			// primeiro teste - persistir
			TipoTratamento entidade = new TipoTratamento();
			entidade.setCodigo("ABC");
			entidade.setDescricao("Descrição fucking");

			entidade = tipoTratamentoServico.salvarOuAtualizar(entidade);

			assertNotNull(entidade.getId());

			// segundo teste - atualizar
			entidade.setDescricao("Fucking descrição");

			tipoTratamentoServico.salvarOuAtualizar(entidade);

			assertEquals("Fucking descrição", entidade.getDescricao());

			// terceiro teste - excluir
			tipoTratamentoServico.excluir(entidade);

			entidade = tipoTratamentoServico.consultarPorId(entidade.getId());

			assertEquals(null, entidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException
	 */
	@Test(expected = ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		TipoTratamento entidade = new TipoTratamento();

		entidade.setId(null);
		entidade.setDescricao(null);
		entidade.setCodigo(null);

		tipoTratamentoServico.verificarEntidade(entidade);
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			TipoTratamento entidade = new TipoTratamento();

			entidade.setId(1L);
			entidade.setDescricao("descrição");
			entidade.setCodigo("código");

			assertEquals(true, tipoTratamentoServico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
