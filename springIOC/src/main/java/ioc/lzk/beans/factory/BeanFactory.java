package ioc.lzk.beans.factory;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description Bean工厂接口
 * @Date 21:12 2022/3/19
 **/
public interface BeanFactory {
    //根据名称获取bean对象
    Object getBean(String name) throws Exception;
    //根据名称获取bean对象，并进行类型转换
    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
