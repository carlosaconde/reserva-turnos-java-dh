package com.ar.conde.reservaDeTurnos.entity;

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
@Table(name = "domicilios")

public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "la calle es obligatoria")
    @Column(name = "calle")
    private String calle;

    @NotEmpty(message = "El numero es obligatorio")
    @Column(name = "numero")
    private String numero;

    @NotEmpty(message = "La localidad es obligatoria")
    @Column(name = "localidad")
    private String localidad;

    @NotEmpty(message = "La provincia es obligatoria")
    @Column(name = "provincia")
    private String provincia;


    public Domicilio(Long id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
