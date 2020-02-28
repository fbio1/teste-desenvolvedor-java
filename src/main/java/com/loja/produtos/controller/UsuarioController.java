package com.loja.produtos.controller;

import com.loja.produtos.dtos.UsuarioDTO;
import com.loja.produtos.dtos.UsuarioListDTO;
import com.loja.produtos.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UsuarioController.BASE_URL)
public class UsuarioController {

	public static final String BASE_URL = "/usuarios";

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public UsuarioListDTO getAll() {
		return new UsuarioListDTO(usuarioService.getAll());
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioDTO getCustomerById(@PathVariable Long id) {
		return usuarioService.getById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO createNewCustomer(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.createNew(usuarioDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO updateCustomer(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.saveByDTO(id, usuarioDTO);
    }
    
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO patchCustomer(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.patch(id, usuarioDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id){
        usuarioService.deleteById(id);
    }
}
