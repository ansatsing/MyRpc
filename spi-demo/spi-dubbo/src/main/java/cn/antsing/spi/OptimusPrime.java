package cn.antsing.spi;

import cn.antsing.spi.api.Robot;

public class OptimusPrime implements Robot {
    static {
        System.out.println("OptimusPrime static code block");
    }

    public OptimusPrime() {
        System.out.println("OptimusPrime construction....");
    }
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
