package br.com.AlertHaven.AlertHaven.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_localizacao")
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {

    @Column(name = "identificacao_unica_abrigo")
    private String identificacaoUnicaAbrigo;

    @Column(name = "latitude_abrigo")
    private String latitudeAbrigo;

    @Column(name = "longitude_abrigo")
    private String longitudeAbrigo;

    @Column(name = "rua_abrigo")
    private String ruaAbrigo;

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;
}
