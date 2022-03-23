package ioc.lzk.context.support;

import ioc.lzk.beans.Bean;
import ioc.lzk.beans.MutableProperty;
import ioc.lzk.beans.Property;
import ioc.lzk.beans.factory.support.BeanRegistry;
import ioc.lzk.beans.factory.xml.XmlBeanReader;

import java.lang.reflect.Method;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description 该类主要是加载类路径下的配置文件，并进行bean对象的创建
 * @Date 21:43 2022/3/19
 **/
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

    //解析xml并加载bean
    public ClassPathXmlApplicationContext(String configLocation) {
        this.configLocation=configLocation;
        this.beanReader=new XmlBeanReader();
        try {
            this.refresh();
        } catch (Exception e) {
        }
    }

    //根据bean id获取bean对象对应的对象 若还没创建，初始化指bean对象向的对象 若注册表中都没该bean则是null
    public Object getBean(String name) throws Exception {
        Object o = single.get(name);
        //如果bean容器中存在该对象，返回即可，否则需要自行创建
        if (o!=null){
            return o;
        }
        //获得bean对象
        BeanRegistry beanRegistry=beanReader.getBeanRegistry();
        Bean bean = beanRegistry.getBean(name);
        //注册表中都无该对象，说明没有注册，返回null 反之，有注册，但是还没有初始化bean指向的对象，需要通过反射创建对象
        if(bean==null){
            return null;
        }
        //获取全限定名
        String className = bean.getClassName();
        Class<?> clazz = Class.forName(className);
        //反射创建对象
        Object beanObj = clazz.newInstance();
        //将bean下面的property进行依赖注入
        MutableProperty mutableProperty=bean.getMutableProperty();
        for (Property property : mutableProperty) {
            //获得property的属性值
            String propertyName = property.getName();
            String propertyValue = property.getValue();
            String propertyRef = property.getRef();
            //要反射操作的方法名  propertyName="userDao" -> setUserDao(拼接)
            String methodName="set"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
            //propertyValue propertyRef只有一个不为空
            if (propertyRef!=null && !"".equals(propertyRef)){
                //依赖注入指向的bean <property name="userDao" ref="userDao"></property>
                Object bean1 = getBean(propertyRef);
                //遍历全类限定名的类中的方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(method.getName().equals(methodName)){
                        method.invoke(beanObj,bean1);
                    }
                }
            }
            //<property name="password" value="12345"></property>
            if (propertyValue!=null && !"".equals(propertyValue)){
                Method method = clazz.getMethod(methodName, String.class);
                method.invoke(beanObj,propertyValue);
            }
        }
        //初始化指bean对象向的对象
        single.put(name,beanObj);
        return null;
    }

    public <T> T getBean(String name, Class<? extends T> clazz) throws Exception {
        Object bean = getBean(name);
        if(bean != null) {
            return clazz.cast(bean);
        }
        return null;
    }
}
