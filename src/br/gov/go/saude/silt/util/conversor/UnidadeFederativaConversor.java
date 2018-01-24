package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.unidade_federativa.entidade.UnidadeFederativa;
import br.gov.go.saude.silt.corp.unidade_federativa.servico.UnidadeFederativaServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "unidadeFederativaConversor", forClass = UnidadeFederativa.class)
@Component("unidadeFederativaConversor")
@Scope("view")
public class UnidadeFederativaConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			UnidadeFederativa unidadeFederativa = null;
			try {
				BigDecimal idBD = new BigDecimal(idArg);
				Long id = idBD.longValue();
				unidadeFederativa = UnidadeFederativaServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return unidadeFederativa;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object unidadeFederativaArg) {
		if(unidadeFederativaArg == null || unidadeFederativaArg.equals("")) {
			return "";
		} else {
			try {
				UnidadeFederativa unidadeFederativa = (UnidadeFederativa) unidadeFederativaArg;
				return String.valueOf(unidadeFederativa.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
