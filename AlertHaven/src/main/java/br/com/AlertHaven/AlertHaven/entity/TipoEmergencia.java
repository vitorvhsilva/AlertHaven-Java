package br.com.AlertHaven.AlertHaven.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_TIPO_EMERGENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEmergencia {

    @Id
    @Column(name = "id_tipo_emergencia")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idTipoEmergencia;

    @Column(name = "tipo_emergencia")
    private String tipoEmergencia;
}
