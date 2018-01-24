package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.raca_cor.entidade.RacaCor;
import br.gov.go.saude.silt.corp.raca_cor.servico.RacaCorServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "racaCorConversor", forClass = RacaCor.class)
@Component("racaCorConversor")
@Scope("view")
public class RacaCorConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			RacaCor racaCor = null;
			try {
				Long id = Long.parseLong(idArg);
				racaCor = RacaCorServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return racaCor;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object racaCorArg) {
		if(racaCorArg == null || racaCorArg.equals("")) {
			return "";
		} else {
			try {
				RacaCor racaCor = (RacaCor) racaCorArg;
				return String.valueOf(racaCor.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
