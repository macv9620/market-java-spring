package com.macv.market.persistence.entity;

import javax.persistence.*;
import javax.swing.text.StyledEditorKit;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    private String descripcion;

    private Boolean estado;

}
