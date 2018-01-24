package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.tipo_tratamento.entidade.TipoTratamento;
import br.gov.go.saude.silt.tipo_tratamento.servico.TipoTratamentoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "tipoTratamentoConversor", forClass = TipoTratamento.class)
@Component("tipoTratamentoConversor")
@Scope("view")
public class TipoTratamentoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			TipoTratamento tipoTratamento = null;
			try {
				Long id = Long.parseLong(idArg);
				tipoTratamento = TipoTratamentoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tipoTratamento;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object tipoTratamentoArg) {
		if(tipoTratamentoArg == null || tipoTratamentoArg.equals("")) {
			return "";
		} else {
			try {
				TipoTratamento tipoTratamento = (TipoTratamento) tipoTratamentoArg;
				return String.valueOf(tipoTratamento.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
