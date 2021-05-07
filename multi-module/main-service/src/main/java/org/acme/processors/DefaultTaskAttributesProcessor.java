package org.acme.processors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.api.Task;
import org.acme.api.TaskAttributesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DefaultTaskAttributesProcessor implements TaskAttributesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger("DefaultTaskAttributesProcessor");

    @Override
    public String getProcessorName() {
        return "DefaultTaskAttributesProcessor";
    }

    @Override
    public void process(Task entity) {
        LOGGER.info("Executing {} with Task: {}", getProcessorName(), entity.getId());
    }
}
