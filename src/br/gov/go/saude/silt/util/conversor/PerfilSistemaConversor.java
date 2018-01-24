package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.portal.perfil_sistema.entidade.PerfilSistema;
import br.gov.go.saude.silt.portal.perfil_sistema.servico.PerfilSistemaServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "perfilSistemaConversor", forClass = PerfilSistema.class)
@Component("perfilSistemaConversor")
@Scope("view")
public class PerfilSistemaConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			PerfilSistema perfil = null;
			try {
				Long id = Long.parseLong(idArg);
				perfil = PerfilSistemaServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return perfil;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object perfilArg) {
		if(perfilArg == null || perfilArg.equals("")) {
			return "";
		} else {
			try {
				PerfilSistema perfil = (PerfilSistema) perfilArg;
				return String.valueOf(perfil.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
