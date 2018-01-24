package br.gov.go.saude.silt.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author Átilla Barros
 * @version $Rev: 182 $ $Author: atillabarros $ $Date: 2013-09-16 15:15:17 -0300 (Seg, 16 Set 2013) $
 * @category Exception
 */
public class StackTraceUtil {

	public static String getStackTrace(Throwable aThrowable) {
		
		final Writer writer = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(writer);
		aThrowable.printStackTrace(printWriter);
		
		String stackTrace = writer.toString();
		
		//Evita IndexOutOfBoundsException 
		if (stackTrace.length() < 20000) {
			return stackTrace;
		} else {
			return writer.toString().substring(0, 20000);
		}
	}
}
