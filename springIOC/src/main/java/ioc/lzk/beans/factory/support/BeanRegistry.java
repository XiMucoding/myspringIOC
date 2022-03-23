package ioc.lzk.beans.factory.support;

import ioc.lzk.beans.Bean;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description bean注册表的接口
 * @Date 20:18 2022/3/19
 **/
public interface BeanRegistry {
    //注册Bean对象到注册表中
    void registerBean(String beanName, Bean bean);
    //从注册表中删除指定名称的Bean对象
    void removeBean(String beanName);
    //根据名称从注册表中获取Bean对象
    Bean getBean(String beanName);
    //判断注册表中是否包含指定名称的Bean对象
    boolean containsBean(String beanName);
    //获取注册表中Bean对象的个数
    int getSize();
    //获取注册表中所有的Bean的名称
    String[] getALLBeanName();
}
