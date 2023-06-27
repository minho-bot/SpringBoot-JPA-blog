package com.minho.blog.test;

import com.minho.blog.model.RoleType;
import com.minho.blog.model.User;
import com.minho.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id = "+user.getId());
        System.out.println("username = "+user.getUsername());
        System.out.println("password = "+user.getPassword());
        System.out.println("email = "+user.getEmail());
        System.out.println("role = "+user.getRole());
        System.out.println("createDate = "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회가완";
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        }catch (IllegalArgumentException e){
            return "삭제에 실패 해당 id 는 없음";
        }


        return "삭제되었습니다." + id;
    }

    @Transactional
//    @PutMapping("dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUSer){
        System.out.println("id : "+ id);
        System.out.println("password : " + requestUSer.getPassword());
        System.out.println("email : " + requestUSer.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정 실패");
        });
        user.setPassword(requestUSer.getPassword());
        user.setEmail(requestUSer.getEmail());

//        userRepository.save(user);

        return user;

    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        return user;
    }
}
