package com.tw.bootcamp.models;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TodoList {
    private ArrayList<TodoItem> todos;
    private int index = 1;

    public TodoList() {
        todos = new ArrayList<>();
    }

    public List<TodoItem> getTodos() {
        return todos;
    }

    public TodoItem addItem(String title) {
        TodoItem newTodo = new TodoItem(getIndex(), title);
        todos.add(newTodo);
        indexIncrement();
        return newTodo;
    }

    private int getIndex() {
        return index;
    }
    private void indexIncrement() { index += 1; }

    public TodoItem updateItem(int itemId, String title) {
        TodoItem theOne = getItemById(itemId);
        if (theOne == null) return null;
        theOne.setTitle(title);
        theOne.setUpdatedDatetime();
        return theOne;
    }

    public TodoItem getItemById(int id) {
        Map<Integer, TodoItem> todosById = transformTodos();
        return todosById.get(id);
    }

    private Map<Integer, TodoItem> transformTodos() {
        Map<Integer, TodoItem> transformed = new HashMap<>();
        for (TodoItem item : todos) {
            transformed.put(item.getId(), item);
        }
        return transformed;
    }

    public boolean deleteItem(int itemId) {
        return getItemById(itemId) != null && todos.remove(getItemById(itemId));
    }
}
