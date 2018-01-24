package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.nivel_acesso.entidade.NivelAcesso;
import br.gov.go.saude.silt.nivel_acesso.servico.NivelAcessoServico;

@FacesConverter(value = "nivelAcessoConversor", forClass = NivelAcesso.class)
@Component("nivelAcessoConversor")
@Scope("view")
public class NivelAcessoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if (idArg.trim().equals("")) {
			return null;
		} else {
			NivelAcesso nivelAcesso = null;
			try {
				Long id = Long.parseLong(idArg);
				nivelAcesso = NivelAcessoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return nivelAcesso;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object nivelAcessoArg) {
		if (nivelAcessoArg == null || nivelAcessoArg.equals("")) {
			return "";
		} else {
			try {
				NivelAcesso nivelAcesso = (NivelAcesso) nivelAcessoArg;
				return String.valueOf(nivelAcesso.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}

}
