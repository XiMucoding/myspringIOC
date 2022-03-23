package ioc.lzk.beans.factory.xml;

import ioc.lzk.beans.Bean;
import ioc.lzk.beans.MutableProperty;
import ioc.lzk.beans.Property;
import ioc.lzk.beans.factory.support.BeanReader;
import ioc.lzk.beans.factory.support.BeanRegistry;
import ioc.lzk.beans.factory.support.SimpleBeanRegistry;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description 读取xml文件并加载到bean注册表
 * @Date 20:39 2022/3/19
 **/
public class XmlBeanReader implements BeanReader {
    //bean注册表
    private BeanRegistry beanRegistry;

    public XmlBeanReader(){
        beanRegistry=new SimpleBeanRegistry();
    }

    public BeanRegistry getBeanRegistry() {
        return beanRegistry;
    }

    //加载xml
    public void loadBeans(String configLocation) throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation);
        //使用dom4j对xml文件进行解析
        SAXReader reader = new SAXReader();
        Document document = reader.read(is);
        Element rootElement = document.getRootElement();
        //解析bean标签
        parseBean(rootElement);
    }

    //解析bean标签
    public void parseBean(Element rootElement){
        List<Element>elementList=rootElement.elements();
        for (Element element1 : elementList) {
            //获取id class 封装成bean
            String id=element1.attributeValue("id");
            String className=element1.attributeValue("class");
            Bean bean=new Bean(id,className);

            //封装bean标签下的property
            List<Element> list=element1.elements("property");
            MutableProperty mutableProperty=new MutableProperty();
            for (Element element2 : list) {
                //解析bean标签下的property
                String name=element2.attributeValue("name");
                String ref=element2.attributeValue("ref");
                String value=element2.attributeValue("value");
                Property property=new Property(name,ref,value);
                mutableProperty.addProperty(property);
            }
            //添加bean标签下的property集合
            bean.setMutableProperty(mutableProperty);

            //给注册表注册bean的信息
            beanRegistry.registerBean(id,bean);
        }
    }
}
