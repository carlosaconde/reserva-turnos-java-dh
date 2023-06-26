package com.ar.conde.reservaDeTurnos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull(message = "es requerido")
    @Column(name="userName", unique = true)
    private Integer userName;

    @NotEmpty(message = "es requerido")
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    private Rol rol;

    @NotNull(message = "es requerido")
    @Column(name="isAdmin")
    private Boolean isAdmin;

    public Usuario(Integer userName, String password, Boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}
