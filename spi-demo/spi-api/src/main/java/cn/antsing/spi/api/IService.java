package cn.antsing.spi.api;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface IService {
    void insertData(String data);
}
