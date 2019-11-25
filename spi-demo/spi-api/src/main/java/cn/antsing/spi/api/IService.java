package cn.antsing.spi.api;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface IService {
    String insertData(String data, URL url);
}
