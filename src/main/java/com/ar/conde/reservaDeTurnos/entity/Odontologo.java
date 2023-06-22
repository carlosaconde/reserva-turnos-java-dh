package com.ar.conde.reservaDeTurnos.entity;

import com.ar.conde.reservaDeTurnos.log4j.Log4j;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="odontologos")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @NotEmpty(message = "El nombre es obligatorio")
    @Column(name = "nombre")
    private String nombre;
    @NotEmpty(message = "el apellido es obligatorio")
    @Column(name = "apellido")
    private String apellido;
    @NotNull(message = "La Matricula es obligatoria")
    @Column(name="matricula")
    private int matricula;

    public Odontologo(String nombre, String apellido, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", matricula=" + matricula +
                '}';
    }

    public void mostrarInfo(){
        Log4j.info("probando logeo");
    }
}
