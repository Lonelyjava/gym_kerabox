package com.gym.kerabox.service;

import java.util.List;

import com.gym.kerabox.dto.PaymentResquest;
import com.gym.kerabox.entity.Payment;

public interface PaymentService {

	public Payment createPayment(PaymentResquest request,String username);

	public Payment getPayment(Long id);

	public List<Payment> getAllPayments();

	public Payment updatePaymentStatus(Long id, String status);

	public Payment refundPayment(Long id);

}
