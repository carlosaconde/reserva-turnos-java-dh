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
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaTurno")
    private String fechaTurno;

    @Column(name = "hora")
    private String hora;

    @ManyToOne
    @JoinColumn(name = "odontologo", nullable = false)
    private Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "paciente", nullable = false)
    private Paciente paciente;

    public Turno(LocalDate fechaTurno, Odontologo odontologo, Paciente paciente, String hora) {
        this.fechaTurno = String.valueOf(fechaTurno);
        this.odontologo = odontologo;
        this.paciente = paciente;
        this.hora = hora;
    }

    public void setHora(String hora) {
        this.hora = (hora);
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = String.valueOf(fechaTurno);
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
                ", fechaTurno='" + fechaTurno + '\'' +
                ", hora='" + hora + '\'' +
                ", odontologo=" + odontologo +
                ", paciente=" + paciente +
                '}';
    }
}
