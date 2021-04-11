package org.polomka.app.entity;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class ClientEntity {
    private int id;
    private String lastN;
    private String firstN;
    private String patron;
    private Date birthday;
    private Date regDate;
    private String email;
    private String phone;
    private String gender;
    private String photoPath;
    private ImageIcon photo;

    public ClientEntity(int id, String lastN, String firstN, String patron, Date birthday, Date regDate, String email, String phone, String gender, String photoPath) {
        this.id = id;
        this.lastN = lastN;
        this.firstN = firstN;
        this.patron = patron;
        this.birthday = birthday;
        this.regDate = regDate;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.photoPath = photoPath;

        this.initImage();
    }

    public ClientEntity(String lastN, String firstN, String patron, Date birthday, Date regDate, String email, String phone, String gender, String photoPath) {
        this(-1, lastN, firstN, patron, birthday, regDate, email, phone, gender, photoPath);
    }

    private void initImage()
    {
        URL url = ClientEntity.class.getClassLoader().getResource(this.photoPath);
        if(url != null) {
            try {
                this.photo = new ImageIcon(ImageIO.read(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.photo = null;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastN() {
        return lastN;
    }

    public void setLastN(String lastN) {
        this.lastN = lastN;
    }

    public String getFirstN() {
        return firstN;
    }

    public void setFirstN(String firstN) {
        this.firstN = firstN;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity client = (ClientEntity) o;

        if (id != client.id) return false;
        if (lastN != null ? !lastN.equals(client.lastN) : client.lastN != null) return false;
        if (firstN != null ? !firstN.equals(client.firstN) : client.firstN != null) return false;
        if (patron != null ? !patron.equals(client.patron) : client.patron != null) return false;
        if (birthday != null ? !birthday.equals(client.birthday) : client.birthday != null) return false;
        if (regDate != null ? !regDate.equals(client.regDate) : client.regDate != null) return false;
        if (email != null ? !email.equals(client.email) : client.email != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        if (gender != null ? !gender.equals(client.gender) : client.gender != null) return false;
        return photoPath != null ? photoPath.equals(client.photoPath) : client.photoPath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lastN != null ? lastN.hashCode() : 0);
        result = 31 * result + (firstN != null ? firstN.hashCode() : 0);
        result = 31 * result + (patron != null ? patron.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastN='" + lastN + '\'' +
                ", firstN='" + firstN + '\'' +
                ", patron='" + patron + '\'' +
                ", birthday=" + birthday +
                ", regDate=" + regDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
