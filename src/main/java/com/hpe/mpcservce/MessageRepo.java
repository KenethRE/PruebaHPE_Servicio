package com.hpe.mpcservce;
import org.springframework.data.jpa.repository.JpaRepository;

interface MessageRepo extends JpaRepository<Message, Long> {

}
