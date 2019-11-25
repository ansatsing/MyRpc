package an.antsing.spi.adaptive.inter;

import an.antsing.spi.adaptive.entiy.Car;
import com.alibaba.dubbo.common.URL;

public interface CarMaker {
    Car makeCar(URL url);
}
