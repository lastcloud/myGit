package index.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class IndexService{

    private final ServiceProperties serviceProperties;

    public IndexService(ServiceProperties serviceProperties){
        this.serviceProperties = serviceProperties;
    }

    public String message(){
        return this.serviceProperties.getMessage();
    }
}