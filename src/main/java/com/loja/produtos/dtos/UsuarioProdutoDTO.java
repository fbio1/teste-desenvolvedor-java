package com.loja.produtos.dtos;

import com.loja.produtos.domains.DomainBase;
import com.loja.produtos.domains.Produto;
import com.loja.produtos.domains.Usuario;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class UsuarioProdutoDTO extends DomainBase implements Serializable {

    private Long id;

    private Produto produto;

    private Usuario usuario;

}
