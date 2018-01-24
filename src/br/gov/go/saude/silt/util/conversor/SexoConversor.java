package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.util.enumerador.SexoEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "sexoConversor", forClass = SexoEnum.class)
@Component("sexoConversor")
@Scope("view")
public class SexoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if(id.trim().equals("")){
			return null;
		} else {
			SexoEnum sexo = null;
			try {
				sexo = EnumeradorUtil.getConstante(SexoEnum.class, Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sexo;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object sexoEnum) {
		if(sexoEnum == null || sexoEnum.equals("")){
			return "";
		} else {
			try {
				SexoEnum sexo = null;
				
				/* Previne siglas que venham do banco */
				for (SexoEnum sex : SexoEnum.values())
					if (sex.getSigla() != null && sex.getSigla().equals(sexoEnum)) {
						sexo = sex;
						break;
					}
				
				if (sexo == null)
					sexo = (SexoEnum) sexoEnum;
				
				return String.valueOf(sexo.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
