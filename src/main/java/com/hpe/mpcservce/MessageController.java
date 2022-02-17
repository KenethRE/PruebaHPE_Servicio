package com.hpe.mpcservce;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MessageController {

  private final MessageRepoCALL repositoryCALL;
  private final MessageRepoMSG repositoryMSG;

  MessageController(MessageRepoCALL repository1, MessageRepoMSG repository2) {
    this.repositoryCALL = repository1;
    this.repositoryMSG = repository2;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/messagesCALL")
  List<MessageCALL> all() {
    return repositoryCALL.findAll();
  }
  // end::get-aggregate-root[]

}
