package org.una.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;



@Entity
@Table(name="t_year")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"blocks"})
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, unique=true)
    private Integer year;
    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Block> blocks;
}
