package com.loja.produtos.service;

import java.io.Serializable;
import java.util.List;

public interface AbstractService <T extends Serializable, K>{

    List<T> getAll();

    T getById(Long id);

    T createNew(T dto);

    T saveByDTO(Long id, T dto);

    T patch(Long id, T dto);

    void deleteById(Long id);
}
