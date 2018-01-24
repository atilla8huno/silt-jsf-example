package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.tipo_logradouro.entidade.TipoLogradouro;
import br.gov.go.saude.silt.corp.tipo_logradouro.servico.TipoLogradouroServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "tipoLogradouroConversor", forClass = TipoLogradouro.class)
@Component("tipoLogradouroConversor")
@Scope("view")
public class TipoLogradouroConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			TipoLogradouro tipoLogradouro = null;
			try {
				Long id = Long.parseLong(idArg);
				tipoLogradouro = TipoLogradouroServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tipoLogradouro;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object tipoLogradouroArg) {
		if(tipoLogradouroArg == null || tipoLogradouroArg.equals("")) {
			return "";
		} else {
			try {
				TipoLogradouro tipoLogradouro = (TipoLogradouro) tipoLogradouroArg;
				return String.valueOf(tipoLogradouro.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
