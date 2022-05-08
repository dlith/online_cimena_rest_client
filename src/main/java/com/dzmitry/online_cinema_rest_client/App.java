package com.dzmitry.online_cinema_rest_client;

import com.dzmitry.online_cinema_rest_client.component.Communication;
import com.dzmitry.online_cinema_rest_client.configuration.MyConfig;
import com.dzmitry.online_cinema_rest_client.entity.Actor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {

    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);
        List<Actor> actors = communication.getAllActors();
        for(Actor actor:actors){
            System.out.println(actor);
        }
    }
}
