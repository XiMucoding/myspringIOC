package ioc.lzk.beans;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description bean的封装类
 * @Date 17:12 2022/3/19
 **/
public class Bean {
    //<bean id="userDao" class="com.lzk.dao.Imp.UserDaoImp"></bean>
    //bean的id
    private String id;
    //全类限定名（用于找到类，用反射创建对象）
    private String className;
    private MutableProperty mutableProperty;
    public Bean() {
    }

    public Bean(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MutableProperty getMutableProperty() {
        return mutableProperty;
    }

    public void setMutableProperty(MutableProperty mutableProperty) {
        this.mutableProperty = mutableProperty;
    }
}
