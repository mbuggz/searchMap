package json;

import beans.YandexMapAnswer;
import core.YandexMapApi;
import org.testng.annotations.Test;

import java.util.List;

import static core.YandexMapApi.getYandexMapAnswers;
import static enums.Language.RU;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestSearchYa ndexMap {
  @Test
  public void testSearchAddress() {
    List<YandexMapAnswer> answers = getYandexMapAnswers(
        YandexMapApi.with()
            .lang(RU)
            .geocode("Москва,+Тверская+улица,+дом+7")
            .callApi());
    assertThat(answers.get(0).geoObject.metaDataProperty.geocoderMetaData.address.formatted,
        equalTo("Москва, Тверская улица, 7"));
  }
}
