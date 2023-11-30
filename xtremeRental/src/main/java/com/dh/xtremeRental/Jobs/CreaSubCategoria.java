package com.dh.xtremeRental.Jobs;

import com.dh.xtremeRental.User.Role;
import com.dh.xtremeRental.User.User;
import com.dh.xtremeRental.entity.SubCategoria;
import com.dh.xtremeRental.repository.IProductoRepository;
import com.dh.xtremeRental.repository.ISubCategoriaRepository;
import com.dh.xtremeRental.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@Component
public class CreaSubCategoria implements CommandLineRunner {


    @Autowired
    private ISubCategoriaRepository iSubCategoriaRepository;

    public void run (String... args) throws Exception {

        if (iSubCategoriaRepository.countBy() == 0){

            SubCategoria subcat1 = new SubCategoria();
            subcat1.setId(1);
            subcat1.setNombre("accesorios");
            SubCategoria subcat2 = new SubCategoria();
            subcat2.setId(2);
            subcat2.setNombre("indumentaria");
            SubCategoria subcat3 = new SubCategoria();
            subcat3.setId(3);
            subcat3.setNombre("calzado");
            SubCategoria subcat4 = new SubCategoria();
            subcat4.setId(4);
            subcat4.setNombre("tablas");

            iSubCategoriaRepository.save(subcat1);
            iSubCategoriaRepository.save(subcat2);
            iSubCategoriaRepository.save(subcat3);
            iSubCategoriaRepository.save(subcat4);

    }}
}
