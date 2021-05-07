package org.acme.processors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.api.User;
import org.acme.api.UserAttributesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DefaultUserAttributesProcessor implements UserAttributesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger("DefaultUserAttributesProcessor");

    @Override
    public String getProcessorName() {
        return "DefaultUserAttributesProcessor";
    }

    @Override
    public void process(User entity) {
        LOGGER.info("Executing {} with user: {}", getProcessorName(), entity.getName());
    }
}
