package edu.ksmith10.greetingcloudapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;



/**
 * PURPOSE: A controller class that's managed by Spring and will be picked up by
 * component scanning. RestController annotation tells Spring to make it a
 * REST-endpoint (basically a URL)
 *
 * Learn the mechanics of GET, POST, PUT AND DELETE
 *
 * *** GET: Retrieve information. GET requests must be safe and idempotent, meaning regardless of
 *          how many times it repeats with the same parameters, the results are the same
 * *** POST: Request that the resource at the URI do something with the provided entity.
 *           Often POST is used to create a new entity, but it can also be used to update an entity.
 * *** PUT: Store an entity at a URI. PUT can create a new entity or update an existing one.
 *          A PUT request is idempotent. Idempotency is the main difference between the expectations of PUT
 *          versus a POST request.
 * *** DELETE: Request that a resource be removed; however, the resource does not have to be removed
 *             immediately. It could be an asynchronous or long-running request.
 *
 * NOTE: the return value will become the body of the REST response.
 */
@RestController
public class HelloController {

    private int count = 1;

    @Autowired
    private GreetingDao greetingDao;

    @RequestMapping(value = "/createGreeting", method = RequestMethod.POST)
    public Greeting createGreeting(@RequestBody Greeting g) throws IOException {
        greetingDao.create(g);
        return g;
    }

    @RequestMapping(value = "/getGreeting/{id}", method = RequestMethod.GET)
    public Greeting getGreeting(@PathVariable("id") int id) throws IOException {
        return greetingDao.getById(id);
    }


    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public Greeting greeting() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("./message.txt"), Greeting.class);
    }

    @RequestMapping(value = "/createGreeting1", method = RequestMethod.POST)
    public Greeting createGreeting1(@RequestBody String content) throws IOException {
        Greeting newGreeting = new Greeting(count++, content);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("./message.txt"), newGreeting);
        return newGreeting;
    }

    @RequestMapping(value = "/createGreeting3", method = RequestMethod.POST)
    public ResponseEntity<Void> createGreeting3(@RequestBody String content)
            throws IOException, URISyntaxException {
        //create new greeting...

        //should not be hardcoded! Only an example...
        final URI location = new URI("http://localhost:8080/greeting/123");

        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        final ResponseEntity<Void> entity = new ResponseEntity<Void>(headers,
                HttpStatus.CREATED);
        return entity;
    }

    @RequestMapping(value = "/updateGreeting", method = RequestMethod.PUT)
    public Greeting updateGreeting(@RequestBody String newMessage) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String message = FileUtils.readFileToString(new File("./message.txt"), StandardCharsets.UTF_8.name());

        Greeting greeting = mapper.readValue(message, Greeting.class);

        greeting.setContent(newMessage);

        mapper.writeValue(new File("./message.txt"), greeting);

        return greeting;

    }

}

