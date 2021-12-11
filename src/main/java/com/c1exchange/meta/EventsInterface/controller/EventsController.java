package com.c1exchange.meta.EventsInterface.controller;

import com.c1exchange.meta.EventsInterface.dto.Event;
import com.c1exchange.meta.EventsInterface.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/cdmp/v1")
public class EventsController {

    @Autowired
    private KafkaTemplate<String,Event> kafkaTemplate;

    @Autowired
    private KafkaAdmin kafkaAdmin;


    @PostMapping(path= "/events", consumes="application/json" )
    public ResponseEntity<ApiResponse> events(@RequestHeader(name="access-key", required = true) String accessKey,
                                              @RequestBody Map<String, Object> event) {
        try {
            if(accessKey.isEmpty() || accessKey.isBlank()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.UNAUTHORIZED,"Event Not Posted"),HttpStatus.UNAUTHORIZED);
            }
            Event transferredMessage = new Event();
            String eventType = event.get("type").toString();
            transferredMessage.setType(eventType);
            transferredMessage.setMessage("Empty"); // Not sure why message is used in Event..
            transferredMessage.setSource(event);
            kafkaTemplate.send(eventType,transferredMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,"Event Posted Successfully"),HttpStatus.OK);

    }


    @GetMapping(path = "/createTopic/{topic}")
    public ResponseEntity<ApiResponse> createTopic(@PathVariable("topic") String topic){
        if(topic == null || topic.isBlank() ){
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,"  No topic Created Successfully"),HttpStatus.BAD_REQUEST);
        }
        kafkaAdmin.createOrModifyTopics(TopicBuilder.name(topic)
                .partitions(3)
                .replicas(1)
                .build());
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,topic +"  Created Successfully"),HttpStatus.OK);
    }
}
