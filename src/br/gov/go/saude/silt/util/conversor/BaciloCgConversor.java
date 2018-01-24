package br.gov.go.saude.silt.util.conversor;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.gov.go.saude.arquitetura.util.EnumeradorUtil;
import br.gov.go.saude.silt.util.enumerador.BaciloCgEnum;

/**
 * @author Átilla Barros
 * @version $Rev: 299 $ $Author: claudiocosta $ $Date: 2014-02-04 16:03:28 -0200 (Ter, 04 Fev 2014) $
 * @category Converter
 */
@FacesConverter(value = "baciloCgConversor", forClass = BaciloCgEnum.class)
@Component("baciloCgConversor")
@Scope("view")
public class BaciloCgConversor implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent comp, String id) {
		if(id.trim().equals("")){
			return null;
		} else {
			BaciloCgEnum baciloCg = null;
			try{
				baciloCg = EnumeradorUtil.getConstante(BaciloCgEnum.class, Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return baciloCg;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent comp, Object baciloCgEnum) {
		if(baciloCgEnum == null || baciloCgEnum.equals("")){
			return "";
		} else {
			try{
				BaciloCgEnum baciloCg = (BaciloCgEnum) baciloCgEnum;
				return String.valueOf(baciloCg.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
