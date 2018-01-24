package br.gov.go.saude.silt.util.enumerador;

import java.util.Arrays;
import java.util.List;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category Enum
 */
public enum TipoDrogaEnum {

	ISONIAZIDA("ISONIAZIDA");

	private String value;

	private TipoDrogaEnum(String value) {
		this.value = value;
	}

	/**
	 * Cria uma lista das constantes do enumerador.
	 * 
	 * @return
	 */
	public static List<TipoDrogaEnum> getConstantes() {
		return Arrays.asList(TipoDrogaEnum.class.getEnumConstants());
	}

	public static TipoDrogaEnum getConstante(String descricao) {
		for (TipoDrogaEnum item : getConstantes()) {
			if (item.getValue().equals(descricao)) {
				return item;
			}
		}
		return null;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
