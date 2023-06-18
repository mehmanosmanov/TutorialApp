package org.tutorial.app.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tutorials")
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(length = 55)
    private String title;
    @Column(length = 100)
    private String name;
    @Column(length = 55)
    private String subject;
    private Boolean published;

}