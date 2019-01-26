/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.carssystem.managedbeans;

import br.com.carssystem.dao.UserDAO;
import br.com.carssystem.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author igors
 */
@ManagedBean
@SessionScoped
public class UserBean extends CrudBean<User, UserDAO>{
    
    private UserDAO userDAO;

    @Override
    public UserDAO getDao() {
        if(userDAO ==null){
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    @Override
    public User createNewEntity() {
        return new User();
    }
    
}
