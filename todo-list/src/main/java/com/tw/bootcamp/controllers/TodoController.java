package com.tw.bootcamp.controllers;

import com.tw.bootcamp.models.TodoItem;
import com.tw.bootcamp.models.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_EMPTY_TITLE;
import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_ITEM_NOT_FOUND;
import static com.tw.bootcamp.constants.SystemMessages.INFOMSG_ITEM_REMOVED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/todos/{itemId}")
public class TodoController {
    @Autowired
    private TodoList todos;

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity updateTodo(@PathVariable int itemId, @RequestParam HashMap<String, String> form) {

        if (!form.containsKey("title") || form.get("title").isEmpty()) {
            Map<String, String> result = new HashMap<>();
            result.put("message", ERRMSG_EMPTY_TITLE);
            return new ResponseEntity<>(result, BAD_REQUEST);
        }
        String title = form.get("title");

        TodoItem updated = todos.updateItem(itemId, title);

        if (updated == null) {
            Map<String, String> result = new HashMap<>();
            result.put("message", ERRMSG_ITEM_NOT_FOUND);
            return new ResponseEntity<>(result, NOT_FOUND);
        }

        return new ResponseEntity<>(updated, OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteTodo(@PathVariable int itemId) {
        boolean removed = todos.deleteItem(itemId);
        Map<String, String> result = new HashMap<>();
        result.put("message", removed ? INFOMSG_ITEM_REMOVED : ERRMSG_ITEM_NOT_FOUND);
        return new ResponseEntity<>(result, removed ? OK : NOT_FOUND);
    }
}
