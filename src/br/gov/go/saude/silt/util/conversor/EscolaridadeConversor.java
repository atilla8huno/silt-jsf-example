package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.escolaridade.entidade.Escolaridade;
import br.gov.go.saude.silt.corp.escolaridade.servico.EscolaridadeServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "escolaridadeConversor", forClass = Escolaridade.class)
@Component("escolaridadeConversor")
@Scope("view")
public class EscolaridadeConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Escolaridade escolaridade = null;
			try {
				Long id = Long.parseLong(idArg);
				escolaridade = EscolaridadeServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return escolaridade;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object escolaridadeArg) {
		if(escolaridadeArg == null || escolaridadeArg.equals("")) {
			return "";
		} else {
			try {
				Escolaridade escolaridade = (Escolaridade) escolaridadeArg;
				return String.valueOf(escolaridade.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
