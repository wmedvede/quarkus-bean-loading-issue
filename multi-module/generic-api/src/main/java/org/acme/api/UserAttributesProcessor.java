package org.acme.api;

/**
 * Simple api that will be implemented different maven modules.
 * The implemented components will be loaded by CDI, @see {@link org.acme.AttributesProcessorRegistry}
 */
public interface UserAttributesProcessor extends AttributesProcessor<User> {

}
