package service;

import model.Accident;
import model.ColorTypeEnum;
import model.Vehicle;

import java.math.BigDecimal;
import java.util.Date;

public class AccidentService {

    public Accident createAccident(Date accidentDate, String description, BigDecimal damagePrice,
                                   int failureRate){

        Accident accident = new Accident(accidentDate,description,damagePrice,failureRate);


        return accident;
    }
}
