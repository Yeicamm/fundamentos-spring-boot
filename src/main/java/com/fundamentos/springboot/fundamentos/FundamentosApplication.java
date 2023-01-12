package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWhiteDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyPrint;
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
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWhiteDependency myBeanWhiteDependency;

	private MyBeanWithProperties myBeanWithProperties;
	private MyPrint myPrint;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWhiteDependency myBeanWhiteDependency, MyPrint myPrint, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWhiteDependency = myBeanWhiteDependency;
		this.myPrint = myPrint;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}
	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//ejemplosAnteriores();
		saveUserInDataBase();
		getInformationjpqlFromUser();
		saveWithErrorTransactional();
	}
	private void saveWithErrorTransactional(){
		User test1 = new User("test1Transactional1", "test1Transactional1@domain.com", LocalDate.now());
		User test2 = new User("test2Transactional1", "test2Transactional1@domain.com", LocalDate.now());
		User test3 = new User("test3Transactional1", "test3Transactional1@domain.com", LocalDate.now());
		User test4 = new User("test4Transactional1", "test4Transactional1@domain.com", LocalDate.now());

		List<User> users = Arrays.asList(test1, test2, test3, test4);
		try {
			userService.saveTransactional(users);

		} catch (Exception e){
			LOGGER.error("Esta es una exception dentro del metodo transaccional " + e);
		}
		userService.getAllUsers().stream()
				.forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccional"+ user));

	}
	private void getInformationjpqlFromUser() {
		/*LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("julie@domain.com")
				.orElseThrow(()->new  RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending())
				.stream()
				.forEach(user -> LOGGER.info(("Usuario con metodo sort" + user)));

		userRepository.findByName("John")
				.stream()
				.forEach(user -> LOGGER.info("Usuario con query method" + user));

		LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("daniela@domain.com", "Daniela")
				.orElseThrow(()-> new RuntimeException("Usuario no encontrado")));

		userRepository.findByNameLike("%J%")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNaemLike " + user));

		userRepository.findByNameOrEmail(null, "user10@domain.com")
				.stream()
				.forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));*/

		userRepository
				.findByBirthDateBetween(LocalDate.of(2023,3, 1), LocalDate.of(2023, 4 ,3))
				.stream()
				.forEach(user -> LOGGER.info("Usuario con intervalo de fechas "+ user));

		userRepository.findByNameContainingOrderByIdDesc("user")
				.stream()
				.forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado"+ user));

		LOGGER.info("El usuario a a partir del named parameter es: " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2023, 03, 13), "daniela@domain.com")
				.orElseThrow(()->new RuntimeException("No se encontro el usuario a partir del named parameter")));

	}


	private void saveUserInDataBase(){
		User user1 = new User("John","john@domain.com", LocalDate.of(2023, 01, 11));
		User user2 = new User("Julie","julie@domain.com", LocalDate.of(2023, 02, 12));
		User user3 = new User("Daniela","daniela@domain.com", LocalDate.of(2023, 03, 13));
		User user4 = new User("user4","user4@domain.com", LocalDate.of(2023, 04, 14));
		User user5 = new User("user5","user5@domain.com", LocalDate.of(2023, 05, 15));
		User user6 = new User("user6","user6@domain.com", LocalDate.of(2023, 06, 16));
		User user7 = new User("user7","user7@domain.com", LocalDate.of(2023, 07, 17));
		User user8 = new User("user8","user8@domain.com", LocalDate.of(2023, 8, 18));
		User user9 = new User("user9","user9@domain.com", LocalDate.of(2023, 9, 19));
		User user10 = new User("user10","user10@domain.com", LocalDate.of(2023, 10, 20));
		User user11 = new User("user11","user11@domain.com", LocalDate.of(2023, 11, 21));
		User user12 = new User("user12","user12@domain.com", LocalDate.of(2023, 12, 22));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}
	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWhiteDependency.printWithDependency();
		myPrint.printImplement();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail() + "-"+ userPojo.getPassword());

		try {
			//error
			int value = 10/2;
			LOGGER.debug("Mi valor:" + value);
		} catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero" + e.getMessage());
		}
	}
}
