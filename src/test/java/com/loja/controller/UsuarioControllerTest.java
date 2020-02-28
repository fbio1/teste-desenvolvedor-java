package com.loja.controller;

import com.loja.produtos.controller.RestResponseEntityExceptionHandler;
import com.loja.produtos.controller.UsuarioController;
import com.loja.produtos.dtos.UsuarioDTO;
import com.loja.produtos.exception.ResourceNotFoundException;
import com.loja.produtos.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.loja.controller.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//@ActiveProfiles("test")
public class UsuarioControllerTest {

    public static final String NAME1 = "Jim";
    public static final String NAME2 = "Amanda";

    private static final String BASE_URL_1 = UsuarioController.BASE_URL.concat("/1");

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(usuarioController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testListUsuarios() throws Exception {
          UsuarioDTO usuario1 = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(NAME1)
                .cpf("81110588020")
                .email("joao@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 998583058")
                .build();

        UsuarioDTO usuario2 = UsuarioDTO.builder()
                .id(2L)
                .nomeComposto(NAME2)
                .cpf("89887466000")
                .email("maria@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 998583058")
                .build();

        List<UsuarioDTO> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioService.getAll()).thenReturn(usuarios);

        mockMvc.perform(get(UsuarioController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarios", hasSize(2)));
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        //given
        UsuarioDTO usuario1 = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(NAME2)
                .cpf("81110588020")
                .email("joao@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 998583058")
                .url(BASE_URL_1)
                .build();

        when(usuarioService.getById(anyLong())).thenReturn(usuario1);

        //expect
        mockMvc.perform(get(BASE_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeComposto", equalTo("Amanda")));
    }

    @Test
    public void createCreateUsuario() throws Exception {
        //given
        UsuarioDTO usuario1 = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(NAME2)
                .cpf("81110588020")
                .email("joao@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 998583058")
                .build();

        UsuarioDTO returnDTO = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(usuario1.getNomeComposto())
                .cpf(usuario1.getCpf())
                .email(usuario1.getEmail())
                .endereco(usuario1.getEndereco())
                .telefone(usuario1.getTelefone())
                .url(BASE_URL_1)
                .build();

        when(usuarioService.createNew(usuario1)).thenReturn(returnDTO);

        //expect
        mockMvc.perform(post(UsuarioController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeComposto", equalTo("Amanda")))
                .andExpect(jsonPath("$.usuario_url", equalTo(BASE_URL_1)));
    }

    @Test
    public void testUpdateUsuario() throws Exception {
        //given
        UsuarioDTO usuario1 = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(NAME2)
                .cpf("81110588020")
                .email("joao@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 99999999")
                .build();

        UsuarioDTO returnDTO = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(usuario1.getNomeComposto())
                .cpf(usuario1.getCpf())
                .email(usuario1.getEmail())
                .endereco(usuario1.getEndereco())
                .telefone(usuario1.getTelefone())
                .url(BASE_URL_1)
                .build();

        when(usuarioService.saveByDTO(anyLong(), any(UsuarioDTO.class))).thenReturn(returnDTO);

        //expect
        mockMvc.perform(put(BASE_URL_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeComposto", equalTo("Amanda")))
                .andExpect(jsonPath("$.telefone", equalTo("(84) 99999999")))
                .andExpect(jsonPath("$.usuario_url", equalTo(BASE_URL_1)));
    }

    @Test
    public void testPatchUsuario() throws Exception {
        //given
        UsuarioDTO usuario1 = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(NAME2)
                .cpf("81110588020")
                .email("joao@gmail.com")
                .endereco("Rua Aeroporto Cruzeiro do Sul")
                .telefone("(84) 99999999")
                .build();

        UsuarioDTO returnDTO = UsuarioDTO.builder()
                .id(1L)
                .nomeComposto(usuario1.getNomeComposto())
                .cpf(usuario1.getCpf())
                .email(usuario1.getEmail())
                .endereco(usuario1.getEndereco())
                .telefone(usuario1.getTelefone())
                .url(BASE_URL_1)
                .build();


        when(usuarioService.patch(anyLong(), any(UsuarioDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(BASE_URL_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(usuario1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeComposto", equalTo("Amanda")))
                .andExpect(jsonPath("$.telefone", equalTo("(84) 99999999")))
                .andExpect(jsonPath("$.usuario_url", equalTo(BASE_URL_1)));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(BASE_URL_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(usuarioService).deleteById(anyLong());
        verify(usuarioService, times(1)).deleteById(anyLong());
    }

    @Test
    public void testNotFoundException() throws Exception {
        when(usuarioService.getById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(UsuarioController.BASE_URL.concat("/6461"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
