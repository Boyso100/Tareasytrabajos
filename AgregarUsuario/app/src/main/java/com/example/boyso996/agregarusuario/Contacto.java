package com.example.boyso996.agregarusuario;

import java.io.Serializable;

/**
 * Created by BOYSO996 on 05/09/2017.
 */

public class Contacto implements Serializable {


    private static final long serialVersionUID=1L;

    private String _nombre;
    private String _email;
    private String _twiter;
    private String _tel;
    private String _fec;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_twiter() {
        return _twiter;
    }

    public void set_twiter(String _twiter) {
        this._twiter = _twiter;
    }

    public String get_tel() {
        return _tel;
    }

    public void set_tel(String _tel) {
        this._tel = _tel;
    }

    public String get_fec() {
        return _fec;
    }

    public void set_fec(String _fec) {
        this._fec = _fec;
    }
}
