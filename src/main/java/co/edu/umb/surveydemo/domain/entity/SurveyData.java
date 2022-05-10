package co.edu.umb.surveydemo.domain.entity;

import lombok.Builder;
import lombok.Data;

@Builder
public record SurveyData(
  String name,
  String document,
  String profession,
  String expYears,
  String backEnd,
  String frontEnd,
  String dataBase) {

}
