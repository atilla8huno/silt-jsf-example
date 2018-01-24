package br.gov.go.saude.silt.estabelecimento.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 186 $ $Author: atillabarros $ $Date: 2013-09-23 13:37:23 -0300 (Seg, 23 Set 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EstabelecimentoTest {

	@Autowired
	private EstabelecimentoServico servico;

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException 
	 */
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		Estabelecimento entidade = new Estabelecimento();
		
		entidade.setEstabelecimentoSaude(null);
		entidade.setNucleo(null);
		
		servico.verificarEntidade(entidade);
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			Estabelecimento entidade = new Estabelecimento();

			entidade.setEstabelecimentoSaude(new EstabelecimentoSaude());
			entidade.setNucleo(Boolean.TRUE);

			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
