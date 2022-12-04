/**
 * @author: ArnoldG6.
 * @version: 1.0
 * Contact me via "arnoldgq612@gmail.com".
 *
 */
package org.una.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_block")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"year","availableSpaces"})
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "block", fetch = FetchType.EAGER)
    private Set <AvailableSpace> availableSpaces;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "year_id", nullable = false)
    private Year year;

}