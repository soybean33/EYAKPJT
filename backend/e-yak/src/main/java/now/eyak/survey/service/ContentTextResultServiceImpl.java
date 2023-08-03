package now.eyak.survey.service;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import now.eyak.member.domain.Member;
import now.eyak.member.exception.NoSuchMemberException;
import now.eyak.member.repository.MemberRepository;
import now.eyak.survey.domain.*;
import now.eyak.survey.dto.request.ContentTextResultDto;
import now.eyak.survey.dto.request.ContentTextResultUpdateDto;
import now.eyak.survey.dto.response.ContentTextResultResponseDto;
import now.eyak.survey.repository.ContentTextResultRepository;
import now.eyak.survey.repository.SurveyContentRepository;
import now.eyak.survey.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContentTextResultServiceImpl implements ContentTextResultService {

    private final ContentTextResultRepository contentTextResultRepository;
    private final SurveyContentRepository surveyContentRepository;
    private final MemberRepository memberRepository;
    private final SurveyRepository surveyRepository;

    private final JPAQueryFactory queryFactory;
    /**
     * Text설문 응답 저장
     * @param contentTextResultDto
     * @param memberId
     * @return
     */
    @Transactional
    @Override
    public ContentTextResult saveTextSurveyResult(ContentTextResultDto contentTextResultDto, Long memberId) {
        SurveyContent surveyContent = surveyContentRepository.findById(contentTextResultDto.getSurveyContentId()).orElseThrow(() -> new NoSuchElementException("surveyContentId에 해당하는 SurveyContent가 없습니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("해당하는 회원 정보가 없습니다."));
        ContentTextResult contentTextResult = ContentTextResult.builder()
                .text(contentTextResultDto.getText())
                .surveyContent(surveyContent)
                .member(member)
                .build();

        return contentTextResultRepository.save(contentTextResult);
    }

    /**
     * Text설문 응답 수정
     * @param contentTextResultUpdateDto
     * @param memberId
     * @return
     */
    @Transactional
    @Override
    public ContentTextResult updateTextSurveyResult(ContentTextResultUpdateDto contentTextResultUpdateDto, Long memberId) {
        SurveyContent surveyContent = surveyContentRepository.findById(contentTextResultUpdateDto.getSurveyContentId()).orElseThrow(() -> new NoSuchElementException("surveyContentId에 해당하는 SurveyContent가 없습니다."));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("해당하는 회원 정보가 없습니다."));
        ContentTextResult contentTextResult = contentTextResultRepository.findByIdAndMember(contentTextResultUpdateDto.getId(), member).orElseThrow(() -> new NoSuchElementException("회원에 대해서 해당하는 ContentTextResult가 존재하지 않습니다."));

        contentTextResult = contentTextResultUpdateDto.update(contentTextResult);

        return contentTextResultRepository.save(contentTextResult);
    }

    /**
     * Text설문 응답 삭제
     * @param contextTextResultId
     * @param memberId
     */
    @Transactional
    @Override
    public void deleteTextSurveyResult(Long contextTextResultId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("해당하는 회원 정보가 없습니다."));

        contentTextResultRepository.deleteById(contextTextResultId);

    }

    /**
     * Text 설문 조회
     * @param date
     * @param memberId
     * @return
     */
    @Transactional
    @Override
    public List<ContentTextResultResponseDto> getTextResultsByDateAndMember(LocalDate date, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchMemberException("해당하는 회원 정보가 없습니다."));
        Survey survey = surveyRepository.findByDate(date).orElseThrow(() -> new NoSuchElementException("해당하는 날짜의 설문기록이 없습니다."));

        QContentTextResult qContentTextResult = QContentTextResult.contentTextResult;
        QSurveyContent qSurveyContent = QSurveyContent.surveyContent;
        QSurvey qSurvey = QSurvey.survey;

        List<ContentTextResultResponseDto> textResults = queryFactory
                .select(Projections.constructor(ContentTextResultResponseDto.class,
                        qContentTextResult.member.id,
                        qContentTextResult.text,
                        qContentTextResult.createdAt,
                        qContentTextResult.updatedAt))
                .from(qContentTextResult)
                .leftJoin(qContentTextResult.surveyContent, qSurveyContent)
                .leftJoin(qSurveyContent.survey, qSurvey)
                .where(qSurvey.date.eq(date).and(qContentTextResult.member.eq(member)))
                .fetch();

        return textResults;
    }

}

