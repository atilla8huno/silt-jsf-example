package br.gov.go.saude.silt.solicitacao_transferencia.teste;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.solicitacao_transferencia.entidade.SolicitacaoTransferencia;
import br.gov.go.saude.silt.solicitacao_transferencia.servico.SolicitacaoTransferenciaServico;
import br.gov.go.saude.silt.usuario_estabelecimento.entidade.UsuarioEstabelecimento;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 186 $ $Author: atillabarros $ $Date: 2013-09-23 13:37:23 -0300 (Seg, 23 Set 2013) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SolicitacaoTransferenciaTest {

	@Autowired
	private SolicitacaoTransferenciaServico servico;

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException
	 */
	@Test(expected = ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		SolicitacaoTransferencia entidade = new SolicitacaoTransferencia();

			entidade.setId(null);
			entidade.setCasoNotificacao(null);
			entidade.setDataCadastro(null);
			entidade.setObservacao(null);
			entidade.setSolicitacaoAtendida(Boolean.TRUE);
			entidade.setUsuarioAutorizador(null);
			entidade.setUsuarioSolicitante(null);

		servico.verificarEntidade(entidade);
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			SolicitacaoTransferencia entidade = new SolicitacaoTransferencia();

			entidade.setId(1L);
			entidade.setCasoNotificacao(new CasoNotificacao());
			entidade.setDataCadastro(new Date());
			entidade.setObservacao("obs");
			entidade.setSolicitacaoAtendida(Boolean.TRUE);
			entidade.setUsuarioAutorizador(new UsuarioEstabelecimento());
			entidade.setUsuarioSolicitante(new UsuarioEstabelecimento());

			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
