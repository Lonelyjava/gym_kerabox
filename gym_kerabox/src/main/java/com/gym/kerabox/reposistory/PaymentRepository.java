package com.gym.kerabox.reposistory;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.kerabox.entity.Payment;

@Repository
public interface PaymentRepository
        extends JpaRepository<Payment, Long> {
}
