package com.hpe.mpcservice;
import org.springframework.data.jpa.repository.JpaRepository;

interface MessageRepo extends JpaRepository<Message, Long> {
}
