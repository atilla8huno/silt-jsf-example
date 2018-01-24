package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.util.enumerador.TipoDrogaEnum;

@FacesConverter(value = "tipoDrogaConversor", forClass = TipoDrogaEnum.class)
@Component("tipoDrogaConversor")
@Scope("view")
public class TipoDrogaConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if (id.trim().equals("")) {
			return null;
		} else {
			TipoDrogaEnum item = null;
			try {
				item = TipoDrogaEnum.getConstante(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return item;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object enumerador) {
		if (enumerador == null || enumerador.equals("")) {
			return "";
		} else {
			try {
				TipoDrogaEnum item = (TipoDrogaEnum) enumerador;
				return item.getValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

}
