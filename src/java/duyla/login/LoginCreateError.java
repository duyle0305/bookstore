/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.login;

import java.io.Serializable;

/**
 *
 * @author ANH DUY
 */
public class LoginCreateError implements Serializable{
    private String usernameLengtherr;
    private String passwordLengtherr;
    private String fullnameLengtherr;
    private String confirmNotMatchPassword;
    private String usernameIsExisted;

    public LoginCreateError() {
    }

    
    /**
     * @return the usernameLengtherr
     */
    public String getUsernameLengtherr() {
        return usernameLengtherr;
    }

    /**
     * @param usernameLengtherr the usernameLengtherr to set
     */
    public void setUsernameLengtherr(String usernameLengtherr) {
        this.usernameLengtherr = usernameLengtherr;
    }

    /**
     * @return the passwordLengtherr
     */
    public String getPasswordLengtherr() {
        return passwordLengtherr;
    }

    /**
     * @param passwordLengtherr the passwordLengtherr to set
     */
    public void setPasswordLengtherr(String passwordLengtherr) {
        this.passwordLengtherr = passwordLengtherr;
    }

    /**
     * @return the fullnameLengtherr
     */
    public String getFullnameLengtherr() {
        return fullnameLengtherr;
    }

    /**
     * @param fullnameLengtherr the fullnameLengtherr to set
     */
    public void setFullnameLengtherr(String fullnameLengtherr) {
        this.fullnameLengtherr = fullnameLengtherr;
    }

    /**
     * @return the confirmNotMatchPassword
     */
    public String getConfirmNotMatchPassword() {
        return confirmNotMatchPassword;
    }

    /**
     * @param confirmNotMatchPassword the confirmNotMatchPassword to set
     */
    public void setConfirmNotMatchPassword(String confirmNotMatchPassword) {
        this.confirmNotMatchPassword = confirmNotMatchPassword;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
