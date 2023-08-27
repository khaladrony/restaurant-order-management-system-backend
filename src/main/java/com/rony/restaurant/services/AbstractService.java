package com.rony.restaurant.services;

import com.rony.restaurant.exception.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AbstractService<T> {
    T create(T t) throws ServiceException;

    T update(Long id, T t) throws ServiceException;

    Boolean delete(Long id) throws ServiceException;

    T findById(Long id) throws ServiceException;

    Page<T> findAll(int page, int size);

    List<T> findAll();
}
