console.log("Me estoy ejecutando paciente listar")



function listarPacientes(event) {
    event.preventDefault();
    actualizarTabla();

    const url= '/pacientes';
    const settings ={
        "method":"GET"
        }
    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
            console.log(data);
            let tabla=document.getElementById("pacientesTable");
                for (let i = 0; i < data.length; i++) {
                    let row = tabla.insertRow(-1);
                    let idCell = row.insertCell(0);
                    let nombreCell = row.insertCell(1);
                    let apellidoCell = row.insertCell(2);
                    let dniCell = row.insertCell(3);
                    let domicilioCell = row.insertCell(4);
                    let fechaAltaCell = row.insertCell(5);
                    let accionesCell = row.insertCell(6);
                    let eliminarCell=row.deleteCell;

                    idCell.innerHTML= data[i].id;
                    nombreCell.innerHTML = data[i].nombre;
                    apellidoCell.innerHTML = data[i].apellido;
                    dniCell.innerHTML = data[i].dni;
                    domicilioCell.innerHTML = data[i].domicilio;
                    fechaAltaCell.innerHTML = data[i].fechaAlta;


                    // Agregar botones para modificar y eliminar el paciente
                    accionesCell.innerHTML = "<button onclick='modificarPaciente(" + data[i].id + ")'>Modificar</button> <button onclick='eliminarPaciente(" + data[i].id + ")'>Eliminar</button>";
                }
            })}


function actualizarTabla() {
    let tabla=document.getElementById("pacientesTable");

    // Limpiar la tabla
    while (tabla.rows.length > 1) {
        tabla.deleteRow(1);
    }
}

function modificarPaciente(id) {

    
        const url= '/pacientes'+"/"+id;
        const settings ={
            "method":"GET"
            }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            
    document.getElementById('divModificacion de Pacientes').style.display = 'block';

    document.getElementById("id-control").value=(data.id);
    document.getElementById("nombre-control").value=(data.nombre);
    document.getElementById("apellido-control").value=(data.apellido);
    document.getElementById("dni-control").value=(data.dni);
    document.getElementById("domicilio-control").value=(data.domicilio);
    document.getElementById("fechaAlta-control").value=(data.fechaAlta);
    })
    .catch(function (e){alert(e); 
        console.log(e);     
    })
    }


function agregarPacienteModificado(event) {
    event.preventDefault();

    // Obtener los valores del formulario
    let id = document.getElementById("id-control").value;
    let nombre = document.getElementById("nombre-control").value;
    let apellido = document.getElementById("apellido-control").value;
    let dni = document.getElementById("dni-control").value;
    let domicilio = document.getElementById("domicilio-control").value;
    let fechaAlta = document.getElementById("fechaAlta-control").value;

    // Crear objeto paciente
    const paciente = {
        id:id,
        nombre: nombre,
        apellido: apellido,
        dni: dni,
        domicilio: domicilio,
        fechaAlta: fechaAlta
    };
    
    let error="";

    const url= '/pacientes';
    const settings ={
        "method":"PUT",
        "headers":{
            'Content-Type': 'application/json',
        },
        "body":JSON.stringify(paciente)
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        window.alert("Paciente modificado correctamente")
        resetUploadFormModificado();
        document.getElementById('divModificacion de Pacientes').style.display = 'none';
         // Actualizar la tabla
        actualizarTabla();
        listarPacientes();

    })    
    .catch(function (e){
        console.log(e);     
    })
    
    
} 

function resetUploadFormModificado(){
    document.getElementById("id-control").value="";
    document.getElementById("nombre-control").value="";
    document.getElementById("apellido-control").value="";
    document.getElementById("dni-control").value="";
    document.getElementById("domicilio-control").value="";
    document.getElementById("fechaAlta-control").value=null;
}


// Función para eliminar un paciente
function eliminarPaciente(id) {

    const url= '/pacientes'+"/"+id;
    const settings ={
        "method":"DELETE",
        }
        fetch(url,settings)
        .then(response => response.json())
    
    actualizarTabla();
    listarPacientes();
}

////////////////////////////////LISTENER///////////////////////////////


document.getElementById("listarPacientes").addEventListener('click',listarPacientes);
document.getElementById("altaForm-control").addEventListener('submit',agregarPacienteModificado);