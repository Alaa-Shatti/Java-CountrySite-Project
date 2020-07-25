/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

/**
 *
 * @author ms
 */
@Named(value = "valid")
@SessionScoped
public class ValidateController implements Serializable {

    public boolean validatePass(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String pass = (String) value;
        if (pass.length() < 8 || pass.length() > 20) {
            String tmp="password length must be 8 between 20 in length";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, tmp, tmp));
        } else {
            return true;
        }
    }
    
     public boolean validateEmail(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String title = (String) value;
     
        if (title.contains("@")||title.contains(".")) {
            return true;
          
        } else if(title.contains("")||title.contains("")) {
             String tmp="This field isn't email adress required!!";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, tmp, tmp));
        }
        return false;
    }
     public boolean validateDate(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String title = (String) value;
     
        if (title.contains("-")) {
            return true;
          
        } else if(title.contains("")||title.contains("")) {
             String tmp="This field isn't date required!!";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, tmp, tmp));
        }
        return false;
    }
       public boolean validateTime(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String title = (String) value;
     
        if (title.contains(":")) {
            return true;
          
        } else if(title.contains("")||title.contains("")) {
             String tmp="This field isn't date required!!";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, tmp, tmp));
        }
        return false;
    }
       
        public boolean validateStringLength(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String pass = (String) value;
        if (pass.length() < 1 || pass.length() > 1000) {
            String tmp="This field is required";
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, tmp, tmp));
        } else {
            return true;
        }
    }
}