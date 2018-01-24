package br.gov.go.saude.silt.util;

import org.apache.log4j.Level;

/**
 * @author Átilla Barros
 * @version $Rev: 83 $ $Author: atillabarros $ $Date: 2013-07-31 15:40:21 -0300 (Qua, 31 Jul 2013) $
 * @category log4j Level
 */
public class AuditLevel extends Level {

	private static final long serialVersionUID = 384807060940446379L;

	/**
	 * Value of audit level. This value is lesser than WARN_INT and higher than
	 * INFO_INT
	 */
	public static final int AUDIT_INT = FATAL_INT + 10000;

	public static final AuditLevel AUDIT = new AuditLevel(AUDIT_INT, "AUDIT", 7);

	protected AuditLevel(int level, String levelStr, int syslogEquivalent) {
		super(level, levelStr, syslogEquivalent);
	}

	public static AuditLevel toLevel(int val, Level defaultLevel) {
		return AUDIT;
	}

	public static AuditLevel toLevel(String sArg, Level defaultLevel) {
		return AUDIT;
	}
}
