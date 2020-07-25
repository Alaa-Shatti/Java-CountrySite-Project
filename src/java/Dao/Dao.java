/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.List;

/**
 *
 * @author ms
 * @param <T>
 */
public interface Dao <T>{
    
    public List<T> findAll() ;
    public void create (T Entity) ;
    public void update (T Entity) ;
    public void delete (T Entity) ;
    public T find (Long id) ;
    public List<T> findAll(int page , int pageSize) ;
    
}
