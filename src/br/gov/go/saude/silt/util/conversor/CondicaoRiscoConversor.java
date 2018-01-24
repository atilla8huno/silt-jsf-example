package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.condicao_risco.entidade.CondicaoRisco;
import br.gov.go.saude.silt.condicao_risco.servico.CondicaoRiscoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "condicaoRiscoConversor", forClass = CondicaoRisco.class)
@Component("condicaoRiscoConversor")
@Scope("view")
public class CondicaoRiscoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			CondicaoRisco condicaoRisco = null;
			try {
				Long id = Long.parseLong(idArg);
				condicaoRisco = CondicaoRiscoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return condicaoRisco;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object condicaoRiscoArg) {
		if(condicaoRiscoArg == null || condicaoRiscoArg.equals("")) {
			return "";
		} else {
			try {
				CondicaoRisco condicaoRisco = (CondicaoRisco) condicaoRiscoArg;
				return String.valueOf(condicaoRisco.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
