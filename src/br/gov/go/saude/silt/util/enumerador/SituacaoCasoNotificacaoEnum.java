package br.gov.go.saude.silt.util.enumerador;

/**
 * Classe enumeradora que contem algumas informacoes contidade na tabela situacao que sera utilizado para buscar no banco de dados o real registro.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 293 $ $Author: claudiocosta $ $Date: 2014-01-24 10:57:08 -0200 (Sex, 24 Jan 2014) $
 * @category Enum
 */
public enum SituacaoCasoNotificacaoEnum {

	EM_TRATAMENTO("EM TRATAMENTO"), EM_TRANSFERENCIA("EM TRANSFERÊNCIA");

	private String value;

	private SituacaoCasoNotificacaoEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
