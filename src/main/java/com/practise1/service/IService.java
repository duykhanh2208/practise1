package com.practise1.service;

import java.util.Optional;

public interface IService<E> {
    public Iterable<E> findAll();

    public Optional<E> findById(Long id);

    public void save(E e);

    public void delete(Long id);
}