package com.dzmitry.online_cinema_rest_client.component;

import com.dzmitry.online_cinema_rest_client.entity.Actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:3333/springboot-rest/api/actors";

    public List<Actor> getAllActors(){
        ResponseEntity<List<Actor>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Actor>>() {});

        return responseEntity.getBody();
    }

    public Actor getActor(int id){
        return restTemplate.getForObject(URL + "/" + id, Actor.class);
    }

    public void saveActor(Actor actor){
        int id = actor.getId();
        if(id == 0){
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(URL, actor, String.class);
            System.out.println("New actor was added to DB");
            System.out.println(responseEntity.getBody());
        }else {
            restTemplate.put(URL, actor);
            System.out.println("Actor with ID = " + id + " was updated");
        }
    }

    public void deleteActor(int id){
        restTemplate.delete(URL + "/" + id);
        System.out.println("Actor with ID = " + id + " was deleted from DB");
    }

}
