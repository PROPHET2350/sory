package com.example.demo;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
public class companies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String year;

    public String expediente;
    public String ruc;
    public String nombre;
    public String ramaAct;
    public String descripcionRama;
    public String CIU;
    public String pais;
    public String provincia;
    public String ciudad;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    public List<String> cuentas;

    public companies() {
    }

    public companies(
            String year,
            String expediente,
            String ruc,
            String nombre,
            String ramaAct,
            String descripcionRama,
            String CIU,
            String pais,
            String provincia,
            String ciudad,
            List<String> cuentas
    ) {
        this.year = year;
        this.expediente = expediente;
        this.ruc = ruc;
        this.nombre = nombre;
        this.ramaAct = ramaAct;
        this.descripcionRama = descripcionRama;
        this.CIU = CIU;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.cuentas = cuentas;
    }
}
