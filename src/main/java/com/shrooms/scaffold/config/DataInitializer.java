package com.shrooms.scaffold.config;

import com.shrooms.scaffold.model.scaffold.MaterialType;
import com.shrooms.scaffold.model.scaffold.Scaffold;
import com.shrooms.scaffold.model.scaffold.ScaffoldCategory;
import com.shrooms.scaffold.model.user.RoleType;
import com.shrooms.scaffold.model.user.User;
import com.shrooms.scaffold.repository.scaffold.ScaffoldRepository;
import com.shrooms.scaffold.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ScaffoldRepository scaffoldRepository;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ScaffoldRepository scaffoldRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.scaffoldRepository = scaffoldRepository;
    }

    @Override
    public void run(String... args)  {
        seedAdmin();
        seedScaffolds();
    }

    private void seedAdmin() {
        if (userRepository.count() == 0) {
            User admin = User.builder()
                    .username("admin")
                    .firstName("Admin")
                    .lastName("Shrooms")
                    .email("admin.shrooms@gmail.com")
                    .password(passwordEncoder.encode("shrooms123"))
                    .roleType(RoleType.ADMIN)
                    .profilePicture("/images/default-profile.png")
                    .build();

            userRepository.save(admin);
        }
    }

    private void seedScaffolds() {
        if (scaffoldRepository.count() == 0) {
            Scaffold facade = Scaffold.builder()
                    .name("Facade Steel Scaffold")
                    .description("""
                Facade scaffolding is used for both small and large renovation and construction projects. 
                It is a flexible scaffolding solution that can be adapted around different types of buildings 
                and construction sites. Suitable for multiple craftsmen working simultaneously, while providing 
                safe and stable access during renovation work.
                """)
                    .height(2.00)
                    .length(2.50)
                    .width(1.00)
                    .materialType(MaterialType.STEEL)
                    .scaffoldCategory(ScaffoldCategory.FACADE)
                    .priceForRent(new BigDecimal("120.00"))
                    .priceForSale(new BigDecimal("2800.00"))
                    .imageUrl("https://storage.googleapis.com/desteigerconcurrent_public/thumbnail/60/3b/d4/1762251522/asc-gevelsteiger-40m2_1920x1920.png")
                    .available(true)
                    .build();

            Scaffold mobile = Scaffold.builder()
                    .name("Mobile Aluminium Scaffold")
                    .description("""
A mobile scaffold (or rolling scaffold) is a temporary, freestanding work platform mounted on lockable caster wheels. 
It allows workers to easily move elevated platforms around a job site without dismantling the structure, making it ideal 
for tasks that require frequent position changes, such as painting, plastering, and maintenance.
""")
                    .height(4.2)
                    .width(1.8)
                    .length(3.0)
                    .materialType(MaterialType.ALUMINIUM)
                    .scaffoldCategory(ScaffoldCategory.MOBILE)
                    .priceForRent(new BigDecimal("80.00"))
                    .priceForSale(new BigDecimal("1300.00"))
                    .imageUrl("https://storage.googleapis.com/desteigerconcurrent_public/thumbnail/a8/92/1f/1701169637/light_9019004_s_1920x1920.png")
                    .available(true)
                    .build();

            scaffoldRepository.save(facade);
            scaffoldRepository.save(mobile);
        }
        }
    }


