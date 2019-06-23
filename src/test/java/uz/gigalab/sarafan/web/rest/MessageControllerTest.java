//package uz.gigalab.sarafan.web.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
////@WebMvcTest(MessageController.class)
//public class MessageControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    private ObjectMapper om = new ObjectMapper();
//
//    //@Test
//    public void getAllMessages() throws Exception {
//        this.mockMvc.perform(
//                get("/messages").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(4)))
//                .andExpect(jsonPath("$[0].id").value("1"))
//                .andExpect(jsonPath("$[1].id", is("2")))
//        ;
//    }
//
//    //@Test
//    public void getMessage() throws Exception {
//        mockMvc.perform(
//                get("/messages/1").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is("1")))
//                .andExpect(jsonPath("$.text", is("Text 1")))
//        ;
//    }
//
//    //@Test
//    public void create() throws Exception {
//        Map<String, String> data = new HashMap<>();
//        data.put("text", "Text 5");
//
//        MockHttpServletRequestBuilder builder = post("/messages")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(om.writeValueAsString(data));
//
//        mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is("5")))
//                .andExpect(jsonPath("$.text", is("Text 5")))
//        ;
//
//    }
//
//    //@Test
//    public void update() throws Exception {
//        Map<String, String> data = new HashMap<>();
//        data.put("id", "4");
//        data.put("text", "Text 4444");
//
//        MockHttpServletRequestBuilder builder = put("/messages")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(om.writeValueAsString(data));
//
//        mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is("4")))
//                .andExpect(jsonPath("$.text", is("Text 4444")))
//        ;
//    }
//
//    //@Test
//    public void deleteMessage() throws Exception {
//        MockHttpServletRequestBuilder builder = delete("/messages/4")
//                .contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(builder)
//                .andDo(print())
//                .andExpect(status().isNoContent())
//        ;
//    }
//
//
//    //@Test
//    public void getMessage_NotFound() throws Exception {
//        this.mockMvc.perform(
//                get("/messages/-999").contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isNotFound())
//        ;
//    }
//}