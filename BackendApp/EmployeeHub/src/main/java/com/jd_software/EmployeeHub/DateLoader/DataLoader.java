package com.jd_software.EmployeeHub.DateLoader;

import com.jd_software.EmployeeHub.Entity.Employee;
import com.jd_software.EmployeeHub.Entity.User;
import com.jd_software.EmployeeHub.Repository.EmployeeRepository;
import com.jd_software.EmployeeHub.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) {
        loadUsers();
        loadEmployees();
    }

    private void loadUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            User user = new User();
            user.setName("Normal User");
            user.setEmail("user@gmail.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("USER");

            userRepository.saveAll(Arrays.asList(admin, user));
            System.out.println("✅ Default users loaded");
        }
    }

    private void loadEmployees() {
        if (employeeRepository.count() == 0) {
            Employee e1 = new Employee("Aarav Sharma", "HR", "Active", LocalDate.of(2023, 5, 10));
            Employee e2 = new Employee("Neha Patel", "Engineering", "Active", LocalDate.of(2022, 11, 15));
            Employee e3 = new Employee("Ravi Singh", "Sales", "Inactive", LocalDate.of(2021, 8, 1));
            Employee e4 = new Employee("Priya Gupta", "Finance", "Active", LocalDate.of(2020, 3, 20));
            Employee e5 = new Employee("Suresh Kumar", "Engineering", "Active", LocalDate.of(2019, 12, 1));
            Employee e6 = new Employee("Anjali Mehta", "HR", "Active", LocalDate.of(2023, 6, 5));
            Employee e7 = new Employee("Manish Joshi", "Engineering", "Active", LocalDate.of(2021, 9, 25));
            Employee e8 = new Employee("Kiran Reddy", "Sales", "Active", LocalDate.of(2022, 1, 10));
            Employee e9 = new Employee("Sunita Verma", "Finance", "Inactive", LocalDate.of(2018, 7, 30));
            Employee e10 = new Employee("Rajesh Yadav", "Engineering", "Active", LocalDate.of(2020, 11, 18));
            Employee e11 = new Employee("Pooja Nair", "HR", "Active", LocalDate.of(2023, 2, 14));
            Employee e12 = new Employee("Vikram Singh", "Engineering", "Inactive", LocalDate.of(2019, 8, 21));
            Employee e13 = new Employee("Deepa Iyer", "Sales", "Active", LocalDate.of(2021, 5, 16));
            Employee e14 = new Employee("Mohit Sinha", "Finance", "Active", LocalDate.of(2020, 4, 9));
            Employee e15 = new Employee("Lakshmi Das", "Engineering", "Active", LocalDate.of(2019, 10, 12));
            Employee e16 = new Employee("Amitabh Chatterjee", "HR", "Active", LocalDate.of(2023, 3, 28));
            Employee e17 = new Employee("Meena Krishnan", "Engineering", "Active", LocalDate.of(2022, 6, 22));
            Employee e18 = new Employee("Ankur Malhotra", "Sales", "Inactive", LocalDate.of(2020, 12, 7));
            Employee e19 = new Employee("Divya Sharma", "Finance", "Active", LocalDate.of(2021, 7, 19));
            Employee e20 = new Employee("Sanjay Gupta", "Engineering", "Active", LocalDate.of(2018, 9, 3));
            Employee e21 = new Employee("Neeraj Kumar", "HR", "Active", LocalDate.of(2023, 1, 11));
            Employee e22 = new Employee("Radhika Pillai", "Engineering", "Inactive", LocalDate.of(2022, 4, 27));
            Employee e23 = new Employee("Tapan Dasgupta", "Sales", "Active", LocalDate.of(2020, 8, 14));
            Employee e24 = new Employee("Komal Jha", "Finance", "Active", LocalDate.of(2019, 3, 6));
            Employee e25 = new Employee("Siddharth Rao", "Engineering", "Active", LocalDate.of(2021, 11, 23));

            employeeRepository.saveAll(Arrays.asList(
                    e1, e2, e3, e4, e5, e6, e7, e8, e9, e10,
                    e11, e12, e13, e14, e15, e16, e17, e18, e19, e20,
                    e21, e22, e23, e24, e25));

            System.out.println("✅ Default employees loaded");
        }
    }
}
