package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.util.enumerador.SimNaoEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "simNaoConversor", forClass = SimNaoEnum.class)
@Component("simNaoConversor")
@Scope("view")
public class SimNaoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if(id.trim().equals("")){
			return null;
		} else {
			SimNaoEnum simNao = null;
			try {
				simNao = EnumeradorUtil.getConstante(SimNaoEnum.class, Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return simNao;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object simNaoEnum) {
		if(simNaoEnum == null || simNaoEnum.equals("")){
			return "";
		} else {
			try {
				SimNaoEnum simNao = (SimNaoEnum) simNaoEnum;
				return String.valueOf(simNao.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
