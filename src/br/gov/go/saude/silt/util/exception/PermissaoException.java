package br.gov.go.saude.silt.util.exception;

import org.apache.log4j.Logger;

import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.util.Mensagem;

/**
 * @author Átilla Barros
 * @version $Rev: 174 $ $Author: atillabarros $ $Date: 2013-09-10 11:32:29 -0300 (Ter, 10 Set 2013) $
 * @category Exception
 */
@SuppressWarnings("serial")
public class PermissaoException extends Exception {

	private String message;
	private Logger log = Logger.getRootLogger();

	public PermissaoException(String idMensagem) {
		setMessage(Mensagem.get(idMensagem));
		writeLog(idMensagem);
	}
	
	public PermissaoException(String idMensagem, Object... parametros) {
		setMessage(Mensagem.get(idMensagem, parametros) );
		writeLog(idMensagem);
	}
	
	private void writeLog(String idMensagem) {
		AuditoriaErroServico.getInstancia().audit(idMensagem, this.getMessage(), "br.gov.go.saude.silt.util.exception.PermissaoException");
		log.warn(this.getMessage());
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String mensagem) {
		this.message = mensagem;
	}
}
