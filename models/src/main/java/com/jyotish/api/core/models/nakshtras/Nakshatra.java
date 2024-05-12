package com.jyotish.api.core.models.nakshtras;

import com.jyotish.api.core.models.nakshtras.enums.NakshatraData;
import com.jyotish.api.core.models.entity.Planet;
import lombok.Data;

@Data
public class Nakshatra {

    public NakshatraData nakshatraData;
    public Planet ruler;

    public Nakshatra(int number) {
        this.nakshatraData = NakshatraData.getNakshatraDataByNumber(number);
    }

    @Override
    public String toString() {
        return nakshatraData.nakshatraName;
    }
}
