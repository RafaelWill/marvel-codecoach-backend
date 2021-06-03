package be.marvel.code.coach.domain.entity;

import org.assertj.core.util.Lists;

import java.util.List;

public enum Role {
    COACHEE(Feature.FIND_PERSON_BY_ID, Feature.BECOME_COACH, Feature.FIND_COACHES, Feature.REQUEST_SESSION),
    COACH(Feature.FIND_PERSON_BY_ID, Feature.FIND_COACHES, Feature.REQUEST_SESSION),
    ADMIN;

    private final List<Feature> featureList;

    Role(Feature... featureList) {
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }
}
