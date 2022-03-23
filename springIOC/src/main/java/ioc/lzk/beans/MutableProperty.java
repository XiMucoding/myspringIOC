package ioc.lzk.beans;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description 一个bean标签可以有多个property子标签，所以再定义一个MutableProperty类，用来存储并管理多个Property对象。
 * @Date 16:31 2022/3/19
 **/
public class MutableProperty implements Iterable<Property> {
    //存储bean下的所有property
    private final Map<String,Property> propertyMap;
    //构造方法
    public MutableProperty(){
        this.propertyMap=new ConcurrentHashMap<String, Property>();
    }
    public MutableProperty(ConcurrentHashMap<String, Property> map){
        this.propertyMap=(map!=null?map:new ConcurrentHashMap<String, Property>());
    }

    //返回迭代器
    public Iterator<Property> iterator() {
        return this.propertyMap.values().iterator();
    }

    //将propertyMap的value值取出组成数组
    public Property[] propertyMapToArray(){
        return this.propertyMap.values().toArray(new Property[0]);
    }
    //根据name获取Property，无则返回null
    public Property getProperty(String name){
        return this.propertyMap.get(name);
    }
    //isEmpty()
    public  boolean isEmpty(){
        return this.propertyMap.isEmpty();
    }
    //添加property,返回this用于链式编程
    public  MutableProperty addProperty(Property property){
        if (propertyMap.containsKey(property.getName())){
            //该key已经注册，则更新value (key,value)
            propertyMap.remove(property.getName());
            propertyMap.put(property.getName(),property);
            return this;
        }
        ////该key未注册
        this.propertyMap.put(property.getName(),property);
        return this;
    }
    //是否存在该key
    public boolean containsKey(String name){
        return propertyMap.containsKey(name);
    }
}
