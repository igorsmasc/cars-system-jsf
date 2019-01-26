package br.com.carssystem.managedbeans;

import br.com.carssystem.dao.CrudDAO;
import br.com.carssystem.util.exception.ErrorSystem;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author igors
 */
public abstract class CrudBean<E, D extends CrudDAO> {
    
    private String screenState = "find"; //Inserir, editar e buscar;
    
    private E entity;
    private List<E> entitys;
    
    public void newOne(){ //New
        entity = createNewEntity();
        changeToInsert();
    }
    public void save(){
        try {
            getDao().save(entity);
            entity = createNewEntity();
            addMessage("SAVED! Saved successfully!", FacesMessage.SEVERITY_INFO);
            changeToFind();
        } catch (ErrorSystem ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void edit(E entity){ 
        this.entity = entity;
        changeToEdit();
    }
    public void delete(E entity){
        try {
            getDao().delete(entity);
            entitys.remove(entity);
            addMessage("DELETED! Deleted successfully!", FacesMessage.SEVERITY_INFO);
        } catch (ErrorSystem ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void find(){    
        if(!isFind()){
            changeToFind();
            return;
        }
    
        try {
            entitys = getDao().findAll();
            if(entitys.isEmpty()){
                addMessage("We have nothing registered!", FacesMessage.SEVERITY_INFO);
            }
        } catch (ErrorSystem ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            addMessage(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    
    public void addMessage(String message, FacesMessage.Severity tipoErro){ 
        FacesMessage fm = new FacesMessage(tipoErro, message, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    //getters and setters

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public List<E> getEntitys() {
        return entitys;
    }

    public void setEntitys(List<E> entitys) {
        this.entitys = entitys;
    }
    
    
    //Responsável por criar os métodos nas classes bean
    public abstract D getDao();
    public abstract E createNewEntity();
    
    //MÉTODOS PARA CONTROLE DA TELA
    public boolean isInsert(){
        return "insert".equals(screenState);
    }   
    public boolean isEdit(){
        return "edit".equals(screenState);
    }    
    public boolean isFind(){
        return "find".equals(screenState);
    }   
    
    public void changeToInsert(){
        screenState = "insert";
    }
    public void changeToEdit(){
        screenState = "edit";
    }
    public void changeToFind(){
        screenState = "find";
    }

}
