package br.gov.go.saude.silt.util;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * @author Átilla Barros
 * @version $Rev: 226 $ $Author: claudiocosta $ $Date: 2013-11-06 15:10:29 -0200 (Qua, 06 Nov 2013) $
 * @category Spring
 */
public class SpringContainer {
	
	private ApplicationContext contextoSpring;

    private ApplicationContext getContextoSpring() {
        if (contextoSpring == null) {
    		/*
    		 * Utilizado para execução real no servidor
    		 */
    		contextoSpring = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
    		
    		//contextoSpring = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    		
        }
        return contextoSpring;
    }
    
    private ApplicationContext getContextoSpringJUnit() {
        if (contextoSpring == null) {
    		/*
    		 * Utilizado para testes e execuções offline
    		 */
    		contextoSpring = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        }
        return contextoSpring;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String nome) {
        ApplicationContext contexto = getContextoSpring();
        if (contexto != null) {
            try {
                return (T) contexto.getBean(nome);
            } catch (NoSuchBeanDefinitionException ex) {
                return null;
            }
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public <T> T getBeanJUnit(String nome) {
        ApplicationContext contexto = getContextoSpringJUnit();
        if (contexto != null) {
            try {
                return (T) contexto.getBean(nome);
            } catch (NoSuchBeanDefinitionException ex) {
                return null;
            }
        }
        return null;
    }

    public static synchronized SpringContainer getInstancia() {
        return _instancia;
    }

    private static SpringContainer _instancia = new SpringContainer();

    private SpringContainer() {
    }

}
