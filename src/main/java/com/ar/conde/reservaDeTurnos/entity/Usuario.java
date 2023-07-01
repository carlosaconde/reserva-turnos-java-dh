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

    @NotEmpty(message = "es requerido")
    @Column(name="userName", unique = true)
    private String userName;

    @NotNull(message = "es requerido")
    @Column(name = "idNumber", unique = true)
    private Integer idNumber;

    @NotEmpty(message = "es requerido")
    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    private Rol rol;


    @NotNull(message = "es requerido")
    @Column(name="isAdmin")
    private Boolean isAdmin;

    public Usuario(String userName, String password, Boolean isAdmin, Integer idNumber) {
        this.idNumber=idNumber;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Usuario(String userName, String password, Boolean isAdmin, Integer idNumber, Rol rol) {
        this.idNumber=idNumber;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.rol= rol;
    }
}
