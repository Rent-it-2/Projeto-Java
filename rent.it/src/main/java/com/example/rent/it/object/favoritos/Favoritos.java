package com.example.rent.it.object.favoritos;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkItem")
    private Item fkItem;

}
