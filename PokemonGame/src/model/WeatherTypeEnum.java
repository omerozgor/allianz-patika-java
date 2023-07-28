package model;

public enum WeatherTypeEnum {
    RAINY(0),
    SUNNNY(1),
    WINDY(2),
    CLOUDY(3);

    public final int weatherType;

    WeatherTypeEnum(int weatherType) {
        this.weatherType = weatherType;
    }
}
