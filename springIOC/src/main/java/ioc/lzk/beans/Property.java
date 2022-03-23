package ioc.lzk.beans;

/*
 * @Author lzk
 * @Email 1801290586@qq.com
 * @Description applicationContext.xml 中bean的下一级property的映射表
 * @Date 16:17 2022/3/19
 **/
public class Property {
    //依赖注册的变量名
    private String name;
    //注入的值是一个bean的话ref就指向bean的id [name=ref(指向另一个bean)]
    private String ref;
    //注入的值是字符串就将值赋给value  [name=value]
    private String value;

    public Property() {
    }

    public Property(String name, String ref, String value) {
        this.name = name;
        this.ref = ref;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
