/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Data.User;
import Logic.LogicFacade;
import Logic.Exceptions.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Younes
 */
public class RegisterCustomerCommand extends Command {

    public RegisterCustomerCommand() {
    }

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        
        String username = request.getParameter( "username" );
        String password1 = request.getParameter( "password1" );
        String password2 = request.getParameter( "password2" );
        String email = request.getParameter("email");
        if (password1.equals(password2)) {
            try {
                User user = LogicFacade.createUserCustomer(username, password1, email);
                HttpSession session = request.getSession();
                session.setAttribute( "user", user );
                session.setAttribute( "role", user.getRole() );
                return user.getRole() + "page";
            } catch (ClassNotFoundException ex) {
                
            }
        } else {
            throw new LoginException( "The two passwords did not match" );
        }
        return null;
    }
        
        
        
    }
