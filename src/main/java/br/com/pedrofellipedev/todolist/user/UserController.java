package br.com.pedrofellipedev.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private IUserRepository IUserRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var foundUser = this.IUserRepository.findByUsername(userModel.getUsername())

    if (foundUser != null) {
      return ResponseEntity.status(400).body("User already exists");
    }

    var userCreated = this.IUserRepository.save(userModel);

      return ResponseEntity.status(200).body(userCreated);
  }
}
