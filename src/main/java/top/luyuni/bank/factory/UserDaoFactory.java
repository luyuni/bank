package top.luyuni.bank.factory;

import top.luyuni.bank.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class UserDaoFactory {
    private static final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    public static <T> T getSingleton(String beanName){
        return null;
    }

    /**
     *
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> requiredType) {
        String type = requiredType.toString();
        int idx = type.lastIndexOf(".");
        String beanName = type.substring(idx + 1);
        return (T) getBean(beanName);
    }
    public static Object getBean(String beanName){
        Object ret = null;
        if(! singletonObjects.containsKey(beanName)){
            InputStream inputStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("classInfo.properties");
            Properties p = new Properties();
            Class cls = null;
            try {
                p.load(inputStream);
                String clazz = p.getProperty(beanName);
                System.out.println(clazz);
                cls = Class.forName(clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (singletonObjects){
                try {
                    ret = cls.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                singletonObjects.put(beanName, ret);
            }
        }else {
            ret = singletonObjects.get(beanName);
        }
        return ret;
    }
}
