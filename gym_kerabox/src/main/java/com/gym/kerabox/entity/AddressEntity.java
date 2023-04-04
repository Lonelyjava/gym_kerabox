package com.gym.kerabox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gym_kerabox_address")
public class AddressEntity extends BaseEntity{


    private String address1;
    private String address2;
    private String distric;
    private String state;
    private int pincode;

    @OneToOne(mappedBy = "address")
    private  UserEntity userEntity;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", distric='" + distric + '\'' +
                ", state='" + state + '\'' +
                ", pincode=" + pincode +
                ", userEntity=" + userEntity +
                '}';
    }
}
