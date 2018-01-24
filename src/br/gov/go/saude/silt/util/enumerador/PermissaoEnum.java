package br.gov.go.saude.silt.util.enumerador;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Átilla Barros
 * @version $Rev: 181 $ $Author: claudiocosta $ $Date: 2013-09-12 14:17:14 -0300 (Qui, 12 Set 2013) $
 * @category Enum
 */
public enum PermissaoEnum {

	INCLUIR("Incluir"), ALTERAR("Alterar"), EXCLUIR("Excluir"), CONSULTAR("Consultar");
	
	private String value;
	
	/**
	 * Lista todos os itens do enum.
	 * 
	 * @return List<PermissaoEnum>
	 */
	public static final List<PermissaoEnum> listar() {
		List<PermissaoEnum> lista = new ArrayList<PermissaoEnum>();
		
		for (PermissaoEnum item : PermissaoEnum.values()) {
			lista.add(item);
		}
		
		return lista;
	}

	private PermissaoEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
