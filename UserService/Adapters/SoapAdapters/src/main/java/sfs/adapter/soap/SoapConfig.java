package sfs.adapter.soap;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
public class SoapConfig {

    private final Bus bus;
    private final UserSoapAdapter userSoapAdapter;

    public SoapConfig(Bus bus, UserSoapAdapter userSoapAdapter) {
        this.bus = bus;
        this.userSoapAdapter = userSoapAdapter;
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userSoapAdapter);
        endpoint.publish("/users"); // Usługa SOAP będzie dostępna pod adresem /services/users
        return endpoint;
    }
}