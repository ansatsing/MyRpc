package an.antsing.spi.adaptive.inter;

import an.antsing.spi.adaptive.entiy.Wheel;

import com.alibaba.dubbo.common.URL;

public interface WheelMaker {
    Wheel makeWheel(URL url);
}
