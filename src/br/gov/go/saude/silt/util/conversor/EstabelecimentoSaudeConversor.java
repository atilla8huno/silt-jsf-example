package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.corp.estabelecimento_saude.entidade.EstabelecimentoSaude;
import br.gov.go.saude.silt.corp.estabelecimento_saude.servico.EstabelecimentoSaudeServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "estabelecimentoSaudeConversor", forClass = EstabelecimentoSaude.class)
@Component("estabelecimentoSaudeConversor")
@Scope("view")
public class EstabelecimentoSaudeConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			EstabelecimentoSaude estabelecimentoSaude = null;
			try {
				Long id = Long.parseLong(idArg);
				estabelecimentoSaude = EstabelecimentoSaudeServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return estabelecimentoSaude;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object estabelecimentoSaudeArg) {
		if(estabelecimentoSaudeArg == null || estabelecimentoSaudeArg.equals("")) {
			return "";
		} else {
			try {
				EstabelecimentoSaude estabelecimentoSaude = (EstabelecimentoSaude) estabelecimentoSaudeArg;
				if (estabelecimentoSaude.getId() != null) {
					return String.valueOf(estabelecimentoSaude.getId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
