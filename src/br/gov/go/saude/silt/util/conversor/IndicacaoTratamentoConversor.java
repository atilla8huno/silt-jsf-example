package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.indicacao_tratamento.entidade.IndicacaoTratamento;
import br.gov.go.saude.silt.indicacao_tratamento.servico.IndicacaoTratamentoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "indicacaoTratamentoConversor", forClass = IndicacaoTratamento.class)
@Component("indicacaoTratamentoConversor")
@Scope("view")
public class IndicacaoTratamentoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			IndicacaoTratamento indicacaoTratamento = null;
			try {
				Long id = Long.parseLong(idArg);
				indicacaoTratamento = IndicacaoTratamentoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return indicacaoTratamento;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object indicacaoTratamentoArg) {
		if(indicacaoTratamentoArg == null || indicacaoTratamentoArg.equals("")) {
			return "";
		} else {
			try {
				IndicacaoTratamento indicacaoTratamento = (IndicacaoTratamento) indicacaoTratamentoArg;
				return String.valueOf(indicacaoTratamento.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
