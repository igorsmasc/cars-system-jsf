package br.com.carssystem.dao;
import br.com.carssystem.util.exception.ErrorSystem;
import java.util.List;

/**
 *
 * @author igors
 */
public interface CrudDAO<E> { // 'E' representa minha entidade
    
    public void save(E entity) throws ErrorSystem;
    public void delete(E entity) throws ErrorSystem;
    public List<E> findAll() throws ErrorSystem;
    
}
