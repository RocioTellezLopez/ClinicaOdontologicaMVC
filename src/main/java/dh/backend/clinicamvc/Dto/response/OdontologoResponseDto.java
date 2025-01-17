package dh.backend.clinicamvc.Dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private String numeroMatricula;
    private String nombre;
    private String apellido;

}
