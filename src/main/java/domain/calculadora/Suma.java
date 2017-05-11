package domain.calculadora;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by AHernandezS on 24/03/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Suma  implements Serializable {

    @JsonProperty
    private Integer num1;
    @JsonProperty
    private Integer num2;

    public Integer getNum1() {
        return num1;
    }

    public Integer getNum2() {
        return num2;
    }
}