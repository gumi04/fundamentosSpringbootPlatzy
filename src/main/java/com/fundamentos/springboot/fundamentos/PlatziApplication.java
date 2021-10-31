package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWhitDependecy;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PlatziApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(PlatziApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWhitDependecy myBeanWhitDependecy;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    public PlatziApplication(@Qualifier("componentToImplement") ComponentDependency componentDependency, MyBean myBean,MyBeanWhitDependecy myBeanWhitDependecy, MyBeanWithProperties myBeanWithProperties,UserPojo userPojo, UserRepository userRepository, UserService userService){

        this.componentDependency= componentDependency;
        this.myBean= myBean;
        this.myBeanWhitDependecy= myBeanWhitDependecy;
        this.myBeanWithProperties=myBeanWithProperties;
        this.userPojo=userPojo;
        this.userRepository=userRepository;
        this.userService= userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(PlatziApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUsersInDb();
        getInformationJpqlFromUser();
        safeWithTransaccional();
    }

    private void safeWithTransaccional(){
        User test1= new User("test1","test1@gmail.com", LocalDate.now());
        User test2= new User("test2","test2@gmail.com", LocalDate.now());
        User test3= new User("test3","test1@gmail.com", LocalDate.now());
        User test4= new User("test4","test4@gmail.com", LocalDate.now());

        List<User> users= Arrays.asList(test1,test2,test3,test4);
        try {
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Error en la transaccion "+e);
        }


        userService.getAllUsers().stream().forEach(user -> LOGGER.info("usuario metodo transactional"+user));
    }

    private  void getInformationJpqlFromUser(){
        /*LOGGER.info("Ususario con el metodo por findByUserEmail "
                +userRepository.findByUserEmail("guma@gmail.com")
                .orElseThrow(()->new RuntimeException("No se encontro el usuario")));

        userRepository.findAndSort("pat", Sort.by("id").descending())
                .stream().forEach(user -> LOGGER.info("User with method fin and sort"+user));

        userRepository.findByName("ruben").stream().forEach(u->LOGGER.info("usuario con query method "+u));

        LOGGER.info("Usuario con metodo findByNameAndEmail "+userRepository.findByNameAndEmail("ruben","ruben@gmail.com")
                .orElseThrow(()-> new RuntimeException("usuario no encontrado")));

        userRepository.findByNameLike("%mar%").
                stream().
                forEach(u -> LOGGER.info("Usuario findByNameLike"+u));

        userRepository.findByNameOrEmail(null,"guma@gmail.com").
                stream().
                forEach(u -> LOGGER.info("Usuario findByNameOrEmail"+u));
       */
        userRepository.findByBirthDateBetween(LocalDate.of(2021,3,1),
                        LocalDate.of(2021,5,15))
                .stream().forEach(u->LOGGER.info("Usuario con intervalo de fechas"+u));

        userRepository.findByNameLikeOrderByIdDesc("%guma%")
                .stream()
                .forEach(u->LOGGER.info("metodo findByNameLikeOrderByIdDesc"+ u));

        LOGGER.info("El usuario a partir de name parameter es: "+ userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021,3,10),"guma@gmail.com")
                .orElseThrow(()->
                        new RuntimeException("No se encontro el usuario a partir del name paramater")));
    }



    private void saveUsersInDb(){
        User user1= new User("gumaro","guma@gmail.com", LocalDate.of(2021,3,10));
        User user2= new User("paty","paty@gmail.com", LocalDate.of(2021,1,11));
        User user3= new User("ruben","ruben@gmail.com", LocalDate.of(2021,7,12));
        User user4= new User("coco","coco@gmail.com", LocalDate.of(2021,6,13));
        User user5= new User("mary","mary@gmail.com", LocalDate.of(2021,5,14));
        User user6= new User("alice","alice@gmail.com", LocalDate.of(2021,12,15));
        User user7= new User("marlen","marlen@gmail.com", LocalDate.of(2021,10,16));
        User user8= new User("diana","diana@gmail.com", LocalDate.of(2021,9,17));
        User user9= new User("julia","julia@gmail.com", LocalDate.of(2021,06,19));
        User user10= new User("fernando","fernando@gmail.com", LocalDate.of(2021,12,18));
        List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10);
        list.stream().forEach(userRepository::save);
    }

    private  void ejemplosAnteriores(){
        componentDependency.saludar();
        myBean.print();
        myBeanWhitDependecy.printWhitDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail()+" "+userPojo.getPassword());
        LOGGER.error("Esto es un error");
    }
}
