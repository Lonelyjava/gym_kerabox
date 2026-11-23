package com.gym.kerabox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.kerabox.dto.PaymentResquest;
import com.gym.kerabox.dto.PaymentStatusRequest;
import com.gym.kerabox.entity.Payment;
import com.gym.kerabox.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/createPayment")
	@Operation(summary = "create payment")
	public ResponseEntity<Payment> createPayment(@RequestBody PaymentResquest request,@AuthenticationPrincipal UserDetails userDetails) {
		String username = userDetails.getUsername();
		return new ResponseEntity<>(paymentService.createPayment(request,username), HttpStatus.CREATED);
	}

	@GetMapping("getPayment/{id}")
	@Operation(summary = "get payment")
	public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.getPayment(id));
	}

	@GetMapping("getAllPayment")
	@Operation(summary = "Get all payment")
	public ResponseEntity<List<Payment>> getAllPayments() {
		return ResponseEntity.ok(paymentService.getAllPayments());
	}

	@PutMapping("updatePaymentStatus/{id}")
	@Operation(summary = "update payment status")
	public ResponseEntity<Payment> updateStatus(@PathVariable Long id, @RequestBody PaymentStatusRequest request) {

		return ResponseEntity.ok(paymentService.updatePaymentStatus(id, request.getStatus()));
	}

	@GetMapping("getRefundPayment/{id}")
	@Operation(summary = "Get refund payment")
	public ResponseEntity<Payment> refundPayment(@PathVariable Long id) {
		return ResponseEntity.ok(paymentService.refundPayment(id));
	}
}
