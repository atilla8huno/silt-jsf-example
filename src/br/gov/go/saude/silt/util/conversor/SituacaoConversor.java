package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.silt.situacao.entidade.Situacao;
import br.gov.go.saude.silt.situacao.servico.SituacaoServico;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "situacaoConversor", forClass = Situacao.class)
@Component("situacaoConversor")
@Scope("view")
public class SituacaoConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String idArg) {
		if(idArg.trim().equals("")) {
			return null;
		} else {
			Situacao situacao = null;
			try {
				Long id = Long.parseLong(idArg);
				situacao = SituacaoServico.getInstancia().consultarPorId(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return situacao;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object situacaoArg) {
		if(situacaoArg == null || situacaoArg.equals("")) {
			return "";
		} else {
			try {
				Situacao situacao = (Situacao) situacaoArg;
				return String.valueOf(situacao.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
