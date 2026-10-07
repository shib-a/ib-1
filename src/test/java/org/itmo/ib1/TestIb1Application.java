package org.itmo.ib1;

import org.springframework.boot.SpringApplication;

public class TestIb1Application {

    public static void main(String[] args) {
        SpringApplication.from(Ib1Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
