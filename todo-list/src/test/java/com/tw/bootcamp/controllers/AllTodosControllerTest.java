package com.tw.bootcamp.controllers;

import com.tw.bootcamp.models.TodoItem;
import com.tw.bootcamp.models.TodoList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_EMPTY_TITLE;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AllTodosController.class)
public class AllTodosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoList todoList;

    @Test
    public void should_return_all_todos_when_exist() throws Exception {
        given(todoList.getTodos()).willReturn(Arrays.asList(new TodoItem(1, "1"), new TodoItem(2, "2")));

        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_return_empty_array_when_empty_list() throws Exception {
        given(todoList.getTodos()).willReturn(new ArrayList<TodoItem>());
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$", hasSize(0))));
    }

    @Test
    public void should_return_new_item_when_title_passed() throws Exception {
        String newTodoTitle = "hello new one";
        given(todoList.addItem(newTodoTitle)).willReturn(new TodoItem(1, newTodoTitle));

        mockMvc.perform(post("/todos")
                .param("title", newTodoTitle)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(newTodoTitle)));

        verify(todoList, times(1)).addItem(newTodoTitle);
    }

    @Test
    public void should_return_error_message_when_empty_title() throws Exception {
        mockMvc.perform(post("/todos")
                .param("title", "")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect((status().isBadRequest()))
                .andExpect(jsonPath("$.message", is(ERRMSG_EMPTY_TITLE)));

    }

    @Test
    public void should_return_error_message_when_no_title() throws Exception {
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect((status().isBadRequest()))
                .andExpect(jsonPath("$.message", is(ERRMSG_EMPTY_TITLE)));

    }

}