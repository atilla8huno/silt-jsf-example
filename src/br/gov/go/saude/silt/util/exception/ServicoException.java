package br.gov.go.saude.silt.util.exception;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.util.Mensagem;

/**
 * @author Átilla Barros
 * @version $Rev: 174 $ $Author: atillabarros $ $Date: 2013-09-10 11:32:29 -0300 (Ter, 10 Set 2013) $
 * @category Exception
 */
@SuppressWarnings("serial")
public class ServicoException extends Exception {

	private String message;
	private Logger log = Logger.getRootLogger();

	public ServicoException(Throwable t) {
		if (t instanceof DAOException) {
			this.setMessage(t.getMessage());
		} else if (t instanceof ServicoException) {
			this.setMessage(t.getMessage());
		} else if (t instanceof DataAccessException) {
			this.setMessage(new DAOException(t).getMessage());
		} else if (t instanceof JDBCException){
			this.setMessage(new DAOException(t).getMessage());
		} else if (t instanceof RelatorioException){
			this.setMessage(t.getMessage());
		} else if (t instanceof PermissaoException){
			this.setMessage(t.getMessage());
		} else {
			String codigo = String.valueOf(new Date().getTime());
			this.setMessage(Mensagem.get(Mensagem.MSG_ERRO_GERAL, codigo));
			log.error(this.getMessage(), t);
			AuditoriaErroServico.getInstancia().audit(codigo, this.getMessage(), StackTraceUtil.getStackTrace(t));
		}
	}
	
	public ServicoException(String idMensagem) {
		setMessage( Mensagem.get(idMensagem) );
		writeLog(idMensagem);
	}
	
	public ServicoException(String idMensagem, Object... parametros) {
		setMessage( Mensagem.get(idMensagem, parametros) );
		writeLog(idMensagem);
	}
	
	private void writeLog(String idMensagem) {
		AuditoriaErroServico.getInstancia().audit(idMensagem, this.getMessage(), "br.gov.go.saude.silt.util.exception.ServicoException");
		log.warn(this.getMessage());
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String mensagem) {
		this.message = mensagem;
	}
}
