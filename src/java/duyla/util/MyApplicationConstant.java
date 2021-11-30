/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.util;

/**
 *
 * @author ANH DUY
 */
public class MyApplicationConstant {
    public class DispactchFeature {
        public static final String PROCESS_LOGIN = "processLogin";
        public static final String PROCESS_SEARCH = "processSearchLastname";
        public static final String PROCESS_DELETE = "processDeleteAccount";
        public static final String PROCESS_UPDATE = "processUpdateAccount";
        public static final String PROCESS_ADDTOCART = "processAddBookToCart";
        public static final String PROCESS_REMOVE = "processRemoveBookFromCart";
        public static final String PROCESS_LOGOUT = "processLogOut";
        public static final String PROCESS_SIGNUP = "processCreateAccount";
        public static final String VIEW_SHOPPING = "viewShopping";
    }

    public class LoginFeature {
        public static final String PROCESS_LOGIN = "processLogin";
        public static final String VIEW_LOGIN = "viewLogin";
        public static final String VIEW_SEARCH = "viewSearch";
    }

    public class SignUpFeature {
        public static final String VIEW_SIGNUP = "viewSignUp";
        public static final String VIEW_LOGIN = "viewLogin";
    }   
}
