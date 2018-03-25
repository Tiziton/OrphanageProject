package pl.polsl.orphanage.service.mapper.helper;

import org.springframework.stereotype.Service;
import pl.polsl.orphanage.service.mapper.annotations.*;

@Service
@BooleanBitFormater
public class BooleanFormater {

    @BooleanToBit
    public Short map(Boolean value){
        if(value != null){
            return value == true ? (short)1 : (short)0;
        }
        return null;
    }

    @BitToBoolean
    public Boolean map(Short value){
        if(value != null){
            return value == 1 ? true : false;
        }
        return  null;
    }
}
