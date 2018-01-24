package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.util.enumerador.ExameEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "exameConversor", forClass = ExameEnum.class)
@Component("exameConversor")
@Scope("view")
public class ExameConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if(id.trim().equals("")){
			return null;
		} else {
			ExameEnum exame = null;
			try {
				exame = EnumeradorUtil.getConstante(ExameEnum.class, Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return exame;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object exameEnum) {
		if(exameEnum == null || exameEnum.equals("")){
			return "";
		} else {
			try {
				ExameEnum exame = (ExameEnum) exameEnum;
				return String.valueOf(exame.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
