package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.municipio.entidade.Municipio;
import br.gov.go.saude.silt.corp.municipio.servico.MunicipioServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "municipioConversor", forClass = Municipio.class)
@Component("municipioConversor")
@Scope("view")
public class MunicipioConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Municipio municipio = null;
			try {
				Long id = Long.parseLong(idArg);
				municipio = MunicipioServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return municipio;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object municipioArg) {
		if(municipioArg == null || municipioArg.equals("")) {
			return "";
		} else {
			try {
				Municipio municipio = (Municipio) municipioArg;
				return String.valueOf(municipio.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
