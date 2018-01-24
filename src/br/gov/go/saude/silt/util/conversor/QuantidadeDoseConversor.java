package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.util.enumerador.QuantidadeDoseEnum;

@FacesConverter(value = "quantidadeDoseConversor", forClass = QuantidadeDoseEnum.class)
@Component("quantidadeDoseConversor")
@Scope("view")
public class QuantidadeDoseConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if (id.trim().equals("")) {
			return null;
		} else {
			QuantidadeDoseEnum item = null;
			try {
				item = QuantidadeDoseEnum.getConstante(id);
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
				QuantidadeDoseEnum item = (QuantidadeDoseEnum) enumerador;
				return String.valueOf(item.getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

}
