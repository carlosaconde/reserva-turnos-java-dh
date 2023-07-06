package com.ar.ndifabio.reservaDeTurnos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio",referencedColumnName = "id")
    private Domicilio domicilio;

    @NotEmpty(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;


    @NotEmpty(message = "El apellido es obligatorio")
    @Column(name = "apellido")
    private String apellido;

    @NotNull(message = "El dni es obligatorio")
    @Column(name = "dni", unique = true)
    private int dni;

    @Column(name = "fechaDeAlta")
    private LocalDate fechaDeAlta;


    public Paciente(Long id, Domicilio domicilio, String nombre, String apellido, int dni, LocalDate fechaDeAlta) {
        this.id = id;
        this.domicilio = domicilio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", domicilio=" + domicilio +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", fechaDeAlta=" + fechaDeAlta +
                '}';
    }
}
