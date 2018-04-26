
package ua.tqs.hw1;

import javax.json.JsonObject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Pedro Nunes, nmec 59542
 */
public class ConverterAPITest {

    private javax.ws.rs.client.Client client;
    private javax.ws.rs.client.WebTarget target;
    
    @Before
    public void initClient() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://www.apilayer.net/api/live?access_key=4a61b8f9cbf465a7c1a69b102ac46a22");
    }

    @Test
    public void fetch() {
    Response response = target.request(MediaType.APPLICATION_JSON).get();
    assertThat(response.getStatus(), CoreMatchers.is(200));
    
    JsonObject info = response.readEntity(JsonObject.class);        
    assertNotEquals( 0, info.size());       
    }
    
    @After
    public void cleanup() {
        client.close();
    }

}