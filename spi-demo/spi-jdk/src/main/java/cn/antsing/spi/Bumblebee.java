package cn.antsing.spi;

import cn.antsing.spi.api.Robot;

public class Bumblebee implements Robot {
    static {
        System.out.println("Bumblebee static code block");
    }

    public Bumblebee() {
        System.out.println("Bumblebee construction....");
    }

    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
