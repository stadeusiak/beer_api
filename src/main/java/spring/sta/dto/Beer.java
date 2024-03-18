package spring.sta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Beer {

    private Long id;
    private String name;
    private String tagline;
    private String first_brewed;
    private String description;
    private String image_url;
    private double abv;
    private int ibu;
    private int target_fg;
    private int target_og;
    private int ebc;
    private int srm;
    private double ph;
    private double attenuation_level;
    private Volume volume;
    private Volume boil_volume;
    private Method method;
    private Ingredients ingredients;
    private List<String> food_pairing;
    private String brewers_tips;
    private String contributed_by;
}
