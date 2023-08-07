package now.eyak.routine.controller;

import lombok.RequiredArgsConstructor;
import now.eyak.routine.domain.MedicineRoutineCheck;
import now.eyak.routine.dto.MedicineRoutineCheckUpdateDto;
import now.eyak.routine.service.MedicineRoutineCheckService;
import now.eyak.survey.domain.ContentTextResult;
import now.eyak.survey.dto.request.ContentTextResultDto;
import now.eyak.util.ApiVersionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class RoutineController {

    private final ApiVersionHolder apiVersionHolder;
    private final MedicineRoutineCheckService medicineRoutineCheckService;

    /**
     * 약 복용 체크 기록 및 수정(true or false)
     * @param medicineRoutineCheckUpdateDto
     * @param memberId
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("{memberId}/medicine-routine-checks")
    public ResponseEntity updateMedicineRoutineCheck(
            @RequestBody MedicineRoutineCheckUpdateDto medicineRoutineCheckUpdateDto,
            @AuthenticationPrincipal Long memberId
    ) throws URISyntaxException {

        MedicineRoutineCheck medicineRoutineCheck = medicineRoutineCheckService.updateMedicineRoutineCheck(medicineRoutineCheckUpdateDto, memberId);

        return ResponseEntity.ok().build();
    }


}
