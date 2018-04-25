package core;

import enums.Language;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static enums.YandexMapParameter.Param_Text;
import static enums.YandexMapParameter.Param_Lang;
import static utils.TestProperties.getMapUrl;

public class ApiBuilder {
  static YandexMapApi mapApi;

  public ApiBuilder(YandexMapApi mapApi) {
    this.mapApi = mapApi;
  }

  public ApiBuilder geocode(String text) {
    mapApi.getParams().put(Param_Text.toString(), text);
    return this;
  }

  public ApiBuilder lang(Language language) {
    mapApi.getParams().put(Param_Lang.toString(), language.langCode);
    return this;
  }

  public Response callApi() {
    return RestAssured
        .given(mapApi.baseRequestConfiguration())
        .queryParams(mapApi.getParams())
        .log()
        .all()
        .get(getMapUrl())
        .prettyPeek();
  }
}
