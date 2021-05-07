package org.acme;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import io.quarkus.runtime.Startup;
import org.acme.api.AttributesProcessor;
import org.acme.api.TaskAttributesProcessor;
import org.acme.api.UserAttributesProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Startup
public class AttributesProcessorRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(AttributesProcessorRegistry.class);

    private final List<UserAttributesProcessor> userAttributesProcessors;
    private final List<TaskAttributesProcessor> taskAttributesProcessors;

    /**
     * Here's were the components are loaded.
     * In general we have that:
     *
     * 1) when we run the quarkus app:   java -jar target/quarkus-app/quarkus-run.jar
     * all the implementations in "main-service" module and the "second-implementation" project are loaded fine.
     *
     * 2) when we generate and execute the native image, all works fine same as in 1)
     *
     * 3) when we run in dev mode, only the implementations in "main-service" module are loaded, while the implementations
     *
     * in the "second-implementation" project fails, throwing the following exception.
     * Caused by: java.lang.RuntimeException: Failed to initialize Arc
     *         at io.quarkus.arc.Arc.initialize(Arc.java:26)
     *         at io.quarkus.arc.runtime.ArcRecorder.getContainer(ArcRecorder.java:40)
     *         at io.quarkus.deployment.steps.ArcProcessor$generateResources-1025303321.deploy_0(ArcProcessor$generateResources-1025303321.zig:76)
     *         at io.quarkus.deployment.steps.ArcProcessor$generateResources-1025303321.deploy(ArcProcessor$generateResources-1025303321.zig:40)
     *         at io.quarkus.runner.ApplicationImpl.<clinit>(ApplicationImpl.zig:224)
     *         ... 15 more
     * Caused by: java.lang.NoClassDefFoundError: org/acme/api/TaskAttributesProcessor
     *         at java.base/java.lang.ClassLoader.defineClass1(Native Method)
     *         at java.base/java.lang.ClassLoader.defineClass(ClassLoader.java:1016)
     *         at io.quarkus.bootstrap.classloading.QuarkusClassLoader.loadClass(QuarkusClassLoader.java:411)
     *         at io.quarkus.bootstrap.classloading.QuarkusClassLoader.loadClass(QuarkusClassLoader.java:371)
     *         at io.quarkus.bootstrap.classloading.QuarkusClassLoader.loadClass(QuarkusClassLoader.java:421)
     *         at io.quarkus.bootstrap.classloading.QuarkusClassLoader.loadClass(QuarkusClassLoader.java:371)
     *         at java.base/java.lang.Class.forName0(Native Method)
     *         at java.base/java.lang.Class.forName(Class.java:398)
     *         at org.acme.processors.SecondTaskAttributesProcessor_Bean.<init>(SecondTaskAttributesProcessor_Bean.zig:138)
     *         at io.quarkus.arc.setup.Default_ComponentsProvider.addBeans1(Default_ComponentsProvider.zig:263)
     *         at io.quarkus.arc.setup.Default_ComponentsProvider.getComponents(Default_ComponentsProvider.zig:38)
     *         at io.quarkus.arc.impl.ArcContainerImpl.<init>(ArcContainerImpl.java:112)
     *         at io.quarkus.arc.Arc.initialize(Arc.java:20)
     *         ... 19 more
     * Caused by: java.lang.ClassNotFoundException: org.acme.api.TaskAttributesProcessor
     *
     *
     */
    @Inject
    public AttributesProcessorRegistry(Instance<AttributesProcessor<?>> processorsInstance) {
        userAttributesProcessors = new ArrayList<>();
        taskAttributesProcessors = new ArrayList<>();
        for (AttributesProcessor<?> processor : processorsInstance) {
            LOGGER.info("Loading processor: {}", processor.getProcessorName());
            if (processor instanceof UserAttributesProcessor) {
                userAttributesProcessors.add((UserAttributesProcessor) processor);
            } else if (processor instanceof TaskAttributesProcessor) {
                taskAttributesProcessors.add((TaskAttributesProcessor) processor);
            } else {
                throw new IllegalArgumentException("Unexpected processor implementation: " + processor.getClass());
            }
        }
    }
}
