package cn.antsing.rpc.schema;

/**
 * 服务消费者标签类
 *  <syq:consumer id="userService" interfac="cn.antsing.service.IUserService" address="192.168.0.9:8089,192.168.0.9:8088"/>
 */
public class Consumer {
    private String id;
    private String interfac;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
