package br.gov.go.saude.silt.util;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

/**
 * @author Átilla Barros
 * @version	$Rev: 301 $ $Author: atillabarros $ $Date: 2014-02-07 14:34:03 -0200 (Sex, 07 Fev 2014) $
 * @category Scope
 */
public class ViewScopeCallbackRegistrer implements ViewMapListener {

	@SuppressWarnings("unchecked")
	public void processEvent(SystemEvent event) throws AbortProcessingException {  
        if (event instanceof PostConstructViewMapEvent) {  
            PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent) event;  
            UIViewRoot viewRoot = (UIViewRoot) viewMapEvent.getComponent();  
            viewRoot.getViewMap().put(ViewScope.VIEW_SCOPE_CALLBACKS, new HashMap<String, Runnable>());  
        } else if (event instanceof PreDestroyViewMapEvent) {  
            PreDestroyViewMapEvent viewMapEvent = (PreDestroyViewMapEvent) event;  
            UIViewRoot viewRoot = (UIViewRoot) viewMapEvent.getComponent();  
            Map<String, Runnable> callbacks = (Map<String, Runnable>) viewRoot.getViewMap().get(ViewScope.VIEW_SCOPE_CALLBACKS);  
            if (callbacks != null) {  
                for (Runnable c : callbacks.values()) {  
                    c.run();  
                }  
                callbacks.clear();  
            }  
        }  
    }  
  
    public boolean isListenerForSource(Object source) {  
        return source instanceof UIViewRoot;  
    }
}
