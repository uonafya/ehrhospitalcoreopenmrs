package org.openmrs.module.hospitalcore.db;

import java.util.List;

public interface EhrSingleClassDAO<T> {

    T getById(Integer id);

    T getByUuid(String uuid);

    List<T> getAll();

    List<T> getAll(boolean includeRetired);

    List<T> getAllData(boolean includeVoided);

    List<T> getAll(String fuzzySearchPhrase);

    T saveOrUpdate(T object);

    T update(T object);

    void delete(T object);
}
