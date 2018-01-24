package br.gov.go.saude.silt.util.enumerador;

/**
 * Classe enum utilizada no lançamento de historicos de alteração de uma entidade monitorada.
 * 
 * @author Cláudio Espíndola Costa
 * @version $Rev: 283 $ $Author: claudiocosta $ $Date: 2013-12-19 16:03:21 -0200 (Qui, 19 Dez 2013) $
 * @category Enum
 */
public enum TipoAcaoUsuarioEnum {

	ALTERACAO("ALTERAÇÃO DE DADOS"), CADASTRO("CADASTRO"), CADASTRO_DOSE("CADASTRO DE DOSE"), TRANSFERENCIA("TRANSFERÊNCIA"), EXCLUSAO("EXCLUSÃO"), ENCERRAMENTO(
			"ENCERRAMENTO"), RETORNO("RETORNO");

	private String value;

	private TipoAcaoUsuarioEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
