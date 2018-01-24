package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.tipo_contato.entidade.TipoContato;
import br.gov.go.saude.silt.corp.tipo_contato.servico.TipoContatoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "tipoContatoConversor", forClass = TipoContato.class)
@Component("tipoContatoConversor")
@Scope("view")
public class TipoContatoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			TipoContato tipoContato = null;
			try {
				Long id = Long.parseLong(idArg);
				tipoContato = TipoContatoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tipoContato;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object tipoContatoArg) {
		if(tipoContatoArg == null || tipoContatoArg.equals("")) {
			return "";
		} else {
			try {
				TipoContato tipoContato = (TipoContato) tipoContatoArg;
				return String.valueOf(tipoContato.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
