package com.aluracurse.challengebooks.service;

public interface IConverterData {
    <T> T getData(String json, Class<T> class_);
}
