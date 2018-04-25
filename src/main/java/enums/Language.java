package enums;

public enum Language {
  EN("en_EN"),
  RU("ru_RU");

  public String langCode;

  Language(String lang) {
    this.langCode = lang;
  }
}
