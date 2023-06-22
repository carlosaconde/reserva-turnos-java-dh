package com.ar.conde.reservaDeTurnos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name="turnos")
public class Turno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaTurno")
    private String fechaTurno;



    public void setFechaTurno(String fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    @ManyToOne
    @JoinColumn(name="odontologo", nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name="paciente", nullable = false)
    private Paciente paciente;



    public Turno(String fechaTurno, Odontologo odontologo, Paciente paciente) {
        this.fechaTurno = fechaTurno;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fechaTurno=" + fechaTurno +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                '}';
    }
}
