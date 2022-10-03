package exercise;

import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// BEGIN
@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

    private Map<String, String> inspectingBeans = new HashMap<>();

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Inspect inspectAnnotation = bean.getClass().getAnnotation(Inspect.class);

        if (inspectAnnotation != null) {
            inspectingBeans.put(beanName, inspectAnnotation.level());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Logger logger = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

        if (inspectingBeans.containsKey(beanName)) {
            Object originalBean = bean;
            InvocationHandler handler = new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    if (inspectingBeans.get(beanName).equals("info")) {
                        logger.info("Was called method: {}() with arguments: {}", method.getName(), args);
                    }

                    if (inspectingBeans.get(beanName).equals("debug")) {
                        logger.debug("Was called method: {}() with arguments: {}", method.getName(), args);
                    }

                    return method.invoke(originalBean, args);
                }
            };

            bean = Proxy.newProxyInstance(
                    bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(),
                    handler);

        }

        return bean;
    }

}
// END
