package com.aprendizagem.models;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_tool")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tool extends RepresentationModel<Tool> { // Extende a classe RepresentationModel para cria os links

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String description;
    private String tags;

}