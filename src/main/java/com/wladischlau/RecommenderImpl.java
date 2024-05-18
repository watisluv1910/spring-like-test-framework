package com.wladischlau;

/**
 * @author Vladislav Nasevich
 */
@Singleton
@Deprecated
public class RecommenderImpl implements Recommender {

    @InjectProperty("whiskey")
    private String alcohol;

    public RecommenderImpl() {
        System.out.println("RecommenderImpl created");
    }

    @Override
    public void recommend() {
        System.out.println("TO PROTECT FROM VIRUS, DRINK " + alcohol);
    }
}
