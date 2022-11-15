package at.fhtw.swen3.services;

import java.util.Collection;

public interface CrudService<Object> {
    public abstract void create(Object o);
    public abstract void update(String id, Object o);
    public abstract void delete(String id);
    public abstract Collection<Object> getAll();
}