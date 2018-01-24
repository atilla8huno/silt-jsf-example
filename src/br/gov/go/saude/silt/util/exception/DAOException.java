package br.gov.go.saude.silt.util.exception;

import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.dao.DataAccessException;

import br.gov.go.saude.silt.auditoria_erro.servico.AuditoriaErroServico;
import br.gov.go.saude.silt.util.Mensagem;

/**
 * @author Átilla Barros
 * @version $Rev: 102 $ $Author: atillabarros $ $Date: 2013-08-05 11:10:16 -0300 (Seg, 05 Ago 2013) $
 * @category Exception
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {

    private String message;
    private Logger log = Logger.getRootLogger();

    public DAOException(Throwable t) {
        if(t instanceof DataAccessException || t instanceof JDBCException) {
            if(t.getCause() instanceof JDBCException){
                String codigoErro = String.valueOf( ((JDBCException)t.getCause()).getErrorCode() );
                this.setMessage( Mensagem.get(codigoErro) );
                AuditoriaErroServico.getInstancia().audit(codigoErro, this.getMessage(), StackTraceUtil.getStackTrace(t));
            } else if (t.getCause() instanceof SQLException){
                String codigoErro = String.valueOf( ((SQLException)t.getCause()).getErrorCode() );
                this.setMessage( Mensagem.get(codigoErro) );
                AuditoriaErroServico.getInstancia().audit(codigoErro, this.getMessage(), StackTraceUtil.getStackTrace(t));
            } else {
                String codigo = String.valueOf(new Date().getTime());
                this.setMessage( Mensagem.get(Mensagem.MSG_ERRO_PERSISTENCIA, codigo ) );
                AuditoriaErroServico.getInstancia().audit(codigo, t.getLocalizedMessage() != null ? t.getLocalizedMessage() : Mensagem.MSG_ERRO_PERSISTENCIA , StackTraceUtil.getStackTrace(t));
            }
        } else {
            String codigo = String.valueOf(new Date().getTime());
            this.setMessage( Mensagem.get(Mensagem.MSG_ERRO_PERSISTENCIA, codigo ) );
            AuditoriaErroServico.getInstancia().audit(codigo, t.getLocalizedMessage() != null ? t.getLocalizedMessage() : Mensagem.MSG_ERRO_PERSISTENCIA, StackTraceUtil.getStackTrace(t));
        }

        log.warn(this.getMessage());
    }

    public DAOException(String mensagem) {
        this.setMessage(mensagem);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String mensagem) {
        this.message = mensagem;
    }
}
