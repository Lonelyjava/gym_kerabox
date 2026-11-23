package com.gym.kerabox.serviceImpl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.gym.kerabox.dto.PaymentResquest;
import com.gym.kerabox.entity.Payment;
import com.gym.kerabox.entity.PaymentCardDetailsEntity;
import com.gym.kerabox.enums.PaymentStatus;
import com.gym.kerabox.reposistory.PaymentRepository;
import com.gym.kerabox.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	@Transactional
	public Payment createPayment(PaymentResquest request,String username) {
		if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than zero");
		}

		if (request.getOrderId() == null || request.getCustomerId() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order ID and Customer ID are required");
		}

		Payment payment = new Payment();
		PaymentCardDetailsEntity cardDetailsEntity = new PaymentCardDetailsEntity();
		payment.setOrderId(request.getOrderId());
		payment.setCustomerId(request.getCustomerId());
		payment.setAmount(request.getAmount());
		payment.setCurrency(request.getCurrency() == null ? "INR" : request.getCurrency());
		payment.setPaymentMethod(request.getPaymentMethod());
		payment.setStatus(PaymentStatus.PENDING);
		payment.setTransactionReference("TXN-" + UUID.randomUUID().toString());

		
		cardDetailsEntity.setCardCompany(request.getCardCompany());
		cardDetailsEntity.setCardExpireDate(request.getCardExpireDate());
		cardDetailsEntity.setCardNumber(request.getCardNumber());
		cardDetailsEntity.setCardType(request.getCardType());
		cardDetailsEntity.setCreatedDate(LocalDate.now());
		cardDetailsEntity.setCvc(request.getCvc());
		cardDetailsEntity.setFullname(request.getFullname());
		cardDetailsEntity.setUpiId(request.getUpiId());
		cardDetailsEntity.setUsername(username);
		payment.setCardInfo(cardDetailsEntity);
		return paymentRepository.save(payment);
		
		
	}

	public Payment getPayment(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Transactional
	public Payment updatePaymentStatus(Long id, String status) {

		Payment payment = getPayment(id);

		PaymentStatus newStatus;

		try {
			newStatus = PaymentStatus.valueOf(status.toUpperCase());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payment status");
		}

		if (payment.getStatus() != PaymentStatus.PENDING) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only pending payments can be updated");
		}

		if (newStatus != PaymentStatus.SUCCESS && newStatus != PaymentStatus.FAILED) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Allowed statuses are SUCCESS or FAILED");
		}

		payment.setStatus(newStatus);

		return paymentRepository.save(payment);
	}

	@Transactional
	public Payment refundPayment(Long id) {

		Payment payment = getPayment(id);

		if (payment.getStatus() != PaymentStatus.SUCCESS) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Only successful payments can be refunded");
		}

		payment.setStatus(PaymentStatus.REFUNDED);

		return paymentRepository.save(payment);
	}

}
