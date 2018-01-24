package br.gov.go.saude.silt.historico_caso_notificacao.teste;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.historico_caso_notificacao.entidade.HistoricoCasoNotificacao;
import br.gov.go.saude.silt.historico_caso_notificacao.servico.HistoricoCasoNotificacaoServico;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 148 $ $Author: atillabarros $ $Date: 2013-08-28 10:33:42 -0300 (Qua, 28 Ago 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HistoricoCasoNotificacaoTest {

	@Autowired
	private HistoricoCasoNotificacaoServico servico;

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException 
	 */
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		HistoricoCasoNotificacao entidade = new HistoricoCasoNotificacao();
		
		entidade.setCasoNotificacao(null);
		entidade.setDataCadastro(null);
		entidade.setObservacao(null);
		entidade.setSituacao(null);
		entidade.setUsuarioEstabelecimento(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			HistoricoCasoNotificacao entidade = new HistoricoCasoNotificacao();
			
			entidade.setCasoNotificacao(new CasoNotificacao());
			entidade.setDataCadastro(new Date());
			entidade.setObservacao("obs");
			entidade.setSituacao(new Situacao());
			entidade.setUsuarioEstabelecimento(new UsuarioEstabelecimento());
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
