package springBootProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springBootProject.model.User;
import springBootProject.repositories.UserRepository;


import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;}

//==============================================================
public List<User> findAll(){return userRepository.findAll();}
//==============================================================
public User findOne(int id){
     Optional<User> foundUser = userRepository.findById(id);
     return foundUser.orElse(null);}
//==============================================================
    @Transactional
    public void save(User user){userRepository.save(user);}
//==============================================================
    @Transactional
    public void update(int id, User updateUser){
        updateUser.setId(id);
        userRepository.save(updateUser);// ДЛЯ ОБНОВЛЕНИЯ СУЩНОСТИ И ДЛЯ ДОБАВЛЕНИЯ ОДИН И ТОТ ЖЕ МЕТОД SAVE
    }
//==============================================================
    @Transactional
    public void delete(int id){
        userRepository.deleteById(id);
    }
//==============================================================
}