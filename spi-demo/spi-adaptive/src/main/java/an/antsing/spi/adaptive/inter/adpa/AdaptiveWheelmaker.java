package an.antsing.spi.adaptive.inter.adpa;

import an.antsing.spi.adaptive.entiy.Wheel;
import an.antsing.spi.adaptive.inter.WheelMaker;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

import com.alibaba.dubbo.common.URL;

public class AdaptiveWheelmaker implements WheelMaker {
    @Override
    public Wheel makeWheel(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url == null");
        }

        // 1.从 URL 中获取 WheelMaker 名称
        String wheelMakerName = url.getParameter("Wheel.maker");
        if (wheelMakerName == null) {
            throw new IllegalArgumentException("wheelMakerName == null");
        }

        // 2.通过 SPI 加载具体的 WheelMaker
        WheelMaker wheelMaker = ExtensionLoader
                .getExtensionLoader(WheelMaker.class).getExtension(wheelMakerName);

        // 3.调用目标方法
        return wheelMaker.makeWheel(url);
    }
}
