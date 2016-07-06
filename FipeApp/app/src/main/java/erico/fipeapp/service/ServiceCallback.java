package erico.fipeapp.service;

/**
 * Interface acionada em chamadas assíncronas
 * Created by erico on 06/07/16.
 */
public interface ServiceCallback<T> {
    void onSuccess(T obj);
}
