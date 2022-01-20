package com.kakaocert.springboot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.kakaocert.api.KakaocertService;
import com.kakaocert.api.KakaocertServiceImp;
import com.kakaocert.springboot.autoconfigure.properties.kakaocertServiceProperties;

@Configuration
@ConditionalOnClass(KakaocertService.class)
@EnableConfigurationProperties({ kakaocertServiceProperties.class})
public class KakaocertAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(KakaocertAutoConfiguration.class);
    
    @Autowired
    private kakaocertServiceProperties kakaocertServiceProperties;
    
    @Lazy
    @Bean(name = "KakaocertService")
    @ConditionalOnMissingBean
    public KakaocertService KakaocertServiceConfig() {

        KakaocertServiceImp kakaocertServiceImp = new KakaocertServiceImp();

        kakaocertServiceImp.setLinkID(kakaocertServiceProperties.getLinkID());
        kakaocertServiceImp.setSecretKey(kakaocertServiceProperties.getSecretKey());
        kakaocertServiceImp.setUseStaticIP(kakaocertServiceProperties.isUseStaticIP());
        kakaocertServiceImp.setIPRestrictOnOff(kakaocertServiceProperties.isIsIPRestrictOnOff());

        logger.debug("KakaocertService Initialized");

        return kakaocertServiceImp;
    }
}
