package br.gov.go.saude.silt.util.exception;

import java.util.Date;

import org.apache.log4j.Logger;

import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.util.Mensagem;

/**
 * @author Átilla Barros
 * @version $Rev: 102 $ $Author: atillabarros $ $Date: 2013-08-05 11:10:16 -0300 (Seg, 05 Ago 2013) $
 * @category Exception
 */
@SuppressWarnings("serial")
public class RelatorioException extends Exception {

	private Logger log = Logger.getRootLogger();
	
	private static String codigo = String.valueOf(new Date().getTime());

	public RelatorioException(Throwable e) throws Exception {
		super(Mensagem.get(Mensagem.MSG_ERRO_RELATORIO, codigo), e);
		AuditoriaErroServico.getInstancia().audit(codigo, e.getLocalizedMessage() != null ? e.getLocalizedMessage() : Mensagem.MSG_ERRO_RELATORIO, StackTraceUtil.getStackTrace(e));
		log.error(this.getMessage(), e);
	}
}
