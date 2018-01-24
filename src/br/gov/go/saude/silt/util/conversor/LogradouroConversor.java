package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.logradouro.entidade.Logradouro;
import br.gov.go.saude.silt.corp.logradouro.servico.LogradouroServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "logradouroConversor", forClass = Logradouro.class)
@Component("logradouroConversor")
@Scope("view")
public class LogradouroConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Logradouro logradouro = null;
			try {
				Long id = Long.parseLong(idArg);
				logradouro = LogradouroServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return logradouro;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object logradouroArg) {
		if(logradouroArg == null || logradouroArg.equals("")) {
			return "";
		} else {
			try {
				Logradouro logradouro = (Logradouro) logradouroArg;
				return String.valueOf(logradouro.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
