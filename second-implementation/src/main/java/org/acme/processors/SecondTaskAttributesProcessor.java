package org.acme.processors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.api.Task;
import org.acme.api.TaskAttributesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SecondTaskAttributesProcessor implements TaskAttributesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger("SecondTaskAttributesProcessor");

    @Override
    public String getProcessorName() {
        return "SecondTaskAttributesProcessor";
    }

    @Override
    public void process(Task task) {
        LOGGER.info("Executing {} with Task: {}", getProcessorName(), task.getId());
    }
}
