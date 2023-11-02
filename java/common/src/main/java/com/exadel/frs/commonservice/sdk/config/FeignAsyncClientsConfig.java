//package com.exadel.frs.commonservice.sdk.config;
//
//
//import com.exadel.frs.commonservice.sdk.faces.feign.FacesFeignClient;
//import com.exadel.frs.commonservice.sdk.storage.feign.StorageFeignClient;
//import com.exadel.frs.commonservice.system.global.EnvironmentProperties;
//import feign.Feign;
//import feign.Request;
//import feign.Retryer;
//import feign.form.spring.SpringFormEncoder;
//import feign.jackson.JacksonDecoder;
//import feign.jackson.JacksonEncoder;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.scheduling.annotation.EnableAsync;
//
//import static com.exadel.frs.commonservice.system.global.EnvironmentProperties.ServerType.PYTHON;
//import static com.exadel.frs.commonservice.system.global.EnvironmentProperties.ServerType.STORAGE;
//import static com.zaxxer.hikari.util.ClockSource.toMillis;
//import static feign.Logger.Level.FULL;
//import static java.util.concurrent.TimeUnit.MILLISECONDS;
//
//@Configuration
//@RequiredArgsConstructor
//public class FeignAsyncClientsConfig
//{
//    @Value("${app.feign.storage.connect-timeout}")
//    private int storageConnectTimeout;
//
//    @Value("${app.feign.storage.read-timeout}")
//    private int storageReadTimeout;
//
//    @Value("${app.feign.storage.retryer.max-attempts}")
//    private int storageRetryerMaxAttempts;
//
//    private final EnvironmentProperties properties;
//
//    // python java
//    @Bean
//    public StorageFeignClient storageFeignClient  () {
//        return Feign.builder()
//                .encoder(new SpringFormEncoder(new JacksonEncoder()))
//                .decoder(new JacksonDecoder())
//                .logLevel(FULL)
//                .retryer(storageFeignRetryer())
//                .options(new Request.Options(storageConnectTimeout, MILLISECONDS, storageReadTimeout, MILLISECONDS, true))
//                .target(StorageFeignClient.class, properties.getServers().get(STORAGE).getUrl());
//    }
//
//    @Bean
//    public Retryer storageFeignRetryer()
//    {
//        return new Retryer.Default(100, toMillis(1), storageRetryerMaxAttempts);
//    }
//}
