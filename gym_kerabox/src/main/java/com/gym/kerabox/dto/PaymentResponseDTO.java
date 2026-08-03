package com.gym.kerabox.dto;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;

//@Data
//@ApiModel(value = "PaymentResponseDTO", description = "Response DTO for a payment")
public class PaymentResponseDTO {

//    @ApiModelProperty(notes = "ID of the payment")
    private int id;

//    @ApiModelProperty(notes = "Name of the payment")
    private String name;

//    @ApiModelProperty(notes = "Number of the payment")
    private String number;

//    @ApiModelProperty(notes = "Email of the payment")
    private String email;

//    @ApiModelProperty(notes = "Address of the payment")
    private String address;

//    @ApiModelProperty(notes = "Bill value of the payment")
    private int billValue;

//    @ApiModelProperty(notes = "Card number of the payment")
    private String cardNumber;

//    @ApiModelProperty(notes = "Card holder of the payment")
    private String cardHolder;

//    @ApiModelProperty(notes = "Date value of the payment")
    private String dateValue;

//    @ApiModelProperty(notes = "CVC of the payment")
    private String cvc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBillValue() {
		return billValue;
	}

	public void setBillValue(int billValue) {
		this.billValue = billValue;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getDateValue() {
		return dateValue;
	}

	public void setDateValue(String dateValue) {
		this.dateValue = dateValue;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}
    
    
}