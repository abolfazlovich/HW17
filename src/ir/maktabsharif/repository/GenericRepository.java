package ir.maktabsharif.repository;

import ir.maktabsharif.model.Event;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, R> {
    T save(T t);

    void update(T t);

    void delete(R r);

    T findById(Long aLong);

    List<T> findAll();

}
