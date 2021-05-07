package org.acme.api;

public interface AttributesProcessor<T> {

    String getProcessorName();

    void process(T entity);

}
