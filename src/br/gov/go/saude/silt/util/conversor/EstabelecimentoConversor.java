package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.estabelecimento.entidade.Estabelecimento;
import br.gov.go.saude.silt.estabelecimento.servico.EstabelecimentoServico;

/**
 * @author Cláudio Espíndola Costa
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "estabelecimentoConversor", forClass = Estabelecimento.class)
@Component("estabelecimentoConversor")
@Scope("view")
public class EstabelecimentoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Estabelecimento estabelecimento = null;
			try {
				Long id = Long.parseLong(idArg);
				estabelecimento = EstabelecimentoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return estabelecimento;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object estabelecimentoArg) {
		if(estabelecimentoArg == null  || estabelecimentoArg.equals("")) {
			return "";
		} else {
			try {
				Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoArg;
				if (estabelecimento.getId() != null) {
					return String.valueOf(estabelecimento.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
