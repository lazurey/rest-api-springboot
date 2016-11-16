package com.tw.bootcamp.controllers;

import com.sun.media.jfxmedia.Media;
import com.tw.bootcamp.models.TodoItem;
import com.tw.bootcamp.models.TodoList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_EMPTY_TITLE;
import static com.tw.bootcamp.constants.SystemMessages.ERRMSG_ITEM_NOT_FOUND;
import static com.tw.bootcamp.constants.SystemMessages.INFOMSG_ITEM_REMOVED;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoList todoList;

    private static final MediaType JSON_PATCH = new MediaType("application", "json-patch+json");

    @Test
    public void should_return_updated_item_when_title_passed() throws Exception {
        String updated = "update this title";
        given(todoList.updateItem(1, updated)).willReturn(new TodoItem(1, updated));
        mockMvc.perform(patch("/todos/1").param("title", updated).accept(JSON_PATCH).contentType(JSON_PATCH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(updated)));
        verify(todoList).updateItem(1, updated);
    }

    @Test
    public void should_return_error_message_when_empty_title() throws Exception {
        mockMvc.perform(patch("/todos/1").param("title", "").accept(JSON_PATCH).contentType(JSON_PATCH))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(ERRMSG_EMPTY_TITLE)));
    }

    @Test
    public void should_return_error_message_when_no_title() throws Exception {
        mockMvc.perform(patch("/todos/1").accept(JSON_PATCH).contentType(JSON_PATCH))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(ERRMSG_EMPTY_TITLE)));

    }

    @Test
    public void should_return_error_message_when_update_item_not_found() throws Exception {
        given(todoList.updateItem(2, "no title")).willReturn(null);
        mockMvc.perform(patch("/todos/2").param("title", "no title").accept(JSON_PATCH).contentType(JSON_PATCH))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(ERRMSG_ITEM_NOT_FOUND)));
    }

    @Test
    public void should_return_removed_message_when_item_exists() throws Exception {
        given(todoList.deleteItem(1)).willReturn(true);
        mockMvc.perform(delete("/todos/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(INFOMSG_ITEM_REMOVED)));

        verify(todoList).deleteItem(1);
    }

    @Test
    public void should_return_error_message_when_item_not_found() throws Exception {
        given(todoList.deleteItem(2)).willReturn(false);
        mockMvc.perform(delete("/todos/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(ERRMSG_ITEM_NOT_FOUND)));
        verify(todoList).deleteItem(2);
    }
}
