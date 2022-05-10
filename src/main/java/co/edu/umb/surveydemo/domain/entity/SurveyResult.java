package co.edu.umb.surveydemo.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
public record SurveyResult(
  Map<String, Long> professionData,
  Map<String, Long> yearsData,
  Map<String, Long> backEndData,
  Map<String, Long> frontEndData,
  Map<String, Long> dataBaseData) {
}
