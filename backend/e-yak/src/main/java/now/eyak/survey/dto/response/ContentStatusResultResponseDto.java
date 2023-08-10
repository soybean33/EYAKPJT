package now.eyak.survey.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import now.eyak.survey.enumeration.ChoiceStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ContentStatusResultResponseDto {
    private Long contentStatusResultId;
    private Long memberId;
    private List<ChoiceStatus> selectedStatusChoices;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public ContentStatusResultResponseDto(Long contentStatusResultId, Long memberId, List<ChoiceStatus> selectedStatusChoices, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.contentStatusResultId = contentStatusResultId;
        this.memberId = memberId;
        this.selectedStatusChoices = selectedStatusChoices;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}