package com.loja.produtos.domains;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U>
{
    @CreatedBy
    @Column(name = "created_by")
    @ApiModelProperty(notes = "Criado por")
    private U createdBy;

    @CreatedDate
    @Column(name = "created_date")
    @ApiModelProperty(notes = "Data de Criação")
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    @ApiModelProperty(notes = "Ultimo usuário que modificou")
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @ApiModelProperty(notes = "Data da ultima modificação")
    private Date lastModifiedDate;
}
