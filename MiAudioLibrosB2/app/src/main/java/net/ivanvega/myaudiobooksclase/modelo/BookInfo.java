package net.ivanvega.myaudiobooksclase.modelo;

/**
 * Created by SERVIDOR on 15/09/2016.
 */
public class BookInfo {
    String name;
    String autor;
    int resourceImage;
    String url;



    public String getName() {
        return name;
    }

    public BookInfo(String name, String autor, int resourceImage, String url) {
        this.name = name;
        this.autor = autor;
        this.resourceImage = resourceImage;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(int resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
