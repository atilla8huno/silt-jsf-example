package br.gov.go.saude.silt.auditoria_erro.teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.auditoria_erro.entidade.AuditoriaErro;
import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.usuario_estabelecimento.servico.UsuarioEstabelecimentoServico;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 266 $ $Author: claudiocosta $ $Date: 2013-12-02 11:33:51 -0200 (Seg, 02 Dez 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AuditoriaErroTest {

	@Autowired
	private AuditoriaErroServico servico;
	
	@Test
	public void deveSalvarEAtualizarEExcluirOObjeto() {
		try {
			//primeiro teste - persistir
			AuditoriaErro entidade = new AuditoriaErro();
			entidade.setDataCadastro(new Date());
			entidade.setExcecao("NullPointerException");
			entidade.setCodigo(null); //String.valueOf(new Date().getTime()));
			entidade.setMensagem("Objeto nulo");
			entidade.setPagina("auditoriaErroListagem.jsf");
			entidade.setUsuarioEstabelecimento(UsuarioEstabelecimentoServico.getInstancia().consultarPorId(5L));
			
			entidade = servico.salvarOuAtualizar(entidade);
			assertNotNull(entidade.getId());
			
			//segundo teste - atualizar
			entidade.setMensagem("Fucking descrição");
			
			entidade = servico.salvarOuAtualizar(entidade);
			
			assertEquals("Fucking descrição", entidade.getMensagem());
			
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
		AuditoriaErro entidade= new AuditoriaErro();
		
		entidade.setDataCadastro(null);
		entidade.setExcecao(null);
		entidade.setMensagem(null);
		entidade.setPagina(null);
		entidade.setUsuarioEstabelecimento(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			AuditoriaErro entidade= new AuditoriaErro();
			
			entidade.setId(1L);
			entidade.setDataCadastro(Calendar.getInstance().getTime());
			entidade.setExcecao("NullPointerException");
			entidade.setMensagem("Objeto nulo");
			entidade.setPagina("auditoriaErroListagem.jsf");
			entidade.setUsuarioEstabelecimento(new UsuarioEstabelecimento());
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
