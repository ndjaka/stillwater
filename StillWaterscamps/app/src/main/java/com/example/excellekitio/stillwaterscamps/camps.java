package com.example.excellekitio.stillwaterscamps;

/**
 * Created by excelle kitio on 14/11/2017.
 */
public class camps {
    private String idcamps;
    private int intervaled_age;
    private String periode;

    public camps() {
    }
    public camps(String idcamps, int intervaled_age, String periode) {
        this.idcamps = idcamps;
        this.intervaled_age = intervaled_age;
        this.periode = periode;
    }
    public void setIdcamps(String idcamps) {
        this.idcamps = idcamps;
    }

    public int getIntervaled_age() {
        return intervaled_age;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public void setIntervaled_age(int intervaled_age) {
        this.intervaled_age = intervaled_age;
    }

    public String getIdcamps() {

        return idcamps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof camps)) return false;

        camps camps = (camps) o;

        if (intervaled_age != camps.intervaled_age) return false;
        if (idcamps != null ? !idcamps.equals(camps.idcamps) : camps.idcamps != null) return false;
        return periode != null ? periode.equals(camps.periode) : camps.periode == null;

    }

    @Override
    public int hashCode() {
        int result = idcamps != null ? idcamps.hashCode() : 0;
        result = 31 * result + intervaled_age;
        result = 31 * result + (periode != null ? periode.hashCode() : 0);
        return result;
    }
}


