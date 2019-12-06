package cn.antsing.rpc.schema;

/**
 * 服务生产者标签类
 *  <syq:producer interfac="cn.antsing.service.IUserService" ref="userService" />
 */
public class Producer {
    private String interfac;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterfac() {
        return interfac;
    }

    public void setInterfac(String interfac) {
        this.interfac = interfac;
    }
}
