package com.GearRentPro.dao;

import com.GearRentPro.entity.SuperEntity;
import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {
    boolean save(T entity) throws Exception;      // Must have throws Exception
    boolean update(T entity) throws Exception;    // Must have throws Exception
    boolean delete(ID id) throws Exception;       // Must have throws Exception
    T find(ID id) throws Exception;               // Must have throws Exception
    List<T> findAll() throws Exception;           // Must have throws Exception
}