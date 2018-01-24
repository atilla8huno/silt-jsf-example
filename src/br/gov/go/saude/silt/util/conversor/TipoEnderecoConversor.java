package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.corp.enumerador.EnumTipoEndereco;
import br.gov.go.saude.silt.corp.tipo_endereco.entidade.TipoEndereco;

/**
 * @author Átilla Barros
 * @version $Rev: 302 $ $Author: claudiocosta $ $Date: 2014-02-11 18:19:03 -0200 (Ter, 11 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "tipoEnderecoConversor", forClass = TipoEndereco.class)
@Component("tipoEnderecoConversor")
@Scope("view")
public class TipoEnderecoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if (idArg.trim().equals("")) {
			return null;
		} else {
			EnumTipoEndereco enumTipoEndereco = null;
			try {
				enumTipoEndereco = EnumeradorUtil.getConstante(EnumTipoEndereco.class, Long.parseLong(idArg));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return enumTipoEndereco;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object tipoEnderecoArg) {
		if (tipoEnderecoArg == null || tipoEnderecoArg.equals("")) {
			return "";
		} else {
			try {
				EnumTipoEndereco enumTipo = (EnumTipoEndereco) tipoEnderecoArg;
				return String.valueOf(enumTipo.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
