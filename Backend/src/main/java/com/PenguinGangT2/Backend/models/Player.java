package com.PenguinGangT2.Backend.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
public class Player {
    
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String season;

    @NotBlank
    private String team;

    @NotBlank
    private String age;

    @NotBlank
    private String gp;

    @NotBlank
    private String gs;

    @NotBlank
    private String min;

    @NotBlank
    private String pts;

    @NotBlank
    private String fgm;

    @NotBlank
    private String fga;

    @NotBlank
    private String fgPercent;

    @NotBlank
    private String threePM;

    @NotBlank
    private String threePA;

    @NotBlank
    private String threePPercent;

    @NotBlank
    private String ftm;

    @NotBlank
    private String fta;

    @NotBlank
    private String ftPercent;

    @NotBlank
    private String oreb;

    @NotBlank
    private String dreb;

    @NotBlank
    private String reb;
    
    @NotBlank
    private String ast;

    @NotBlank
    private String stl;

    @NotBlank
    private String blk;

    @NotBlank
    private String tov;

    @NotBlank
    private String pf;

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    
    public String getSeason(){
        return season;
    }

    public String getTeam(){
        return team;
    }

    public String getAge(){
        return age;
    }

    public String getGp(){
        return gp;
    }

    public String getGs(){
        return gs;
    }

    public String getMin(){
        return min;
    }

    public String getPts(){
        return pts;
    }

    public String getFgm(){
        return fgm;
    }

    public String getFga(){
        return fga;
    }

    public String getFgPercent(){
        return fgPercent;
    }

    public String getThreePM(){
        return threePM;
    }

    public String getThreePA(){
        return threePA;
    }

    public String getThreePPercent(){
        return threePPercent;
    }

    public String getFtm(){
        return ftm;
    }

    public String getFta(){
        return fta;
    }

    public String getFtPercent(){
        return ftPercent;
    }

    public String getOreb(){
        return oreb;
    }

    public String getDreb(){
        return dreb;
    }

    public String getReb(){
        return reb;
    }

    public String getAst(){
        return ast;
    }

    public String getStl(){
        return stl;
    }

    public String getBlk(){
        return blk;
    }

    public String getTov(){
        return tov;
    }

    public String getPf(){
        return pf;
    }
}
