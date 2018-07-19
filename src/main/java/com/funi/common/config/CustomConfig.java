package com.funi.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class CustomConfig extends PropertyPlaceholderConfigurer {

    private final static String PLACEHOLDER_START = "${";

    private static Map<String, String> ctx;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        resolvePlaceholder(props);
        super.processProperties(beanFactoryToProcess, props);
        
    }

    private void resolvePlaceholder(Properties props) {
        Iterator iterator = props.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry entry = (Map.Entry) iterator.next();
            final Object value = entry.getValue();
            if (value != null && String.class.isInstance(value)) {
                final String resolved = resolvePlaceholder(value.toString(), props);
                if (!value.equals(resolved)) {
                    if (resolved == null) {
                        iterator.remove();
                    } else {
                        entry.setValue(resolved);
                    }
                }
            }
        }
    }
}
