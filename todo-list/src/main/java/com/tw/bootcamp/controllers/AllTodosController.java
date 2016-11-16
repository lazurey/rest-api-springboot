package com.tw.bootcamp.controllers;

import com.tw.bootcamp.models.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_EMPTY_TITLE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/todos")
public class AllTodosController {

    @Autowired
    private TodoList todos;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTodos() {
        return new ResponseEntity<>(todos.getTodos(), OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTodo(@RequestParam HashMap<String, String> form) {
        if (form.isEmpty()
                || !form.containsKey("title")
                || form.get("title").isEmpty()) {
            Map<String, String> result = new HashMap<>();
            result.put("message", ERRMSG_EMPTY_TITLE);
            return new ResponseEntity<>(result, BAD_REQUEST);
        }
        return new ResponseEntity<>(todos.addItem(form.get("title")), OK);
    }

}
