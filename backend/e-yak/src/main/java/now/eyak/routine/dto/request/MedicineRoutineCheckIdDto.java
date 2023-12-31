package now.eyak.routine.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import now.eyak.routine.enumeration.Routine;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class MedicineRoutineCheckIdDto {
    private LocalDate date;
    private Routine routine;
    private Long prescriptionId;

    @Builder
    public MedicineRoutineCheckIdDto(LocalDate date, Routine routine, Long prescriptionId) {
        this.date = date;
        this.routine = routine;
        this.prescriptionId = prescriptionId;
    }
}
