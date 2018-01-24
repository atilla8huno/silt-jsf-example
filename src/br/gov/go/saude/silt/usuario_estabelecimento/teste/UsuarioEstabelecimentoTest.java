package br.gov.go.saude.silt.usuario_estabelecimento.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.corp.pessoa_fisica.entidade.PessoaFisica;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.portal.usuario_sistema.entidade.UsuarioSistema;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 178 $ $Author: claudiocosta $ $Date: 2013-09-12 11:02:30 -0300 (Qui, 12 Set 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UsuarioEstabelecimentoTest {

	@Autowired
	private UsuarioEstabelecimentoServico servico;
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException 
	 */
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		UsuarioEstabelecimento entidade = new UsuarioEstabelecimento();
		
		entidade.setId(null);
		entidade.setEstabelecimento(null);
		entidade.setPessoaFisica(null);
		entidade.setUsuarioSistema(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			UsuarioEstabelecimento entidade = new UsuarioEstabelecimento();
			
			entidade.setId(1L);
			entidade.setEstabelecimento(new Estabelecimento());
			entidade.setPessoaFisica(new PessoaFisica());
			entidade.setUsuarioSistema(new UsuarioSistema());
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
