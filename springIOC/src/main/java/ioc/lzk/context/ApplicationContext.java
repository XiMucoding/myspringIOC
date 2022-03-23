package ioc.lzk.context;

import ioc.lzk.beans.factory.BeanFactory;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description ApplicationContext
 * @Date 21:19 2022/3/19
 **/
public interface ApplicationContext extends BeanFactory {
    //进行配置文件加载并进行对象创建
    void refresh() throws IllegalStateException, Exception;
}
