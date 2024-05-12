package com.jyotish.api.core.models.signs;

import com.jyotish.api.core.models.signs.enums.SignData;
import com.jyotish.api.core.models.entity.Planet;
import lombok.Data;

@Data
public class Sign {

    public SignData signData;
    public Planet ruler;

    public Sign(int number) {
        this.signData = SignData.getSignDataByNumber(number);
    }

    @Override
    public String toString() {
        return signData.signName;
    }
}
