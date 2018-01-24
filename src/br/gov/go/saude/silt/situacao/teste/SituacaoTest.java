package br.gov.go.saude.silt.situacao.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.situacao.servico.SituacaoServico;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SituacaoTest {

	@Autowired
	private SituacaoServico servico;
	
	/**
	 * Verifica os casos de teste: salvar, atualizar e excluir a entidade
	 */
	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() {
		try {
			//primeiro teste - persistir
			Situacao entidade = new Situacao();
			entidade.setStatus(StatusEnum.ATIVO);
			entidade.setDescricao("Descrição fucking");
			
			entidade = servico.salvarOuAtualizar(entidade);
			
			assertNotNull(entidade.getId());
			
			//segundo teste - atualizar
			entidade.setDescricao("Fucking descrição");
			
			servico.salvarOuAtualizar(entidade);
			
			assertEquals("Fucking descrição", entidade.getDescricao());
			
			//terceiro teste - excluir
			servico.excluir(entidade);
			
			entidade = servico.consultarPorId(entidade.getId());
			
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
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		Situacao entidade = new Situacao();
		
		entidade.setId(null);
		entidade.setDescricao(null);
		entidade.setStatus(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			Situacao entidade = new Situacao();
			
			entidade.setId(1L);
			entidade.setDescricao("descrição");
			entidade.setStatus(StatusEnum.ATIVO);
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
