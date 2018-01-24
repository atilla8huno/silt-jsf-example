package br.gov.go.saude.silt.util.template;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * @author Átilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade Genérica
 */
@MappedSuperclass
public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	public void setExcluido(boolean excluido) {	}

	/**
	 * Este método verifica se a entidade já foi persistida no banco de dados.
	 * 
	 * @return boolean
	 */
	public boolean isTransient() {
		return getId() == null || getId() <= 0;
	}
}
