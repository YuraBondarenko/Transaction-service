package com.transactionservice.mapper;

public interface MapperToDto<T, S> {
    T getDto(S entity);
}
