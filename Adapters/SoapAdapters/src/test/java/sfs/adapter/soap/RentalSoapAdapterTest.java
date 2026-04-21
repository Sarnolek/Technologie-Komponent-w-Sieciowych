package sfs.adapter.soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sfs.ports.api.RentalService;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalSoapAdapterTest {

    @Mock
    private RentalService rentalService;

    private Server server;
    private TestRentalSoapClient soapClient;

    @WebService(targetNamespace = "http://soap.adapter.sfs/")
    public interface TestRentalSoapClient {
        @WebMethod
        void deleteRental(String id) throws Exception;
    }

    @BeforeEach
    void setUp() {
        RentalSoapAdapter adapter = new RentalSoapAdapter(rentalService);
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(RentalSoapAdapter.class);
        factory.setAddress("local://rentals");
        factory.setServiceBean(adapter);
        server = factory.create();

        JaxWsProxyFactoryBean clientFactory = new JaxWsProxyFactoryBean();
        clientFactory.setServiceClass(TestRentalSoapClient.class);
        clientFactory.setAddress("local://rentals");
        soapClient = (TestRentalSoapClient) clientFactory.create();
    }

    @AfterEach
    void tearDown() {
        if (server != null) {
            server.destroy();
        }
    }

    @Test
    void shouldDeleteRentalViaSoapClient() throws Exception {
        soapClient.deleteRental("123");

        verify(rentalService, times(1)).deleteRental("123");
    }
}