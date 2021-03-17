package com.transactionservice.mapper;

public interface MapperToEntity<T, S> {
    T getEntity(S dto);
}
