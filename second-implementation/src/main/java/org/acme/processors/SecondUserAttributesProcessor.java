package org.acme.processors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.api.User;
import org.acme.api.UserAttributesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SecondUserAttributesProcessor implements UserAttributesProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger("SecondUserAttributesProcessor");

    @Override
    public String getProcessorName() {
        return "SecondUserAttributesProcessor";
    }

    @Override
    public void process(User user) {
        LOGGER.info("Executing {} with user: {}", getProcessorName(), user.getName());
    }
}
