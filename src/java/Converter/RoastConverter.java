package Converter;

import Pojo.Roast;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Candace
 */
@FacesConverter(value="brewConverter")
public class RoastConverter extends EnumConverter {
    public RoastConverter() {
        super(Roast.class);
    }
}
