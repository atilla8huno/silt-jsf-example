package br.gov.go.saude.silt.caso_notificacao.teste;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.gov.go.saude.silt.caso_notificacao.entidade.CasoNotificacao;
import br.gov.go.saude.silt.caso_notificacao.servico.CasoNotificacaoServico;
import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.util.enumerador.QuantidadeDoseEnum;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;
import br.gov.go.saude.silt.util.enumerador.TipoDrogaEnum;
import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author Átilla Barros
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category Teste
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CasoNotificacaoTest {

	@Autowired
	private CasoNotificacaoServico servico;

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 * 
	 * @throws ServicoException
	 */
	@Test(expected = ServicoException.class)
	public void deveVerificarOObjetoEDispararExceptionDeValidacao() throws ServicoException {
		CasoNotificacao entidade = new CasoNotificacao();

		entidade.setIndividuo(null);
		entidade.setEstabelecimento(null);
		entidade.setSituacao(null);
		entidade.setTipoTratamento(null);
		entidade.setDataCadastro(null);
		entidade.setDataNotificacao(null);
		entidade.setDataInicioTratamento(null);
		entidade.setStatus(null);
		entidade.setQuantidadeDose(null);
		entidade.setTipoDroga(null);

		servico.verificarEntidade(entidade);
	}

	/**
	 * Verifica se o método verificarEntidade da camada de serviço está validando atributos null.
	 */
	@Test
	public void deveVerificarOObjetoERetornarTrue() {
		try {
			CasoNotificacao entidade = new CasoNotificacao();

			entidade.setId(1L);
			entidade.setIndividuo(new Individuo());
			entidade.setEstabelecimento(new Estabelecimento());
			entidade.setSituacao(new Situacao());
			entidade.setTipoTratamento(new TipoTratamento());
			entidade.setDataCadastro(new Date());
			entidade.setDataNotificacao(new Date());
			entidade.setDataInicioTratamento(new Date());
			entidade.setStatus(StatusEnum.ATIVO);
			entidade.setQuantidadeDoseEnum(QuantidadeDoseEnum.SEIS_MESES);
			entidade.setTipoDroga(TipoDrogaEnum.ISONIAZIDA);

			assertEquals(true, servico.verificarEntidade(entidade));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
