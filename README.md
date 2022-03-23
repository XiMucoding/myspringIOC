# springIOC（xml）

> 自定义的springIoc（配置文件版）

**使用**

1. 先build模块

![image-20220323233453125](E:\Typora（笔记）\images\image-20220323233453125.png)

2. 再引入包

![image-20220323233714072](E:\Typora（笔记）\images\image-20220323233714072.png)



**设计模式**

* 工厂模式。这个使用工厂模式 + 配置文件的方式。
* 单例模式。Spring IOC管理的bean对象都是单例的，此处的单例不是通过构造器进行单例的控制的，而是spring框架对每一个bean只创建了一个对象。
* 模板方法模式。AbstractApplicationContext类中的finishBeanInitialization()方法调用了子类的getBean()方法，因为getBean()的实现和环境息息相关。
* 迭代器模式。对于MutableProperty类定义使用到了迭代器模式，因为此类存储并管理PropertyValue对象，也属于一个容器，所以给该容器提供一个遍历方式。

spring框架其实使用到了很多设计模式，如AOP使用到了代理模式，选择JDK代理或者CGLIB代理使用到了策略模式，还有适配器模式，装饰者模式，观察者模式等。

![image-20220320151040352](C:\Users\21092\AppData\Roaming\Typora\typora-user-images\image-20220320151040352.png)

## bean相关的pojo类

![image-20220320144246853](C:\Users\21092\AppData\Roaming\Typora\typora-user-images\image-20220320144246853.png)

### 1.Property

> applicationContext.xml 中bean的下一级property的映射表

![image-20220319162854008](C:\Users\21092\AppData\Roaming\Typora\typora-user-images\image-20220319162854008.png)

### 2.MutableProperty

> 一个bean标签可以有多个property子标签，所以再定义一个MutableProperty类，用来存储并管理多个Property对象。

```java
class MutableProperty implements Iterable<Property> {
    //存储bean下的所有property
    private final Map<String,Property> propertyMap;
    //构造方法
    public MutableProperty(){
        this.propertyMap=new ConcurrentHashMap<String, Property>();
    }
    public MutableProperty(ConcurrentHashMap<String, Property> map){
        this.propertyMap=(map!=null?map:new ConcurrentHashMap<String, Property>());
    }
    /*其他函数...*/
}
```

### 3.Bean 

![image-20220319210720111](C:\Users\21092\AppData\Roaming\Typora\typora-user-images\image-20220319210720111.png)

## 注册表相关类

### 1.BeanRegistry接口

> BeanRegistry接口定义了注册表的相关操作

* 注册Bean对象到注册表中
* 从注册表中删除指定名称的Bean对象
* 根据名称从注册表中获取Bean对象
* 判断注册表中是否包含指定名称的Bean对象
* 获取注册表中Bean对象的个数
* 获取注册表中所有的Bean的名称

### 2.SimpleBeanRegistry类

> BeanRegistry实现接口，定义了Map集合作为注册表容器



## 解析器相关类

### 1.BeanReader接口

> BeanReader：解析配置文件并在注册表中注册bean的信息

* 获取注册表的功能，让外界可以通过该对象获取注册表对象
* 加载配置文件，并注册bean数据

### 2.XmlBeanReader类

> XmlBeanReader:专门用来解析xml配置文件的(使用dom4j解析，**记得导包**)



## IOC容器相关类

### 1.BeanFactory接口

> 在该接口中定义IOC容器的统一规范即获取bean对象。

```java
public interface BeanFactory {
	//根据bean对象的名称获取bean对象
    Object getBean(String name) throws Exception;
	//根据bean对象的名称获取bean对象，并进行类型转换
    <T> T getBean(String name, Class<? extends T> clazz) throws Exception;
}
```

###  2.ApplicationContext接口

该接口的所以的子实现类对bean对象的创建都是非延时的，所以在该接口中定义 `refresh()` 方法，该方法主要完成以下两个功能：

* 加载配置文件。
* 根据注册表中的Bean对象封装的数据进行bean对象的创建。

```java
public interface ApplicationContext extends BeanFactory {
	//进行配置文件加载并进行对象创建
    void refresh() throws IllegalStateException, Exception;
}
```

### 3.AbstractApplicationContext类

* 作为ApplicationContext接口的子类，所以该类也是非延时加载，所以需要在该类中定义一个Map集合，作为bean对象存储的容器。

* 声明BeanReader类型的变量，用来进行xml配置文件的解析，符合单一职责原则。

  BeanReader类型的对象创建交由子类实现，因为只有子类明确到底创建BeanReader哪儿个子实现类对象。

### 4.ClassPathXmlApplicationContext类

> 该类主要是加载类路径下的配置文件，并进行bean对象的创建，主要完成以下功能：

* 在构造方法中，创建BeanReader对象。
* 在构造方法中，调用refresh()方法，用于进行配置文件加载、创建bean对象并存储到容器中。
* 重写父接口中的getBean()方法，并实现依赖注入操作

