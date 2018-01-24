package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.util.enumerador.StatusEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "statusConversor", forClass = StatusEnum.class)
@Component("statusConversor")
@Scope("view")
public class StatusConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if(id.trim().equals("")){
			return null;
		} else {
			StatusEnum status = null;
			try {
				status = EnumeradorUtil.getConstante(StatusEnum.class, Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return status;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object statusEnum) {
		if(statusEnum == null || statusEnum.equals("")){
			return "";
		} else {
			try {
				StatusEnum status = (StatusEnum) statusEnum;
				return String.valueOf(status.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
