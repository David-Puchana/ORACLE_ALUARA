package com.courseoracle.screenmatch.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> class_);
}
