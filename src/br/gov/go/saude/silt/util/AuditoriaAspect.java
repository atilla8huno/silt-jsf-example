package br.gov.go.saude.silt.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import br.gov.go.saude.silt.util.exception.ServicoException;

/**
 * @author �tilla Barros
 * @version $Rev: 186 $ $Author: atillabarros $ $Date: 2013-09-23 13:37:23 -0300 (Seg, 23 Set 2013) $
 * @category Aspect
 */
@Aspect
public class AuditoriaAspect {

	/**
	 * Este m�todo verifica as permiss�es do usu�rio de acordo com permiss�o recebida por argumento.
	 * 
	 * @param	JoinPoint point
	 * @throws 	ServicoException
	 */
	@After(value="execution(br.gov.go.saude.silt.util.template.infra_estrutura.Servico * *(..))")
	public void registrarLogDaTransacao(JoinPoint point) throws ServicoException {
		try {
			
		} catch (Exception e) {
			throw new ServicoException(e);
		}
	}
}
