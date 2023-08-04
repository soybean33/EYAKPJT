package now.eyak.prescription.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import now.eyak.prescription.domain.Prescription;
import now.eyak.routine.enumeration.Routine;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PrescriptionResponseDto {
    private Long id;
    private String icd;
    private String customName;
    private String krName;
    private String engName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<Routine> routines;
    private Integer iotLocation; // 약통 칸 번호
    private Float medicineDose; // 1회 투여 개수
    private Integer medicineShape; // 이모지 번호
    private String unit; // 투여 단위
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PrescriptionResponseDto from(Prescription prescription) {
        return PrescriptionResponseDto.builder()
                .id(prescription.getId())
                .customName(prescription.getCustomName())
                .icd(prescription.getIcd())
                .krName(prescription.getKrName())
                .engName(prescription.getEngName())
                .startDateTime(prescription.getStartDateTime())
                .endDateTime(prescription.getEndDateTime())
                .iotLocation(prescription.getIotLocation())
                .medicineDose(prescription.getMedicineDose())
                .medicineShape(prescription.getMedicineShape())
                .unit(prescription.getUnit())
                .createdAt(prescription.getCreatedAt())
                .updatedAt(prescription.getUpdatedAt())
                .build();
    }

}
