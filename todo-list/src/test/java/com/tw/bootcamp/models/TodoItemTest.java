package com.tw.bootcamp.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TodoItemTest {

    @Test
    public void should_create_todo_item_based_on_title() {
        String title = "Test first todo model";
        int id = 1;
        TodoItem todo = new TodoItem(id, title);

        assertEquals(todo.getTitle(), title);
        assertEquals(todo.getId(), id);
        assertFalse(todo.getCompleted());
        assertNotNull(todo.getCreatedDatetime());
        assertNotNull(todo.getUpdatedDatetime());
    }

    @Test
    public void should_return_updated_item() throws Exception {
        String title = "Test first todo model";
        int id = 1;
        TodoItem todo = new TodoItem(id, title);
        assertEquals(todo.getTitle(), title);

        Thread.sleep(1000);

        String updatedTitle = "update title for me";
        todo.setTitle(updatedTitle);
        todo.setUpdatedDatetime();
        assertEquals(todo.getTitle(), updatedTitle);
        assertNotEquals(todo.getCreatedDatetime(), todo.getUpdatedDatetime());
    }
}
