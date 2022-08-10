package za.co.staffschedule.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BaseController {

    public <T> ResponseEntity<T> okResponse(T body) {
        return ResponseEntity.ok(body);
    }

    public <T> ResponseEntity<T> createdResponse(String path, Object id, T object) {
        return ResponseEntity.created(getURL(path, id)).body(object);
    }

    private URI getURL(String path, Object id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
    }

}
