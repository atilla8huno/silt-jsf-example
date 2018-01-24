package br.gov.go.saude.silt.individuo.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.individuo.servico.IndividuoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 244 $ $Author: atillabarros $ $Date: 2013-11-20 18:08:08 -0200 (Qua, 20 Nov 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IndividuoTest {

	@Autowired
	private IndividuoServico servico;

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException
	 */
	@Test(expected = ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		Individuo entidade = new Individuo();

		entidade.setId(null);
		entidade.setEstabelecimento(null);
		entidade.setNumeroCartaoSus(null);
		entidade.setPessoaFisica(null);

		servico.verificarEntidade(entidade);
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			Individuo entidade = new Individuo();

			entidade.setId(1L);
			entidade.setEstabelecimento(new Estabelecimento());
			entidade.setNumeroCartaoSus("123");
			entidade.setPessoaFisica(new PessoaFisica());

			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
