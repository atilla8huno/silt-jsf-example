package br.gov.go.saude.silt.historico_transferencia.teste;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.historico_transferencia.entidade.HistoricoTransferencia;
import br.gov.go.saude.silt.historico_transferencia.servico.HistoricoTransferenciaServico;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author �tilla Barros
 * @version $Rev: 148 $ $Author: atillabarros $ $Date: 2013-08-28 10:33:42 -0300 (Qua, 28 Ago 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HistoricoTransferenciaTest {

	@Autowired
	private HistoricoTransferenciaServico servico;
	
	/**
	 * Verifica se o m�todo verificarEntidade da camada de servi�o est� validando atributos null.
	 * 
	 * @throws ServicoException 
	 */
	@Test(expected=ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		HistoricoTransferencia entidade = new HistoricoTransferencia();
		
		entidade.setId(null);
		entidade.setCasoNotificacao(null);
		entidade.setDataCadastro(null);
		entidade.setEstabelecimentoDestino(null);
		entidade.setEstabelecimentoOrigem(null);
		entidade.setUsuarioEstabelecimento(null);
		
		servico.verificarEntidade(entidade);
	}
	
	/**
	 * Verifica se o m�todo verificarEntidade da camada de servi�o est� validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			HistoricoTransferencia entidade = new HistoricoTransferencia();
			
			entidade.setId(1L);
			entidade.setCasoNotificacao(new CasoNotificacao());
			entidade.setDataCadastro(new Date());
			entidade.setEstabelecimentoDestino(new Estabelecimento());
			entidade.setEstabelecimentoOrigem(new Estabelecimento());
			entidade.setUsuarioEstabelecimento(new UsuarioEstabelecimento());
			
			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
