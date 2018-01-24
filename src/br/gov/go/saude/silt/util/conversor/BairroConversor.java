package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.bairro.entidade.Bairro;
import br.gov.go.saude.silt.corp.bairro.servico.BairroServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "bairroConversor", forClass = Bairro.class)
@Component("bairroConversor")
@Scope("view")
public class BairroConversor implements Converter , Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Bairro bairro = null;
			try {
				Long id = Long.parseLong(idArg);
				bairro = BairroServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bairro;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object bairroArg) {
		if(bairroArg == null || bairroArg.equals("")) {
			return "";
		} else {
			try {
				Bairro bairro = (Bairro) bairroArg;
				return String.valueOf(bairro.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
