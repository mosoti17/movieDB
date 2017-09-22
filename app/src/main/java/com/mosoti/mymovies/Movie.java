package com.mosoti.mymovies;

/**
 * Created by mosoti on 9/14/17.
 */

public class Movie {

    private String mType;
    private String mTittle;
    private String mImage;
    private String mOverview;
    private String mReleaseDate;
    private double mRating;

    public Movie(String type,String tittle,String image,String overview,String releasedate,double rating){

        this.mImage=image;
        this.mOverview=overview;
        this.mRating=rating;
        this.mReleaseDate=releasedate;
        this.mTittle=tittle;
        this.mType=type;

    }
    public String getType(){
        return mType;
    }

    public String getTittle(){
        return mTittle;
    }
    public String getImage(){
        return mImage;
    }

    public String getOverview(){
        return mOverview;
    }
    public String getReleaseDate(){
        return mReleaseDate;
    }
    public double getRating(){
        return mRating;
    }


}
