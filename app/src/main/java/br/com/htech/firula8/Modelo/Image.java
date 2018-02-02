package br.com.htech.firula8.Modelo;

import java.io.Serializable;

/**
 * Created by arturhayne on 27/01/2018.
 */

public class Image implements Serializable{
    private String hidpi;
    private String normal;
    private String teaser;

    public Image() {
    }

    public String getHidpi() {
        return hidpi;
    }

    public void setHidpi(String hidpi) {
        this.hidpi = hidpi;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }
}
