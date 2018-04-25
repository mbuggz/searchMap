package enums;

public enum YandexMapParameter {
  Param_Text("geocode"),
  Param_Lang("lang"),
  Param_Format("format");

  private String text;

  YandexMapParameter(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}

