package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.individuo.entidade.Individuo;
import br.gov.go.saude.silt.individuo.servico.IndividuoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "individuoConversor", forClass = Individuo.class)
@Component("individuoConversor")
@Scope("view")
public class IndividuoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("") || idArg.trim().equals("null")) {
			return null;
		} else {
			Individuo individuo = null;
			try {
				Long id = Long.parseLong(idArg);
				individuo = IndividuoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return individuo;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object individuoArg) {
		if(individuoArg == null || individuoArg.equals("")) {
			return "";
		} else {
			try {
				Individuo individuo = (Individuo) individuoArg;
				return String.valueOf(individuo.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
