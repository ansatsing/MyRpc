## 配置示例
*生产者*
```
<syq:producer id="iUserService" interface="cn.antsing.service.IUserService"/>

```

*消费者*
```
<syq:consumer id="userService" interface="cn.antsing.service.IUserService" address="192.168.0.9:8089,192.168.0.9:8088"/>
```


