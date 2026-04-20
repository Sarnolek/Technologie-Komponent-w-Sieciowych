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
    private final SportsFacilitySoapAdapter sportsFacilitySoapAdapter;
    private final RentalSoapAdapter rentalSoapAdapter;

    public SoapConfig(Bus bus, UserSoapAdapter userSoapAdapter, SportsFacilitySoapAdapter sportsFacilitySoapAdapter, RentalSoapAdapter rentalSoapAdapter) {
        this.bus = bus;
        this.userSoapAdapter = userSoapAdapter;
        this.sportsFacilitySoapAdapter = sportsFacilitySoapAdapter;
        this.rentalSoapAdapter = rentalSoapAdapter;
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, userSoapAdapter);
        endpoint.publish("/users"); // Usługa SOAP będzie dostępna pod adresem /services/users
        return endpoint;
    }

    @Bean
    public Endpoint sportsFacilityEndpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, sportsFacilitySoapAdapter);
        endpoint.publish("/facilities");
        return endpoint;
    }

    @Bean
    public Endpoint rentalEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, rentalSoapAdapter);
        endpoint.publish("/rentals");
        return endpoint;
    }

}