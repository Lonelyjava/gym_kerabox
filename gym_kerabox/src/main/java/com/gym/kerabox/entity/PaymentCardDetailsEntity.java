package com.gym.kerabox.entity;



import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CardDetails_payment")
public class PaymentCardDetailsEntity{

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String fullname;
 private String cardNumber;
 private String cardExpireDate;
 private String cvc;
 private String cardType;
 private String cardCompany;
 private String upiId;
 private LocalDate createdDate;
 private String username;
 
 @OneToOne(mappedBy = "cardInfo")
 @JsonIgnore
 private  Payment payment;

 public int getId() {
	return id;
 }

 public void setId(int id) {
	this.id = id;
 }

 public String getFullname() {
	return fullname;
 }

 public void setFullname(String fullname) {
	this.fullname = fullname;
 }

 public String getCardNumber() {
	return cardNumber;
 }

 public void setCardNumber(String cardNumber) {
	this.cardNumber = cardNumber;
 }

 public String getCardExpireDate() {
	return cardExpireDate;
 }

 public void setCardExpireDate(String cardExpireDate) {
	this.cardExpireDate = cardExpireDate;
 }

 public String getCvc() {
	return cvc;
 }

 public void setCvc(String cvc) {
	this.cvc = cvc;
 }

 public String getCardType() {
	return cardType;
 }

 public void setCardType(String cardType) {
	this.cardType = cardType;
 }

 public String getCardCompany() {
	return cardCompany;
 }

 public void setCardCompany(String cardCompany) {
	this.cardCompany = cardCompany;
 }

 public String getUpiId() {
	return upiId;
 }

 public void setUpiId(String upiId) {
	this.upiId = upiId;
 }

 public LocalDate getCreatedDate() {
	return createdDate;
 }

 public void setCreatedDate(LocalDate createdDate) {
	this.createdDate = createdDate;
 }

 public String getUsername() {
	return username;
 }

 public void setUsername(String username) {
	this.username = username;
 }

 public Payment getPayment() {
	return payment;
 }

 public void setPayment(Payment payment) {
	this.payment = payment;
 }

 
 
}