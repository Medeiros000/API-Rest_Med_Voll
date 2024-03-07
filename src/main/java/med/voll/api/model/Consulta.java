package med.voll.api.model;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.enums.Andamento;

@Table(name = "consultas")
@Entity(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String hora;
    @ManyToOne
    private Medico medico;
    private String especialidade;
    @ManyToOne
    private Paciente paciente;
    @Enumerated(EnumType.STRING)
    public Andamento andamento = Andamento.Agendada;
    private String observacoes;
}
