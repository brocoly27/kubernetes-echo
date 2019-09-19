package com.mesofi.kubernetestraining.rest;

import com.mesofi.kubernetestraining.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EchoController {
    private static final String template = "Hello, %s!";
    private final int MAX_REQUESTS = 5;
    private int requestCount = 0;
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/info")
    public String getInfo() {
        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
            String response = "Your current IP address : " + ip + "\n" + "Your current Hostname : " + hostname;
            return response;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/")
    public String health() {
        if (requestCount == MAX_REQUESTS) {
            throw new IllegalStateException("This is my error");
        }
        requestCount++;
        return "OK - Request No: " + requestCount;
    }
}
