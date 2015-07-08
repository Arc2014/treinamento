package br.com.box.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.box.model.Grupo;

@FacesConverter(value = "GrupoConverter")
public class GrupoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Object ret = null;
		if (arg1 instanceof PickList) {
			Object dualList = ((PickList) arg1).getValue();
			DualListModel<?> dl = (DualListModel<?>) dualList;
			for (Object o : dl.getSource()) {
				ret = getGrupoPorId(arg2, ret, o);
			}
			if (ret == null) {
				for (Object o : dl.getTarget()) {
					ret = getGrupoPorId(arg2, ret, o);
				}
			}
		}
		return ret;
	}

	private Object getGrupoPorId(String arg2, Object ret, Object o) {
		String id = "" + ((Grupo) o).getId();
		if (arg2.equals(id)) {
			ret = o;
		}
		return ret;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		String str = "";
		if (arg2 instanceof Grupo) {
			str = "" + ((Grupo) arg2).getId();
		}
		return str;
	}
}
