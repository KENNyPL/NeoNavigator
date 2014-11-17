package pl.cydo.neo.navigator.web.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.cydo.neo.navigator.web.controller.rest.data.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/test")
public class GreetingController {

    private static final String template = "--Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @ResponseBody
    @RequestMapping("/greeting2")
    public Greeting greeting2(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
