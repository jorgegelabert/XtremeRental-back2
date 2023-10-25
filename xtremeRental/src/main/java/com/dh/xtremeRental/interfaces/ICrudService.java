package com.dh.xtremeRental.interfaces;

import java.util.Set;

public interface ICrudService<T,E> {

    public T crear(T t);
    public T buscar(Integer id);
    public T modificar(T t);
    public String eliminar(Integer id);
    Set<T> listartodos();

}
