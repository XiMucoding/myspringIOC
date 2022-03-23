package ioc.lzk.beans.factory.support;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description 解析配置文件并在注册表中注册bean的信息
 * @Date 20:36 2022/3/19
 **/
public interface BeanReader {
    //获取注册表对象
    BeanRegistry getBeanRegistry();
    //加载配置文件并在注册表中进行注册
    void loadBeans(String configLocation) throws Exception;
}
