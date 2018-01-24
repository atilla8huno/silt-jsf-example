package br.gov.go.saude.silt.dose.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.dose.entidade.Dose;
import br.gov.go.saude.silt.dose.servico.DoseServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DoseTest {

	@Autowired
	private DoseServico servico;
	
	/**
	 * Verifica os casos de teste: salvar, atualizar e excluir a entidade
	 */
	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() {
		try {
			//primeiro teste - persistir
			Dose entidade = new Dose();
			entidade.setObservacao("Observação");
			entidade.setQuantidade(30);
			entidade.setCasoNotificacao(new CasoNotificacao());
			entidade.setEstabelecimento(new Estabelecimento());
			
			entidade = servico.salvarOuAtualizar(entidade);
			
			assertNotNull(entidade.getId());
			
			//segundo teste - atualizar
			entidade.setObservacao("Fucking descrição");
			
			servico.salvarOuAtualizar(entidade);
			
			assertEquals("Fucking descrição", entidade.getObservacao());
			
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
		Dose entidade = new Dose();
		
		entidade.setId(null);
		entidade.setObservacao(null);
		entidade.setQuantidade(null);
		entidade.setCasoNotificacao(null);
		entidade.setEstabelecimento(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			Dose entidade = new Dose();
			
			entidade.setId(1L);
			entidade.setObservacao("descrição");
			entidade.setQuantidade(30);
			entidade.setCasoNotificacao(new CasoNotificacao());
			entidade.setEstabelecimento(new Estabelecimento());
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
