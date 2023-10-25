package com.dh.xtremeRental.service;

import com.dh.xtremeRental.dto.AlquilerDto;
import com.dh.xtremeRental.entity.Alquiler;
import com.dh.xtremeRental.interfaces.ICrudService;
import com.dh.xtremeRental.repository.IAlquilerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AlquilerService implements ICrudService<AlquilerDto,Alquiler> {
    @Autowired
    private IAlquilerRepository alquilerRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    ObjectMapper mapper;


    @Override
    public AlquilerDto crear(AlquilerDto alquilerDto) {
        Alquiler a = mapper.convertValue(alquilerDto,Alquiler.class);
        Integer operacion=1;
        Boolean validado = compruebaReglaNegocioAlquiler(a,operacion);
        if(validado){
            Alquiler alquilerCreado= alquilerRepository.save(a);
            return mapper.convertValue(alquilerCreado, AlquilerDto.class);
        }else{
            throw new IllegalArgumentException("No se pudo crear el alquiler");
        }
    }

    @Override
    public AlquilerDto buscar(Integer id) {
        Optional<Alquiler> alquiler = alquilerRepository.findById(id);
        if(alquiler.isPresent()){
            return mapper.convertValue(alquiler, AlquilerDto.class);
        }
        return null;
    }

    @Override
    public AlquilerDto modificar(AlquilerDto alquilerDto) {
        Alquiler a = mapper.convertValue(alquilerDto,Alquiler.class);
        Integer operacion=2;
        Boolean validado = compruebaReglaNegocioAlquiler(a,operacion);
        if(validado){
            Optional<Alquiler> alquiler = alquilerRepository.findById(a.getId());
            if(alquiler.isPresent()){
                Alquiler aCreado= alquilerRepository.save(a);
                return mapper.convertValue(aCreado,AlquilerDto.class);
            }else{throw new IllegalArgumentException("No se pudo pudo modificar ya que no se encontro el alquiler");}
        }
        return null;
    }

    @Override
    public String eliminar(Integer id) {
        Optional<Alquiler> alquiler = alquilerRepository.findById(id);
        if (alquiler.isPresent()) {
            alquilerRepository.deleteById(id);
            return "Alquiler eliminado correctamente";
        } else { throw new IllegalArgumentException("No se pudo eliminar. Alquiler no encontrado");
        }
    }

    @Override
    public Set<AlquilerDto> listartodos() {
        List<Alquiler> alquileres = alquilerRepository.findAll();
        Set<AlquilerDto> alquileresDto = new HashSet<>();
        for (Alquiler a: alquileres) {

            alquileresDto.add(mapper.convertValue(a, AlquilerDto.class));
        }
        return alquileresDto;
    }


    private Boolean compruebaReglaNegocioAlquiler(Alquiler a, Integer operacion) {


        LocalDate fechaActual = LocalDate.now();

        if (a.getId() != null && operacion == 1) {
            throw new IllegalArgumentException("No se puede Ingresar un Id");
        } else if (a.getId() == null && operacion == 2) {
            throw new IllegalArgumentException("Para modificar un elemento es necesario el Id");
        }else if(a.getProducto()== null){
            throw new IllegalArgumentException("El campo producto no puede ser null ni estar vacio");
        }else if(a.getUsuario()== null){
            throw new IllegalArgumentException("El campo usuario no puede ser null ni estar vacio");
        }else if (a.getProducto().getId() < 1  ) {
            throw new IllegalArgumentException("El campo id producto no puede ser negativo");
        }else if (a.getUsuario().getId() < 1  ) {
            throw new IllegalArgumentException("El campo id usuario no puede ser negativo");
//        }else if(a.getHora()== null){
//            throw new IllegalArgumentException("El campo hora no puede ser null ni estar vacio");
//        }else if(a.getFecha()== null){
//            throw new IllegalArgumentException("El campo fecha no puede ser null ni estar vacio");
//        } else if (!pacienteService.existePaciente(a.getPaciente().getId()) ) {
//            throw new IllegalArgumentException("El paciente no existe");
//        }else if (!odontologoService.existeOdontologo(a.getOdontologo().getId()) ) {
//            throw new IllegalArgumentException("El odontologo no existe");
//        } else if (a.getFecha().isBefore(fechaActual)) {
//            throw new IllegalArgumentException("La fecha del alquiler no puede ser inferior a la fecha actual");
//        } else if (finDeSemana(a.getFecha())) {
//            throw new IllegalArgumentException("Disculpa no trabajamos sabados ni domingos");
//        }else if (!(a.getHora().isAfter(LocalTime.parse("08:00:00.00")) && t.getHora().isBefore(LocalTime.parse("17:00:00.00")))) {
//            throw new IllegalArgumentException("Los alquilers deben ser entre las 08:00hs y las 17:00hs ");
//        }else if (alquilerNoDisponible(a.getFecha(),a.getHora(),a.getOdontologo().getId())) {
//            throw new IllegalArgumentException("Este alquiler ya está reservado para este Odontologo, prueba con otro");
//        }else if ((masDeUnAlquiler(a.getFecha(),a.getPaciente().getId()) && operacion == 1)) {
//            throw new IllegalArgumentException("No puedes reservar mas de un alquiler por dia");
        }else {
            return true;
        }
    }

    Boolean alquilerNoDisponible(LocalDate fecha, LocalTime hora, Integer id){
        Optional<Alquiler> alquiler = alquilerRepository.alquilerHoraDia(fecha,hora,id);
        if(alquiler.isPresent()){
            return true;
        }
        return false;
    }

    Boolean finDeSemana(LocalDate fecha ) {
        LocalDate sabado= LocalDate.parse("2023-07-08");
        LocalDate domingo=LocalDate.parse("2023-07-09");
        DayOfWeek sabado1 = sabado.getDayOfWeek();
        DayOfWeek domingo1 = domingo.getDayOfWeek();
        if(fecha.getDayOfWeek()==sabado1 || fecha.getDayOfWeek()==domingo1){
            return true;
        }
        return false;
    }

    Boolean masDeUnAlquiler(LocalDate fecha, Integer id){
        Optional<Alquiler> alquiler = alquilerRepository.alquilerMismoDia(fecha,id);
        if(alquiler.isPresent()){
            return true;
        }
        return false;
    }

}