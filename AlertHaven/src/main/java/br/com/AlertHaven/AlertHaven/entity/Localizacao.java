package br.com.AlertHaven.AlertHaven.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
}
