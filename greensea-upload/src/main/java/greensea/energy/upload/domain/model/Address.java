package greensea.energy.upload.domain.model;

import lombok.Data;

/**
 * @ClassName: Address
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-07-30 10:12
 * @Version: 1.0
 **/
@Data
public class Address {
    String continent;
    String country;

    String areacode;
    String lat;
    String lng;
    String radius;
    String prov;
    String city;
    String district;
}
