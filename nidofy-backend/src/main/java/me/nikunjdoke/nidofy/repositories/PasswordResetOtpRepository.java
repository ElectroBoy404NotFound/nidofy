package me.nikunjdoke.nidofy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.nikunjdoke.nidofy.models.PasswordResetOtp;

@Repository
public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtp, Long> {
	Optional<PasswordResetOtp> findByEmailAndOtp(String email, String otp);
}
