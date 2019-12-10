# 序列化与反序列化定制
*类必须实现以下2个方法：*
- writeObject(java.io.ObjectOutputStream s)
- readObject(java.io.ObjectInputStream s)
```
        private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
            s.defaultWriteObject();
            s.writeObject(name1);
            s.writeObject(name2);
            System.out.println("序列化对象");
        }
        private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            name1=String.valueOf(s.readObject())+"1";
            name2=String.valueOf(s.readObject())+"1";
            System.out.println("反序列化对象");
        }
```