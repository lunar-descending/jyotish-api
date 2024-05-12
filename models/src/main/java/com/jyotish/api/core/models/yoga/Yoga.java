package com.jyotish.api.core.models.yoga;

import com.jyotish.api.core.models.entity.Planet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
public class Yoga {
    private String yogaName;
    private boolean isPresentInHoroscope;
    private boolean isYogaCancelled;
    private List<Planet> planetsAssociated = new ArrayList<>();
    private List<Planet> planetsCancelling = new ArrayList<>();
}
