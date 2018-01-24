package br.gov.go.saude.silt.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.gov.go.saude.silt.util.enumerador.PermissaoEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 181 $ $Author: claudiocosta $ $Date: 2013-09-12 14:17:14 -0300 (Qui, 12 Set 2013) $
 * @category Annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Permissao {

	PermissaoEnum permissao() default PermissaoEnum.CONSULTAR;
	
}
