package br.gov.go.saude.silt.util.template;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * @author �tilla Barros
 * @version $Rev: 307 $ $Author: atillabarros $ $Date: 2014-02-27 15:19:12 -0300 (Qui, 27 Fev 2014) $
 * @category Entidade Gen�rica
 */
@MappedSuperclass
public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	public void setExcluido(boolean excluido) {	}

	/**
	 * Este m�todo verifica se a entidade j� foi persistida no banco de dados.
	 * 
	 * @return boolean
	 */
	public boolean isTransient() {
		return getId() == null || getId() <= 0;
	}
}
