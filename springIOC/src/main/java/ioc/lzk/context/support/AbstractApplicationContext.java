package ioc.lzk.context.support;

import ioc.lzk.beans.Bean;
import ioc.lzk.beans.factory.support.BeanReader;
import ioc.lzk.beans.factory.support.BeanRegistry;
import ioc.lzk.beans.factory.xml.XmlBeanReader;
import ioc.lzk.context.ApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description <类说明>
 * @Date 21:27 2022/3/19
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {
    //xml解析类(交由子类决定使用的实现类)
    protected BeanReader beanReader;
    //xml的路径(交由子类传递)
    protected String configLocation;
    //存储bean对象对应的对象的容器 [bean.name,bean的ref对应的对象或是value]
    protected Map<String, Object> single=new ConcurrentHashMap<String, Object>();

    public void refresh()throws Exception{
        //加载Bean
        beanReader.loadBeans(configLocation);
        //初始化Bean
        beanInitialization();
    }

    //初始化Bean
    private void beanInitialization()throws Exception{
        //获取注册表
        BeanRegistry beanRegistry=beanReader.getBeanRegistry();
        String[] beanNames=beanRegistry.getALLBeanName();
        for (String beanName : beanNames) {
            Bean bean = beanRegistry.getBean(beanName);
            getBean(beanName);
        }
    }
}
