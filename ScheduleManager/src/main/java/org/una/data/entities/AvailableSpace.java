package org.una.data.entities;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="t_available_space")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"block","student"})
public class AvailableSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false, name = "initial_hour")
    private String initialHour;
    @Column(nullable = false, name = "final_hour")
    private String finalHour;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(nullable = false)
    private String day;


}
