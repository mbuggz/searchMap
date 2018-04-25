package core;

import beans.YandexMapAnswer;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.lessThan;
import static utils.TestProperties.getMapUrl;

public class YandexMapApi {
  private HashMap<String, String> params = new HashMap<>();

  public HashMap<String, String> getParams() { return params;}

  public static ApiBuilder with() {
    YandexMapApi api = new YandexMapApi();
    return new ApiBuilder(api);
  }

  public static ResponseSpecification successResponse(){
    return new ResponseSpecBuilder()
        .expectContentType(JSON)
        .expectHeader("Connection", "keep-alive")
        .expectResponseTime(lessThan(20000L))
        .expectStatusCode(HttpStatus.SC_OK)
        .build();
  }

  public static RequestSpecification baseRequestConfiguration(){
    return new RequestSpecBuilder()
        .setAccept(JSON)
        .addHeader("custom header2", "header2.value")
        .setBaseUri(getMapUrl())
        .addQueryParam("format", "json")
        .build();
  }

  public static List<YandexMapAnswer> getYandexMapAnswers(Response response) {
    ArrayList<String> featureMembers = response.jsonPath().get("response.GeoObjectCollection.featureMember");
    JsonElement jsonElement = new Gson().toJsonTree(featureMembers);

    return new Gson().fromJson(jsonElement, new TypeToken<List<YandexMapAnswer>>() {}.getType());
  }
}
