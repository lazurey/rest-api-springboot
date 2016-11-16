package com.tw.bootcamp.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TodoListTest {

    private TodoList todoList = new TodoList();

    @Before
    public void setUp() {
        todoList.addItem("First item");
        todoList.addItem("Second item");
    }

    @Test
    public void should_return_new_iten_when_added() {
        TodoItem todoItem = todoList.addItem("3rd item");
        assertEquals(todoItem.getId(), 3);
        assertEquals(todoItem.getTitle(), "3rd item");
        assertFalse(todoItem.getCompleted());
    }

    @Test
    public void should_return_all_items() {
        assertEquals(todoList.getTodos().size(), 2);
    }

    @Test
    public void should_return_false_if_item_not_found() {
        assertFalse(todoList.deleteItem(100));
    }

    @Test
    public void should_delete_item_if_exists() {
        assertTrue(todoList.deleteItem(1));
    }

    @Test
    public void should_return_updated_item_if_exists() {
        String updatedTitle = "update this title";
        TodoItem item = todoList.updateItem(1, updatedTitle);
        assertNotNull(item);
        assertEquals(item.getTitle(), updatedTitle);
    }

    @Test
    public void should_return_null_when_updated_item_not_found() {
        TodoItem item = todoList.updateItem(100, "no");
        assertNull(item);
    }

    @Test
    public void should_return_item_if_exists() {
        assertNotNull(todoList.getItemById(1));
    }

    @Test
    public void should_return_null_if_not_exists() {
        assertNull(todoList.getItemById(100));
    }
}
