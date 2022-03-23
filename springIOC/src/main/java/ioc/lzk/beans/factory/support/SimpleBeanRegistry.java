package ioc.lzk.beans.factory.support;

import ioc.lzk.beans.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description BeanRegistry的子实现类
 * @Date 20:25 2022/3/19
 **/
public class SimpleBeanRegistry implements BeanRegistry{

    //注册表（记录所有的bean）
    private Map<String,Bean>beanRegistryMap=new ConcurrentHashMap<String, Bean>();

    public void registerBean(String beanName, Bean bean) {
        beanRegistryMap.put(beanName,bean);
    }

    public void removeBean(String beanName) {
        beanRegistryMap.remove(beanName);
    }

    public Bean getBean(String beanName) {
        if(beanRegistryMap.containsKey(beanName)){
            return beanRegistryMap.get(beanName);
        }
        return null;
    }

    public boolean containsBean(String beanName) {
        return beanRegistryMap.containsKey(beanName);
    }

    public int getSize() {
        return beanRegistryMap.size();
    }

    public String[] getALLBeanName() {
        return beanRegistryMap.keySet().toArray(new String[1]);
    }
}
