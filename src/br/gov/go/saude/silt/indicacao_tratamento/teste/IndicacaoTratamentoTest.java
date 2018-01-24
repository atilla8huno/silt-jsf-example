package br.gov.go.saude.silt.indicacao_tratamento.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.indicacao_tratamento.servico.IndicacaoTratamentoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author �tilla Barros
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IndicacaoTratamentoTest {

	@Autowired
	private IndicacaoTratamentoServico servico;
	
	/**
	 * Verifica os casos de teste: salvar, atualizar e excluir a entidade
	 */
	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() {
		try {
			//primeiro teste - persistir
			IndicacaoTratamento entidade = new IndicacaoTratamento();
			entidade.setDescricao("Descri��o fucking");
			
			entidade = servico.salvarOuAtualizar(entidade);
			
			assertNotNull(entidade.getId());
			
			//segundo teste - atualizar
			entidade.setDescricao("Fucking descri��o");
			
			servico.salvarOuAtualizar(entidade);
			
			assertEquals("Fucking descri��o", entidade.getDescricao());
			
			//terceiro teste - excluir
			servico.excluir(entidade);
			
			entidade = servico.consultarPorId(entidade.getId());
			
			assertEquals(null, entidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Verifica se o m�todo verificarEntidade da camada de servi�o est� validando atributos null.
	 * 
	 * @throws ServicoException 
	 */
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		IndicacaoTratamento entidade = new IndicacaoTratamento();
		
		entidade.setId(null);
		entidade.setDescricao(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o m�todo verificarEntidade da camada de servi�o est� validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			IndicacaoTratamento entidade = new IndicacaoTratamento();
			
			entidade.setId(1L);
			entidade.setDescricao("descri��o");
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
