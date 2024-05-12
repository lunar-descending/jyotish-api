package com.jyotish.api.core.models.input;

import com.jyotish.api.core.models.entity.Longitude;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlanetData {
    private BirthData birthData;
    private Longitude lagnaLongitude;
    private Map<String, Longitude> planetLongitudeMap;
}
