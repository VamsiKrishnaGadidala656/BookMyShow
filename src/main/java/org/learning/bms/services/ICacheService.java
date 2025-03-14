package org.learning.bms.services;

public interface ICacheService {
    void set(String key, Object value);

    void setWithTTL(String key, Object value);

    Object get(String key);

    void delete(String key);

    void getAllKeysAndValues();

    void deleteAll();
}
