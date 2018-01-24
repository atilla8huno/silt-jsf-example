package br.gov.go.saude.silt.util.enumerador;

import java.util.Arrays;
import java.util.List;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category Enum
 */
public enum QuantidadeDoseEnum {

	SEIS_MESES("180"), UM_ANO("360");

	private String value;

	/**
	 * Cria uma lista das constantes do enumerador.
	 * 
	 * @return
	 */
	public static List<QuantidadeDoseEnum> getConstantes() {
		return Arrays.asList(QuantidadeDoseEnum.class.getEnumConstants());
	}

	public static QuantidadeDoseEnum getConstante(String descricao) {
		for (QuantidadeDoseEnum item : getConstantes()) {
			if (item.getValue().equals(descricao)) {
				return item;
			}
		}
		return null;
	}

	private QuantidadeDoseEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
