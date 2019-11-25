package an.antsing.spi.adaptive.inter.impl;

import an.antsing.spi.adaptive.entiy.Car;
import an.antsing.spi.adaptive.entiy.RaceCar;
import an.antsing.spi.adaptive.entiy.Wheel;
import an.antsing.spi.adaptive.inter.CarMaker;
import an.antsing.spi.adaptive.inter.WheelMaker;
import com.alibaba.dubbo.common.URL;

public class RaceCarMaker implements CarMaker {
    WheelMaker wheelMaker;

    // 通过 setter 注入 AdaptiveWheelMaker
    public void setWheelMaker(WheelMaker wheelMaker) {
        this.wheelMaker = wheelMaker;
    }
    @Override
    public Car makeCar(URL url) {
        Wheel wheel = wheelMaker.makeWheel(url);
        return new RaceCar(wheel);
    }
}
