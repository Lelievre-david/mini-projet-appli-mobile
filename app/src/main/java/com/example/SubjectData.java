package com.example;

class SubjectData {
    String SubjectName;
    String Image;
    Film film;
    public SubjectData(Film thefilm, String subjectName, String image) {
        this.film = thefilm;
        this.SubjectName = subjectName;
        this.Image = image;
    }

    public String getImage(){
        return Image;
    }

    public String toString(){
        return film.toStringLe2();
    }

}