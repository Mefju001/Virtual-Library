package com.mefju.virtual_library;

import com.mefju.virtual_library.Controller.AdminController;
import com.mefju.virtual_library.Entity.Book;
import com.mefju.virtual_library.Service.BibliotekiService;
import com.mefju.virtual_library.Service.BookService;
import com.mefju.virtual_library.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
public class VirtualLibraryAdminControllerTests {
                         //Testy kontrolerów
    @Autowired
    private MockMvc mockMvc; // Mock MVC do wywoływania żądań
    @MockBean
    private BookService bookService;
    @MockBean
    private BibliotekiService bibliotekiService;
    @MockBean
    private UserService userService;
    @Test
    public void testAdminPage() throws Exception {
        mockMvc.perform(get("/MenuAdmin").with(user("Admin").roles("ADMIN"))) // Wywołanie żądania GET
                .andExpect(status().isOk());
    }
    @Test
    public void testAdminPage2() throws Exception {
        mockMvc.perform(get("/MenuAdmin"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testSave() throws Exception {
        mockMvc.perform(get("/ShowFormForAdd").with(user("Admin").roles("ADMIN"))) // Wywołanie żądania GET
                .andExpect(status().isOk());
    }
    @Test
    public void testSave2() throws Exception {
        mockMvc.perform(get("/ShowFormForAdd"))
                .andExpect(status().isUnauthorized());
    }
    @Test
    public void testMenuWithBasicUser() throws Exception {
        mockMvc.perform(get("/Menu").with(user("basicUser").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void testFormSubmission() throws Exception {
        mockMvc.perform(multipart("/save")
                        .file(new MockMultipartFile("file", "originalFileName.txt", "text/plain", "some xml".getBytes()))
                        .with(user("Admin").roles("ADMIN"))
                        .with(csrf())
                        .flashAttr("Book", new Book()))
                .andExpect(status().isFound()) // Upewnij się, że przekierowanie jest wykonywane
                .andExpect(redirectedUrl("/MenuAdmin")); // Sprawdź, czy użytkownik jest przekierowany na właściwą stronę
        }
    @Test
    void testControllerWithParams() throws Exception {
        mockMvc.perform(get("/MenuAdmin").with(user("Admin").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("Book")) // Sprawdź, czy atrybut modelu jest ustawiony
                .andExpect(view().name("mainAdmin")); // Sprawdź, czy kontroler przekazuje poprawny widok
    }
    @Test
    void testDeleteWithParam() throws Exception {
        mockMvc.perform(get("/Delete").with(user("Admin").roles("ADMIN"))
                        .param("id", String.valueOf(1)))
                .andExpect(status().is3xxRedirection()) // Upewnij się, że przekierowanie jest wykonywane
                .andExpect(redirectedUrl("/MenuAdmin")); // Sprawdź, czy kontroler przekazuje poprawny widok
    }

}
